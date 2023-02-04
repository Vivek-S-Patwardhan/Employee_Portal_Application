package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.dto.EmployeeUpdateDto;
import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.User;

public interface EmployeeService {
	List<User> getAllEmployees();
	void saveEmployee(UserRegistrationDto employee);
	User getEmployeeById(long id);
	
	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    boolean updateEmployee(long id,EmployeeUpdateDto employee);
}
