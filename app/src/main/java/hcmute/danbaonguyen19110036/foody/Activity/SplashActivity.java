package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class SplashActivity extends AppCompatActivity {
    public FoodDao foodDao;
    public ShopDao shopDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        Model model = new Model(this);
        model.loadData();
        ConnectDatabase();
        SaveVariable.notificationModelList = new ArrayList<>();
//        createDataShop();
//        createDataFood();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
            }
        },2000);

    }

    public void ConnectDatabase(){
        foodDao = DatabaseApplication.Instance().createFoodDao();
        shopDao = DatabaseApplication.Instance().createShopDao();
    }

    public void createDataFood(){
        Food food1 = new Food(null,"Bò Tơ Củ Chi","Giao Hàng Tận Nơi, Huyện Hóc Môn, TP. HCM",15000,R.drawable.food4,1L,2L);
        Food food2 = new Food(null,"Bánh 9 Sạch - Bánh Sầu Riêng","Giao Hàng Tận Nơi, Huyện Hóc Môn, TP. HCM",15000,R.drawable.food1,1L,2L);
        Food food3 = new Food(null,"Bánh 9 Sạch - Bánh Pia","Giao Hàng Tận Nơi, Huyện Hóc Môn, TP. HCM",15000,R.drawable.food2,1L,2L);
        Food food4 = new Food(null,"Bánh 9 Sạch - Bánh Mit","Giao Hàng Tận Nơi, Huyện Hóc Môn, TP. HCM",15000,R.drawable.food3,1L,2L);
        //===========
        Food food5 = new Food(null,"Tốc Trà","Trà sữa ngon, vừa túi tiền.",25000,R.drawable.food6,2L,1L);
        Food food6 = new Food(null,"Trung Nguyên Legend Coffee","Coffee sữa ngon, vừa túi tiền.",25000,R.drawable.food9,2L,1L);
        Food food7 = new Food(null,"Đen Đá Cafe","Coffee sữa ngon, hơi đắt.",27000,R.drawable.food7,2L,1L);
        Food food8 = new Food(null,"Mai Bingsu","Bingsu ngon, vừa túi tiền.",35000,R.drawable.food8,1L,1L);
        foodDao.insert(food1);
        foodDao.insert(food2);
        foodDao.insert(food4);
        foodDao.insert(food3);
        //===========
        foodDao.insert(food5);
        foodDao.insert(food6);
        foodDao.insert(food7);
        foodDao.insert(food8);
    }
    public void createDataShop(){
        Shop s1 = new Shop(null, "Uncle Tea - Trà Đài Loan - Lão Tử", "9:00-20:00", "15000-30000","Quan 1 , HCM", R.drawable.location1);
        Shop s2 = new Shop(null, "Bánh 9 Sạch - Bánh Sầu Riêng - Đường Số 7", "7:00-10:00", "20000-50000","Quan 2 , HCM", R.drawable.location2);
        Shop s3 = new Shop(null, "Nẫu Quán - Ẩm Thực Bình Định - Bàu Cát 1", "8:00-18:00", "50000-80000","Quan 3 , HCM", R.drawable.location3);
        Shop s4 = new Shop(null, "Út Tiêu - Bún Nước Mì Trộn", "12:00-15:00", "22000-57000","Quan 4 , HCM", R.drawable.location4);
        Shop s5 = new Shop(null, "Tốc Trà - Cao Thắng", "6:00-22:00", "50000-100000","Quan 5 , HCM", R.drawable.location5);
        Shop s6 = new Shop(null, "Biscotti - Bánh Hạt Dinh Dưỡng - Phú Thọ Hòa", "9:00-20:00", "150000-200000","Quan 6 , HCM", R.drawable.location6);
        Shop s7 = new Shop(null, "Trung Nguyên Legend Coffee - 184 Lê Đại Hành", "13:00-20:30", "35000-90000","Quan 7 , HCM", R.drawable.location7);
        Shop s8 = new Shop(null, "Thế Giới Cuốn Bỏ Thêm - Gỏi Cuốn & Phở Cuốn Các Loại", "9:00-21:15", "15000-60000","Quan 8 , HCM", R.drawable.location8);
        shopDao.insert(s1);
        shopDao.insert(s2);
        shopDao.insert(s3);
        shopDao.insert(s4);
        shopDao.insert(s5);
        shopDao.insert(s6);
        shopDao.insert(s7);
        shopDao.insert(s8);
    }
}