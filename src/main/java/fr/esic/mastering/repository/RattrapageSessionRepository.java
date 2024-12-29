package fr.esic.mastering.repository;

import fr.esic.mastering.entities.RattrapageSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RattrapageSessionRepository extends JpaRepository<RattrapageSession, Long> {
    List<RattrapageSession> findByCandidatId(Long candidatId);
}
