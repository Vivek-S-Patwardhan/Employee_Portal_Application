package net.javaguides.springboot.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	Page<User> findAllByRolesIn(List<Role> roles, Pageable pageable);
	List<User> findAllByRolesIn(List<Role> roles);
	
}
