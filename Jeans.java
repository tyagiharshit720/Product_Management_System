package Collection_Framework.CollectionFrameWorkProjects.Project1;

public class Jeans extends Product {
    private int waistSize;
    private int length;
    private String color;
    private String fit;

    public Jeans(String productId, String brand, double price,
                 int waistSize, int length, String color, String fit) {
        super(productId, "Jeans", brand, price);
        this.waistSize = waistSize;
        this.length = length;
        this.color = color;
        this.fit = fit;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[%s] %s - %dW/%dL, %s, %s - ₹%.0f",
                productId, brand, waistSize, length, color, fit, price);
    }

    // Getters
    public int getWaistSize() { return waistSize; }
    public int getLength() { return length; }
    public String getColor() { return color; }
    public String getFit() { return fit; }
}