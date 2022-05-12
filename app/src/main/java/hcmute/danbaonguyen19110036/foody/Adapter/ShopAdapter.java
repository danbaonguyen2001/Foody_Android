package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.R;

public class ShopAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Shop> shopList;
    TextView foodShop,foodAddress,foodStatus;
    ImageView foodImage;
    public ShopAdapter(Context context, int layout, List<Shop> shopList) {
        this.context = context;
        this.layout = layout;
        this.shopList = shopList;
    }
    @Override
    public int getCount() {
        return shopList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        foodShop = view.findViewById(R.id.food_name_shop);
        foodAddress = view.findViewById(R.id.food_address_shop);
        foodStatus = view.findViewById(R.id.shop_status);
        foodImage=view.findViewById(R.id.img_shop_search);
        foodImage.setImageResource(R.drawable.hamburger);
        //Data
        Shop shop = shopList.get(i);
        foodShop.setText(shop.getShopname());
        foodAddress.setText(shop.getAddress());
        foodStatus.setText(shop.getOpenDoor());
        foodImage.setImageResource(shop.getPath());
        return view;
    }
}
