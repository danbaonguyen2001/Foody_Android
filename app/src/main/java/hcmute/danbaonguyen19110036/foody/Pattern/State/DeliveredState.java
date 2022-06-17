package hcmute.danbaonguyen19110036.foody.Pattern.State;

import android.graphics.Color;
import android.widget.Button;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItem;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Enum.OrderState;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Utils.NotificationModel;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class DeliveredState implements State{
    private Button button;
    private OrderItemDao orderItemDao;
    private OrderItem orderItem;
    private FoodDao foodDao;
    public DeliveredState(Button button, OrderItem orderItem){
        this.button = button;
        this.orderItem = orderItem;
    }
    @Override
    public void handleRequest() {
        ConnectDatabase();
        this.button.setBackgroundColor(Color.GREEN);
        this.button.setTextColor(Color.BLACK);
        this.button.setText("OK");
        orderItem.setStatus(String.valueOf(OrderState.DELIVERED));
        orderItemDao.update(orderItem);
        List<Food> list = foodDao.queryBuilder().where(FoodDao.Properties.Id.eq(orderItem.getFoodId())).list();
        String description = "Bạn vừa thanh toán sản phẩm "+list.get(0).getFoodname() + " số lượng "+orderItem.getQuantity()+ " với tổng số tiền "+orderItem.getTotalPrice();
        SaveVariable.notificationModelList.add(new NotificationModel(list.get(0), description));
    }
    public void ConnectDatabase(){
        orderItemDao = DatabaseApplication.Instance().createOrderItemDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
}
