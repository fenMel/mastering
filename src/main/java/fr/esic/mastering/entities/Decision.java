package fr.esic.mastering.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User candidat;

    @ManyToOne
    private User jury;

    private String commentaireFinal;
    
    @Enumerated(EnumType.STRING) // Stocke l'enum sous forme de chaîne dans la base de données
    @Column(unique = true, nullable = false)
    private VerdictDecision verdict; // ADMIS, NON_ADMIS, RATTRAPAGE
   
}

