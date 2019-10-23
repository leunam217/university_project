package friendsofmine.m2;

import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.UtilisateurService;
import friendsofmine.m2.util.TransactionalRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UtilisateurServiceIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionalRunner txRunner;

    @Autowired
    private DataLoader dataLoader;

    @Autowired
    private UtilisateurService utilisateurService;

    private Utilisateur util;

    @Before
    public void setup() {
        util = new Utilisateur("nom", "prenom", "toto@toto.fr", "F");
    }

    @Test
    public void testSavedUtilisateurHasId(){
        // given: un Utilisateur non persisté util
        // then: util n'a pas d'id
        assertNull(util.getId());
        // when: util est persistée
        utilisateurService.saveUtilisateur(util);
        // then: util a id
        assertNotNull(util.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveUtilisateurNull(){
        // when: null est persisté via un UtilisateurService
        // then: une exception IllegalArgumentException est levée
        utilisateurService.saveUtilisateur(null);
    }

    @Test
    public void testFetchedUtilisateurIsNotNull() {
        // given: un Utilisateur persisté util
        utilisateurService.saveUtilisateur(util);
        // when: on appelle findUtilisateurById avec l'id de cet Utilisateur
        Utilisateur fetched = utilisateurService.findUtilisateurById(util.getId());
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedUtilisateurHasGoodId() {
        // given: un Utilisateur persisté util
        utilisateurService.saveUtilisateur(util);
        // when: on appelle findUtilisateurById avec l'id de cet Utilisateur
        Utilisateur fetched = utilisateurService.findUtilisateurById(util.getId());
        // then: l'Utilisateur obtenu en retour a le bon id
        assertEquals(util.getId(), fetched.getId());
    }

    @Test
    public void testFetchedUtilisateurIsUnchanged() {
        // given: un Utilisateur persisté util
        utilisateurService.saveUtilisateur(util);
        // when: on appelle findUtilisateurById avec l'id de cet Utilisateur
        Utilisateur fetched = utilisateurService.findUtilisateurById(util.getId());
        // then: les attributs de l'Utilisateur obtenu en retour a les bonnes valeurs
        assertEquals(util.getNom(), fetched.getNom());
        assertEquals(util.getPrenom(), fetched.getPrenom());
        assertEquals(util.getEmail(), fetched.getEmail());
        assertEquals(util.getSexe(), fetched.getSexe());
    }

    @Test
    public void testUpdatedUtilisateurIsUpdated() {
        // given: un Utilisateur persisté util
        utilisateurService.saveUtilisateur(util);

        Utilisateur fetched = utilisateurService.findUtilisateurById(util.getId());
        // when: l'email est modifié au niveau "objet"
        fetched.setEmail("tyty@tyty.fr");
        // when: l'objet util est mis à jour en base
        utilisateurService.saveUtilisateur(fetched);
        // when: l'objet util est relu en base
        Utilisateur fetchedUpdated = utilisateurService.findUtilisateurById(util.getId());
        // then: l'email a bien été mis à jour
        assertEquals(fetched.getEmail(), fetchedUpdated.getEmail());
    }

    @Test
    public void testSavedUtilisateurIsSaved() {
        long before = utilisateurService.countUtilisateur();
        // given: un nouvel Utilisateur
        // when: cet Utilisateur est persisté
        utilisateurService.saveUtilisateur(new Utilisateur("john", "john", "john@john.fr", "M"));
        // le nombre d'Utilisateur persisté a augmenté de 1
        assertEquals(before + 1, utilisateurService.countUtilisateur());
    }

    @Test
    public void testUpdateDoesNotCreateANewEntry() {
        // given: un Utilisateur persisté util
        utilisateurService.saveUtilisateur(util);
        long count = utilisateurService.countUtilisateur();

        Utilisateur fetched = utilisateurService.findUtilisateurById(util.getId());
        // when: l'email est modifié au niveau "objet"
        fetched.setEmail("titi@titi.fr");
        // when: l'objet est mis à jour en base
        utilisateurService.saveUtilisateur(fetched);
        // then: une nouvelle entrée n'a pas été créée en base
        assertEquals(count, utilisateurService.countUtilisateur());
    }

    @Test
    public void testFindUtilisateurWithUnexistingId() {
        // when:  findUtilisateurById est appelé avec un id ne correspondant à aucun objet en base
        // then: null est retourné
        assertNull(utilisateurService.findUtilisateurById(1000L));
    }

    @Test
    public void testUtilisateurAreVersioned() {
        // given: un utilisateur fraîchement ajouté en base et positionné dans le contexte de persistance
        Utilisateur utilisateur = entityManager.merge(dataLoader.getThom());

        // when: on consulte le numéro de version de l'utilisateur
        // then: l'utilisateur a le numéro de version 0
        assertThat(utilisateur.getVersion(), is(0L));
        // when: on modifie l'utilisateur en base
        utilisateur.setEmail("new@mail.ru");
        utilisateurService.saveUtilisateur(utilisateur);
        entityManager.flush();
        // then: l'utilisateur a le numéro de version 1
        assertThat(utilisateur.getVersion(), is(1L));

    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingOnConcurrentUtilisateurModification2() {
        txRunner.doInTransaction(em -> {
            em.persist(util);
        });

        txRunner.doInTransaction(em1 -> {
            Utilisateur u1 = em1.find(Utilisateur.class, util.getId());
            txRunner.doInTransaction(em2 -> {
                Utilisateur u2 = em2.find(Utilisateur.class, util.getId());
                u2.setEmail("new2@mail.ru");
            });
            u1.setEmail("new1@mail.ru");
        });
    }

    @Test(timeout = 7000)
    public void testUtilisateurCanBeFetchedInLessThan7Seconds() {
        // when: les utilisateurs thom et karen sont récupérés en base
        utilisateurService.findUtilisateurByEmail("thom@rh.com");
        utilisateurService.findUtilisateurByEmail("karen@yyy.com");
        // when: on répète une demande d'accès à thom et karen
        utilisateurService.findUtilisateurByEmail("thom@rh.com");
        utilisateurService.findUtilisateurByEmail("karen@yyy.com");
        utilisateurService.findUtilisateurByEmail("thom@rh.com");
        utilisateurService.findUtilisateurByEmail("thom@rh.com");
        // then: les 6 requêtes sont exécutées en moins de 7 secondes
    }

}
