package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.model.Education;
import com.resume.model.Person;

@Controller
public class PersonController {
	
	@GetMapping("/")
	public String getForm(Model model){
		Person person = new Person();
		Education edu = new Education();
		
		model.addAttribute("person", person);
		return "person";
	}
	
	@RequestMapping(path="/user", method = RequestMethod.POST)
	public String saveName(@ModelAttribute Person person, Education edu ){

		return "education";
	}
	

}
