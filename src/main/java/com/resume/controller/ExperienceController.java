package com.resume.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resume.helper.StringToDateFormatter;
import com.resume.model.Experience;
import com.resume.repositories.ExperienceRepository;

@Controller
public class ExperienceController {
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@RequestMapping(path="/experience/add/{personId}")
	public String expForm(@PathVariable Long personId, Model expModel){
		
		Experience ex = new Experience();
		expModel.addAttribute("experience", ex);
		return "experienceForm";
	}
	
/*	@RequestMapping(path="/experience/save", method=RequestMethod.POST)
	public String saveExperience(@Valid Experience ex, BindingResult bindingResult,Long personId,  Model model){
		if(bindingResult.hasErrors()){
			return "experienceForm";
		}
		ArrayList<Experience> expList = new ArrayList<Experience>();
		expList.add(ex);
		model.addAttribute("personId", personId);
		model.addAttribute("expList", expList);
		experienceRepository.save(ex);
		return "experienceDetail";
		
	}*/
	
	@RequestMapping(path="/experience/save", method=RequestMethod.POST)
	public String addDuty(@Valid Experience ex, BindingResult bindingResult,Long personId,
					String addDuty, String newduty, Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("personId", personId);
			return "experienceForm";
		}
		
		if(addDuty!=null){
			ArrayList<String> duties = ex.getDuties();
			
			if(newduty!=null){
				duties.add(newduty);
			}
			model.addAttribute("personId", personId);
			model.addAttribute("experience", ex);
			model.addAttribute("dutylist", duties);
			
			return "experienceForm";
			
		} else{
			StringToDateFormatter s = new StringToDateFormatter();
			ex.setsDate(s.dateFormatter(ex.getStartDate()));
			ex.seteDate(s.dateFormatter(ex.getEndDate()));
			ArrayList<Experience> expList = new ArrayList<Experience>();
			ex.getDuties().add(newduty);
			expList.add(ex);
			model.addAttribute("personId", personId);
			model.addAttribute("expList", expList);
			experienceRepository.save(ex);
			
			return "experienceDetail";
		}
	}
	
	
}
