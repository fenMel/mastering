package fr.esic.mastering.repository;

import fr.esic.mastering.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findBySessionEntrainementId(Long sessionId);
}
