package Collection_Framework.CollectionFrameWorkProjects.Project1;

public class Watch extends Product {
    private String model;
    private String type;
    private String bandMaterial;

    public Watch(String productId, String brand, double price,
                 String model, String type, String bandMaterial) {
        super(productId, "Watch", brand, price);
        this.model = model;
        this.type = type;
        this.bandMaterial = bandMaterial;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[%s] %s %s - %s, %s Band - ₹%.0f",
                productId, brand, model, type, bandMaterial, price);
    }

    // Getters
    public String getModel() { return model; }
    public String getType() { return type; }
    public String getBandMaterial() { return bandMaterial; }
}