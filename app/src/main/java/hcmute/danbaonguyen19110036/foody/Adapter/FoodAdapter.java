package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.R;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;
    private TextView foodName,foodDescription,foodPrice;
    private ImageView foodImage;
    public FoodAdapter(Context context, int layout, List<Food> foodList) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        Food food = foodList.get(i);
        foodName = view.findViewById(R.id.food_name);
        foodDescription = view.findViewById(R.id.food_description);
        foodPrice = view.findViewById(R.id.food_price);
        foodImage=view.findViewById(R.id.img_food);
//        // Data
        foodName.setText(food.getFoodname());
        foodDescription.setText(food.getDescription());
        foodPrice.setText(String.valueOf(food.getPrice()));
        foodImage.setImageResource(food.getPath());
        return view;
    }
}
