import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO make a predict for a n characters

public class MarkovModel {
    private int numChar;
    private String myText;
    private Random myRandom;

    public MarkovModel(int n) {
        this.numChar = n;
        this.myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        myText.replace("\n", " ");
    }

    public List<String> getFollows(String key){
        List<String> follows = new ArrayList<>();
        int pos = 0;

        while(myText.indexOf(key, pos) >= 0){
            pos = myText.indexOf(key, pos);

            if (pos + 1 < myText.length())
                follows.add(myText.substring(pos + this.numChar, pos + this.numChar + 1));
            pos += 1;
        }
        return follows;
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
}
