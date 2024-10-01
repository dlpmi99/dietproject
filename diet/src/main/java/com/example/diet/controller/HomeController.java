package com.example.diet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.diet.entity.DietEntity;
import com.example.diet.entity.MenuEntity;
import com.example.diet.repository.DietRepository;


@Controller
public class HomeController {
	
	/* dependency injection */
	private final DietRepository dietRepository;
	
	public HomeController(DietRepository dietRepository) {
		this.dietRepository = dietRepository;
	}
	

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/user")
	public String userForm() {
		return "userForm";
	}
	
	@PostMapping("/user")
	public String userInput(DietEntity entity, Model model) {
		System.out.println(entity);
		List<MenuEntity> list1 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"조식");
		for (MenuEntity e : list1) {
			System.out.println(e);
		}

		List<MenuEntity> list2 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"중식");
		for (MenuEntity e : list2) {
			System.out.println(e);
		}

		List<MenuEntity> list3 = dietRepository.findByBloodAndCholesterol(entity.getBlood(), entity.getCholesterol(),
				"석식");
		for (MenuEntity e : list3) {
			System.out.println(e);
		}
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		
		 return "result";
	}
	
	
	@GetMapping("/health")
	public String health() {
		return "health";
	}
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}
	
	///
	

}
