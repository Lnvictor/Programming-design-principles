
/**
 * Its a generalization of MarkovGenerator for greather usability
 * 
 * @author Victor Pereira
 * @version 1.0
 */

public interface IMarkovModel {
    public void setTraining(String text);

    public void setRandom(int seed);

    public String getRandomText(int numChars);

    public String toString();
}
