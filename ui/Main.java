package ui;

import database.Database;
import models.Admin;
import models.Customer;
import models.Producer;
import models.Product;
import services.ProductService;
import billing.Bill;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        ProductService productService = new ProductService();

        // Adding some initial users
        Admin admin = new Admin("1", "Admin", "adminpass");
        Producer producer = new Producer("2", "Producer", "producerpass");
        Customer customer = new Customer("3", "Customer", "customerpass");

        database.addUser(admin);
        database.addUser(producer);
        database.addUser(customer);

        while (true) {
            System.out.println("Welcome to E-mall Management System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Producer");
            System.out.println("3. Login as Customer");
            System.out.println("4. Exit");

            int choice = getChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    handleAdmin(scanner, database, productService);
                    break;
                case 2:
                    handleProducer(scanner, database, productService);
                    break;
                case 3:
                    handleCustomer(scanner, database, productService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private static void handleAdmin(Scanner scanner, Database database, ProductService productService) {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        Admin admin = (Admin) database.getUser(id);
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Admin logged in successfully.");
            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Logout");

                int choice = getChoice(scanner, 1, 3);

                switch (choice) {
                    case 1:
                        addProduct(scanner, productService, database);
                        break;
                    case 2:
                        removeProduct(scanner, productService, database);
                        break;
                    case 3:
                        return;
                }
            }
        } else {
            System.out.println("Invalid Admin credentials.");
        }
    }

    private static void handleProducer(Scanner scanner, Database database, ProductService productService) {
        System.out.print("Enter Producer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Producer Password: ");
        String password = scanner.nextLine();

        Producer producer = (Producer) database.getUser(id);
        if (producer != null && producer.getPassword().equals(password)) {
            System.out.println("Producer logged in successfully.");
            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Logout");

                int choice = getChoice(scanner, 1, 3);

                switch (choice) {
                    case 1:
                        addProduct(scanner, productService, database);
                        break;
                    case 2:
                        removeProduct(scanner, productService, database);
                        break;
                    case 3:
                        return;
                }
            }
        } else {
            System.out.println("Invalid Producer credentials.");
        }
    }

    private static void handleCustomer(Scanner scanner, Database database, ProductService productService) {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Customer Password: ");
        String password = scanner.nextLine();

        Customer customer = (Customer) database.getUser(id);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Customer logged in successfully.");
            while (true) {
                System.out.println("1. View Products");
                System.out.println("2. Buy Product");
                System.out.println("3. Logout");

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
        } else {
            System.out.println("Invalid Customer credentials.");
        }
    }

    private static void addProduct(Scanner scanner, ProductService productService, Database database) {
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double productPrice = scanner.nextDouble();
        System.out.print("Enter Product Quantity: ");
        int productQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = new Product(productId, productName, productPrice, productQuantity);
        productService.addProduct(product);
        database.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void removeProduct(Scanner scanner, ProductService productService, Database database) {
        System.out.print("Enter Product ID to Remove: ");
        String productId = scanner.nextLine();
        productService.removeProduct(productId);
        database.removeProduct(productId);
        System.out.println("Product removed successfully.");
    }

    // private static void viewProducts(ProductService productService) {
    // productService.getProducts().forEach(product -> {
    // System.out.println("ID: " + product.getId());
    // System.out.println("Name: " + product.getName());
    // System.out.println("Price: " + product.getPrice());
    // System.out.println("Quantity: " + product.getQuantity());
    // System.out.println("----------------------");
    // });
    // }
    private static void viewProducts(ProductService productService) {
        System.out.println("Available Products:");
        productService.getProducts().forEach(product -> {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("----------------------");
        });
    }

    private static void buyProduct(Scanner scanner, ProductService productService, Customer customer) {
        System.out.print("Enter Product ID to Buy: ");
        String productId = scanner.nextLine();
        Product product = productService.searchProduct(productId);
        if (product != null) {
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (quantity <= product.getQuantity()) {
                product.setQuantity(product.getQuantity() - quantity);
                Bill bill = new Bill(customer.getId(), product.getId(), quantity);
                bill.generateBill();
                System.out.println("Purchase successful. Bill generated.");
            } else {
                System.out.println("Insufficient quantity.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static int getChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return choice;
    }
}
