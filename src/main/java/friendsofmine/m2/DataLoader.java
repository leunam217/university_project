package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.UtilisateurService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private ActiviteService activiteService;
    private UtilisateurService utilisateurService;


    private Activite Procrastination, Philo, Jogging, Pingpong, Poker, Muscu, Guitare;
    private Utilisateur Julian, Karen, Ed, Thom;

    public DataLoader(ActiviteService activiteService, UtilisateurService utilisateurService) {
        this.activiteService = activiteService;
        this.utilisateurService = utilisateurService;
    }

    public void run() {
        initUtilisateurs();
        initActivites();
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

    @Override
    public void run(ApplicationArguments args) {
        initUtilisateurs();
        initActivites();
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
}
