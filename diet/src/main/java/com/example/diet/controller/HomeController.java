package com.example.diet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/user")
	public String userForm() {
		return "userForm";
	}
	
	@PostMapping("/user")
	public String userInput() {
		return "userForm";
	}
	@GetMapping("health")
	public String health() {
		return "health";
	}
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}
	
	
	

}
