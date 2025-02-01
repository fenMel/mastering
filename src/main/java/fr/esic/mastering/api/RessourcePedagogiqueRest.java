package fr.esic.mastering.api;


import fr.esic.mastering.entities.RessourcePedagogique;
import fr.esic.mastering.services.RessourcePedagogiqueService;
import fr.esic.mastering.services.SessionEntrainementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources-pedagogiques")
public class RessourcePedagogiqueRest {

    @Autowired
    private RessourcePedagogiqueService ressourceService;
    @Autowired
    private SessionEntrainementService sessionService;

    @PostMapping("/{sessionId}")
    public ResponseEntity<?> ajouterRessource(@PathVariable Long sessionId, @RequestBody RessourcePedagogique ressource) {
        RessourcePedagogique ressourceAjoutee = ressourceService.ajouterRessource(sessionId, ressource);
        return ResponseEntity.ok(ressourceAjoutee);
    }

    @GetMapping("/{sessionId}/ressources")
    public ResponseEntity<?> recupererRessources(@PathVariable Long sessionId) {
        return ResponseEntity.ok(sessionService.recupererRessources(sessionId));
    }


    @DeleteMapping("/{ressourceId}")
    public ResponseEntity<?> supprimerRessource(@PathVariable Long ressourceId) {
        ressourceService.supprimerRessource(ressourceId);
        return ResponseEntity.ok("Ressource supprimée avec succès.");
    }
}
