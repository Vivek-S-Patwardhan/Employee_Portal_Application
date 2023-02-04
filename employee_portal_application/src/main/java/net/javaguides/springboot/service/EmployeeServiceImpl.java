package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.EmployeeUpdateDto;
import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public List<User> getAllEmployees() {
		List<Role> roles = roleRepository.findByName("ROLE_USER");
//		Set<Role> roles = new HashSet<Role>();
	//	roles.add(new Role("ROLE_USER"));
		return userRepository.findAllByRolesIn(roles);
	}

	@Override
	public void saveEmployee(UserRegistrationDto employee) {
		List<Role> roles = roleRepository.findByName("ROLE_USER");
		User user = new User(employee.getFirstName(), 
				employee.getLastName(), employee.getEmail(),
				passwordEncoder.encode(employee.getPassword()),roles);
		
		 userRepository.save(user);
	}

	@Override
	public User getEmployeeById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	//	Set<Role> roles = new HashSet<Role>();
		// roles.add(new Role("ROLE_USER"));
		List<Role> roles = roleRepository.findByName("ROLE_USER");
		return this.userRepository.findAllByRolesIn(roles, pageable);
	}
	
	@Override
	public boolean updateEmployee(long id,EmployeeUpdateDto employee) {
		Optional<User> userRef = userRepository.findById(id); 
		if(userRef.isEmpty())
		{
			return false;
		}
		
		User user = userRef.get();
		user.setFirstName(employee.getFirstName());
		user.setLastName(employee.getLastName());
		user.setEmail(employee.getEmail());
		 userRepository.save(user);
		 
		 return true;
		 
	}
}
