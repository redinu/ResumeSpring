package com.resume.controller;

import java.security.Principal;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.resume.model.Job;
import com.resume.model.Person;
import com.resume.model.Skills;
import com.resume.model.User;
import com.resume.repositories.JobRepository;
import com.resume.repositories.PersonRepository;
import com.resume.repositories.SkillsRepository;
import com.resume.repositories.UserRepository;

@Controller
public class SkillsController {
	@Autowired
	SkillsRepository skillsRepository; 
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	@RequestMapping(path="/skill/add/{personId}")
	public String nextForm( @PathVariable Long personId, Model skModel){
		
		Skills sk = new Skills();
		skModel.addAttribute("skill", sk);
		skModel.addAttribute("personId", personId);
		return "skillsForm";
	}
	
	@RequestMapping(path="/skill/save", method=RequestMethod.POST)
	public String saveSkill(@Valid Skills sk, BindingResult bindingResult,Long personId,Model model){
		
		if(bindingResult.hasErrors()){
			model.addAttribute("personId", personId);
			return "skillsForm";
		}
		List<Skills> skList = new ArrayList<Skills>();
		skList.add(sk);
		model.addAttribute("personId", personId);
		model.addAttribute("skList", skList);
		skillsRepository.save(sk);
		return "skillDetail";
	
	}
	
	@RequestMapping(path="/skill/notification")
	public String notifyBySkill(Principal principal, Model model){
		
		ArrayList<String> test = new ArrayList<String>();
		ArrayList<Job> jobs =new ArrayList<Job>();
		User user = userRepository.findByUsername(principal.getName());
		Person person = personRepository.findPersonByEmail(user.getEmail());
		long id = person.getPersonId();
		List<Skills> skillList = skillsRepository.findSkillsByPersonId(id);
		
		
		for(Skills sk: skillList){
			System.out.println(sk);
			List<Job> jb = jobRepository.findAllBySkills(sk);
			System.out.println(jb);
			jobs.addAll(jb);
		}
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
	
	
	
}
