package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resume.model.Education;
import com.resume.model.Person;

@Controller
public class EducationController {
	
	@GetMapping("/add")
	public String getForm(Model model){
		Person person = new Person();
		Education edu = new Education();
		
		model.addAttribute("person", person);
		model.addAttribute("education",edu);
		return "education";
	}
	
	@RequestMapping(path="/education", method = RequestMethod.POST)
	public String saveEdu(@ModelAttribute Person person, Education edu){
	
		return "experience";
	}
	
}
