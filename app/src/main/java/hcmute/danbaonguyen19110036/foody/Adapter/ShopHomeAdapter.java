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
public class ShopHomeAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Shop> shopList;
    public ShopHomeAdapter(Context context, int layout, List<Shop> shopList) {
        this.context = context;
        this.layout = layout;
        this.shopList = shopList;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textViewShopName,textViewDescription;
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
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.textViewShopName = (TextView) view.findViewById(R.id.txt_shopname);
            holder.textViewDescription = (TextView) view.findViewById(R.id.txt_description_shop);
            holder.imageView = (ImageView) view.findViewById(R.id.img_shop_home);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        Shop shop = shopList.get(i);
        holder.imageView.setImageResource(R.drawable.hamburger);
        holder.textViewShopName.setText(shop.getShopname());
        holder.textViewDescription.setText(shop.getOpenDoor());
        return view;
    }
}
