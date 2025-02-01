package fr.esic.mastering.services;

import fr.esic.mastering.entities.Annonce;
import fr.esic.mastering.entities.SessionEntrainement;
import fr.esic.mastering.repository.AnnonceRepository;
import fr.esic.mastering.repository.SessionEntrainementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepo;

    @Autowired
    private SessionEntrainementRepository sessionRepo;

    public Annonce publierAnnonce(Long sessionId, Annonce annonce) {
        SessionEntrainement session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable."));
        annonce.setSessionEntrainement(session);
        annonce.setDatePublication(LocalDateTime.now());
        return annonceRepo.save(annonce);
    }

    public List<Annonce> recupererAnnonces(Long sessionId) {
        return annonceRepo.findBySessionEntrainementId(sessionId);
    }
}
