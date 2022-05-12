package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Category;
import hcmute.danbaonguyen19110036.foody.Database.CategoryDao;
import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.CartModel;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class SplashActivity extends AppCompatActivity {
    public FoodDao foodDao;
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        Model model = new Model(this);
        model.loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        },2000);
//        foodDao = DatabaseApplication.Instance().createFoodDao();
//        imageView = (ImageView) findViewById(R.id.img_test);
//        createDataFood(imageView);
    }
    public void createDataFood(ImageView imageView){
        List<String> foodList = new ArrayList<>();
        foodList.add("BanhCanhCua");
        foodList.add("BunBo");
        foodList.add("PhoBo");
        foodList.add("NemNuong");
        foodList.add("ComTam");
        for(int i=0;i<5;i++){
            Food f = new Food(null,foodList.get(i),"No Description",15000, ImageView_To_Byte(imageView),1L,1L);
            foodDao.insert(f);
        }
    }

    public byte[] ImageView_To_Byte(ImageView img){
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}