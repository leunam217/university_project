package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.repositories.UtilisateurRepository;
import friendsofmine.m2.services.ActiviteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ActiviteServiceTest {

    private ActiviteService activiteService;

    @MockBean
    private ActiviteRepository activiteRepository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @MockBean
    private Activite activite;

    @MockBean
    private Utilisateur utilisateur;

    @Before
    public void setup() {
        activiteService = new ActiviteService();
        activiteService.setActiviteRepository(activiteRepository);
        activiteService.setUtilisateurRepository(utilisateurRepository);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un ActiviteService est de type CrudRepository
        assertThat(activiteService.getActiviteRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void testSaveFromCrudRepositoryIsInvokedWhenActiviteIsSaved() {
        // given: un ActiviteService et une Activite
        Activite activite = new Activite("Truc", "Description du truc", utilisateur);
        when(activiteService.getUtilisateurRepository().save(utilisateur)).thenReturn(utilisateur);
        when(activiteService.getActiviteRepository().save(activite)).thenReturn(activite);
        // when: la méthode saveActivite est invoquée
        activiteService.saveActivite(activite);
        // then: la méthode save du ActiviteRepository associé est invoquée
        verify(activiteService.getActiviteRepository()).save(activite);
        // then: la méthode save du UtilisateurRepository associé n'est pas invoquée (on requiert ainsi l'utilisation d'une cascade)
        verify(activiteService.getUtilisateurRepository(), never()).save(utilisateur);
    }

    @Test
    public void testFindByIdFromCrudRepositoryIsInvokedWhenActiviteIsFoundById() {
        // given: un ActiviteService
        // when: la méthode findActiviteById est invoquée
        activiteService.findActiviteById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(activiteService.getActiviteRepository()).findById(0L);
    }

    @Test
    public void testFindAllFromCrudRepositoryIsInvokedWhenFindAllActivite() {
        // given: un ActiviteService
        // when: la méthode findAllActivite est invoquée
        activiteService.findAllActivites();
        // then: la méthode findAll du Repository associé est invoquée
        verify(activiteService.getActiviteRepository()).findAll();
    }

}
