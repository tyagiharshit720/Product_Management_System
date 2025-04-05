package Collection_Framework.CollectionFrameWorkProjects.Project1;

import java.util.*;
import java.util.stream.Collectors;

public class ECommerceSystem {
    private final Map<String, Product> productInventory;
    private final Scanner scanner;
    private final PriceComparator priceComparator;
    private final BrandComparator brandComparator;
    private final Map<String, Product> shoppingCart;

    public ECommerceSystem() {
        this.productInventory = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.priceComparator = new PriceComparator(true);
        this.brandComparator = new BrandComparator();
        this.shoppingCart = new HashMap<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Shirts
        addProduct(new Shirt("SH001", "Blackberry", 2500, "Formal", 40, "Blue", "Full"));
        addProduct(new Shirt("SH002", "Van Heusen", 2800, "Formal", 42, "White", "Full"));
        addProduct(new Shirt("SH003", "Louis Philippe", 4500, "Formal", 40, "Blue", "Full"));
        addProduct(new Shirt("SH004", "Peter England", 2200, "Casual", 38, "Green", "Half"));
        addProduct(new Shirt("SH005", "Allen Solly", 3200, "Casual", 40, "Red", "Full"));

        // TShirts
        addProduct(new TShirt("TS001", "Puma", 1200, "M", "Black", "Cotton"));
        addProduct(new TShirt("TS002", "Nike", 1500, "L", "White", "Polyester"));
        addProduct(new TShirt("TS003", "Adidas", 1800, "XL", "Blue", "DryFit"));

        // Jeans
        addProduct(new Jeans("JN001", "Levi's", 3500, 32, 34, "Blue", "Slim"));
        addProduct(new Jeans("JN002", "Polo", 2800, 34, 32, "Black", "Regular"));

        // Watches
        addProduct(new Watch("WT001", "Titan", 8500, "Raga", "Analog", "Steel"));
        addProduct(new Watch("WT002", "Casio", 4500, "G-Shock", "Digital", "Rubber"));
        addProduct(new Watch("WT003", "Sonata", 7000, "s9", "Digital", "Steel"));


        // Mobiles
        addProduct(new Mobile("MB001", "Samsung", 25000, "Galaxy S21", 8, 128));
        addProduct(new Mobile("MB002", "Apple", 75000, "iPhone 13", 6, 256));

        // Laptops
        addProduct(new Laptop("LP001", "Dell", 65000, "XPS 13", "i7", 16, 512));
        addProduct(new Laptop("LP002", "HP", 45000, "Vivobook", "i5", 8, 256));
        addProduct(new Laptop("LP003", "HP", 35000, "Pavilion", "i5", 8, 256));
        addProduct(new Laptop("LP003", "Dell", 30000, "Ryzen", "i5", 8, 256));
        
    }

    // Core CRUD Operations
    public void addProduct(Product product) {
        productInventory.put(product.getProductId(), product);
    }

    public Product getProduct(String productId) {
        return productInventory.get(productId);
    }

    public boolean removeProduct(String productId) {
        return productInventory.remove(productId) != null;
    }

    // Display Operations
    public void displayAllProducts() {
        System.out.println("\n========== COMPLETE PRODUCT INVENTORY ==========");
        productInventory.values().forEach(product ->
                System.out.println(product.getDisplayDetails()));
        System.out.println("Total Products: " + productInventory.size());
    }

    public void displayProductsByCategory(String category) {
        System.out.println("\n=== " + category.toUpperCase() + " ===");
        List<Product> products = productInventory.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            System.out.println("No products found in this category.");
            return;
        }

