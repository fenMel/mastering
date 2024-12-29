package fr.esic.mastering;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.esic.mastering.entities.Role;
import fr.esic.mastering.entities.User;
import fr.esic.mastering.repository.RoleRepository;
import fr.esic.mastering.repository.UserRepository;

@SpringBootApplication
public class MasteringApplication implements CommandLineRunner {
    @Autowired
	private UserRepository  userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(MasteringApplication.class, args);
		System.out.println("lancement terminé");
	}

	@Override
	public void run(String... args) throws Exception {
	 /* SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
	  Role r=new  Role(null, "Cordinateur");
	  Role r2=new  Role(null, "Jury");
	  Role r3=new  Role(null, "apprenant");
	  Role r4=new  Role(null, "Tuteur");
	  
	  roleRepository.save(r);
	  roleRepository.save(r2);
	  roleRepository.save(r3);
	  roleRepository.save(r4);

		
	  User u=new User(null,"joel", "banka", sdf.parse("10/02/2024"),"0000855", "meli@gmail.com", "tizi ouzou", "melissa", r);
	  User u2=new User(null,"chabban", "chabban", sdf.parse("10/02/2024"),"00008855", "meiit@gmail.com", "Ain El Hamman", "melibssa", r2);
	  User u3=new User(null,"melissa", "fenzi", sdf.parse("10/02/2024"),"00200855", "meosi@gmail.com", "Adekar", "melissna", r3);
	  User u4=new User(null,"moi", "meme", sdf.parse("10/02/2024"),"050200855", "meosdi@gmail.com", "Adefkar", "melissnas", r4);

      
	  userRepository.save(u);
	  userRepository.save(u2);
	  userRepository.save(u3);
	  userRepository.save(u4);
*/
	  
	 

	}
	
	 

}
