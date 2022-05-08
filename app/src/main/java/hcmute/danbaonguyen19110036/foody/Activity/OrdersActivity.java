package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.FoodAdapter;
import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.R;
public class OrdersActivity extends AppCompatActivity {
    private FoodDao foodDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        connectData();
        ListView listViewFood = findViewById(R.id.listview_food);
        final List<Food> foodArrayList = foodDao.queryBuilder().orderAsc(FoodDao.Properties.Id).build().list();
        System.out.println(foodArrayList.size());
        FoodAdapter foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
    }
    private void connectData(){
        foodDao = createFoodDao();
    }
    private FoodDao createFoodDao(){
        DaoSession masterSession = getData("Food");
        return masterSession.getFoodDao();
    }
    private DaoSession getData(String tablename){
        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(getApplication(),tablename,null);
        SQLiteDatabase db = master.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession masterSession = daoMaster.newSession();
        return masterSession;
    }
}