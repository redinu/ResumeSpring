package com.resume.controller;


import static org.assertj.core.api.Assertions.useRepresentation;

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
import com.resume.model.User;
import com.resume.repositories.JobRepository;
import com.resume.repositories.UserRepository;

@Controller
public class JobController {
	
	@Autowired
	JobRepository jobRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path = "/jobpost", method=RequestMethod.GET)
	private String getJobForm(Model model){
		model.addAttribute("job", new Job());
		return "jobForm";
	}
	
	@RequestMapping(path="/jobpost", method=RequestMethod.POST)
	private String postJob(@Valid Job job,BindingResult bindingResult, String addSkill,
							String newSkill, Principal principal, Model model){
		if(bindingResult.hasErrors()){
			return "jobForm";
		}
		if(addSkill!=null){
			ArrayList<String> skills = job.getSkills();
			
			if(newSkill!=null){
				skills.add(newSkill);
			}
			User user = userRepository.findByUsername(principal.getName());
			model.addAttribute("userId", user.getId());
			model.addAttribute("job", job);
			model.addAttribute("skillList", skills);
			
			return "jobForm";
			
		}else{
			
			job.setPostedDate(new Date());
			job.getSkills().add(newSkill);
			jobRepository.save(job);
			Iterable<Job> jobs = jobRepository.findAll();
			
			model.addAttribute("jobs", jobs);
			return "jobs";
		}
	}
	
	@RequestMapping(path="/alljobs")	
	private String allJobs(Model model){
		
		Iterable<Job> jobs = jobRepository.findAll();
		
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
	
	@RequestMapping(path="/job/{jobId}")
	private String findJob(@PathVariable("jobId") Long id, Model model){
		
		Job job = jobRepository.findOne(id);
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
