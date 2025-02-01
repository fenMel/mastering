package fr.esic.mastering.api;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esic.mastering.entities.User;
import fr.esic.mastering.repository.UserRepository;
@RestController
@CrossOrigin("*")
public class UserRest {
    @Autowired
	private UserRepository  userRepository;
  
    @PostMapping("login")
	public Optional<User> login(@RequestBody User user) {
		return  userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());	
	}
    
    @GetMapping("users")
    public Iterable<User> getAllUsers(){
	return userRepository.findAll();
    }
    @GetMapping("users/{userId}")
    public Optional<User> User(@PathVariable Long userId){
    	return userRepository.findById(userId);
    }
    @GetMapping("users/email/{userEmail}")
    public Optional<User> User(@PathVariable String userEmail){
    	return userRepository.findByEmail(userEmail);
    }
    @GetMapping("email")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return userRepository.getByEmail(email);
    }
    @PutMapping("users/modif/{id}")
    public  User updateUser(@PathVariable Long id ,@RequestBody User u){
    	u.setId(id);
		return userRepository.save(u);
      	
    }
    @DeleteMapping("users/delet/{id}")
    
    public boolean deletUser(@PathVariable Long id){
    	//avoir un ou 0 max user 
    			Optional<User> user = userRepository.findById(id);
    			if (user.isPresent()) {
    				userRepository.deleteById(id);
    				return true;
    			     }
    			else {
    				return false;
    			     }
    }
    @GetMapping("user/noPwd/{id}")
    public Optional<User> getAllUserWithoutPassword(@PathVariable Long id)
    {
		return userRepository.findById(id);
		}
    
}
