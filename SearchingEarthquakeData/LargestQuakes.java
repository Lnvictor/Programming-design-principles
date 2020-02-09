import edu.duke.*;
import java.util.*;

public class LargestQuakes {
	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int maxIndex = 0;
		
		for(int k = 1; k < data.size(); k++) {
			if(data.get(k).getMagnitude() > data.get(maxIndex).getMagnitude()) {
				maxIndex = k;
			}
		}
		
		return maxIndex;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany){
		ArrayList<QuakeEntry> answer = new ArrayList<>();
		ArrayList<QuakeEntry> copy = new ArrayList<>(data);
		
		for(int j = 0; j < howMany; j++) {
        	int maxIndex = 0;
        	for(int k = 1; k < copy.size(); k++) {
        		if(copy.get(k).getMagnitude() > copy.get(maxIndex).getMagnitude()) {
        			maxIndex = k;
        		}
        	}
        	
        	answer.add(copy.remove(maxIndex));
        }
		
		return answer; 
	}
	
	public void  findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		
		ArrayList<QuakeEntry> quakeData = parser.read(source);
		
//		for(QuakeEntry quake : quakeData) {
//			System.out.println(quake);
//		}
		
        System.out.println("read data for " + quakeData.size() + " quakes");
//        System.out.println("minIndex: " +indexOfLargest(quakeData) + " magnitude: "+
//        					quakeData.get(indexOfLargest(quakeData)).getMagnitude());
        for(QuakeEntry q : getLargest(quakeData, 5)) {
        	System.out.println(q);
        }
	}
	
	public static void main(String[] args) {
		LargestQuakes tester = new LargestQuakes();
		tester.findLargestQuakes();
	}
}
