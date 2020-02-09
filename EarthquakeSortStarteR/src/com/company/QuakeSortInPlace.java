package com.company;

/**
 * this a Sorting algorithms implementations applied for earthquakes database
 * 
 * @author Victor Pereira
 * @version 04/02/2020
 */

import java.util.ArrayList;

public class QuakeSortInPlace {

    public QuakeSortInPlace(){
        //Default Constructor
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int k = 1; k < quakes.size(); k++){
            if(quakes.get(k).getMagnitude() < quakes.get(k - 1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int minIndex = from;

        for(int k = from + 1;  k < quakeData.size(); k++){
            if (quakeData.get(k).getDepth() > quakeData.get(minIndex).getDepth()){
                minIndex = k;
            }
        }

        return minIndex;
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSort){
        int cnumSort = new Integer(numSort);

        for(int k = 0; k < quakeData.size() - numSort - 1; k++){
            if(quakeData.get(k).getMagnitude() > quakeData.get(k+1).getMagnitude()){
                QuakeEntry temp = quakeData.get(k);
                quakeData.set(k, quakeData.get(k+1));
                quakeData.set(k + 1, temp);
            }
        }
        System.out.println("step: "+ (cnumSort + 1));
        for (QuakeEntry q : quakeData){
            System.out.println(q);
        }
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){

        for(int k = 0; k < in.size(); k++){
            int maxIndex = getLargestDepth(in, k);
            QuakeEntry temp = in.get(k);
            in.set(k, in.get(maxIndex));
            in.set(maxIndex, temp);
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {

        for (int k = 0; k < in.size() - 1; k++) {
            onePassBubbleSort(in, k);
        }
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {

        for (int k = 0; k < in.size() - 1; k++) {
            onePassBubbleSort(in, k);
            if (checkInSortedOrder(in)){
                break;
            }
        }
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);

            System.out.println("step: "+ (i + 1));
            for (QuakeEntry q : in){
                System.out.println(q);
            }

            if(checkInSortedOrder(in)) {
                break;
            }

        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");
        sortByMagnitudeWithBubbleSortWithCheck(list);
//        for (QuakeEntry qe: list) {
//            System.out.println(qe);
//        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }

	}

    public static void main(String[] args) {
        QuakeSortInPlace tester = new QuakeSortInPlace();
        tester.testSort();
    }
}
