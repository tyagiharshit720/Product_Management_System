package Collection_Framework.CollectionFrameWorkProjects.Project1;


import java.util.Comparator;



public class PriceComparator implements Comparator<Product> {
    private boolean ascending;

    public PriceComparator(boolean ascending) {
        this.ascending = ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Product p1, Product p2) {
        if (ascending) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        } else {
            return Double.compare(p2.getPrice(), p1.getPrice());
        }
    }
}