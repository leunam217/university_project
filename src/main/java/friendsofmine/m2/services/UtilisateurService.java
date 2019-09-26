package friendsofmine.m2.services;

import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveUtilisateur(Utilisateur activite){
        if (activite==null) throw new IllegalArgumentException();
        utilisateurRepository.save(activite);
    }

    public long countUtilisateur(){
       return utilisateurRepository.count();
    }
}
