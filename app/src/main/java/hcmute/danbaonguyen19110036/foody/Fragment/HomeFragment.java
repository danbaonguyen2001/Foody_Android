package hcmute.danbaonguyen19110036.foody.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Activity.ProductDetail;
import hcmute.danbaonguyen19110036.foody.Adapter.ShopHomeAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class HomeFragment extends Fragment {
    private GridView gridView;
    private ShopHomeAdapter shopHomeAdapter;
    private ShopDao shopDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConnectDatabase();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.grid_shop);
        final List<Shop> shopList = shopDao.loadAll();
        shopHomeAdapter = new ShopHomeAdapter(getActivity(),R.layout.layout_shop_home,shopList);
        gridView.setAdapter(shopHomeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SaveVariable.shop = shopList.get(i);
                startActivity(new Intent(getActivity(), ProductDetail.class));
            }
        });
        return view;
    }
    void ConnectDatabase(){
        shopDao = DatabaseApplication.Instance().createShopDao();
    }
}