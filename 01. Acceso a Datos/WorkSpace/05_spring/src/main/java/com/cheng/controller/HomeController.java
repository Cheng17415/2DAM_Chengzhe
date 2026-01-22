package com.cheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeController {
	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
}
