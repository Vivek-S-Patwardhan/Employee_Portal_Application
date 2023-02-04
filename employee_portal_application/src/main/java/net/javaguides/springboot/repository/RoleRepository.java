package net.javaguides.springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import net.javaguides.springboot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findByName(String name);
	
}


