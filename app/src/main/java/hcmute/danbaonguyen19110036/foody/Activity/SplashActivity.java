package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Category;
import hcmute.danbaonguyen19110036.foody.Database.CategoryDao;
import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        },2000);
    }
}