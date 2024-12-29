package fr.esic.mastering.api;


import fr.esic.mastering.entities.RattrapageSession;
import fr.esic.mastering.services.RattrapageSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rattrapage-sessions")
public class RattrapageSessionRest {

    @Autowired
    private RattrapageSessionService rattrapageSessionService;

    
    
    /**
     * Inscrire automatiquement les candidats non admis à des sessions de rattrapage.
     */
    @PostMapping("/auto-assign")
    public ResponseEntity<?> autoAssignSessions(
            @RequestParam String date,
            @RequestParam String lieu,
            @RequestParam(required = false) String commentaire) {
        try {
            LocalDate sessionDate = LocalDate.parse(date);
            int count = rattrapageSessionService.autoAssignSessions(sessionDate, lieu, commentaire);
            return ResponseEntity.ok("Sessions créées avec succès pour " + count + " candidat(s).");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la création des sessions : " + e.getMessage());
        }
    }
    /**
     * Ajouter une nouvelle session de rattrapage.
     */
    
    
    @PostMapping
    public ResponseEntity<?> addSession(@RequestBody RattrapageSession session) {
        try {
            RattrapageSession savedSession = rattrapageSessionService.addSession(session);
            return ResponseEntity.ok("Session de rattrapage ajoutée avec succès, ID : " + savedSession.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Capture IllegalArgumentException
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de la session de rattrapage.");
        }
    }


    /**
     * Récupérer toutes les sessions de rattrapage.
     */
    @GetMapping
    public ResponseEntity<?> getAllSessions() {
        try {
            List<RattrapageSession> sessions = rattrapageSessionService.getAllSessions();
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des sessions de rattrapage.");
        }
    }

    /**
     * Récupérer les sessions d'un candidat.
     */
    @GetMapping("/candidat/{candidatId}")
    public ResponseEntity<?> getSessionsByCandidat(@PathVariable Long candidatId) {
        try {
            List<RattrapageSession> sessions = rattrapageSessionService.getSessionsByCandidat(candidatId);
            if (sessions.isEmpty()) {
                return ResponseEntity.status(404).body("Aucune session de rattrapage trouvée pour le candidat ID : " + candidatId);
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des sessions pour le candidat.");
        }
    }

    /**
     * Supprimer une session de rattrapage.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        try {
            rattrapageSessionService.deleteSession(id);
            return ResponseEntity.ok("Session de rattrapage supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la suppression de la session.");
        }
    }
}
