import edu.duke.FileResource;
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private int myOrder;
    private HashMap<WordGram, List<String>> followsMap;
    private Random myRandom;
    private String[] myText;

    public EfficientMarkovWord(int order){
        this.myOrder = order;
        this.myRandom = new Random();
        this.followsMap = new HashMap<>();
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

    public void buildMap(WordGram key){
        if(!this.followsMap.containsKey(key)){
            int index = indexOf(myText, key, 0);

            List<String> follows = new ArrayList<>();

            while(index > -1 && (this.myText.length - key.length()) > index){
                follows.add(this.myText[index + key.length()]);
                index = indexOf(myText, key, index + 1);
            }

            this.followsMap.put(key, follows);
        }
    }

    public List<String> getFollows(WordGram kGram){
        buildMap(kGram);
        return this.followsMap.get(kGram);
    }

    private void printHashMapInfo(){

        System.out.println(this.followsMap.size());

        WordGram largest = (WordGram) this.followsMap.keySet().toArray()[0];

        for(WordGram w : this.followsMap.keySet()){
            if(this.followsMap.get(w).size() > this.followsMap.get(largest).size()){
                largest = w;
            }
        }

        System.out.println("Largest size: " + largest.length());

        for(WordGram w : followsMap.keySet()){
            if(this.followsMap.get(w).size() == largest.length()){
                System.out.println(w);
            }
        }
    }

    public void compareMethods(){
        FileResource f = new FileResource("../data/hawthorne.txt");
        MarkovWord o1 = new MarkovWord(2);
        EfficientMarkovWord o2 = new EfficientMarkovWord(2);
        o1.setRandom(42);
        o2.setRandom(42);
        o1.setTraining(f.asString());
        o2.setTraining(f.asString());

        double start = System.nanoTime();
        o1.getRandomText(100000);
        double end = System.nanoTime();

        System.out.print(((end - start) / 100000000) + "\t");

        start = System.nanoTime();
        o2.getRandomText(100000);
        end = System.nanoTime();

        System.out.println(((end - start) / 100000000));
    }

    public static void main(String[] args) {
        EfficientMarkovWord f = new EfficientMarkovWord(2);
        f.setRandom(65);
        String s = new FileResource("../data/confucius.txt").asString();
        f.setTraining(s);
//
        String str = f.getRandomText(65);
        f.printHashMapInfo();
    }
}
