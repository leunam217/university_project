package friendsofmine.m2;

import friendsofmine.m2.domain.Utilisateur;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UtilisateurTest {

    private static Validator validator;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUtilisateurFemme() {
        // given: un Utilisateur util avec un nom, un prénom, un email et un sexe valides
        // when: util est créé
        Utilisateur util = new Utilisateur("Dupont", "Jeanne", "jd@jd.com", "F");
        // then: util est validé par le validator
        assertTrue(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurHomme() {
        // given: un Utilisateur util avec un nom, un prénom, un email et un sexe valides
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "Jacques", "jd@jd.com", "M");
        // then: util est validé par le validator
        assertTrue(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurNomNull() {
        // given: un Utilisateur util avec un nom null
        // when: util est créé
        Utilisateur util = new Utilisateur(null, "Jacques", "jd@jd.com", "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurNomVide() {
        // given: un Utilisateur util avec un nom vide
        // when: util est créé
        Utilisateur util = new Utilisateur("", "Jacques", "jd@jd.com", "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurPrenomNull() {
        // given: un Utilisateur util avec un prénom null
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", null, "jd@jd.com", "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurPrenomVide() {
        // given: un Utilisateur util avec un prénom vide
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "", "jd@jd.com", "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurEmailSansArobase() {
        // given: un Utilisateur util avec un email invalide
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd.jd.com", "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurEmailNull() {
        // given: un Utilisateur util avec un email null
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "Eric", null, "M");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurSexeInvalide() {
        // given: un Utilisateur util avec un sexe non valide
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd@jd.com", "V");
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUtilisateurSexeNull() {
        // given: un Utilisateur util avec un sexe null
        // when: util est créé
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd@jd.com", null);
        // then: util n'est pas validé par le validator
        assertFalse(validator.validate(util).isEmpty());
    }

    @Test
    public void testUnNouvelleUtilisateurEstSansActivite() {
        // given; un nouvel Utilisateur
        // when: l'Utilisateur util est créé
        Utilisateur util = new Utilisateur("Durand", "Eric", "jd@jd.com", "M");
        // then:  util n'a pas d'activité
        assertEquals("Un nouvel Utilisateur n'a pas d'activité" , 0, util.getActivites().size());
    }

}
