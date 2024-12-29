package fr.esic.mastering.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Le jury est obligatoire")
    private User jury; // Le jury ayant évalué

    @ManyToOne
    @NotNull(message = "Le candidat est obligatoire")
    private User candidat; // Le candidat évalué

    @NotNull(message = "Le commentaire est obligatoire")
    @Size(min = 5, message = "Le commentaire doit contenir au moins 5 caractères")
    private String commentaire;

    private double notePresentation; 
    private double noteContenu;
    private double noteClarte;
    private double notePertinence;
    private double noteReponses;
    private double moyenne;

    public double calculerMoyenne() {
        return this.moyenne = (notePresentation + noteContenu + noteClarte + notePertinence + noteReponses) / 5;
    }
}
