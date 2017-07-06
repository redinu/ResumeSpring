package com.resume.controller;

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

import com.resume.model.Skills;
import com.resume.repositories.SkillsRepository;

@Controller
public class SkillsController {
	@Autowired
	SkillsRepository skillsRepository; 
	
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
	
	
	
}
