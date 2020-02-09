import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, int start, String target1, String target2){
        for (int k = start; k < words.length - 1; k++){
            if (words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key = myText[index];
        String key2 = myText[index + 1];

        sb.append(key);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");

        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key, key2);

            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");

            key = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(this.myText,0, key1, key2);

        while(index >= 0){
            if(index < myText.length - 2){
                follows.add(this.myText[index + 2]);
            }
            index = indexOf(myText, index + 2, key1, key2);
        }

        return follows;
    }

//    public void testIndexOf(){
//        String[] words = "This is just a test yes just a test".split("\\s+");
//        System.out.println(indexOf(words, 5, "test"));
//    }

    public static void main(String[] args) {
        MarkovWordOne tester = new MarkovWordOne();
        tester.testIndexOf();
    }

}
