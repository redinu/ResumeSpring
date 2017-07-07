package com.resume.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;/*
import org.springframework.security.core.annotation.AuthenticationPrincipal;*/
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.model.Role;
import com.resume.model.Education;
import com.resume.model.Experience;
import com.resume.model.Person;
import com.resume.model.Skills;
import com.resume.model.User;
import com.resume.repositories.EducationRepository;
import com.resume.repositories.ExperienceRepository;
import com.resume.repositories.PersonRepository;
import com.resume.repositories.RoleRepository;
import com.resume.repositories.SkillsRepository;
import com.resume.repositories.UserRepository;
import com.resume.service.UserService;
import com.resume.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	EducationRepository educationRepository;
	
	@Autowired
	SkillsRepository skillRepository;
	
	@RequestMapping( path = "/")
	public String displayAll(Principal principal, Model model){
		
		List<Person> personList = (List<Person>) personRepository.findAll();
		List<Person> all = new ArrayList<Person>();
		for(Person p : personList){
			long id = p.getPersonId();
			List<Education>  educationList = educationRepository.findEducationByPersonId(id);
			List<Experience> expList = experienceRepository.findExperienceByPersonId(id);
			List<Skills> skillList = skillRepository.findSkillsByPersonId(id);
			p.setEduList( (ArrayList<Education>) educationList );
			p.setExpList((ArrayList<Experience>) expList);
			p.setSkillList((ArrayList<Skills>) skillList);
			all.add(p);
		}
		User user = userRepository.findByUsername(principal.getName());
		model.addAttribute("all", all);
		model.addAttribute("user", user);
		return "home";
	}
	
	@RequestMapping( path = "/login")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping( path = "/register/jobseeker")
	public String register(Model model){
	   model.addAttribute("user",new User());
	    return "jobseekerRegister";
	}
	@RequestMapping( path = "/register/recruiter")
	public String recruiterRegister(Model model){
	   model.addAttribute("user",new User());
	    return "recruiterRegister";
	}

	@RequestMapping(path = "/register", method=RequestMethod.POST)
	public String register(@Valid User user,BindingResult bindingResult, @RequestParam String role, Model model){
		
		Role rol = roleRepository.findByRole(role);
		Set<User> users = new HashSet<User>();
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			if(role.equals("JobSeeker")){
				return "jobseekerRegister";
			}else{
				return "recruiterRegister";
			}
		}
		
		if(!userRepository.existsByUsername(user.getUsername())){
		
			user.setEnabled(true);
			
			user.setRoles(Arrays.asList(rol));
			
			if(role.equals("Recruiter")){
			userService.saveRecruiter(user);
			}else{
				userService.saveJobSeeker(user);
			}
		
			return "redirect:/login";
		}else{
			model.addAttribute("errormessage", "Someone's already using that email. If that’s you, please sign in.");
			if(role.equalsIgnoreCase("jobseeker")){
				return "jobseekerRegister";
			}else{
				return "recruiterRegister";
			}
		}
	}

	
	@RequestMapping(value = "/search/users")
	public String searchFriends(@RequestParam String s, Principal principal, Model model) {
		
		List<Person> personList = personRepository.findByFirstName(s);
		List<Person> all = new ArrayList<Person>();
		for(Person p : personList){
			long id = p.getPersonId();
			List<Education>  educationList = educationRepository.findEducationByPersonId(id);
			List<Experience> expList = experienceRepository.findExperienceByPersonId(id);
			List<Skills> skillList = skillRepository.findSkillsByPersonId(id);
			p.setEduList( (ArrayList<Education>) educationList );
			p.setExpList((ArrayList<Experience>) expList);
			p.setSkillList((ArrayList<Skills>) skillList);
			all.add(p);
		}
		
		model.addAttribute("all",all);
		model.addAttribute("searchString", s);
		
		return "home";
	}
	
	@RequestMapping(value = "/search/company")
	public String searchCompanies(@RequestParam String s, Principal principal, Model model) {
		
		List<Experience> exByComp = experienceRepository.findExperienceByCompany(s);
		
		ArrayList<Person> personList = new ArrayList<Person>();
		for(Experience e : exByComp){
			long pId = e.getPersonId();
			Person person = personRepository.findOne(pId);
			personList.add(person);
		}
		model.addAttribute("searchString", s);
		model.addAttribute("allPer", personList);
		
		return "userSearch";
		
	}
	
	@RequestMapping(value = "/search/school", params = "s")
	public String searchSchools(@RequestParam("s") String s, Principal principal, Model model) {
		
		
		List<Education> ed = (List<Education>) educationRepository.findEducationByInstitute(s);
		
		ArrayList<Person> personList = new ArrayList<Person>();
		for(Education e : ed){
			long pId = e.getPersonId();
			Person person = personRepository.findOne(pId);
			personList.add(person);
		}
		model.addAttribute("searchString", s);
		model.addAttribute("allPer", personList);
		
		return "userSearch";
		
	}
	
	@RequestMapping(value = "/search/skill", params = "s")
	public String searchSkill(@RequestParam("s") String s, Principal principal, Model model) {
		
		
		List<Skills> sk = (List<Skills>) skillRepository.findBySkill(s);
		
		ArrayList<Person> personList = new ArrayList<Person>();
		for(Skills e : sk){
			long pId = e.getPersonId();
			Person person = personRepository.findOne(pId);
			personList.add(person);
		}
		
		model.addAttribute("searchString", s);
		model.addAttribute("allPer", personList);
		
		return "userSearch";
		
	}
	public UserValidator getUserValidator() {
	    return userValidator;
	}
	public void setUserValidator(UserValidator userValidator) {
	    this.userValidator = userValidator;
	}
	
}
