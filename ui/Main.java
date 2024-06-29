package ui;

import database.Database;
import models.Admin;
import models.Customer;
import models.Producer;
import models.Product;
import models.User;
import models.ProductRequest;
import services.ProductService;
import billing.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        ProductService productService = new ProductService();

        while (true) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|      Welcome to VirtualMarket System      |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Login                                  |");
            System.out.println("| 2. Sign Up                                |");
            System.out.println("| 3. Exit                                   |");
            System.out.println("+-------------------------------------------+");
            int choice = getChoice(scanner, 1, 3);

            switch (choice) {
                case 1:
                    login(scanner, database, productService);
                    break;
                case 2:
                    signUp(scanner, database);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private static void login(Scanner scanner, Database database, ProductService productService) {
        System.out.println("+-------------------------------------------+");
        System.out.println("|                  Login                    |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 1. Login as Admin                         |");
        System.out.println("| 2. Login as Producer                      |");
        System.out.println("| 3. Login as Customer                      |");
        System.out.println("+-------------------------------------------+");

        int choice = getChoice(scanner, 1, 3);

        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = database.getUser(id);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("+-------------------------------------------+");
            System.out.println("| " + user.getClass().getSimpleName() + " logged in successfully.            |");
            System.out.println("+-------------------------------------------+");
            switch (choice) {
                case 1:
                    handleAdmin(scanner, database, productService, (Admin) user);
                    break;
                case 2:
                    handleProducer(scanner, database, productService, (Producer) user);
                    break;
                case 3:
                    handleCustomer(scanner, database, productService, (Customer) user);
                    break;
            }
        } else {
            System.out.println("+-------------------------------------------+");
            System.out.println("| Invalid credentials.                      |");
            System.out.println("+-------------------------------------------+");
        }
    }

    private static void signUp(Scanner scanner, Database database) {
        System.out.println("+-------------------------------------------+");
        System.out.println("|                  Sign Up                  |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 1. Sign Up as Admin                       |");
        System.out.println("| 2. Sign Up as Producer                    |");
        System.out.println("| 3. Sign Up as Customer                    |");
        System.out.println("+-------------------------------------------+");

        int choice = getChoice(scanner, 1, 3);

        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        switch (choice) {
            case 1:
                Admin admin = new Admin(id, name, password);
                database.addUser(admin);
                break;
            case 2:
                Producer producer = new Producer(id, name, password);
                database.addUser(producer);
                break;
            case 3:
                Customer customer = new Customer(id, name, password);
                database.addUser(customer);
                break;
        }
        System.out.println("+-------------------------------------------+");
        System.out.println("| User registered successfully.             |");
        System.out.println("+-------------------------------------------+");
    }

    private static void handleAdmin(Scanner scanner, Database database, ProductService productService, Admin admin) {
        while (true) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|               Admin Menu                  |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. View Product Requests                  |");
            System.out.println("| 2. Accept Product Request                 |");
            System.out.println("| 3. Reject Product Request                 |");
            System.out.println("| 4. Logout                                 |");
            System.out.println("+-------------------------------------------+");

            int choice = getChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    viewProductRequests(database);
                    break;
                case 2:
                    processProductRequest(scanner, database, productService, true);
                    break;
                case 3:
                    processProductRequest(scanner, database, productService, false);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void handleProducer(Scanner scanner, Database database, ProductService productService,
            Producer producer) {
        while (true) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|              Producer Menu                |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Add Product Request                    |");
            System.out.println("| 2. Logout                                 |");
            System.out.println("+-------------------------------------------+");
            int choice = getChoice(scanner, 1, 2);

            switch (choice) {
                case 1:
                    addProductRequest(scanner, database, producer);
                    break;
                case 2:
                    return;
            }
        }
    }

    private static void handleCustomer(Scanner scanner, Database database, ProductService productService,
            Customer customer) {
        while (true) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|              Customer Menu                |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. View Products                          |");
            System.out.println("| 2. Buy Product                            |");
            System.out.println("| 3. Logout                                 |");
            System.out.println("+-------------------------------------------+");

            int choice = getChoice(scanner, 1, 3);

            switch (choice) {
                case 1:
                    viewProducts(productService);
                    break;
                case 2:
                    buyProduct(scanner, productService, customer);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void addProductRequest(Scanner scanner, Database database, Producer producer) {
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double productPrice = scanner.nextDouble();
        System.out.print("Enter Product Quantity: ");
        int productQuantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(productId, productName, productPrice, productQuantity);
        ProductRequest request = new ProductRequest(producer.getId(), product);
        database.addProductRequest(request);
        System.out.println("+-------------------------------------------+");
        System.out.println("| Product request added successfully.       |");
        System.out.println("| Awaiting admin approval.                  |");
        System.out.println("+-------------------------------------------+");
    }

    private static void viewProductRequests(Database database) {
        System.out.println("+-------------------------------------------+");
        System.out.println("|           Pending Product Requests        |");
        System.out.println("+-------------------------------------------+");
        for (ProductRequest request : database.getProductRequests()) {
            if ("pending".equals(request.getStatus())) {
                System.out.println("| Producer ID: " + request.getProducerId());
                System.out.println("| Product ID: " + request.getProduct().getId());
                System.out.println("| Product Name: " + request.getProduct().getName());
                System.out.println("| Product Price: " + request.getProduct().getPrice());
                System.out.println("| Product Quantity: " + request.getProduct().getQuantity());
                System.out.println("| Status: " + request.getStatus());
                System.out.println("+-------------------------------------------+");
            }
        }
    }

    private static void processProductRequest(Scanner scanner, Database database, ProductService productService,
            boolean accept) {
        System.out.print("Enter Product ID to " + (accept ? "Accept" : "Reject") + ": ");
        String productId = scanner.nextLine();
        ProductRequest requestToProcess = null;

        for (ProductRequest request : database.getProductRequests()) {
            if (request.getProduct().getId().equals(productId) && "pending".equals(request.getStatus())) {
                requestToProcess = request;
                break;
            }
        }

        if (requestToProcess != null) {
            if (accept) {
                requestToProcess.setStatus("accepted");
                productService.addProduct(requestToProcess.getProduct());
                System.out.println("+-------------------------------------------+");
                System.out.println("| Product request accepted and product added |");
                System.out.println("| successfully.                             |");
                System.out.println("+-------------------------------------------+");
            } else {
                requestToProcess.setStatus("rejected");
                System.out.println("+-------------------------------------------+");
                System.out.println("| Product request rejected.                 |");
                System.out.println("+-------------------------------------------+");
            }
        } else {
            System.out.println("Product request not found or already processed.");
        }
    }

    private static void viewProducts(ProductService productService) {
        System.out.println("+-------------------------------------------+");
        System.out.println("|            Available Products             |");
        System.out.println("+-------------------------------------------+");
        productService.getProducts().forEach(product -> {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("----------------------");
        });
    }

    private static void buyProduct(Scanner scanner, ProductService productService, Customer customer) {
    List<Product> cart = new ArrayList<>();
    while (true) {
        System.out.print("Enter Product ID to Buy: ");
        String productId = scanner.nextLine();
        Product product = productService.searchProduct(productId);
        if (product != null) {
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if (quantity <= product.getQuantity()) {
                cart.add(new Product(product.getId(), product.getName(), product.getPrice(), quantity));
                System.out.println("Product added to cart.");
            } else {
                System.out.println("Insufficient quantity.");
            }
        } else {
            System.out.println("Product not found.");
        }

        System.out.println("Do you want to buy another product? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        if (!response.equals("yes")) {
            break;
        }
    }

    if (!cart.isEmpty()) {
        System.out.print("Do you want to generate the bill? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            boolean allProductsAvailable = true;
            for (Product cartProduct : cart) {
                Product product = productService.searchProduct(cartProduct.getId());
                if (product == null || cartProduct.getQuantity() > product.getQuantity()) {
                    allProductsAvailable = false;
                    System.out.println("Insufficient quantity for product: " + cartProduct.getName());
                }
            }

            if (allProductsAvailable) {
                for (Product cartProduct : cart) {
                    Product product = productService.searchProduct(cartProduct.getId());
                    product.setQuantity(product.getQuantity() - cartProduct.getQuantity());
                }
                Bill bill = new Bill(customer.getId(), customer.getName(), cart);
                bill.generateBill();
                System.out.println("Purchase successful. Bill generated.");
            } else {
                System.out.println("Purchase aborted due to insufficient quantities.");
            }
        } else {
            System.out.println("Bill not generated. Purchase aborted.");
        }
    }
}

    private static int getChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        return choice;
    }
}
