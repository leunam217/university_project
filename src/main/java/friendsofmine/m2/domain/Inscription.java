package friendsofmine.m2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Inscription {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    private Activite activite;

    @NotNull
    @OneToOne
    private Utilisateur participant;

    private Date dateInscription;

    public Inscription() {
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Utilisateur getParticipant() {
        return participant;
    }

    public void setParticipant(Utilisateur participant) {
        this.participant = participant;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
