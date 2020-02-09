
import edu.duke.FileResource;

import java.util.*;

public class MarkovWord implements IMarkovModel{
    private int myOrder;
    private Random myRandom;
    private String[] myText;

    public MarkovWord(int order){
        this.myOrder = order;
        this.myRandom = new Random();
    }

    @Override
    public void setTraining(String text) {
        this.myText = text.split("\\s+");
    }

    @Override
    public void setRandom(int seed) {
        this.myRandom = new Random(seed);
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - this.myOrder);  // random word to start with
        WordGram key = new WordGram(this.myText, index, this.myOrder);

        sb.append(key);
        sb.append(" ");

        for(int k=0; k < numWords - this.myOrder; k++){
            List<String> follows = getFollows(key);

            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);

            key = key.shiftAdd(next);
            sb.append(next);
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public int indexOf(String[] source, WordGram target, int start){
        String[] arrayC = new String[target.length()];
        for (int k = start; k < source.length - (target.length() - 1); k++){
            System.arraycopy(source, k, arrayC, 0, target.length());

            WordGram temp = new WordGram(arrayC, 0, arrayC.length);
            if(target.equals(temp)){
                return k;
            }
        }

        return -1;
    }

    public List<String> getFollows(WordGram kGram){
        List<String> follows = new ArrayList<>();
        int index = indexOf(myText, kGram, 0);

        //TODO: getFollows unterminated
        while(index > -1 && (this.myText.length - kGram.length()) > index){
            follows.add(this.myText[index + kGram.length()]);
            index = indexOf(myText, kGram, index + 1);
        }

        return follows;
    }

    public static void main(String[] args) {
        MarkovWord f = new MarkovWord(3);
        f.setRandom(621);
        String s = new FileResource("../data/confucius.txt").asString();
        f.setTraining(s);

        System.out.println(f.getRandomText(50));
    }
}
