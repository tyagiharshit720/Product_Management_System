package Collection_Framework.CollectionFrameWorkProjects.Project1;

public abstract class Product {
    protected String productId;
    protected String category;
    protected String brand;
    protected double price;

    public Product(String productId, String category, String brand, double price) {
        this.productId = productId;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public abstract String getDisplayDetails();

    public String getProductId() {
        return productId;
    }
    public String getCategory() {
        return category;
    }
    public String getBrand() {
        return brand;
    }
    public double getPrice() {
        return price;
    }
}
