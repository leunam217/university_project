package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ActiviteServiceIntegrationTest {

    @Autowired
    private ActiviteService activiteService;

    private Activite act;
    private Utilisateur utilisateur;

    @Before
    public void setup() {
        utilisateur = new Utilisateur("unNom", "unPrenom", "ttt@tttt.fr", "F");
        act = new Activite("titre", "descriptif", utilisateur);
    }

    @Test
    public void testSavedActiviteHasId(){
        // given: une Activite non persistée act
        // then: act n'a pas d'id
        assertNull(act.getId());
        // when: act est persistée
        activiteService.saveActivite(act);
        // then: act a un id
        assertNotNull(act.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveActiviteNull(){
        // when: null est persisté via un ActiviteService
        // then: une exception IllegalArgumentException est levée
        activiteService.saveActivite(null);
    }

    @Test
    public void testFetchedActiviteIsNotNull() {
        // given: une Activite persistée act
        activiteService.saveActivite(act);
        // when: on appelle findActiviteById avec l'id de cette Activite
        Activite fetched = activiteService.findActiviteById(act.getId());
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedActiviteIsUnchangedForDescriptif() {
        // given: une Activite persistée act
        activiteService.saveActivite(act);
        // when: on appelle findActiviteById avec l'id de cette Activite
        Activite fetched = activiteService.findActiviteById(act.getId());
        // then: l'Activite obtenue en retour a le bon id
        assertEquals(fetched.getId(), act.getId());
        // then : l'Activite obtenue en retour a le bon descriptif
        assertEquals(fetched.getDescriptif(), act.getDescriptif());
    }

    @Test
    @Transactional
    public void testUpdatedActiviteIsUpdated() {
        // given: une Activite persistée act
        activiteService.saveActivite(act);

        Activite fetched = activiteService.findActiviteById(act.getId());
        // when: le descriptif est modifié au niveau "objet"
        fetched.setDescriptif("Nouvelle description");
        // when: l'objet act est mis à jour en base
        activiteService.saveActivite(fetched);
        // when: l'objet act est relu en base
        Activite fetchedUpdated = activiteService.findActiviteById(act.getId());
        // then: le descriptif a bien été mis à jour
        assertEquals(fetched.getDescriptif(), fetchedUpdated.getDescriptif());
    }

    @Test
    @Transactional
    public void testUpdateDoesNotCreateANewEntry() {
        // given: une Activite persistée act
        activiteService.saveActivite(act);
        long count = activiteService.countActivite();
        Activite fetched = activiteService.findActiviteById(act.getId());
        // when: le descriptif est modifié au niveau "objet"
        fetched.setDescriptif("Nouvelle description");
        // when: l'objet act est mis à jour en base
        activiteService.saveActivite(fetched);
        // then: une nouvelle entrée n'a pas été créée en base
        assertEquals(count, activiteService.countActivite());
    }

    @Test
    public void testFindActiviteWithUnexistingId() {
        // when:  findActiviteById est appelé avec un id ne correspondant à aucun objet en base
        // then: null est retourné
        assertNull(activiteService.findActiviteById(1000L));
    }

    @Test
    public void testSaveActiviteWithNewUtilisateur() {
        // given: une Activite non persistée act
        // when: act est persistée
        activiteService.saveActivite(act);
        // then: son responsable est persisté aussi
        assertNotNull(act.getResponsable().getId());
        // then: act est ajouté à la liste des activités du responsable
        assertTrue(act.getResponsable().getActivites().contains(act));
    }

    @Test
    public void testSaveActiviteWithAlreadySavedUtilisateur() {
        // given: une Activite et un Utilisateur non persistés act
        // when: l'Utilisateur est persisté
        activiteService.getUtilisateurRepository().save(utilisateur);
        // when: act est persistée
        activiteService.saveActivite(act);
        // then: act a un id
        assertNotNull(act.getId());
        // then: act est ajouté à la liste des activités du responsable
        assertTrue(act.getResponsable().getActivites().contains(act));
    }

    @Test
    public void testAnActiviteIsOnlyAddedOnceToTheResponsable() {
        long countActiviteResponsable = utilisateur.getActivites().size();
        Activite act1 = new Activite("uneActivite", "unDescriptif", utilisateur);
        activiteService.saveActivite(act1);
        assertEquals(countActiviteResponsable + 1, utilisateur.getActivites().size());
        activiteService.saveActivite(act1);
        assertEquals(countActiviteResponsable + 1, utilisateur.getActivites().size());
    }

    @Test
    public void testFindAllActiviteFromDataLoaderCardinal() {
        // given: un DataLoader initialisant la base à 7 activités
        // when: la liste des activités est récupérée
        ArrayList<Activite> activites = activiteService.findAllActivites();
        // then: il y a 7 activités dedans
        assertEquals("Nombre d'activités présentes dans le DataLoader récupérées dans le service",
                7, activites.size());
    }

    @Test
    public void testFindAllActiviteFromDataLoaderAreSortedByTitre() {
        // given: un DataLoader initialisant la base des Activite
        // when: la liste des activités est récupérée
        ArrayList<Activite> activites = activiteService.findAllActivites();
        // then: la liste est triée selon le Titre des Activites
        assertTrue("les éléments 0 et 1 de la liste sont bien triés",
                (activites.get(0).getTitre()).compareTo(activites.get(1).getTitre()) < 0);
        assertTrue("les éléments 1 et 2 de la liste sont bien triés",
                (activites.get(1).getTitre()).compareTo(activites.get(2).getTitre()) < 0);
        assertTrue("les éléments 2 et 3 de la liste sont bien triés",
                (activites.get(2).getTitre()).compareTo(activites.get(3).getTitre()) < 0);
        assertTrue("les éléments 3 et 4 de la liste sont bien triés",
                (activites.get(3).getTitre()).compareTo(activites.get(4).getTitre()) < 0);
        assertTrue("les éléments 4 et 5 de la liste sont bien triés",
                (activites.get(4).getTitre()).compareTo(activites.get(5).getTitre()) < 0);
        assertTrue("les éléments 5 et 6 de la liste sont bien triés",
                (activites.get(5).getTitre()).compareTo(activites.get(6).getTitre()) < 0);
    }


}
