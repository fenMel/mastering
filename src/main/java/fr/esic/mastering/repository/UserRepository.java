package fr.esic.mastering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.esic.mastering.entities.RoleType;
import fr.esic.mastering.entities.User;



public interface UserRepository extends CrudRepository<User, Long>{

	
	
	public Optional<User> findByEmailAndPassword(String email, String password);
	 
	@Query("select u from User u where u.email = ?1    and u.password =?2 ")
	public Optional<User> getByLoginAndPassword(String email, String password);

   public Optional<User> findByEmail(String email);
   
   @Query("select u from User u where u.email = ?1 ")
   public Optional<User> getByEmail(String email);
   
  /* public Optional<User> findById(Long id);
   @Query("select u.id, u.nom, u.prenom, u.email, u.role, u.dateNaissance, u.lieuxDeNaissance, u.tel from User u where u.id = ?1")
   public Optional<Object[]> getUserWithoutPassword(Long id);*/
   
   // Recherche des utilisateurs par type de rôle
   List<User> findByRoleRoleUtilisateur(RoleType roleUtilisateur);   
}
