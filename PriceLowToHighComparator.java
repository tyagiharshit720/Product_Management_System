package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.Comparator;

public class PriceLowToHighComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}