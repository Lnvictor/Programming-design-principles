import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel{
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }

        int index = myRandom.nextInt(myText.length() - 4);
        List<String> follows = new ArrayList<>();
        String key = this.myText.substring(index, index + 4);
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
        return "MarkovModel of order 4.";
    }
}
