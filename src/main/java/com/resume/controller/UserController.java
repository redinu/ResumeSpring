package com.resume.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;/*
import org.springframework.security.core.annotation.AuthenticationPrincipal;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.model.Education;
import com.resume.model.Experience;
import com.resume.model.Person;
import com.resume.model.Skills;
import com.resume.model.User;
import com.resume.repositories.EducationRepository;
import com.resume.repositories.ExperienceRepository;
import com.resume.repositories.PersonRepository;
import com.resume.repositories.SkillsRepository;
import com.resume.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	EducationRepository educationRepository;
	
	@Autowired
	SkillsRepository skillRepository;
	 
	@RequestMapping( path = "/login")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping( path = "/register")
	public String register(Model model){
	   model.addAttribute("user",new User());
	    return "register";
	}
	
	@RequestMapping( path = "/register", method=RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult,Model model){
		if(bindingResult.hasErrors()){
			return "register";
		}
		if(!userRepository.existsByUsername(user.getUsername())){
			userRepository.save(user);
			return "redirect:/login";
		}else{
			model.addAttribute("errormessage", "Someone's already using that email. If that’s you, please sign in.");
			return "register";
		}
	}
	@RequestMapping( path = "/login", method=RequestMethod.POST)
	public String checklogin(@ModelAttribute User user, Model model){
		
		if(userRepository.existsByUsername(user.getUsername())){
			User u = userRepository.findUserByUsername(user.getUsername());
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())){
				return "redierct:/";
			}else if(!user.getUsername().equals(u.getUsername()) || !user.getPassword().equals(u.getPassword())){
				model.addAttribute("errormessage", "Incorrect username and password");
				
				return "login";
			}
			
		}else{
			model.addAttribute("errormessage", "UserName doesn't exist. Do you want to register?");
			return "register";
		}
		return null;
		
	}
	
	@RequestMapping(value = "/friends/search", params = "s")
	public String searchFriends(@RequestParam("s") String s, Principal principal, Model model) {
		Person p = new Person();
		List<Person> personList = personRepository.findPersonByFirstName(principal.getName());
		
		model.addAttribute("friends",personList);
		model.addAttribute("searchString", s);
		
		return "search";
	}
	
	@RequestMapping(value = "/search/company", params = "s")
	public String searchCompanies(@RequestParam("s") String s, Principal principal, Model model) {
		
	//	List<Experience> exByComp= experienceRepository.findExperienceByCompany(s);
		
		List<Experience> ex = (List<Experience>) experienceRepository.findAll();
		List<Experience> exByComp = experienceRepository.findExperienceByCompany(s);
		ArrayList<Person> personList = new ArrayList<Person>();
		for(Experience e : exByComp){
			long pId = e.getPersonId();
			Person person = personRepository.findOne(pId);
			personList.add(person);
		}
		model.addAttribute("allExp",exByComp);
		model.addAttribute("searchString", s);
		model.addAttribute("allPer", personList);
		
		return "companies";
		
	}
	
	@RequestMapping(value = "/search/education", params = "s")
	public String searchSchools(@RequestParam("s") String s, Principal principal, Model model) {
		
		
		List<Education> ed = (List<Education>) educationRepository.findAll();
		
		model.addAttribute("allEdu", ed);
		model.addAttribute("searchString", s);
		
		return "schools";
		
	}
	
	@RequestMapping(value = "/search/education", params = "s")
	public String searchSkill(@RequestParam("s") String s, Principal principal, Model model) {
		
		
		List<Skills> sk = (List<Skills>) skillRepository.findAll();
		
		model.addAttribute("allSkills", sk);
		model.addAttribute("searchString", s);
		
		return "skill";
		
	}
}
