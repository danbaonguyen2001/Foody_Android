package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import java.util.List;
import hcmute.danbaonguyen19110036.foody.Adapter.ShopAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
public class SearchActivity extends AppCompatActivity {
    private ShopDao shopDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        ConnectDatabase();
        ListView listViewShop = findViewById(R.id.listview_food_search);
        final List<Shop> shopArrayList = shopDao.loadAll();
        ShopAdapter shopAdapter = new ShopAdapter(SearchActivity.this,R.layout.layout_search,shopArrayList);
        listViewShop.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();
    }
    public void backHome(View view){
        startActivity(new Intent(SearchActivity.this,HomeActivity.class));
    }
    public void ConnectDatabase(){
        shopDao = DatabaseApplication.Instance().createShopDao();
    }
}