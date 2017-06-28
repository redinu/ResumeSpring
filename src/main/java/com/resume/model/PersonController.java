package com.resume.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.model.Education;
import com.resume.model.Person;

@Controller
public class PersonController {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	EducationRepository educationRepository;
	@Autowired
	ExperienceRepository experienceRepository;
	@Autowired
	SkillsRepository skillsRepository; 
	
	@GetMapping("/")
	public String getForm(Model model){
		Person person = new Person();
//		Education edu = new Education();
		
		model.addAttribute("person", person);
//		model.addAttribute("education",edu);
		return "personForm";
	}
	@RequestMapping(path= "/user", method=RequestMethod.POST)
	public String savePerson(@Valid Person person, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()){
			return "personForm";
		}
		
		personRepository.save(person);
		Person p = personRepository.findPersonByEmail(person.getEmail());
		model.addAttribute("personId",p.getPersonId());
		model.addAttribute("education", new Education());
		return "educationForm";
	}
	
	@RequestMapping(path= "/resume/{personId}")
	public String displayResume( @PathVariable("personId") Long personId, Model model){
		System.out.println(personId);
		Person p = personRepository.findOne(personId);
		System.out.println(p);
		
		List<Education> edu = educationRepository.findEducationByPersonId(personId);
		List<Experience> exp = experienceRepository.findExperienceByPersonId(personId);
		List<Skills> sk = skillsRepository.findSkillsByPersonId(personId);
		p.setEduList( (ArrayList<Education>) edu);
		p.setExpList((ArrayList<Experience>) exp);
		p.setSkillList((ArrayList<Skills>) sk);
		
		personRepository.save(p);
		model.addAttribute("person", p);
		return "resume";
	}
	

}
