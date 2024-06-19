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
