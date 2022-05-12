package hcmute.danbaonguyen19110036.foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import hcmute.danbaonguyen19110036.foody.Adapter.CartAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.CartModel;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class CartActivity extends AppCompatActivity {
    private Button btnCheckOut;
    private static TextView txtTotal,subTotal;
    private FoodDao foodDao;
    private ListView listView;
    private List<CartModel> cartModelList;
    private int totalPrice;
    Model model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);
        ConnectDatabase();
        AnhXa();
        model = new Model(this);
        model.loadData();
        cartModelList = SaveVariable.cartModelList;
        if (cartModelList==null){
            cartModelList = new ArrayList<>();
        }
        totalPrice = SaveVariable.TotalPrice();
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this,R.layout.cart_items,cartModelList);
        listView.setAdapter(cartAdapter);
        txtTotal.setText(String.valueOf(totalPrice));
        subTotal.setText(String.valueOf(totalPrice));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    public void ConnectDatabase(){

        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void backHome(View view){
        startActivity(new Intent(CartActivity.this,HomeActivity.class));
    }
    public void AnhXa(){
        listView = findViewById(R.id.listview_cart);
        btnCheckOut=findViewById(R.id.btnCheckOut);
        txtTotal=findViewById(R.id.tvTotalPrice);
        subTotal=findViewById(R.id.subtotal_cart);
    }
    public static void setPrice(){
        txtTotal.setText(String.valueOf(SaveVariable.TotalPrice()));
        subTotal.setText(String.valueOf(SaveVariable.TotalPrice()));
    }
}
