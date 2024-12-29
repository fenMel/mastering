package fr.esic.mastering.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RattrapageSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User candidat; // Le candidat participant à la session

    private LocalDate date; // Date de la session de rattrapage

    @ManyToOne
    private User jury; 
    
    private String lieu; // Lieu où la session se tiendra

    private String commentaire; // Commentaire ou note supplémentaire (optionnel)
}
