package hcmute.danbaonguyen19110036.foody.Database;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.R;

public class DatabaseApplication extends Application {
    public DaoSession daoSession;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private ShopDao shopDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private FoodDao foodDao;

    private static DatabaseApplication _instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        _instance=this;
        initDatabase();
    }
    public static DatabaseApplication Instance(){
        return _instance;
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
    public UserDao createUserDao(){
        DaoSession masterSession = createTable("User");
        return masterSession.getUserDao();
    }
    public CategoryDao createCategoryDao(){
        DaoSession masterSession = createTable("Category");
        return masterSession.getCategoryDao();
    }
    public FoodDao createFoodDao(){
        DaoSession masterSession = createTable("Food");
        return masterSession.getFoodDao();
    }
    public OrderDao createOrderDao(){
        DaoSession masterSession = createTable("Order");
        return masterSession.getOrderDao();
    }
    public OrderItemDao createOrderItemDao(){
        DaoSession masterSession = createTable("OrderItem");
        return masterSession.getOrderItemDao();
    }
    public ShopDao createShopDao(){
        DaoSession masterSession = createTable("Shop");
        return masterSession.getShopDao();
    }
    public DaoSession createTable(String tablename){
        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(this,tablename,null);
        SQLiteDatabase db = master.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession masterSession = daoMaster.newSession();
        return masterSession;
    }

    public void createData(){
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
            Food f = new Food(null,foodList.get(i),"No Description",15000, R.drawable.hamburger,1L,1L);
            foodDao.insert(f);
        }
    }
}