import java.util.*;

public class WordGramTester {
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}

	public void testAddShift(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		WordGram o1 = new WordGram(words, 0, words.length);

		WordGram o2 = o1.shiftAdd("Vitao");

		System.out.println(o1.wordAt(o1.length() - 1));
		System.out.println(o2.wordAt(o2.length() - 1));
	}

	public static void main(String[] args) {
		WordGramTester f = new WordGramTester();
		f.testAddShift();
	}
	
}
