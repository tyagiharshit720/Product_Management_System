package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.Comparator;

public class PriceComparatorInAscendingOrder implements Comparator<Shirt> {

    @Override
    public int compare(Shirt s1, Shirt s2) {
        // Comparing shirts based on their price in ascending order
        return Double.compare(s1.getPrice(), s2.getPrice());
    }
}
