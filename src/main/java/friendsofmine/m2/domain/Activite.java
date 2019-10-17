package friendsofmine.m2.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Activite {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=1)
    @NotNull
    private String titre;

    private String descriptif;

    @JsonManagedReference
    @ManyToOne
    @NotNull
    private Utilisateur responsable;

    public Activite(String titre, String descriptif, Utilisateur responsable) {
        this.titre = titre;
        this.descriptif = descriptif;
        this.responsable = responsable;
    }

    public Activite(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }



    public String getTitre() {
        return titre;
    }

    public Utilisateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
