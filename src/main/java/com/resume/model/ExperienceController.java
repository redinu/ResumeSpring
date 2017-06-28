package com.resume.model;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(path="/experience/save", method=RequestMethod.POST)
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
		
	}
	
	
	
}
