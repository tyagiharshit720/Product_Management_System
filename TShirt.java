package Collection_Framework.CollectionFrameWorkProjects.Project1;

public class TShirt extends Product {
    private String size;
    private String color;
    private String material;

    public TShirt(String productId, String brand, double price,
                  String size, String color, String material) {
        super(productId, "TShirt", brand, price);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[%s] %s - Size %s, %s, %s - ₹%.0f",
                productId, brand, size, color, material, price);
    }

    // Getters
    public String getSize() { return size; }
    public String getColor() { return color; }
    public String getMaterial() { return material; }
}