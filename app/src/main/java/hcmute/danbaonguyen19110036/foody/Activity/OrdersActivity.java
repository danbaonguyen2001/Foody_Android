package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import hcmute.danbaonguyen19110036.foody.Adapter.FoodAdapter;
import hcmute.danbaonguyen19110036.foody.R;
public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ListView listViewFood = findViewById(R.id.listview_food);
        ArrayList<String> foodArrayList = new ArrayList<>();
        foodArrayList.add("G");
        FoodAdapter foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
    }
}