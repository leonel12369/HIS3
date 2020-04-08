package com.his.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {
	
	@GetMapping({"/","home"})
	public String principal(Model model) {
		model.addAttribute("titulo","HIS-principal");
		return "views/Principal/index";
	}

	@GetMapping("/layout")
	public String layout(Model model) {
		model.addAttribute("titulo","HIS-principal");
		return "views/layout/layout";
	}
}
