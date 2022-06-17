package hcmute.danbaonguyen19110036.foody.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.OrderAdapter;
import hcmute.danbaonguyen19110036.foody.Database.OrderItem;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.R;

public class BillFragment extends Fragment {
    private ListView listView;
    private List<OrderItem> orderItemList;
    private OrderItemDao orderItemDao;
    private OrderAdapter orderAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConnectDatabase();
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        orderItemList = orderItemDao.loadAll();
        listView = view.findViewById(R.id.listview_order);
        orderAdapter = new OrderAdapter(orderItemList,getActivity(),R.layout.layout_state_item);
        listView.setAdapter(orderAdapter);
        return view;
    }
    public void ConnectDatabase(){
        orderItemDao = DatabaseApplication.Instance().createOrderItemDao();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(orderAdapter!=null){
            orderAdapter.notifyDataSetChanged();
        }
    }
}