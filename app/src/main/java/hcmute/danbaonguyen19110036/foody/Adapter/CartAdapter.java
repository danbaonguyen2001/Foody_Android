package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.R;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;
    public CartAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }
    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        public TextView foodName,foodCategory,foodQuantity,foodPrice;
        public ImageView foodImage;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.foodName = (TextView) view.findViewById(R.id.food_name_cart);
            holder.foodCategory = (TextView) view.findViewById(R.id.category_cart);
            holder.foodQuantity = (TextView) view.findViewById(R.id.quantity_cart);
            holder.foodPrice = (TextView) view.findViewById(R.id.price_card);
//            holder.foodImage = (ImageView) view.findViewById(R.id.img_cart);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        Food food = foodList.get(i);
        holder.foodName.setText(food.getFoodname());
        return view;
    }
}
