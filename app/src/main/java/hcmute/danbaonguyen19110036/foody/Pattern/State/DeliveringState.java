package hcmute.danbaonguyen19110036.foody.Pattern.State;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;

import hcmute.danbaonguyen19110036.foody.Database.OrderItem;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Enum.OrderState;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;

public class DeliveringState implements State{
    private Button button;
    private OrderItemDao orderItemDao;
    private OrderItem orderItem;
    public DeliveringState(Button view, OrderItem orderItem){
        this.button = view;
        this.orderItem = orderItem;
    }
    @Override
    public void handleRequest() {
        ConnectDatabase();
        this.button.setBackgroundColor(Color.RED);
        this.button.setTextColor(Color.WHITE);
        orderItem.setStatus(String.valueOf(OrderState.DELIVERING));
        orderItemDao.update(orderItem);
    }
    public void ConnectDatabase(){
        orderItemDao = DatabaseApplication.Instance().createOrderItemDao();
    }
}
