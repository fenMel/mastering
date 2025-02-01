package fr.esic.mastering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esic.mastering.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
  
	
}