        products.forEach(p -> System.out.println(p.getDisplayDetails()));
        System.out.println("Total " + category + "s: " + products.size());
    }

    // Sorting Operations
    public void displayProductsSortedByPrice(String category, boolean ascending) {
        priceComparator.setAscending(ascending);

        List<Product> products = productInventory.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .sorted(priceComparator)
                .collect(Collectors.toList());

        System.out.printf("\n=== %s SORTED BY PRICE (%s) ===\n",
                category.toUpperCase(), ascending ? "LOW TO HIGH" : "HIGH TO LOW");

        if (products.isEmpty()) {
            System.out.println("No products found in this category.");
            return;
        }

        products.forEach(p -> System.out.println(p.getDisplayDetails()));
    }

    public void displayProductsSortedByBrand(String category) {
        List<Product> products = productInventory.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .sorted(brandComparator)
                .collect(Collectors.toList());

        System.out.println("\n=== " + category.toUpperCase() + " SORTED BY BRAND ===");

        if (products.isEmpty()) {
            System.out.println("No products found in this category.");
            return;
        }

        String currentBrand = "";
        for (Product p : products) {
            if (!p.getBrand().equals(currentBrand)) {
                currentBrand = p.getBrand();
                System.out.println("\n" + currentBrand + ":");
            }
            System.out.println("  " + p.getDisplayDetails());
        }
    }

    // Brand Operations
    public void displayBrandStatistics() {
        Map<String, Long> brandCounts = productInventory.values().stream()
                .collect(Collectors.groupingBy(Product::getBrand, Collectors.counting()));

        System.out.println("\n=== BRAND STATISTICS ===");
        brandCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " products"));
    }

    // Category-specific Operations
    public void displayShirtSizeStatistics() {
        Map<Integer, Long> sizeCounts = productInventory.values().stream()
                .filter(p -> p instanceof Shirt)
                .map(p -> (Shirt) p)
                .collect(Collectors.groupingBy(Shirt::getSize, Collectors.counting()));

        System.out.println("\n=== SHIRT SIZE DISTRIBUTION ===");
        sizeCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.println("Size " + entry.getKey() + ": " + entry.getValue() + " shirts"));
    }

    // Customer Purchase Flow
    public void customerPurchaseFlow() {
        System.out.println("\n========== PRODUCT SELECTION ==========");

        // Show categories
        Set<String> categories = productInventory.values().stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        System.out.println("Available Categories:");
        categories.forEach(System.out::println);

        // Select category
        System.out.print("\nEnter category: ");
        String category = scanner.nextLine();

        // Show brands in category
        Set<String> brands = productInventory.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .map(Product::getBrand)
                .collect(Collectors.toSet());

        System.out.println("\nAvailable Brands:");
        brands.forEach(System.out::println);

        // Select brand
        System.out.print("\nEnter brand: ");
        String brand = scanner.nextLine();

        // Show matching products
        List<Product> products = productInventory.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        System.out.println("\nAvailable Products:");
        products.forEach(p -> {
            System.out.println("\nID: " + p.getProductId());
            System.out.println(p.getDisplayDetails());

            // Show type-specific details
            if (p instanceof Shirt) {
                Shirt s = (Shirt) p;
                System.out.println("Size: " + s.getSize() + " | Color: " + s.getColor());
            } else if (p instanceof Mobile) {
                Mobile m = (Mobile) p;
                System.out.println("RAM: " + m.getRam() + "GB | Storage: " + m.getStorage() + "GB");
            }
        });

        // Select product
        System.out.print("\nEnter Product ID to purchase: ");
        String productId = scanner.nextLine();

        Product selected = productInventory.get(productId);
        if (selected != null) {
            System.out.println("\nSelected Product:");
            System.out.println(selected.getDisplayDetails());

            System.out.println("\n1. Add to Cart");
            System.out.println("2. Buy Now");
            System.out.print("Choose option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                shoppingCart.put(productId, selected);
                System.out.println("Added to cart!");
            } else if (option == 2) {
                processCheckout(Collections.singletonList(selected));
            }
        } else {
            System.out.println("Invalid Product ID!");
        }
    }

    private void processCheckout(List<Product> products) {
        System.out.println("\n========== CHECKOUT ==========");
        products.forEach(p -> System.out.println(p.getDisplayDetails()));

        double total = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("\nTotal: ₹" + total);
        System.out.println("\n1. Confirm Purchase");
        System.out.println("2. Cancel");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("\nPurchase successful! Thank you.");
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    // Shopping Cart Operations
    public void viewShoppingCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n========== SHOPPING CART ==========");
        shoppingCart.values().forEach(p -> System.out.println(p.getDisplayDetails()));

        double total = shoppingCart.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("\nTotal: ₹" + total);

        System.out.println("\n1. Checkout");
        System.out.println("2. Remove Item");
        System.out.println("3. Continue Shopping");
        System.out.print("Choose option: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            processCheckout(new ArrayList<>(shoppingCart.values()));
            shoppingCart.clear();
        } else if (option == 2) {
            System.out.print("Enter Product ID to remove: ");
            String productId = scanner.nextLine();
            shoppingCart.remove(productId);
            System.out.println("Item removed.");
        }
    }

    // Main Menu
    public void displayMainMenu() {
        while (true) {
            System.out.println("\n========== E-COMMERCE SYSTEM ==========");
            System.out.println("1. Browse Products (Customer)");
            System.out.println("2. View All Products");
            System.out.println("3. View Products by Category");
            System.out.println("4. Sort Products by Price");
            System.out.println("5. View Brand");
            System.out.println("6. View Shirt By Size ");
            System.out.println("7. View Shopping Cart");
            System.out.println("8. Add New Product (Admin)");
            System.out.println("9. Remove Product (Admin)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerPurchaseFlow();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    displayProductsByCategory(category);
                    break;
                case 4:
                    System.out.print("Enter category to sort: ");
                    String sortCategory = scanner.nextLine();
                    System.out.print("Sort order (1=Low-High, 2=High-Low): ");
                    int sortOrder = scanner.nextInt();
                    scanner.nextLine();
                    displayProductsSortedByPrice(sortCategory, sortOrder == 1);
                    break;
                case 5:
                    displayBrandStatistics();
                    break;
                case 6:
                    displayShirtSizeStatistics();
                    break;
                case 7:
                    viewShoppingCart();
                    break;
                case 8:
                    addProductViaMenu();
                    break;
                case 9:
                    System.out.print("Enter product ID to remove: ");
                    String productId = scanner.nextLine();
                    if (removeProduct(productId)) {
                        System.out.println("Product removed successfully!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addProductViaMenu() {
        System.out.println("\n=== ADD NEW PRODUCT ===");
        System.out.println("1. Shirt");
        System.out.println("2. TShirt");
        System.out.println("3. Jeans");
        System.out.println("4. Watch");
        System.out.println("5. Mobile");
        System.out.println("6. Laptop");
        System.out.print("Select product type: ");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        try {
            switch (type) {
                case 1: // Shirt
                    System.out.print("SubCategory (Formal/Casual): ");
                    String subCat = scanner.nextLine();
                    System.out.print("Size: ");
                    int size = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Sleeve Type: ");
                    String sleeve = scanner.nextLine();
                    addProduct(new Shirt(id, brand, price, subCat, size, color, sleeve));
                    break;
                case 2: // TShirt
                    System.out.print("Size (S/M/L/XL): ");
                    String tSize = scanner.nextLine();
                    System.out.print("Color: ");
                    String tColor = scanner.nextLine();
                    System.out.print("Material: ");
                    String material = scanner.nextLine();
                    addProduct(new TShirt(id, brand, price, tSize, tColor, material));
                    break;
                case 3: // Jeans
                    System.out.print("Waist Size: ");
                    int waist = scanner.nextInt();
                    System.out.print("Length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Color: ");
                    String jColor = scanner.nextLine();
                    System.out.print("Fit (Slim/Regular/Skinny): ");
                    String fit = scanner.nextLine();
                    addProduct(new Jeans(id, brand, price, waist, length, jColor, fit));
                    break;
                case 4: // Watch
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Type (Analog/Digital/Smart): ");
                    String watchType = scanner.nextLine();
                    System.out.print("Band Material: ");
                    String band = scanner.nextLine();
                    addProduct(new Watch(id, brand, price, model, watchType, band));
                    break;
                case 5: // Mobile
                    System.out.print("Model: ");
                    String mModel = scanner.nextLine();
                    System.out.print("RAM (GB): ");
                    int ram = scanner.nextInt();
                    System.out.print("Storage (GB): ");
                    int storage = scanner.nextInt();
                    scanner.nextLine();
                    addProduct(new Mobile(id, brand, price, mModel, ram, storage));
                    break;
                case 6: // Laptop
                    System.out.print("Model: ");
                    String lModel = scanner.nextLine();
                    System.out.print("Processor: ");
                    String processor = scanner.nextLine();
                    System.out.print("RAM (GB): ");
                    int lRam = scanner.nextInt();
                    System.out.print("Storage (GB): ");
                    int lStorage = scanner.nextInt();
                    scanner.nextLine();
                    addProduct(new Laptop(id, brand, price, lModel, processor, lRam, lStorage));
                    break;
                default:
                    System.out.println("Invalid product type!");
                    return;
            }
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();
        system.displayMainMenu();
    }
}
