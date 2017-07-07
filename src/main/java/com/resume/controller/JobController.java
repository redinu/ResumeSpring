package com.resume.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.model.Job;
import com.resume.model.Skills;
import com.resume.model.User;
import com.resume.repositories.JobRepository;
import com.resume.repositories.SkillsRepository;
import com.resume.repositories.UserRepository;

@Controller
public class JobController {
	
	@Autowired
	JobRepository jobRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SkillsRepository skillsRepository; 
	
	@RequestMapping(path = "/jobpost", method=RequestMethod.GET)
	private String getJobForm(Model model){
		model.addAttribute("job", new Job());
		return "jobForm";
	}
	
	@RequestMapping(path="/jobpost", method=RequestMethod.POST)
	private String postJob(@Valid Job job, BindingResult bindingResult, 
							 Principal principal, Model model){
		
		User user = userRepository.findByUsername(principal.getName());
		
		if(bindingResult.hasErrors()){
			return "jobForm";
		}
			job.setPostedDate(new Date());
			job.setPostedBy(user.getFirstName());
			jobRepository.save(job);
			
			List<Job> j = jobRepository.findByPostedBy(user.getFirstName());
			int i = j.size();
			Job jobatIndex = j.get(i-1);
			model.addAttribute("jobId",jobatIndex.getJobId());
			model.addAttribute("skills", new Skills());
			return "jobskillForm";
		
	}
	@RequestMapping(path="/job/skill/{jobId}")
	private String postJob(@PathVariable("jobId")Long jobId, ArrayList<Skills> skList, Model model){
		
		model.addAttribute("jobId", jobId);
		model.addAttribute("skillList",skList);
		model.addAttribute("skills", new Skills());
		return "jobskillForm";
	}
	
	@RequestMapping(path="/job/skill", method=RequestMethod.POST)
	private String addSkillToJob(@Valid Skills skill, BindingResult bindingResult,Long jobId, Principal principal, Model model  ){
		
		
		ArrayList<Skills> skills =  new ArrayList<Skills>();
		User user = userRepository.findByUsername(principal.getName());
		
		if(bindingResult.hasErrors()){
			return "jobskillForm";
		}
			skill.setJob(jobRepository.findOne(jobId));
			skillsRepository.save(skill);
			skills.add(skill);
			model.addAttribute("jobId", jobId);
			model.addAttribute("skList", skills);
			return "jobskilldetail";
		}
	
	
	@RequestMapping(path="/alljobs")	
	private String allJobs(Model model){
		
		Iterable<Job> jobs = jobRepository.findAll();
		
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
	
	@RequestMapping(path="/job/{jobId}")
	private String findJob(@PathVariable("jobId") Long jobId, Model model){
		
		Job job = jobRepository.findOne(jobId);
		List<Skills> skl = skillsRepository.findByJob_JobId(jobId);
		model.addAttribute("skills", skl);
		model.addAttribute("job", job);
		return "jobDetail";
	}
	
	@RequestMapping(path="/job/title")
	private String searchByTitle(@RequestParam("s")String title, Model model){
		List<Job> jobList = jobRepository.findByTitle(title);
		model.addAttribute("joblist", jobList);
		return "jobsearch";
	}
	
	@RequestMapping(path="/job/company")
	private String searchByCompany(@RequestParam("s")String employer, Model model){
		List<Job> jobList = jobRepository.findByEmployer(employer);
		model.addAttribute("joblist", jobList);
		return "jobsearch";
	}
}
