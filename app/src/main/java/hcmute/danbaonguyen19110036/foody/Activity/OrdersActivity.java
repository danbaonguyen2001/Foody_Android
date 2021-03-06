package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.FoodAdapter;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.BlackSugar;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.Buble;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.EggPudding;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.FoodDrinks;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.FruitPudding;
import hcmute.danbaonguyen19110036.foody.Pattern.Decorator.IDrinks;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.CartModel;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class OrdersActivity extends AppCompatActivity {
    private FoodDao foodDao;
    private ShopDao shopDao;
    ListView listViewFood;
    TextView textViewShopName,textViewShopAddress,textViewDialog,tvItemName,tvItemDescription,textViewPrice,tvPriceItem,textViewAddtoCart;
    Button btnClose;
    EditText editTextSearch;
    ImageView imageViewAdd,imageViewSub,imageViewBanner,imageViewItemDialog;
    FoodAdapter foodAdapter;
    Model model;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    public List<Food> foodArrayList;
    public static int quantity;
    public static int price;
    private BlackSugar blackSugar;
    private Buble buble;
    private FruitPudding fruitPudding;
    private EggPudding eggPudding;
    private FoodDrinks drinks;
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
        foodArrayList = foodDao.queryBuilder().where(FoodDao.Properties.ShopId.eq(SaveVariable.shop.getId())).list();
        foodAdapter = new FoodAdapter(OrdersActivity.this,R.layout.layout_food,foodArrayList);
        listViewFood.setAdapter(foodAdapter);
        textViewShopName = findViewById(R.id.tvShopNameMenu);
        textViewShopAddress = findViewById(R.id.tvShopAddressMenu);
        imageViewBanner = findViewById(R.id.order_banner);
        imageViewBanner.setImageResource(SaveVariable.shop.getPath());
        textViewShopName.setText(SaveVariable.shop.getShopname());
        textViewShopAddress.setText(SaveVariable.shop.getAddress());
        model = new Model(this);
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
        price = quantity*food.getPrice();
        drinks = new FoodDrinks(price);
        textViewPrice.setText(String.valueOf(drinks.Price()));
        imageViewItemDialog.setImageResource(food.getPath());
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox1.isChecked()){
                    blackSugar = new BlackSugar(drinks);
                    drinks.setTotalPrice(blackSugar.Price());
                }
                else {
                    drinks.setTotalPrice(drinks.Price()- blackSugar.getBlackSugarPrice());
                }
                textViewPrice.setText(String.valueOf(drinks.Price()));
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox2.isChecked()){
                    buble = new Buble(drinks);
                    drinks.setTotalPrice(buble.Price());
                }
                else {
                    drinks.setTotalPrice(drinks.Price()- buble.getBublePrice());
                }
                textViewPrice.setText(String.valueOf(drinks.Price()));
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox3.isChecked()){
                    fruitPudding = new FruitPudding(drinks);
                    drinks.setTotalPrice(fruitPudding.Price());
                }
                else {
                    drinks.setTotalPrice(drinks.Price()- fruitPudding.getFruitPrice());
                }
                textViewPrice.setText(String.valueOf(drinks.Price()));
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox4.isChecked()){
                    eggPudding = new EggPudding(drinks);
                    drinks.setTotalPrice(eggPudding.Price());
                }
                else {
                    drinks.setTotalPrice(drinks.Price()- eggPudding.getEggPuddingPrice());
                }
                textViewPrice.setText(String.valueOf(drinks.Price()));

            }
        });
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                drinks.setTotalPrice(drinks.Price()+food.getPrice());
                textViewDialog.setText(String.valueOf(quantity));
                textViewPrice.setText(String.valueOf(drinks.Price()));
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
                drinks.setTotalPrice(drinks.Price()-food.getPrice());
                textViewPrice.setText(String.valueOf(drinks.Price()));
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        textViewAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food.setPrice((int)drinks.Price());
                CartModel cartModel = new CartModel(quantity,food);
                if(SaveVariable.cartModelList==null){
                    SaveVariable.cartModelList = new ArrayList<>();
                }
                SaveVariable.cartModelList.add(cartModel);
                model.saveItemCart();
                dialog.dismiss();
                Toast.makeText(OrdersActivity.this,"Add to cart success",Toast.LENGTH_SHORT).show();
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
        textViewAddtoCart = (TextView) dialog.findViewById(R.id.tvAddToCart);
        imageViewItemDialog = (ImageView) dialog.findViewById(R.id.imgItemDialog);
        checkBox1=(CheckBox) dialog.findViewById(R.id.checkbox_1);
        checkBox2=(CheckBox) dialog.findViewById(R.id.checkbox_2);
        checkBox3=(CheckBox) dialog.findViewById(R.id.checkbox_3);
        checkBox4=(CheckBox) dialog.findViewById(R.id.checkbox_4);
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