package friendsofmine.m2.services;

import friendsofmine.m2.domain.Inscription;
import friendsofmine.m2.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription saveInscription(Inscription ins) {
        if ( ins == null )
            throw new IllegalArgumentException();
        if (ins.getDateInscription() ==null){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            ins.setDateInscription(new Date(System.currentTimeMillis()));
        }
        return inscriptionRepository.save(ins);
    }

    public Inscription findInscriptionById(Long id) {
        return inscriptionRepository.findById(id).orElse(null);
    }

    public long countInscription() {
        return inscriptionRepository.count();
    }

    public void deleteInscription(Long id) {
        inscriptionRepository.deleteById(id);
    }
}
