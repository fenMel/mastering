package fr.esic.mastering.api;


import fr.esic.mastering.entities.Decision;
import fr.esic.mastering.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decisions")
public class DecisionRest {

    @Autowired
    private DecisionService decisionService;

    /**
     * Ajouter une décision basée sur la moyenne des évaluations.
     */
    @PostMapping
    public ResponseEntity<?> addDecision(@RequestBody Map<String, Object> requestData) {
        try {
            Long candidatId = Long.valueOf(requestData.get("candidatId").toString());
            Long juryId = requestData.get("juryId") != null ? Long.valueOf(requestData.get("juryId").toString()) : null;
            String commentaireFinal = requestData.get("commentaireFinal").toString();

            Decision decision = decisionService.addDecision(candidatId, juryId, commentaireFinal);
            return ResponseEntity.ok("Décision ajoutée avec succès, ID : " + decision.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    /**
     * Récupérer toutes les décisions.
     */
    @GetMapping
    public ResponseEntity<?> getAllDecisions() {
        try {
            List<Decision> decisions = decisionService.getAllDecisions();
            return ResponseEntity.ok(decisions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des décisions.");
        }
    }

    /**
     * Récupérer les décisions d'un candidat.
     */
    @GetMapping("/candidat/{candidatId}")
    public ResponseEntity<?> getDecisionsByCandidat(@PathVariable Long candidatId) {
        try {
            List<Decision> decisions = decisionService.getDecisionsByCandidat(candidatId);
            if (decisions.isEmpty()) {
                return ResponseEntity.status(404).body("Aucune décision trouvée pour le candidat ID : " + candidatId);
            }
            return ResponseEntity.ok(decisions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des décisions pour le candidat.");
        }
    }

    /**
     * Supprimer une décision par ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDecision(@PathVariable Long id) {
        try {
            decisionService.deleteDecision(id);
            return ResponseEntity.ok("Décision supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la suppression de la décision.");
        }
    }
}
