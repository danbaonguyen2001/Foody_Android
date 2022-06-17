package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Order;
import hcmute.danbaonguyen19110036.foody.Database.OrderDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItem;
import hcmute.danbaonguyen19110036.foody.Enum.OrderState;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.CancelFood;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.FoodReceiver;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Pattern.State.DeliveredState;
import hcmute.danbaonguyen19110036.foody.Pattern.State.DeliveringState;
import hcmute.danbaonguyen19110036.foody.Pattern.State.PendingState;
import hcmute.danbaonguyen19110036.foody.Pattern.State.StatusContext;
import hcmute.danbaonguyen19110036.foody.R;

public class OrderAdapter extends BaseAdapter {
    private List<OrderItem> orderItemList;
    private Context context;
    private int layout;
    private FoodDao foodDao;
    private OrderDao orderDao;
    private StatusContext statusContext = new StatusContext();
    public OrderAdapter(List<OrderItem> orderItemList, Context context, int layout) {
        this.orderItemList = orderItemList;
        this.context = context;
        this.layout = layout;
    }
    public class ViewHolder{
        // Khai báo các view trong layout item của adater
        public ImageView avatar;
        public TextView username,state;
        public Button btnCancel,btnOk;
    }
    @Override
    public int getCount() {
        return orderItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ConnectDatabase();
        if(view==null){
            // Ánh xạ các View
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.avatar=view.findViewById(R.id.img_avataruser);
            viewHolder.username = view.findViewById(R.id.txt_chat_username);
            viewHolder.state = view.findViewById(R.id.txt_chat_newmessage);
            viewHolder.btnCancel = view.findViewById(R.id.btnCancel);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        OrderItem orderItem = orderItemList.get(i);
        List<Food> foods = foodDao.queryBuilder().where(FoodDao.Properties.Id.eq(orderItem.getFoodId())).list();
        List<Order> orders = orderDao.queryBuilder().where(OrderDao.Properties.Id.eq(orderItem.getOrderId())).list();
        Date d2 = new Date();
        long diff = d2.getTime() - orders.get(0).getOrderDate().getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        if(diffMinutes>=-1){
            statusContext.setState(new DeliveredState(viewHolder.btnCancel,orderItem));
            statusContext.applyState();
        }
        else if(diffMinutes>=10){
            statusContext.setState(new DeliveringState(viewHolder.btnCancel,orderItem));
            statusContext.applyState();
        }
        viewHolder.state.setText(orderItem.getStatus());
        viewHolder.avatar.setImageResource(foods.get(0).getPath());
        viewHolder.username.setText(foods.get(0).getFoodname());
        // Nếu user click vào button addfriend thì ta listRequest của user hiên tại
        // và listpendingaccept của Receiver sẽ được cập nhật lại
        viewHolder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diffMinutes>=10){
                    System.out.println(orderItem.getStatus());
                    Toast.makeText(context, "Đơn hàng đã được giao không thể hủy đơn hàng", Toast.LENGTH_SHORT).show();
                }
                else {
                    statusContext.setState(new PendingState(orderItem.getId()));
                    statusContext.applyState();
                    orderItemList.remove(i);
                    notifyDataSetChanged();
                }
            }
        });
        return view;
    }
    public void ConnectDatabase(){
        orderDao = DatabaseApplication.Instance().createOrderDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
}
