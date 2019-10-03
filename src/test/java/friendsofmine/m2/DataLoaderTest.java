package friendsofmine.m2;

import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DataLoaderTest {

    private DataLoader dataLoader;

    @MockBean
    private ActiviteService activiteService;

    @MockBean
    private UtilisateurService utilisateurService;

    @Before
    public void setUp() {
        dataLoader = new DataLoader(activiteService, utilisateurService);
    }

    @Test
    public void testDataLoaderIsAnApplicationRunner(){
        // un DataLoader peut être construit à partir d'un ActiviteService et d'un UtilisateurService
        assertThat(dataLoader, is(notNullValue()));
        // un DataLoader est un ApplicationRunner
        assertTrue(dataLoader instanceof ApplicationRunner);
    }

    @Test
    public void testInitMethodCalls() {
        DataLoader spy = spy(dataLoader);
        // when: la méthode run est appelée
        spy.run(null);
        // then: la méthode initUtilisateurs() qui ajoute les utilisateurs en base est invoquée
        verify(spy).initUtilisateurs();
        // then: la méthode initActivites() qui ajoute les activités en base est invoquée
        verify(spy).initActivites();

    }
}
