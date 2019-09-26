package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
public class ActiviteTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTitreNonVideEtDescrptif() {
        // given: une Activite act avec un titre et un descriptif valides
        // when: act est créé
        Activite act = new Activite("unTitre", "unDescriptif");
        // then: act est validé par le validator
        assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifVide() {
        // given: une Activite act avec un titre et un descriptif vide
        // when: act est créé
        Activite act = new Activite("unTitre", "");
        // then: act est validé par le validator
        assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNonVideEtDescriptifNull() {
        // given: une Activite act avec un titre et un descriptif null
        // when: act est créé
        Activite act = new Activite("unTitre", null);
        // then: act est validé par le validator
        assertTrue(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreVide() {
        // given: une Activite act avec un titre vide et un descriptif
        // when: act est créé
        Activite act = new Activite("", "unDescriptif");
        // then: act n'est pas validé par le validator
        assertFalse(validator.validate(act).isEmpty());
    }

    @Test
    public void testTitreNull() {
        // given: une Activite act avec un titre null et un descriptif
        // when: act est créé
        Activite act = new Activite(null, "unDescriptif");
        // then: act n'est pas validé par le validator
        assertFalse(validator.validate(act).isEmpty());
    }
}
