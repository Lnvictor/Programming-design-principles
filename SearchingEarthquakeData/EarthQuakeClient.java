import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        
        for (QuakeEntry quake : quakeData) {
        	if (quake.getMagnitude() > magMin) {
        		answer.add(quake);
        	}
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO

        for (QuakeEntry quake : quakeData) {
        	if(quake.getLocation().distanceTo(from) < distMax) {
        		answer.add(quake);
        	}
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    										double minDepth, double maxDepth){
    	
    	ArrayList<QuakeEntry> answer = new ArrayList<>(); 
    	
    	for (QuakeEntry quake : quakeData) {
    		if(quake.getDepth() > minDepth && quake.getDepth() < maxDepth) {
    			answer.add(quake);
    		}
    	}
    	
    	return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    											String where, String phrase){
    	
    	ArrayList<QuakeEntry> answer = new ArrayList<>();
    	
    	for(QuakeEntry quake: quakeData) {
    		if (quake.getInfo().indexOf(phrase) == 0 && where.equals("start")) {
    			answer.add(quake);
    		}
    		else if(quake.getInfo().indexOf(phrase) == quake.getInfo().length() - phrase.length()
    				&& where.equals("end")) {
    			answer.add(quake);
    		}
    		else if(quake.getInfo().contains(phrase) && where.equals("any")){
    			answer.add(quake);
    		}
    	}
    	
    	return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = filterByMagnitude(parser.read(source), 5.0);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000000, city);
        
        System.out.println("read data for " + list.size() + " quakes");
        for(QuakeEntry q : answer) {
        	System.out.println(q.getLocation().distanceTo(city) + " " +q.getInfo());
        }
        System.out.println("Found " + answer.size() + " quakes matching that criteria");
    }
    
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	ArrayList<QuakeEntry> quakes = filterByDepth(list, -10000.0, -8000.0);
    	
    	System.out.println("read data for " + list.size() + " quakes");
        
    	for(QuakeEntry q : quakes) {
        	System.out.println(q);
        }
        
    	System.out.println("Found " + quakes.size() + " quakes matching that criteria");
    }
    
    public void  quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	ArrayList<QuakeEntry> quakes = filterByPhrase(list, "end", "California");
    	
    	System.out.println("read data for " + list.size() + " quakes");
        
    	for(QuakeEntry q : quakes) {
        	System.out.println(q);
        }
        
    	System.out.println("Found " + quakes.size() + " quakes matching that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public static void main(String[] args) {
		EarthQuakeClient tester = new EarthQuakeClient();
		tester.bigQuakes();
	}
    
}
