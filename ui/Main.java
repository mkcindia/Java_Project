// package ui;

// import database.Database;
// import models.Admin;
// import models.Customer;
// import models.Producer;
// import models.Product;
// import models.User;
// import services.ProductService;
// import billing.Bill;

// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         Database database = new Database();
//         ProductService productService = new ProductService();

//         while (true) {
//             System.out.println("Welcome to E-mall Management System");
//             System.out.println("1. Login");
//             System.out.println("2. Sign Up");
//             System.out.println("3. Exit");

//             int choice = getChoice(scanner, 1, 3);

//             switch (choice) {
//                 case 1:
//                     login(scanner, database, productService);
//                     break;
//                 case 2:
//                     signUp(scanner, database);
//                     break;
//                 case 3:
//                     System.out.println("Exiting...");
//                     return;
//             }
//         }
//     }

//     private static void login(Scanner scanner, Database database, ProductService productService) {
//         System.out.println("1. Login as Admin");
//         System.out.println("2. Login as Producer");
//         System.out.println("3. Login as Customer");

//         int choice = getChoice(scanner, 1, 3);

//         System.out.print("Enter User ID: ");
//         String id = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();

//         User user = database.getUser(id);
//         if (user != null && user.getPassword().equals(password)) {
//             System.out.println(user.getClass().getSimpleName() + " logged in successfully.");
//             switch (choice) {
//                 case 1:
//                     handleAdmin(scanner, database, productService, (Admin) user);
//                     break;
//                 case 2:
//                     handleProducer(scanner, database, productService, (Producer) user);
//                     break;
//                 case 3:
//                     handleCustomer(scanner, database, productService, (Customer) user);
//                     break;
//             }
//         } else {
//             System.out.println("Invalid credentials.");
//         }
//     }

//     private static void signUp(Scanner scanner, Database database) {
//         System.out.println("1. Sign Up as Admin");
//         System.out.println("2. Sign Up as Producer");
//         System.out.println("3. Sign Up as Customer");

//         int choice = getChoice(scanner, 1, 3);

//         System.out.print("Enter User ID: ");
//         String id = scanner.nextLine();
//         System.out.print("Enter Name: ");
//         String name = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();

//         switch (choice) {
//             case 1:
//                 Admin admin = new Admin(id, name, password);
//                 database.addUser(admin);
//                 break;
//             case 2:
//                 Producer producer = new Producer(id, name, password);
//                 database.addUser(producer);
//                 break;
//             case 3:
//                 Customer customer = new Customer(id, name, password);
//                 database.addUser(customer);
//                 break;
//         }
//         System.out.println("User registered successfully.");
//     }

//     private static void handleAdmin(Scanner scanner, Database database, ProductService productService, Admin admin) {
//         while (true) {
//             System.out.println("1. Add Product");
//             System.out.println("2. Remove Product");
//             System.out.println("3. Logout");

//             int choice = getChoice(scanner, 1, 3);

//             switch (choice) {
//                 case 1:
//                     addProduct(scanner, productService, database);
//                     break;
//                 case 2:
//                     removeProduct(scanner, productService, database);
//                     break;
//                 case 3:
//                     return;
//             }
//         }
//     }

//     private static void handleProducer(Scanner scanner, Database database, ProductService productService, Producer producer) {
//         while (true) {
//             System.out.println("1. Add Product");
//             System.out.println("2. Remove Product");
//             System.out.println("3. Logout");

//             int choice = getChoice(scanner, 1, 3);

//             switch (choice) {
//                 case 1:
//                     addProduct(scanner, productService, database);
//                     break;
//                 case 2:
//                     removeProduct(scanner, productService, database);
//                     break;
//                 case 3:
//                     return;
//             }
//         }
//     }

//     private static void handleCustomer(Scanner scanner, Database database, ProductService productService, Customer customer) {
//         while (true) {
//             System.out.println("1. View Products");
//             System.out.println("2. Buy Product");
//             System.out.println("3. Logout");

//             int choice = getChoice(scanner, 1, 3);

//             switch (choice) {
//                 case 1:
//                     viewProducts(productService);
//                     break;
//                 case 2:
//                     buyProduct(scanner, productService, customer);
//                     break;
//                 case 3:
//                     return;
//             }
//         }
//     }

//     private static void addProduct(Scanner scanner, ProductService productService, Database database) {
//         System.out.print("Enter Product ID: ");
//         String productId = scanner.nextLine();
//         System.out.print("Enter Product Name: ");
//         String productName = scanner.nextLine();
//         System.out.print("Enter Product Price: ");
//         double productPrice = scanner.nextDouble();
//         System.out.print("Enter Product Quantity: ");
//         int productQuantity = scanner.nextInt();
//         scanner.nextLine(); // Consume newline

