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

    private UserDao userDao;
    private CategoryDao categoryDao;
    private ShopDao shopDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private FoodDao foodDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        initDatabase();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
            }
        },2000);
    }
    public void initDatabase(){
        userDao = createUserDao();
        categoryDao = createCategoryDao();
        shopDao = createShopDao();
        orderDao = createOrderDao();
        orderItemDao = createOrderItemDao();
        foodDao = createFoodDao();
//        createData();
    }
    private UserDao createUserDao(){
        DaoSession masterSession = createTable("User");
        return masterSession.getUserDao();
    }
    private CategoryDao createCategoryDao(){
        DaoSession masterSession = createTable("Category");
        return masterSession.getCategoryDao();
    }
    private FoodDao createFoodDao(){
        DaoSession masterSession = createTable("Food");
        return masterSession.getFoodDao();
    }
    private OrderDao createOrderDao(){
        DaoSession masterSession = createTable("Order");
        return masterSession.getOrderDao();
    }
    private OrderItemDao createOrderItemDao(){
        DaoSession masterSession = createTable("OrderItem");
        return masterSession.getOrderItemDao();
    }
    private ShopDao createShopDao(){
        DaoSession masterSession = createTable("Shop");
        return masterSession.getShopDao();
    }
    private DaoSession createTable(String tablename){
        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(getApplication(),tablename,null);
        SQLiteDatabase db = master.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession masterSession = daoMaster.newSession();
        return masterSession;
    }

    private void createData(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Foods");
        categoryList.add("Drinks");
        categoryList.add("Snacks");
        categoryList.add("Vegetables");
        for(int i=0;i<4;i++){
            Category c = new Category(null,categoryList.get(i));
            categoryDao.insert(c);
        }
        List<String> shopList = new ArrayList<>();
        shopList.add("Shop1");
        shopList.add("Shop2");
        for(int i=0;i<2;i++){
            Shop s = new Shop(null,shopList.get(i),"9:00-23:00","15000-30000");
            shopDao.insert(s);
        }
        List<String> foodList = new ArrayList<>();
        foodList.add("BanhCanhCua");
        foodList.add("BunBo");
        foodList.add("PhoBo");
        foodList.add("NemNuong");
        foodList.add("ComTam");
        for(int i=0;i<5;i++){
            Food f = new Food(null,foodList.get(i),"No Description",15000,R.drawable.hamburger,1L,1L);
            foodDao.insert(f);
        }
    }
}