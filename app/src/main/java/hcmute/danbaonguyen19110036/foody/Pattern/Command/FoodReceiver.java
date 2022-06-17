package hcmute.danbaonguyen19110036.foody.Pattern.Command;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.Date;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Enum.OrderState;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Order;
import hcmute.danbaonguyen19110036.foody.Database.OrderDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItem;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Utils.NotificationModel;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class FoodReceiver {
    public OrderDao orderDao;
    public OrderItemDao orderItemDao;
    public FoodDao foodDao;
    public void Cancel(Long orderItemID){
        ConnectDatabase();
        final DeleteQuery<OrderItem> orderItemDeleteQuery = orderItemDao.queryBuilder()
                .where(OrderItemDao.Properties.Id.eq(orderItemID)).buildDelete();
        orderItemDeleteQuery.executeDeleteWithoutDetachingEntities();
    }
    public void ConnectDatabase(){
        orderDao = DatabaseApplication.Instance().createOrderDao();
        orderItemDao = DatabaseApplication.Instance().createOrderItemDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void Order(){
        ConnectDatabase();
        Order order = new Order(null,new Date(), SaveVariable.user.getId(),SaveVariable.TotalPrice());
        orderDao.insert(order);
        List<Order> orders = orderDao.loadAll();
        Long orderId = orders.get(orders.size()-1).getId();
        for(int i=0;i<SaveVariable.cartModelList.size();i++){
            Food food = SaveVariable.cartModelList.get(i).food;
            int quantity = SaveVariable.cartModelList.get(i).getQuantity();
            int totalPrice = SaveVariable.cartModelList.get(i).getTotalPrice();
            OrderItem orderItem = new OrderItem(null,food.getId(),orderId,quantity,totalPrice, String.valueOf(OrderState.PENDING));
            orderItemDao.insert(orderItem);
            String description = "Bạn vừa mua sản phẩm "+food.getFoodname() + " số lượng "+quantity+ " với tổng số tiền "+totalPrice;
            SaveVariable.notificationModelList.add(new NotificationModel(food,description));
        }

    }
}
