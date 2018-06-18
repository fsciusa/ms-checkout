package microservices.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private int purchaseId;

    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;

    public Purchase() {}

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }

    @Override
    public String toString() {
        return String.format("Purchase [purchaseId=%s]", purchaseId);
    }
}
