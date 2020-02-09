
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author Victor Pereira
 * @version 1.0
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] words, int start, String target){
		for (int k = start; k < words.length; k++){
			if (words[k].equals(target)){
				return k;
			}
		}
		return -1;
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int index = indexOf(this.myText,0, key);

	    while(index >= 0){
	    	if(index < myText.length - 1){
	    		follows.add(this.myText[index + 1]);
			}
	    	index = indexOf(myText, index + 1, key);
		}

	    return follows;
    }

    public void testIndexOf(){
    	String[] words = "This is just a test yes just a test".split("\\s+");
    	System.out.println(indexOf(words, 5, "test"));
	}

	public static void main(String[] args) {
		MarkovWordOne tester = new MarkovWordOne();
		tester.testIndexOf();
	}

}
