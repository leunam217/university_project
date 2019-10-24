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
    private Inscription karenAuPingPong;
    private Inscription karenALaPhilo;
    private Inscription edAuJogging;
    private Inscription thomAuPoker;
    private Inscription thomAuPingPong;
    private Utilisateur thom;
    private Utilisateur ed;
    private Utilisateur karen;
    private Utilisateur julian;
    private Activite guitare;
    private Activite muscu;
    private Activite poker;
    private Activite pingpong;
    private Activite jogging;
    private Activite philo;
    private Activite procrastination;


    public DataLoader(ActiviteService activiteService, UtilisateurService utilisateurService, InscriptionService inscriptionService) {
        this.activiteService = activiteService;
        this.utilisateurService = utilisateurService;
        this.inscriptionService = inscriptionService;
    }


    public void initUtilisateurs() {
        initThom();
        initEd();
        initKaren();
        initJulian();
        utilisateurService.saveUtilisateur(thom);
        utilisateurService.saveUtilisateur(ed);
        utilisateurService.saveUtilisateur(karen);
        utilisateurService.saveUtilisateur(julian);
    }


    public void initActivites() {
        initGuitare();
        initMuscu();
        initPoker();
        initPingpong();
        initJogging();
        initPhilo();
        initProcrastination();
        activiteService.saveActivite(guitare);
        activiteService.saveActivite(muscu);
        activiteService.saveActivite(poker);
        activiteService.saveActivite(pingpong);
        activiteService.saveActivite(jogging);
        activiteService.saveActivite(philo);
        activiteService.saveActivite(procrastination);
    }


    @Override
    public void run(ApplicationArguments args) {
        initUtilisateurs();
        initActivites();
        initInscriptions();
    }

    public void initInscriptions() {
        initThomAuPingPong();
        initThomAuPoker();
        initEdAuJogging();
        initKarenALaPhilo();
        initKarenAuPingPong();
        inscriptionService.saveInscription(thomAuPingPong);
        inscriptionService.saveInscription(thomAuPoker);
        inscriptionService.saveInscription(edAuJogging);
        inscriptionService.saveInscription(karenALaPhilo);
        inscriptionService.saveInscription(karenAuPingPong);
    }


    public void initProcrastination() {
        procrastination =new Activite("Procrastination","On verra demain", thom);
    }

    public void initPhilo() {
        philo= new Activite("Philo","Le club des admirateurs de Socrate",thom);
    }

    public void initJogging() {
        jogging = new Activite("Jogging","Tous les midis",ed);
    }

    public void initPingpong() {
        pingpong = new Activite("Ping Pong","Matériel non fourni", julian);
    }

    public void initPoker() {
        poker = new Activite("Poker","Petite blind à 1 euro", karen);
    }

    public void initMuscu() {
        muscu = new Activite("Muscu","Créneau réservé le mardi",ed);
    }

    public void initGuitare() {
        guitare = new Activite("Guitare","Matériel non fourni",thom);
    }

    public void initJulian() {
        julian = new Utilisateur("Casablancas","Julian","jc@ts.com","M");
    }

    public void initKaren() {
        karen = new Utilisateur("Orzolek","Karen","karen@yyy.com","F");
    }

    public void initEd() {
        ed = new Utilisateur("Obrien","Ed","ed@rh.com","M");
    }

    public void initThom() {
        thom = new Utilisateur("Yorke","Thom","thom@rh.com","M");
    }

    public void initThomAuPingPong (){
        thomAuPingPong = new Inscription(thom,pingpong,null);
    }

    public void initThomAuPoker() {
        thomAuPoker = new Inscription(thom,poker,null);
    }

    public void initEdAuJogging() {
        edAuJogging = new Inscription(ed,jogging,null);
    }

    public void initKarenALaPhilo() {
        karenALaPhilo = new Inscription(karen,philo,null);
    }

    public void initKarenAuPingPong() {
        karenAuPingPong = new Inscription(karen,pingpong,null);
    }

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

    public Inscription getKarenAuPingPong() {
        return karenAuPingPong;
    }

    public void setKarenAuPingPong(Inscription karenAuPingPong) {
        this.karenAuPingPong = karenAuPingPong;
    }

    public Inscription getKarenALaPhilo() {
        return karenALaPhilo;
    }

    public void setKarenALaPhilo(Inscription karenALaPhilo) {
        this.karenALaPhilo = karenALaPhilo;
    }

    public Inscription getEdAuJogging() {
        return edAuJogging;
    }

    public void setEdAuJogging(Inscription edAuJogging) {
        this.edAuJogging = edAuJogging;
    }

    public Inscription getThomAuPoker() {
        return thomAuPoker;
    }

    public void setThomAuPoker(Inscription thomAuPoker) {
        this.thomAuPoker = thomAuPoker;
    }

    public Inscription getThomAuPingPong() {
        return thomAuPingPong;
    }

    public void setThomAuPingPong(Inscription thomAuPingPong) {
        this.thomAuPingPong = thomAuPingPong;
    }

    public Utilisateur getThom() {
        return thom;
    }

    public void setThom(Utilisateur thom) {
        this.thom = thom;
    }

    public Utilisateur getEd() {
        return ed;
    }

    public void setEd(Utilisateur ed) {
        this.ed = ed;
    }

    public Utilisateur getKaren() {
        return karen;
    }

    public void setKaren(Utilisateur karen) {
        this.karen = karen;
    }

    public Utilisateur getJulian() {
        return julian;
    }

    public void setJulian(Utilisateur julian) {
        this.julian = julian;
    }

    public Activite getGuitare() {
        return guitare;
    }

    public void setGuitare(Activite guitare) {
        this.guitare = guitare;
    }

    public Activite getMuscu() {
        return muscu;
    }

    public void setMuscu(Activite muscu) {
        this.muscu = muscu;
    }

    public Activite getPoker() {
        return poker;
    }

    public void setPoker(Activite poker) {
        this.poker = poker;
    }

    public Activite getPingpong() {
        return pingpong;
    }

    public void setPingpong(Activite pingpong) {
        this.pingpong = pingpong;
    }

    public Activite getJogging() {
        return jogging;
    }

    public void setJogging(Activite jogging) {
        this.jogging = jogging;
    }

    public Activite getPhilo() {
        return philo;
    }

    public void setPhilo(Activite philo) {
        this.philo = philo;
    }

    public Activite getProcrastination() {
        return procrastination;
    }

    public void setProcrastination(Activite procrastination) {
        this.procrastination = procrastination;
    }
}