//         Product product = new Product(productId, productName, productPrice, productQuantity);
//         productService.addProduct(product);
//         database.addProduct(product);
//         System.out.println("Product added successfully.");
//     }

//     private static void removeProduct(Scanner scanner, ProductService productService, Database database) {
//         System.out.print("Enter Product ID to Remove: ");
//         String productId = scanner.nextLine();
//         productService.removeProduct(productId);
//         database.removeProduct(productId);
//         System.out.println("Product removed successfully.");
//     }

//     private static void viewProducts(ProductService productService) {
//         System.out.println("Available Products:");
//         productService.getProducts().forEach(product -> {
//             System.out.println("ID: " + product.getId());
//             System.out.println("Name: " + product.getName());
//             System.out.println("Price: " + product.getPrice());
//             System.out.println("Quantity: " + product.getQuantity());
//             System.out.println("----------------------");
//         });
//     }

//     private static void buyProduct(Scanner scanner, ProductService productService, Customer customer) {
//         System.out.print("Enter Product ID to Buy: ");
//         String productId = scanner.nextLine();
//         Product product = productService.searchProduct(productId);
//         if (product != null) {
//             System.out.print("Enter Quantity: ");
//             int quantity = scanner.nextInt();
//             scanner.nextLine(); // Consume newline
//             if (quantity <= product.getQuantity()) {
//                 product.setQuantity(product.getQuantity() - quantity);
//                 Bill bill = new Bill(customer.getId(), product.getId(), quantity);
//                 bill.generateBill();
//                 System.out.println("Purchase successful. Bill generated.");
//             } else {
//                 System.out.println("Insufficient quantity.");
//             }
//         } else {
//             System.out.println("Product not found.");
//         }
//     }

//     private static int getChoice(Scanner scanner, int min, int max) {
//         int choice;
//         while (true) {
//             System.out.print("Enter your choice (" + min + "-" + max + "): ");
//             if (scanner.hasNextInt()) {
//                 choice = scanner.nextInt();
//                 scanner.nextLine(); // Consume newline
//                 if (choice >= min && choice <= max) {
//                     break;
//                 } else {
//                     System.out.println("Invalid choice. Please try again.");
//                 }
//             } else {
//                 System.out.println("Invalid input. Please enter a number.");
//                 scanner.nextLine(); // Consume invalid input
//             }
//         }
//         return choice;
//     }
// }
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

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        ProductService productService = new ProductService();

        while (true) {
            System.out.println("Welcome to E-mall Management System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");

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
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Producer");
        System.out.println("3. Login as Customer");

        int choice = getChoice(scanner, 1, 3);

        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = database.getUser(id);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println(user.getClass().getSimpleName() + " logged in successfully.");
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
            System.out.println("Invalid credentials.");
        }
    }

    private static void signUp(Scanner scanner, Database database) {
        System.out.println("1. Sign Up as Admin");
        System.out.println("2. Sign Up as Producer");
        System.out.println("3. Sign Up as Customer");

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
        System.out.println("User registered successfully.");
    }

    private static void handleAdmin(Scanner scanner, Database database, ProductService productService, Admin admin) {
        while (true) {
            System.out.println("1. View Product Requests");
            System.out.println("2. Accept Product Request");
            System.out.println("3. Reject Product Request");
            System.out.println("4. Logout");

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

    private static void handleProducer(Scanner scanner, Database database, ProductService productService, Producer producer) {
        while (true) {
            System.out.println("1. Add Product Request");
            System.out.println("2. Logout");

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

    private static void handleCustomer(Scanner scanner, Database database, ProductService productService, Customer customer) {
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
        scanner.nextLine(); // Consume newline

        Product product = new Product(productId, productName, productPrice, productQuantity);
        ProductRequest request = new ProductRequest(producer.getId(), product);
        database.addProductRequest(request);
        System.out.println("Product request added successfully. Awaiting admin approval.");
    }

    private static void viewProductRequests(Database database) {
        System.out.println("Pending Product Requests:");
        for (ProductRequest request : database.getProductRequests()) {
            if ("pending".equals(request.getStatus())) {
                System.out.println("Producer ID: " + request.getProducerId());
                System.out.println("Product ID: " + request.getProduct().getId());
                System.out.println("Product Name: " + request.getProduct().getName());
                System.out.println("Product Price: " + request.getProduct().getPrice());
                System.out.println("Product Quantity: " + request.getProduct().getQuantity());
                System.out.println("Status: " + request.getStatus());
                System.out.println("----------------------");
            }
        }
    }

    private static void processProductRequest(Scanner scanner, Database database, ProductService productService, boolean accept) {
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
                System.out.println("Product request accepted and product added successfully.");
            } else {
                requestToProcess.setStatus("rejected");
                System.out.println("Product request rejected.");
            }
        } else {
            System.out.println("Product request not found or already processed.");
        }
    }

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
