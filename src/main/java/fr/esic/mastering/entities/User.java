package fr.esic.mastering.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY) 
 private Long id;
 
 private String nom;
 private String prenom;
 private Date DateNaissance;
 private String tel;
 
 @Column(unique = true, nullable = false)
 private String email;
 private String lieuxDeNaissance;

 private String password;
  
 @ManyToOne
 private Role role;

}
