package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.services.ActiviteService;
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
public class ActiviteServiceTest {

    private ActiviteService activiteService;

    @MockBean
    private ActiviteRepository activiteRepository;

    @Before
    public void setup() {
        activiteService = new ActiviteService();
        activiteService.setActiviteRepository(activiteRepository);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un ActiviteService est de type CrudRepository
        assertThat(activiteService.getActiviteRepository(), instanceOf(CrudRepository.class));
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
    public void testSaveFromCrudRepositoryIsInvokedWhenActiviteIsSaved() {
        // given: un ActiviteService et une Activite
        Activite activite = new Activite("Truc", "Description du truc");
        // when: la méthode saveActivite est invoquée
        activiteService.saveActivite(activite);
        // then: la méthode save du Repository associé est invoquée
        verify(activiteService.getActiviteRepository()).save(activite);
    }

}