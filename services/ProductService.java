// // package services;

// // import models.Product;

// // import java.util.ArrayList;
// // import java.util.List;

// // public class ProductService {
// //     private final List<Product> products = new ArrayList<>();

// //     public synchronized void addProduct(Product product) {
// //         products.add(product);
// //         notifyAll();
// //     }

// //     public synchronized void removeProduct(String productId) {
// //         products.removeIf(product -> product.getId().equals(productId));
// //         notifyAll();
// //     }

// //     public Product searchProduct(String idOrName) {
// //         return products.stream()
// //                 .filter(product -> product.getId().equals(idOrName) || product.getName().equals(idOrName))
// //                 .findFirst()
// //                 .orElse(null);
// //     }

// //     public List<Product> getProducts() {
// //         return products;
// //     }
// // }

// package services;

// import models.Product;

// import java.util.HashMap;
// import java.util.Map;

// public class ProductService {
//     private Map<String, Product> products = new HashMap<>();

//     public void addProduct(Product product) {
//         products.put(product.getId(), product);
//     }

//     public void removeProduct(String productId) {
//         products.remove(productId);
//     }

//     public Product searchProduct(String productId) {
//         return products.get(productId);
//     }

//     public Map<String, Product> getProducts() {
//         return products;
//     }
// }

package services;

import models.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Map<String, Product> productMap;

    public ProductService() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void removeProduct(String productId) {
        productMap.remove(productId);
    }

    public Product searchProduct(String productId) {
        return productMap.get(productId);
    }

    public Iterable<Product> getProducts() {
        return productMap.values();
    }
}
