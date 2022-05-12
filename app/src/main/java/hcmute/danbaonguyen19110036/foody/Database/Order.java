package hcmute.danbaonguyen19110036.foody.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Order")
public class Order {
    @Id(autoincrement = true)
    private Long id;
    private Date OrderDate;
    private Long userId;
    private int totalPrice;

    @Generated(hash = 171747360)
    public Order(Long id, Date OrderDate, Long userId, int totalPrice) {
        this.id = id;
        this.OrderDate = OrderDate;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getOrderDate() {
        return this.OrderDate;
    }
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public int getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
