package fr.esic.mastering.api;

import fr.esic.mastering.dto.EvaluationDTO;
import fr.esic.mastering.entities.Evaluation;
import fr.esic.mastering.services.EvaluationService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationRest {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * Endpoint pour récupérer toutes les évaluations sous forme de DTO.
     *
     * @return Liste des évaluations avec les données et la moyenne calculée
     */
    @GetMapping
    public ResponseEntity<?> getAllEvaluations() {
        try {
            List<Evaluation> evaluations = evaluationService.getAll();

            // Convertir les entités Evaluation en DTO
            List<EvaluationDTO> dtos = evaluations.stream()
                    .map(evaluation -> evaluationService.convertToDTO(evaluation))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des évaluations.");
        }
    }

    /**
     * Endpoint pour ajouter une évaluation.
     *
     * @param evaluation L'évaluation à ajouter
     * @return Une réponse avec un message de succès ou d'échec
     */
    //////// 
    @PostMapping
    public ResponseEntity<?> addEvaluation(@RequestBody  @Valid Evaluation evaluation) {
        try {
            Evaluation savedEvaluation = evaluationService.addEvaluation(evaluation);
            return ResponseEntity.ok("Évaluation ajoutée avec succès, ID : " + savedEvaluation.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'évaluation.");
        }
    }
    /*
    @PostMapping
    public ResponseEntity<?> addEvaluation(@RequestBody  @Valid Evaluation evaluation) {
        try {
            Evaluation savedEvaluation = evaluationService.addEvaluation(evaluation);
            return ResponseEntity.ok("Évaluation ajoutée avec succès, ID : " + savedEvaluation.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'évaluation.");
        }
    }
*/
    /**
     * Endpoint pour récupérer les évaluations d'un candidat.
     *
     * @param candidatId L'ID du candidat
     * @return Liste des évaluations associées au candidat ou un message d'erreur
     */
    @GetMapping("/candidat/{candidatId}")
    public ResponseEntity<?> getEvaluationsByCandidat(@PathVariable Long candidatId) {
        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsByCandidat(candidatId);
            if (evaluations.isEmpty()) {
                return ResponseEntity.status(404).body("Aucune évaluation trouvée pour le candidat ID : " + candidatId);
            }
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des évaluations pour le candidat.");
        }
    }

    /**
     * Endpoint pour récupérer les évaluations d'un jury.
     *
     * @param juryId L'ID du jury
     * @return Liste des évaluations associées au jury ou un message d'erreur
     */
    @GetMapping("/jury/{juryId}")
    public ResponseEntity<?> getEvaluationsByJury(@PathVariable Long juryId) {
        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsByJury(juryId);
            if (evaluations.isEmpty()) {
                return ResponseEntity.status(404).body("Aucune évaluation trouvée pour le jury ID : " + juryId);
            }
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des évaluations pour le jury.");
        }
    }

    /**
     * Endpoint pour mettre à jour une évaluation.
     *
     * @param id            L'ID de l'évaluation à mettre à jour
     * @param newEvaluation Les nouvelles données de l'évaluation
     * @return Une réponse avec un message de succès ou d'échec
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation newEvaluation) {
        try {
            Evaluation updatedEvaluation = evaluationService.updateEvaluation(id, newEvaluation);
            return ResponseEntity.ok("Évaluation mise à jour avec succès !");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la mise à jour de l'évaluation.");
        }
    }

    /**
     * Endpoint pour supprimer une évaluation.
     *
     * @param id L'ID de l'évaluation à supprimer
     * @return Une réponse avec un message de succès ou d'échec
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long id) {
        try {
            evaluationService.deleteEvaluation(id);
            return ResponseEntity.ok("Évaluation supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la suppression de l'évaluation.");
        }
    }
}
