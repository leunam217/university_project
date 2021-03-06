package friendsofmine.m2.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Inscription {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Activite activite;

    @NotNull
    @ManyToOne
    private Utilisateur participant;

    private Date dateInscription;

    public Inscription() {
    }

    public Inscription(Utilisateur participant, Activite activite , Date dateInscription) {
        this.activite = activite;
        this.participant = participant;
        this.dateInscription = dateInscription;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
