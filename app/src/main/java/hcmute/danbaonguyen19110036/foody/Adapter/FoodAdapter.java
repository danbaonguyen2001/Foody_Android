package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.R;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> foodList;
    public FoodAdapter(Context context, int layout, List<String> foodList) {
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
        TextView foodName = view.findViewById(R.id.food_name_shop);
        TextView foodDescription = view.findViewById(R.id.food_description);
        TextView foodPrice = view.findViewById(R.id.food_price);
        ImageView foodImage=view.findViewById(R.id.img_food);

        foodName.setText("Giang");
        foodDescription.setText("Giang");
        foodPrice.setText("Giang");
        foodImage.setImageResource(R.drawable.hamburger);
        return view;
    }
}
