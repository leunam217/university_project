package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.repositories.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    public void setActiviteRepository(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public ActiviteRepository getActiviteRepository() {
        return activiteRepository;
    }

    public Activite findActiviteById (Long id){
        return activiteRepository.findById(id).orElse(null);
    }

    public void saveActivite(Activite activite){
        if (activite==null) throw new IllegalArgumentException();
        activiteRepository.save(activite);
    }

    public long countActivite(){
        return  activiteRepository.count();
    }



}
