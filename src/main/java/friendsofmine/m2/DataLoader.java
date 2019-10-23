package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Inscription;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.InscriptionService;
import friendsofmine.m2.services.UtilisateurService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    private ActiviteService activiteService;

    private UtilisateurService utilisateurService;

    private InscriptionService inscriptionService;

    private Activite Procrastination, Philo, Jogging, Pingpong, Poker, Muscu, Guitare;
    private Utilisateur Julian, Karen, Ed, Thom;

    public DataLoader(ActiviteService activiteService, UtilisateurService utilisateurService, InscriptionService inscriptionService) {
        this.activiteService = activiteService;
        this.utilisateurService = utilisateurService;
        this.inscriptionService = inscriptionService;
    }


    public void initUtilisateurs() {
        Thom = new Utilisateur("Yorke","Thom","thom@rh.com","M");
        utilisateurService.saveUtilisateur(Thom);
        Ed = new Utilisateur("Obrien","Ed","ed@rh.com","M");
        utilisateurService.saveUtilisateur(Ed);
        Karen = new Utilisateur("Orzolek","Karen","karen@yyy.com","F");
        utilisateurService.saveUtilisateur(Karen);
        Julian = new Utilisateur("Casablancas","Julian","jc@ts.com","M");
        utilisateurService.saveUtilisateur(Julian);
    }


    public void initActivites() {
        Guitare = new Activite("Guitare","Matériel non fourni",Thom);
        activiteService.saveActivite(Guitare);
        Muscu = new Activite("Muscu","Créneau réservé le mardi",Ed);
        activiteService.saveActivite(Muscu);
        Poker = new Activite("Poker","Petite blind à 1 euro", Karen);
        activiteService.saveActivite(Poker);
        Pingpong = new Activite("Ping Pong","Matériel non fourni", Julian);
        activiteService.saveActivite(Pingpong);
        Jogging = new Activite("Jogging","Tous les midis",Ed);
        activiteService.saveActivite(Jogging);
        Philo = new Activite("Philo","Le club des admirateurs de Socrate",Thom);
        activiteService.saveActivite(Philo);
        Procrastination = new Activite("Procrastination","On verra demain", Thom);
        activiteService.saveActivite(Procrastination);
    }


    @Override
    public void run(ApplicationArguments args) {
        initUtilisateurs();
        initActivites();
        initInscriptions();
    }

    public void initInscriptions() {
        inscriptionService.saveInscription(getThomAuPingPong());
        inscriptionService.saveInscription(getThomAuPoker());
        inscriptionService.saveInscription(getEdAuJogging());
        inscriptionService.saveInscription(getKarenALaPhilo());
        inscriptionService.saveInscription(getKarenAuPingPong());
    }


    public Activite getProcrastination() {
        return Procrastination;
    }

    public Activite getPhilo() {
        return Philo;
    }

    public Activite getJogging() {
        return Jogging;
    }

    public Activite getPingpong() {
        return Pingpong;
    }

    public Activite getPoker() {
        return Poker;
    }

    public Activite getMuscu() {
        return Muscu;
    }

    public Activite getGuitare() {
        return Guitare;
    }

    public Utilisateur getJulian() {
        return Julian;
    }

    public Utilisateur getKaren() {
        return Karen;
    }

    public Utilisateur getEd() {
        return Ed;
    }

    public Utilisateur getThom() {
        return Thom;
    }

    public Inscription getThomAuPingPong (){
        return new Inscription(getThom(),getPingpong(),null);
    }

    public Inscription getThomAuPoker() {
        return new Inscription(getThom(),getPoker(),null);
    }

    public Inscription getEdAuJogging() {
        return new Inscription(getEd(),getJogging(),null);
    }

    public Inscription getKarenALaPhilo() {
        return new Inscription(getKaren(),getPhilo(),null);
    }

    public Inscription getKarenAuPingPong() {
        return new Inscription(getKaren(),getPingpong(),null);
    }

}
