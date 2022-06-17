package hcmute.danbaonguyen19110036.foody.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "OrderItem")
public class OrderItem {
    @Id(autoincrement = true)
    private Long id;
    private Long foodId;
    private Long orderId;
    private int quantity;
    private int totalPrice;
    public String status;

    @Generated(hash = 1020887657)
    public OrderItem(Long id, Long foodId, Long orderId, int quantity,
            int totalPrice, String status) {
        this.id = id;
        this.foodId = foodId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }
    @Generated(hash = 403153068)
    public OrderItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFoodId() {
        return this.foodId;
    }
    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }
    public Long getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
