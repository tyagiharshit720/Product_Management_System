package Collection_Framework.CollectionFrameWorkProjects.Project1;


public class Laptop extends Product {
    private String model;
    private String processor;
    private int ram;
    private int storage;

    public Laptop(String productId, String brand, double price,
                  String model, String processor, int ram, int storage) {
        super(productId, "Laptop", brand, price);
        this.model = model;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public String getDisplayDetails() {
        return String.format("[%s] %s %s - %s, %dGB/%dGB - ₹%.0f",
                productId, brand, model, processor, ram, storage, price);
    }

    // Getters
    public String getModel() { return model; }
    public String getProcessor() { return processor; }
    public int getRam() { return ram; }
    public int getStorage() { return storage; }
}