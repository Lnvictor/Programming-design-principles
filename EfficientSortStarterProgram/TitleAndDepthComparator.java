import java.util.*;


public class TitleAndDepthComparator implements Comparator<QuakeEntry> {


    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        int temp = o1.getInfo().compareTo(o2.getInfo());

        if(temp !=0){
            return temp;
        }

        return Double.compare(o1.getDepth(), o2.getDepth());
    }
}
