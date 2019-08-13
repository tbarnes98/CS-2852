package Week1;

import java.util.Iterator;
import java.util.List;

public class Exam1Review {


    private static double findMax(List<Double> listOfDoubles) {
        double max = 0.0;
        //could also use foreach
        Iterator<Double> it = listOfDoubles.iterator();
        while(it.hasNext()){
            max = Math.max(max,it.next());
        }
        return max;
    }
}
