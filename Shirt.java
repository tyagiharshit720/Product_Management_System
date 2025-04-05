package Collection_Framework.CollectionFrameWorkProjects.Project1;

public class Shirt extends Product {
    private int size;
    private String color;
    private String sleeveType;

    public Shirt(String productId, String brand, double price, String category, int size, String color, String sleeveType) {
        super(productId, category, brand, price);
        this.size = size;
        this.color = color;
        this.sleeveType = sleeveType;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[Shirt] ID: %s | Brand: %s | Size: %d | Color: %s | Sleeve: %s | Price: ₹%.2f",
                productId, brand, size, color, sleeveType, price);
    }

    public int getSize() {
        return size;
    }


    public String getColor() {
        return color;
    }

    public String getSleeveType() {
        return sleeveType;
    }
}
