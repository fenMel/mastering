package fr.esic.mastering.repository;


import fr.esic.mastering.entities.SessionEntrainement;
import fr.esic.mastering.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionEntrainementRepository extends JpaRepository<SessionEntrainement, Long> {
    List<SessionEntrainement> findByCoordinateur(User coordinateur);
}
