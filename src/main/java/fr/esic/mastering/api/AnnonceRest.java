package fr.esic.mastering.api;


import fr.esic.mastering.entities.Annonce;
import fr.esic.mastering.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sessions-entrainement")
public class AnnonceRest {

    @Autowired
    private AnnonceService annonceService;

    @PostMapping("/{sessionId}/annonces")
    public ResponseEntity<?> publierAnnonce(@PathVariable Long sessionId, @RequestBody Annonce annonce) {
        Annonce annoncePubliee = annonceService.publierAnnonce(sessionId, annonce);
        return ResponseEntity.ok(annoncePubliee);
    } 

    @GetMapping("/{sessionId}/annonces")
    public ResponseEntity<?> recupererAnnonces(@PathVariable Long sessionId) {
        List<Annonce> annonces = annonceService.recupererAnnonces(sessionId);
        return ResponseEntity.ok(annonces);
    }
}
