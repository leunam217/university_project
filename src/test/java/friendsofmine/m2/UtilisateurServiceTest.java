package friendsofmine.m2;

import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.UtilisateurRepository;
import friendsofmine.m2.services.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UtilisateurServiceTest {

    private UtilisateurService utilisateurService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Before
    public void setup() {
        utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurRepository(utilisateurRepository);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un UtilisateurService est de type CrudRepository
        assertThat(utilisateurService.getUtilisateurRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void testFindByIdFromCrudRepositoryIsInvokedWhenUtilisateurIsFoundById() {
        // given: un UtilisateurService
        // when: la méthode findUtilisateurById est invoquée
        utilisateurService.findUtilisateurById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(utilisateurService.getUtilisateurRepository()).findById(0L);
    }

    @Test
    public void testSaveFromCrudRepositoryIsInvokedWhenUtilisateurIsSaved() {
        // given: un UtilisateurService et un Utilisateur
        Utilisateur util = new Utilisateur("Hammond", "Missy", "missy@ts.com", "F");
        // when: la méthode saveUtilisateur est invoquée
        utilisateurService.saveUtilisateur(util);
        // then: la méthode save du Repository associé est invoquée
        verify(utilisateurService.getUtilisateurRepository()).save(util);
    }

}