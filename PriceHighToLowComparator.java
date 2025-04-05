package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.Comparator;

public class PriceHighToLowComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p2.getPrice(), p1.getPrice()); // Reverse order
    }
}