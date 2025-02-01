package fr.esic.mastering.services;

import fr.esic.mastering.entities.SessionEntrainement;
import fr.esic.mastering.entities.User;
import fr.esic.mastering.repository.AnnonceRepository;
import fr.esic.mastering.repository.RessourcePedagogiqueRepository;
import fr.esic.mastering.repository.SessionEntrainementRepository;
import fr.esic.mastering.repository.UserRepository;
import fr.esic.mastering.entities.RessourcePedagogique;
import fr.esic.mastering.entities.Annonce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionEntrainementService {

    @Autowired
    private SessionEntrainementRepository sessionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RessourcePedagogiqueRepository ressourceRepo;

    @Autowired
    private AnnonceRepository annonceRepo;
   

    public SessionEntrainement creerSession(SessionEntrainement session) {
        return sessionRepo.save(session);
    }

    public void ajouterParticipant(Long sessionId, Long utilisateurId) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        User utilisateur = userRepo.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable."));
        session.getParticipants().add(utilisateur);
        sessionRepo.save(session);
    }

    public List<SessionEntrainement> recupererToutesLesSessions() {
        return sessionRepo.findAll();
    }

    public SessionEntrainement recupererSession(Long sessionId) {
        return sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
    }

    public SessionEntrainement mettreAJourSession(Long sessionId, SessionEntrainement sessionDetails) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        session.setTitre(sessionDetails.getTitre());
        session.setDescription(sessionDetails.getDescription());
        session.setDateDebut(sessionDetails.getDateDebut());
        session.setDateFin(sessionDetails.getDateFin());
        return sessionRepo.save(session);
    }

    public void supprimerSession(Long sessionId) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        sessionRepo.delete(session);
    }

    public RessourcePedagogique ajouterRessource(Long sessionId, RessourcePedagogique ressource) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        ressource.setSessionEntrainement(session);
        return ressourceRepo.save(ressource);
    }

    public Annonce publierAnnonce(Long sessionId, Annonce annonce) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        annonce.setSessionEntrainement(session);
        return annonceRepo.save(annonce);
    }
    public void supprimerParticipant(Long sessionId, Long utilisateurId) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        User utilisateur = userRepo.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable."));
        session.getParticipants().remove(utilisateur);
        sessionRepo.save(session);
    }
   

    public List<RessourcePedagogique> recupererRessources(Long sessionId) {
        return ressourceRepo.findBySessionEntrainementId(sessionId);
    }
}
