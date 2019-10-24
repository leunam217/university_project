package friendsofmine.m2.controllers;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Inscription;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.InscriptionService;
import friendsofmine.m2.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

@RestController
public class InscriptionController {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private InscriptionService inscriptionService;

    public ActiviteService getActiviteService() {
        return activiteService;
    }

    public void setActiviteService(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public InscriptionService getInscriptionService() {
        return inscriptionService;
    }

    public void setInscriptionService(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    class NotFound extends RuntimeException {
    }
    @PostMapping(path = "/api/inscription")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Inscription addInscription(@RequestParam Long utilisateur_id, @RequestParam Long activite_id) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(utilisateur_id);
        Activite activite = activiteService.findActiviteById(activite_id);
        return inscriptionService.saveInscription(new Inscription(utilisateur,activite,null));
    }

    @GetMapping(produces = "application/json", path ="/api/inscription/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Inscription getInscription (@PathVariable("id") Long id){
        Inscription i = inscriptionService.findInscriptionById(id);
        if( i!= null) return i;
        throw new NotFound();
    }

    @DeleteMapping( produces = "application/json",path ="api/inscription/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteInscription(@PathVariable("id") Long l) {
        inscriptionService.deleteInscription(l);
    }
}
