package models;

public class ProductRequest {
    private String producerId;
    private Product product;
    private String status;

    public ProductRequest(String producerId, Product product) {
        this.producerId = producerId;
        this.product = product;
        this.status = "pending";
    }

    public String getProducerId() {
        return producerId;
    }

    public Product getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
