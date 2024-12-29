package fr.esic.mastering.repository;


import fr.esic.mastering.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByCandidatId(Long candidatId);
    List<Evaluation> findByJuryId(Long juryId);
}
