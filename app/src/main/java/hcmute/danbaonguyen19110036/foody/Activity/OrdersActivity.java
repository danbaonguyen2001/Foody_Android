package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class OrdersActivity extends AppCompatActivity {
    private FoodDao foodDao;
    private ShopDao shopDao;
    ListView listViewFood;
    TextView textViewShopName,textViewShopAddress,textViewDialog,tvItemName,tvItemDescription,textViewPrice,tvPriceItem;
    Button btnClose;
    EditText editTextSearch;
    ImageView imageViewAdd,imageViewSub;
    FoodAdapter foodAdapter;
    public List<Food> foodArrayList;
    public static int quantity;
    public static int price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_orders);
        ConnectData();
        listViewFood =(ListView) findViewById(R.id.listview_food);
        editTextSearch = (EditText) findViewById(R.id.edtFoodName);
        foodArrayList = foodDao.queryBuilder().where(FoodDao.Properties.CategoryId.eq(SaveVariable.shop.getId())).list();
        System.out.println(foodArrayList.size());
        foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
        textViewShopName = findViewById(R.id.tvShopNameMenu);
        textViewShopAddress = findViewById(R.id.tvShopAddressMenu);
        textViewShopName.setText(SaveVariable.shop.getShopname());
        textViewShopAddress.setText(SaveVariable.shop.getAddress());
       listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               openDialog(Gravity.BOTTOM,foodArrayList.get(i));

           }
       });
    }
    private void ConnectData(){
        shopDao = DatabaseApplication.Instance().createShopDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void backShop(View view){
        startActivity(new Intent(OrdersActivity.this,ProductDetail.class));
    }
    public void openDialog(int gravity,Food food){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_add_item);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if(Gravity.BOTTOM==gravity){
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }
        AnhXa(dialog);
        tvItemName.setText(food.getFoodname());
        tvItemDescription.setText(food.getDescription());
        tvPriceItem.setText(String.valueOf(food.getPrice()));
        quantity = Integer.parseInt(textViewDialog.getText().toString());
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                textViewDialog.setText(String.valueOf(quantity));
                price = quantity*food.getPrice();
                textViewPrice.setText(String.valueOf(price));
            }
        });
        imageViewSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity==1){
                    return;
                }
                quantity--;
                textViewDialog.setText(String.valueOf(quantity));
                price = quantity*food.getPrice();
                textViewPrice.setText(String.valueOf(price));
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void AnhXa(Dialog dialog){
        btnClose = (Button) dialog.findViewById(R.id.btn_Close_Dialog);
        imageViewAdd = (ImageView) dialog.findViewById(R.id.imgPlus);
        imageViewSub = (ImageView) dialog.findViewById(R.id.imgSub);
        textViewDialog = (TextView) dialog.findViewById(R.id.tvQuantity);
        tvItemName = (TextView) dialog.findViewById(R.id.tvItemName);
        tvItemDescription = (TextView) dialog.findViewById(R.id.tvItemDescription);
        textViewPrice = (TextView) dialog.findViewById(R.id.tvPriceTotal);
        tvPriceItem = (TextView) dialog.findViewById(R.id.tvPriceItem);
    }

    public void Search(View view){
        String q = editTextSearch.getText().toString();
        if(q.isEmpty()){
            return;
        }
        foodArrayList= foodDao.queryBuilder().where(FoodDao.Properties.Foodname.like("%" + q + "%")).list();
        foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
    }
}