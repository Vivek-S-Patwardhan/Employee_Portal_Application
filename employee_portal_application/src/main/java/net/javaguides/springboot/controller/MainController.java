package net.javaguides.springboot.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserServiceImpl;
@Controller
public class MainController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	UserRepository userRepo;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/profile")

	public String profile(Model model) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findByEmail(userDetails.getUsername());
		// Collection<Role> roles = user.getRoles();
		List<String> roles =  user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
		String role = roles.get(0);
		model.addAttribute("role",role);
		model.addAttribute("user",user);
		return "profile";		
	}
	
//	-----------
	
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
