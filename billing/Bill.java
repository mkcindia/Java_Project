// package billing;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.time.LocalDateTime;

// public class Bill {
//     private final String customerId;
//     private final String productId;
//     private final int quantity;
//     private final LocalDateTime dateTime;

//     public Bill(String customerId, String productId, int quantity) {
//         this.customerId = customerId;
//         this.productId = productId;
//         this.quantity = quantity;
//         this.dateTime = LocalDateTime.now();
//     }

//     public void generateBill() {
//         try (FileWriter writer = new FileWriter("bill_" + customerId + ".txt")) {
//             writer.write("Customer ID: " + customerId + "\n");
//             writer.write("Product ID: " + productId + "\n");
//             writer.write("Quantity: " + quantity + "\n");
//             writer.write("Date and Time: " + dateTime.toString() + "\n");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// package billing;

// import java.io.FileWriter;
// import java.io.IOException;
// import java.time.LocalDateTime;

// public class Bill {
//     private String customerId;
//     private String productId;
//     private int quantity;

//     public Bill(String customerId, String productId, int quantity) {
//         this.customerId = customerId;
//         this.productId = productId;
//         this.quantity = quantity;
//     }

//     public void generateBill() {
//         LocalDateTime now = LocalDateTime.now();
//         String billContent = "Customer ID: " + customerId + "\n" +
//                 "Product ID: " + productId + "\n" +
//                 "Quantity: " + quantity + "\n" +
//                 "Date and Time: " + now + "\n";

//         try (FileWriter writer = new FileWriter("bill_" + now + ".txt")) {
//             writer.write(billContent);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

package billing;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    private String customerId;
    private String productId;
    private int quantity;

    public Bill(String customerId, String productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void generateBill() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);

        String billContent = "Customer ID: " + customerId + "\n" +
                "Product ID: " + productId + "\n" +
                "Quantity: " + quantity + "\n" +
                "Date and Time: " + now + "\n";

        // Specify the directory where you want to save the bill file
        String filePath = "D:/" + formattedDateTime + ".txt";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(billContent);
            System.out.println("Bill generated successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating bill: " + e.getMessage());
        }
    }
}
