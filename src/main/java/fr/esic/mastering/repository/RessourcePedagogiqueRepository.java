package fr.esic.mastering.repository;
import fr.esic.mastering.entities.RessourcePedagogique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RessourcePedagogiqueRepository extends JpaRepository<RessourcePedagogique, Long> {

    List<RessourcePedagogique> findBySessionEntrainementId(Long sessionId);
}
