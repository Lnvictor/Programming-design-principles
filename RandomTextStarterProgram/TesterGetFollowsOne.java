import edu.duke.FileResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TesterGetFollowsOne {

    private MarkovModel mark;

    @BeforeEach
    public void setUp(){
        mark = new MarkovModel(2);
    }

    @Test
    public void testGetFollows(){
        mark.setTraining("This is a test yes this is a test");
        Assertions.assertTrue(mark.getFollows("t").size() == 4);
    }

    @Test
    public void testGetFollowsWithFile(){
        FileResource f = new FileResource("data/melville.txt");
        String str = f.asString();
        mark.setTraining(str);

        Assertions.assertTrue(mark.getFollows("th").size() == 11548);
    }

    @AfterEach
    public void tearDown(){
        mark = null;
    }

}
