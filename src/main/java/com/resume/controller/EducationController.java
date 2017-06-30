package com.resume.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resume.helper.StringToDateFormatter;
import com.resume.model.Education;
import com.resume.repositories.EducationRepository;

@Controller
public class EducationController {
	
	@Autowired
	EducationRepository educationRepository;
	
	@RequestMapping(path="/education/save", method = RequestMethod.POST)
	public String saveEdu(@Valid Education edu, BindingResult bindingResult,Long personId, Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("personId", personId);
			return "educationForm";
		}

		ArrayList<Education> eduList = new ArrayList<Education>();
		Education e = new Education();
		StringToDateFormatter s = new StringToDateFormatter();
		e.setEndyear(s.dateFormatter(edu.getYear()));
		e.setInstitute(edu.getInstitute());
		e.setTypeOfDegree(edu.getTypeOfDegree());
		e.setYear(edu.getYear());
		e.setPersonId(personId);
		eduList.add(e);
		model.addAttribute("eduList", eduList);
		model.addAttribute("personId", personId);

		educationRepository.save(e);

		return "educationDetail";
	}
	@RequestMapping(path="/education/add/{personId}")
	public String showForm( @PathVariable Long personId, Model eduModel){
		
		eduModel.addAttribute("education", new Education());
		eduModel.addAttribute("personId", personId);
		return "educationForm";
	}
	
	
	
	
}
