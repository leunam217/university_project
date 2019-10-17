package friendsofmine.m2;

import friendsofmine.m2.controllers.ActiviteController;
import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.services.ActiviteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ActiviteControllerTest {

    @MockBean
    private ActiviteService activiteService;

    private ActiviteController activiteController;

    @Before
    public void setUp() {
        activiteController = new ActiviteController();
        activiteController.setActiviteService(activiteService);
    }

    @Test
    public void testControllerDelegationToService() {
        // when: on récupère dans le contrôleur la liste des activités
        activiteController.findAllActvitesWithResponsable();

        // then: la requête est traitée par le service correspondant
        verify(activiteService).findAllActivites();
    }

}
