package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.Comparator;

public class BrandComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getBrand().compareToIgnoreCase(p2.getBrand());
    }
}
