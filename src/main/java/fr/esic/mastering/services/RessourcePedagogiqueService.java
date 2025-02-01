package fr.esic.mastering.services;
import fr.esic.mastering.entities.RessourcePedagogique;
import fr.esic.mastering.entities.SessionEntrainement;
import fr.esic.mastering.repository.RessourcePedagogiqueRepository;
import fr.esic.mastering.repository.SessionEntrainementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service


public class RessourcePedagogiqueService {

    @Autowired
    private RessourcePedagogiqueRepository ressourceRepo;

    @Autowired
    private SessionEntrainementRepository sessionRepo;

    public RessourcePedagogique ajouterRessource(Long sessionId, RessourcePedagogique ressource) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        ressource.setSessionEntrainement(session);
        return ressourceRepo.save(ressource);
    }

    public List<RessourcePedagogique> recupererRessources(Long sessionId) {
        return ressourceRepo.findBySessionEntrainementId(sessionId);
    }

    public void supprimerRessource(Long ressourceId) {
        RessourcePedagogique ressource = ressourceRepo.findById(ressourceId)
                .orElseThrow(() -> new RuntimeException("Ressource introuvable."));
        ressourceRepo.delete(ressource);
    }

}



