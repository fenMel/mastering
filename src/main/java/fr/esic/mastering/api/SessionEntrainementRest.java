package fr.esic.mastering.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esic.mastering.entities.Annonce;
import fr.esic.mastering.entities.RessourcePedagogique;
import fr.esic.mastering.entities.SessionEntrainement;
import fr.esic.mastering.services.SessionEntrainementService;

@RestController
@RequestMapping("/api/sessions-entrainement")
public class SessionEntrainementRest {
    @Autowired
    private SessionEntrainementService sessionService;

    @PostMapping
    public ResponseEntity<?> creerSession(@RequestBody SessionEntrainement session) {
        return ResponseEntity.ok(sessionService.creerSession(session));
    }

    @PostMapping("/{sessionId}/participants")
    public ResponseEntity<?> ajouterParticipant(@PathVariable Long sessionId, @RequestBody Long utilisateurId) {
        sessionService.ajouterParticipant(sessionId, utilisateurId);
        return ResponseEntity.ok("Participant ajouté !");
    }

    @DeleteMapping("/{sessionId}/participants/{utilisateurId}")
    public ResponseEntity<?> supprimerParticipant(@PathVariable Long sessionId, @PathVariable Long utilisateurId) {
        sessionService.supprimerParticipant(sessionId, utilisateurId);
        return ResponseEntity.ok("Participant supprimé avec succès.");
    }

    @PostMapping("/{sessionId}/ressources")
    public ResponseEntity<?> ajouterRessource(@PathVariable Long sessionId, @RequestBody RessourcePedagogique ressource) {
        return ResponseEntity.ok(sessionService.ajouterRessource(sessionId, ressource));
    }

    

    @GetMapping
    public ResponseEntity<?> recupererToutesLesSessions() {
        return ResponseEntity.ok(sessionService.recupererToutesLesSessions());
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> recupererSession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(sessionService.recupererSession(sessionId));
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<?> mettreAJourSession(@PathVariable Long sessionId, @RequestBody SessionEntrainement sessionDetails) {
        return ResponseEntity.ok(sessionService.mettreAJourSession(sessionId, sessionDetails));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> supprimerSession(@PathVariable Long sessionId) {
        sessionService.supprimerSession(sessionId);
        return ResponseEntity.ok("Session supprimée avec succès.");
    }
}
