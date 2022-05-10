package hcmute.danbaonguyen19110036.foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.CartAdapter;
import hcmute.danbaonguyen19110036.foody.Adapter.FoodAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.R;

public class CartActivity extends AppCompatActivity {
    private Button btnCheckOut;
    private TextView txtTotal;
    private FoodDao foodDao;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);
        ConnectDatabase();
        listView = findViewById(R.id.listview_cart);
        final List<Food> foodArrayList = foodDao.loadAll();
        btnCheckOut=findViewById(R.id.btnCheckOut);
        txtTotal=findViewById(R.id.tvTotalPrice);
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this,R.layout.cart_items,foodArrayList);
        listView.setAdapter(cartAdapter);

    }
    public void ConnectDatabase(){
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void backHome(View view){
        startActivity(new Intent(CartActivity.this,HomeActivity.class));
    }
}
