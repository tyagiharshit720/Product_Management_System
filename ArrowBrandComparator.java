package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.Comparator;

public class ArrowBrandComparator implements Comparator<Shirt> {
    @Override
    public int compare(Shirt s1, Shirt s2) {
        // Comparing shirts based on their brand, specifically for "Arrow" brand
        return s1.getBrand().compareTo(s2.getBrand());
    }
}
