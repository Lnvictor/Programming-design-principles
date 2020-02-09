import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {


    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        String aux = o1.getInfo().substring(o1.getInfo().length() - 1);
        int temp = o1.getInfo().substring(o1.getInfo().length() - 1).compareTo(
                    o2.getInfo().substring(o2.getInfo().length() - 1));

        if(temp != 0){
            return temp;
        }

        if(o1.getMagnitude() > o2.getMagnitude()){
            return 1;
        }
        if(o1.getMagnitude() < o2.getMagnitude()){
            return -1;
        }
        return 0;
    }
}
