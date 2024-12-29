package fr.esic.mastering.services;

import fr.esic.mastering.dto.EvaluationDTO;
import fr.esic.mastering.entities.Evaluation;
import fr.esic.mastering.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour gérer les évaluations.
 * Contient la logique métier pour ajouter, mettre à jour, supprimer et récupérer des évaluations.
 */
@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    /**
     * Ajouter une nouvelle évaluation dans la base de données.
     * La moyenne est calculée automatiquement avant l'enregistrement.
     *
     * @param evaluation L'évaluation à ajouter
     * @return L'évaluation ajoutée avec son ID généré
     */
    public Evaluation addEvaluation(Evaluation evaluation) {
    	
        // Valider les contraintes de pourcentage
    	 validateEvaluation(evaluation); 
    	 
        // Calculer la moyenne
        evaluation.calculerMoyenne();

        // Sauvegarder l'évaluation dans la base de données
        return evaluationRepository.save(evaluation);
    }

    /**
     * Mettre à jour une évaluation existante.
     * La moyenne est recalculée automatiquement avant l'enregistrement.
     *
     * @param id            L'ID de l'évaluation à mettre à jour
     * @param newEvaluation Les nouvelles données de l'évaluation
     * @return L'évaluation mise à jour
     */
    public Evaluation updateEvaluation(Long id, Evaluation newEvaluation) {
        return evaluationRepository.findById(id).map(evaluation -> {
            // Mettre à jour les champs
            evaluation.setNotePresentation(newEvaluation.getNotePresentation());
            evaluation.setNoteContenu(newEvaluation.getNoteContenu());
            evaluation.setNoteClarte(newEvaluation.getNoteClarte());
            evaluation.setNotePertinence(newEvaluation.getNotePertinence());
            evaluation.setNoteReponses(newEvaluation.getNoteReponses());
            evaluation.setCommentaire(newEvaluation.getCommentaire());

            
            
            // Valider les contraintes de pourcentage
             validateEvaluation(evaluation);
            // Recalculer la moyenne après mise à jour
            evaluation.calculerMoyenne();

            // Sauvegarder l'évaluation mise à jour
            return evaluationRepository.save(evaluation);
        }).orElseThrow(() -> new RuntimeException("Évaluation introuvable avec l'ID : " + id));
    }
       
    /**
     * Récupérer toutes les évaluations.
     *
     * @return Liste de toutes les évaluations
     */
    public List<Evaluation> getAll() {
        return evaluationRepository.findAll();
    }

    /**
     * Récupérer les évaluations pour un candidat donné.
     *
     * @param candidatId L'ID du candidat
     * @return Liste des évaluations associées au candidat
     */
    public List<Evaluation> getEvaluationsByCandidat(Long candidatId) {
        return evaluationRepository.findByCandidatId(candidatId);
    }

    /**
     * Récupérer les évaluations faites par un jury donné.
     *
     * @param juryId L'ID du jury
     * @return Liste des évaluations associées au jury
     */
    public List<Evaluation> getEvaluationsByJury(Long juryId) {
        return evaluationRepository.findByJuryId(juryId);
    }

    /**
     * Supprimer une évaluation par ID.
     *
     * @param id L'ID de l'évaluation à supprimer
     */
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    /**
     * Convertir une entité Evaluation en DTO.
     *
     * @param evaluation L'entité Evaluation
     * @return Un DTO contenant les données de l'évaluation
     */
    public EvaluationDTO convertToDTO(Evaluation evaluation) {
        return new EvaluationDTO(
            evaluation.getId(),
            evaluation.getJury().getId(),
            evaluation.getCandidat().getId(),
            evaluation.getNotePresentation(),
            evaluation.getNoteContenu(),
            evaluation.getNoteClarte(),
            evaluation.getNotePertinence(),
            evaluation.getNoteReponses(),
            evaluation.getMoyenne(), // Récupération de la moyenne calculée
            evaluation.getCommentaire()
        );
    }
    
    public  List<String>  validateEvaluation(Evaluation evaluation) throws IllegalArgumentException {
    	 List<String> errors = new ArrayList<>();
        double globalMax = 100.0; // Note globale maximale
        double notePresentation = evaluation.getNotePresentation();
        double noteContenu = evaluation.getNoteContenu();
        double noteClarte = evaluation.getNoteClarte();
        double notePertinence = evaluation.getNotePertinence();
        double noteReponses = evaluation.getNoteReponses();

        if (notePresentation > globalMax * 0.30) {
            throw new IllegalArgumentException("La note de présentation ne peut pas dépasser 30% de la note globale.");
        }
        if (noteContenu > globalMax * 0.15) {
            throw new IllegalArgumentException("La note de contenu ne peut pas dépasser 15% de la note globale.");
        }
        if (noteClarte > globalMax * 0.15) {
            throw new IllegalArgumentException("La note de clarté ne peut pas dépasser 15% de la note globale.");
        }
        if (notePertinence > globalMax * 0.15) {
            throw new IllegalArgumentException("La note de pertinence ne peut pas dépasser 15% de la note globale.");
        }
        if (noteReponses > globalMax * 0.25) {
            throw new IllegalArgumentException("La note de réponses ne peut pas dépasser 25% de la note globale.");
        }

        // Validation du commentaire
        if (evaluation.getCommentaire() == null || evaluation.getCommentaire().trim().isEmpty()) {
            errors.add("Le commentaire est obligatoire.");
        }

        // Retourne toutes les erreurs collectées
        return errors;
    }

}
