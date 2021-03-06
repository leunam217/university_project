package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void setActiviteRepository(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public ActiviteRepository getActiviteRepository() {
        return activiteRepository;
    }


    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Activite findActiviteById (Long id){
        return activiteRepository.findById(id).orElse(null);
    }

    public Activite saveActivite(Activite activite){
        if (activite==null) throw new IllegalArgumentException();
        Activite activite1 = activiteRepository.save(activite);
        activite1.getResponsable().getActivites().add(activite1);
        return activite1;
    }

    public long countActivite(){
        return  activiteRepository.count();
    }


    public ArrayList<Activite> findAllActivites() {
        ArrayList<Activite> result = new ArrayList<>();
        activiteRepository.findAll().forEach(result::add);
        result.sort(Comparator.comparing(Activite::getTitre));
        return result;
    }
}
