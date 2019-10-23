package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur findUtilisateurById (Long id){
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur){
        if (utilisateur==null) throw new IllegalArgumentException();
        Utilisateur persisted=utilisateurRepository.save(utilisateur);
        return persisted;
    }

    public long countUtilisateur(){
       return utilisateurRepository.count();
    }

    @Cacheable("activites")
    public Utilisateur findUtilisateurByEmail(String email) {
        // simulate slow service
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return utilisateurRepository.findByEmail(email);
    }


}
