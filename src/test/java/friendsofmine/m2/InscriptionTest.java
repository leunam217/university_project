package friendsofmine.m2;

import friendsofmine.m2.domain.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;

@RunWith(SpringRunner.class)
public class InscriptionTest {

    private static Validator validator;

    private Date date;
    private Utilisateur util, resp;
    private Activite act;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setupData() {
        date = new Date();
        util = new Utilisateur("Dupuis", "Bernard", "bernie@domain.com", "M");
        resp = new Utilisateur("Dupond", "Sofia", "sof@domain.com", "F");
        act = new Activite("Chant", "Cours particulier uniquement", resp);
    }

    @Test
    public void testActiviteEtParticipantEtDateNonNull() {
        // given: une Inscription avec un utilisateur, une activité, une date valide
        // when: l'Inscription est créé
        Inscription inscription = new Inscription();
        inscription.setActivite(act);
        inscription.setParticipant(util);
        inscription.setDateInscription(date);
        // then: l'Inscription est validé par le validator
        assertTrue(validator.validate(inscription).isEmpty());
    }


    @Test
    public void testParticipantNull() {
        // given: une Inscription avec un utilisateur null
        // when: l'Inscription est créé
        Inscription inscription = new Inscription();
        inscription.setActivite(act);
        inscription.setParticipant(null);
        inscription.setDateInscription(date);
        // then: l'Inscription n'est pas validé par le validator
        assertFalse(validator.validate(inscription).isEmpty());
    }

    @Test
    public void testActiviteNull() {
        // given: une Inscription avec une activité null
        // when: l'Inscription est créé
        Inscription inscription = new Inscription();
        inscription.setActivite(null);
        inscription.setParticipant(util);
        inscription.setDateInscription(date);
        // then: l'Inscription n'est pas validé par le validator
        assertFalse(validator.validate(inscription).isEmpty());
    }

    @Test
    public void testDateInscriptionNull() {
        // given: une Inscription avec une date null
        // when: l'Inscription est créé
        Inscription inscription = new Inscription();
        inscription.setActivite(act);
        inscription.setParticipant(util);
        inscription.setDateInscription(null);
        // then: l'Inscription est validé par le validator
        assertTrue(validator.validate(inscription).isEmpty());
    }
}