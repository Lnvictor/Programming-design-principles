
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
    	markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 38);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 38);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 38);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 38);

    }

    public void testHashMap(){
    	EfficientMarkovModel m = new EfficientMarkovModel(2);
    	m.setRandom(42);
    	m.setTraining("yes­this­is­a­thin­pretty­pink­thistle");
    	String r  = m.getRandomText(50);
    	m.printHashMapInfo();
	}

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void compareMethods(){
    	FileResource f = new FileResource("../data/hawthorne.txt");

    	MarkovModel m1 = new MarkovModel(2);
    	EfficientMarkovModel m2 = new EfficientMarkovModel(2);
    	m1.setTraining(f.asString());
		m2.setTraining(f.asString());

    	m1.setRandom(42);
		m2.setRandom(42);

		double start = System.nanoTime();
		m1.getRandomText(100);
		double end = System.nanoTime();
		System.out.print("Markov:" + ((end - start) / 100000000) + "\t");


		start = System.nanoTime();
		m2.getRandomText(100);
		end = System.nanoTime();
		System.out.print("EfiicientMarkov:" + ((end - start) / 100000000) + "\n");

	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface t = new MarkovRunnerWithInterface();
		FileResource f = new FileResource("../data/romeo.txt");
		t.runModel(new EfficientMarkovModel(5), f.asString(), 200, 615);
	}
	
}
