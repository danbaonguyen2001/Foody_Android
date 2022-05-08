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
    @Generated(hash = 1438648636)
    public OrderItem(Long id, Long foodId, Long orderId) {
        this.id = id;
        this.foodId = foodId;
        this.orderId = orderId;
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
}
