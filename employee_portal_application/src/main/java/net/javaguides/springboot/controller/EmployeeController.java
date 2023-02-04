package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.dto.EmployeeUpdateDto;
import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.EmployeeService;
import net.javaguides.springboot.service.UserService;

@Controller
public class EmployeeController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeService employeeService;
	
	// display list of employees
	@GetMapping("/")
	
	public String viewHomePage(Model model) {
		//get authentication principal from security context holder.
		//convert this principal to user details(this we will get from security package).
		//create a new file - user details impl to convert the prin to user details.
		//this will give user name of the user who has logged in and also gives roles for that user.
		//this username can be used to fetch the data from the user table and roles can be 
		//used to check the permission on the page.
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		User employee = new User();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") UserRegistrationDto employee) {
		// save employee to database
	employeeService.saveEmployee(employee);
		
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		User employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@PostMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable (value = "id") long id,@ModelAttribute("employee") EmployeeUpdateDto employee) {
		// save employee to database
		boolean updated = employeeService.updateEmployee(id,employee);
		if(!updated) {
			return "redirect:/error";
		}
		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
	
}
