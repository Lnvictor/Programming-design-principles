
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    public void setRandom(int seed){ this.myRandom = new Random(seed); }
 
    abstract public String getRandomText(int numChars);

    abstract public String toString();

    protected List<String> getFollows(String key){
        List<String> follows = new ArrayList<>();
        int pos = 0;

        while(myText.indexOf(key, pos) >= 0){
            pos = myText.indexOf(key, pos);

            if (pos + key.length() < myText.length())
                follows.add(myText.substring(pos + key.length(), pos + key.length() + 1));
            pos += +key.length();
        }
        return follows;
    }

}
