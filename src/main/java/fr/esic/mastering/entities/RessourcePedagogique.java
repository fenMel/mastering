package fr.esic.mastering.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RessourcePedagogique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type; // PDF, VIDEO, etc.
    private String chemin; // Chemin du fichier ou lien URL

    @ManyToOne
    @JsonIgnore
    private SessionEntrainement sessionEntrainement; // Association avec une session
}
