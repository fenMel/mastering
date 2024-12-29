package fr.esic.mastering.services;

import fr.esic.mastering.entities.Decision;
import fr.esic.mastering.entities.RattrapageSession;
import fr.esic.mastering.entities.RoleType;
import fr.esic.mastering.entities.User;
import fr.esic.mastering.entities.VerdictDecision;
import fr.esic.mastering.repository.DecisionRepository;
import fr.esic.mastering.repository.RattrapageSessionRepository;
import fr.esic.mastering.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RattrapageSessionService {

    @Autowired
    private RattrapageSessionRepository rattrapageSessionRepository;

    
    @Autowired
    private DecisionRepository decisionRepository;
   
    @Autowired
    private UserRepository userRepository;

    /**
     * Inscrire automatiquement les candidats non admis à une session de rattrapage.
     *
     * @param date  Date de la session
     * @param lieu  Lieu de la session
     * @param commentaire Commentaire pour la session
     * @return Nombre de sessions créées
     */
    public int autoAssignSessions(LocalDate date, String lieu, String commentaire) {
        // Récupérer toutes les décisions avec verdict NON_ADMIS
        List<Decision> nonAdmisDecisions = decisionRepository.findByVerdict(VerdictDecision.NON_ADMIS);

        // Vérifier s'il existe des jurys disponibles
        List<User> jurys = userRepository.findByRoleRoleUtilisateur(RoleType.JURY);
        if (jurys.isEmpty()) {
            throw new RuntimeException("Aucun jury disponible pour les sessions de rattrapage.");
        }

        // Créer une session pour chaque candidat NON_ADMIS
        for (Decision decision : nonAdmisDecisions) {
            User candidat = decision.getCandidat();

            // Assigner un jury de manière circulaire ou aléatoire
            User jury = jurys.get((int) (Math.random() * jurys.size()));

            // Créer une nouvelle session
            RattrapageSession session = new RattrapageSession();
            session.setCandidat(candidat);
            session.setJury(jury);
            session.setDate(date);
            session.setLieu(lieu);
            session.setCommentaire(commentaire);

            // Sauvegarder la session
            rattrapageSessionRepository.save(session);
        }

        // Retourner le nombre de sessions créées
        return nonAdmisDecisions.size();
    }
   
    
    /**
     * Ajouter une nouvelle session de rattrapage.
     *
     * @param session La session de rattrapage à ajouter
     * @return La session ajoutée
     */
    public List<RattrapageSession> getAllSessions() {
        return rattrapageSessionRepository.findAll();
    }
 
    /**
     * Récupérer toutes les sessions de rattrapage.
     *
     * @return Liste des sessions
     */
    public RattrapageSession addSession(RattrapageSession session) {
        if (session.getCandidat() == null) {
            throw new IllegalArgumentException("Le candidat est requis pour une session de rattrapage.");
        }
        return rattrapageSessionRepository.save(session);
    }

    /**
     * Récupérer les sessions de rattrapage d'un candidat.
     *
     * @param candidatId L'ID du candidat
     * @return Liste des sessions associées
     */
    public List<RattrapageSession> getSessionsByCandidat(Long candidatId) {
        return rattrapageSessionRepository.findByCandidatId(candidatId);
    }

    /**
     * Supprimer une session par ID.
     *
     * @param id L'ID de la session
     */
    public void deleteSession(Long id) {
        rattrapageSessionRepository.deleteById(id);
    }
}
