package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.FoodAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
public class OrdersActivity extends AppCompatActivity {
    private FoodDao foodDao;
    private ShopDao shopDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        connectData();
        ListView listViewFood = findViewById(R.id.listview_food);
        final List<Food> foodArrayList = foodDao.loadAll();
        final List<Shop> shopList = shopDao.loadAll();
        System.out.println(foodArrayList.size());
        System.out.println(shopList.size());
        FoodAdapter foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
    }
    private void connectData(){
        shopDao = DatabaseApplication.Instance().createShopDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void backShop(View view){
        startActivity(new Intent(OrdersActivity.this,ProductDetail.class));
    }
}