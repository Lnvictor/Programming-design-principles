import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
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
            if(pos + 1 < myText.length())
                follows.add(myText.substring(pos + 1, pos + 2));
            pos++;
        }
        return follows;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }

        int index = myRandom.nextInt(myText.length() - 1);
        List<String> follows = new ArrayList<>();
        String key = this.myText.substring(index, index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(key);


        for(int k=0; k < numChars; k++){
            follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            sb.append(follows.get(this.myRandom.nextInt(follows.size())));
            key =sb.toString().substring(sb.length() - 1);
        }

        return sb.toString();
    }
}
