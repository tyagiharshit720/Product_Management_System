package Collection_Framework.CollectionFrameWorkProjects.Project1;

public class Mobile extends Product {
    private String model;
    private int ram;
    private int storage;

    public Mobile(String productId, String brand, double price,
                  String model, int ram, int storage) {
        super(productId, "Mobile", brand, price);
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[%s] %s %s - %dGB/%dGB - ₹%.0f",
                productId, brand, model, ram, storage, price);
    }

    // Getters
    public String getModel() { return model; }
    public int getRam() { return ram; }
    public int getStorage() { return storage; }
}