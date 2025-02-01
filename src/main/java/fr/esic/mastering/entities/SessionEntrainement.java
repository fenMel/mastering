package fr.esic.mastering.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionEntrainement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @ManyToOne
    private User coordinateur;

    @ManyToMany
    private List<User> participants;

    @OneToMany(mappedBy = "sessionEntrainement", cascade = CascadeType.ALL)
    private List<RessourcePedagogique> ressources;

    @OneToMany(mappedBy = "sessionEntrainement", cascade = CascadeType.ALL)
    private List<Annonce> annonces;
}
