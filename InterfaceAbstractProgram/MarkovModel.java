/**
* Generic model for Markov generator
*
*
* @author Victor
* @version 1.0
*
**/

import java.util.ArrayList;
import java.util.List;


public class MarkovModel extends AbstractMarkovModel{
    private int numChar;

    public MarkovModel(int n) {
        super();
        this.numChar = n;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }

        int index = myRandom.nextInt(myText.length() - this.numChar);
        List<String> follows = new ArrayList<>();
        String key = this.myText.substring(index, index + this.numChar);
        StringBuilder sb = new StringBuilder();
        sb.append(key);


        for(int k=0; k < numChars; k++){
            follows = getFollows(key);

            if(follows.size() == 0){
                break;
            }

            int next = this.myRandom.nextInt(follows.size());
            sb.append(follows.get(next));
            key = key.substring(1) + follows.get(next);
        }

        return sb.toString();
    }

    public String toString(){
        return ("MarkovModel of order "+ this.numChar + ".");
    }
}
