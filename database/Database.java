// // package database;

// // import models.User;
// // import models.Product;

// // import java.util.HashMap;
// // import java.util.Map;

// // public class Database {
// //     private final Map<String, User> users = new HashMap<>();
// //     private final Map<String, Product> products = new HashMap<>();

// //     public void addUser(User user) {
// //         users.put(user.getId(), user);
// //     }

// //     public User getUser(String userId) {
// //         return users.get(userId);
// //     }

// //     public void addProduct(Product product) {
// //         products.put(product.getId(), product);
// //     }

// //     public Product getProduct(String productId) {
// //         return products.get(productId);
// //     }

// //     public void removeUser(String userId) {
// //         users.remove(userId);
// //     }

// //     public void removeProduct(String productId) {
// //         products.remove(productId);
// //     }
// // }

// package database;

// import models.Product;
// import models.User;

// import java.util.HashMap;
// import java.util.Map;

// public class Database {
//     private Map<String, User> users = new HashMap<>();
//     private Map<String, Product> products = new HashMap<>();

//     public void addUser(User user) {
//         users.put(user.getId(), user);
//     }

//     public User getUser(String id) {
//         return users.get(id);
//     }

//     public void addProduct(Product product) {
//         products.put(product.getId(), product);
//     }

//     public void removeProduct(String productId) {
//         products.remove(productId);
//     }

//     public Product getProduct(String productId) {
//         return products.get(productId);
//     }
// }

package database;

import models.User;
import models.Product;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, User> userMap;
    private Map<String, Product> productMap;

    public Database() {
        this.userMap = new HashMap<>();
        this.productMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    public User getUser(String id) {
        return userMap.get(id);
    }

    public void removeUser(String id) {
        userMap.remove(id);
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void removeProduct(String productId) {
        productMap.remove(productId);
    }
}
