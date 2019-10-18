package friendsofmine.m2;

import friendsofmine.m2.services.BatchService;
import friendsofmine.m2.services.UtilisateurService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchTest {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BatchService batchService;

    @Test
    public void testBatchAddWithFailure() {
        // given: un nombre "count" d'utilisateur en base
        long count = utilisateurService.countUtilisateur();

        // when: on essaie d'ajouter un lot d'utilisateur de manière atomique
        // when: l'ajout du lot échoue volontairement dans saveUsAll
        try {
            batchService.saveUsAll();
        } catch(Exception e) {

        }

        // then: aucun utilisateur du lot n'a été ajouté en base
        assertEquals(count , utilisateurService.countUtilisateur());
    }
}
