package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
@Controller
public class MainController {

	@Autowired
	UserRepository userRepo;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
//	@PostMapping("/login")
//	public String postLogin(String email, String password)
//	{
//		User user = userRepo.findByEmail(email);
//		
//		return "redirect:/";
//	}
	/*
	 * @GetMapping("/") public String home() { return "index"; }
	 */
}
