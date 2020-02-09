import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_zh_TW;

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int numChar;
    public HashMap<String, List<String>> followsMap;

    public EfficientMarkovModel(int n) {
        super();
        this.numChar = n;
        this.followsMap = new HashMap<>();
    }

    private void buildMap(String key){
        if(!this.followsMap.containsKey(key)) {
            this.followsMap.put(key, super.getFollows(key));
        }
    }

    protected List<String> getFollows(String key){
        buildMap(key);
        return followsMap.get(key);
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

            if(follows.size() == 0)
                break;

            int next = this.myRandom.nextInt(follows.size());
            sb.append(follows.get(next));
            key = key.substring(1) + follows.get(next);
            printHashMapInfo();
        }

        return sb.toString();
    }

    public void printHashMapInfo(){
        //System.out.println(this.followsMap);
        System.out.println(this.followsMap.size());

        String key = "";
        for(String str : this.followsMap.keySet()){
            if(key.isEmpty()){
                key = str;
            }
            else if(followsMap.get(str).size() > followsMap.get(key).size()){
                key = str;
            }
        }

        System.out.println(followsMap.get(key).size());

        //for(List<String> l : followsMap.values()){
            //System.out.println(l);
        //}
    }

    public String toString(){
            return ("MarkovModel of order "+ this.numChar + ".");
        }
}
