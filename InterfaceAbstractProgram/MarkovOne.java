import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel{
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

    public String toString(){
        return "MarkovModel of order 1.";
    }
}
