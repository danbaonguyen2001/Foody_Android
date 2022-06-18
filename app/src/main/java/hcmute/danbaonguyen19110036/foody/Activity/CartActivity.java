package hcmute.danbaonguyen19110036.foody.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;;
import com.razorpay.PaymentResultListener;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import java.util.ArrayList;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Adapter.CartAdapter;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderDao;
import hcmute.danbaonguyen19110036.foody.Database.OrderItemDao;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.CancelFood;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.FoodApp;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.FoodReceiver;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.OrderFood;
import hcmute.danbaonguyen19110036.foody.Pattern.Strategy.CheckoutContext;
import hcmute.danbaonguyen19110036.foody.Pattern.Strategy.Razorpay;
import hcmute.danbaonguyen19110036.foody.Pattern.Strategy.Strapi;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.CartModel;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class CartActivity extends AppCompatActivity implements PaymentResultListener{
    private static TextView txtTotal,subTotal;
    private FoodDao foodDao;
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private ListView listView;
    private List<CartModel> cartModelList;
    private int totalPrice;
    private Model model;
    private ConstraintLayout constraintLayoutSuccess;
    private PaymentSheet paymentSheet;
    private FoodReceiver foodReceiver = new FoodReceiver();
    private OrderFood orderFood = new OrderFood(foodReceiver);
    private FoodApp foodApp = new FoodApp(orderFood);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);
        PaymentConfiguration.init(this,SaveVariable.STRAPI_PUBLISH_KEY);
        paymentSheet= new PaymentSheet(this,paymentSheetResult -> {
            onPaymentResult(paymentSheetResult);
        });
        ConnectDatabase();
        AnhXa();
        CheckoutContext context =new CheckoutContext(new Strapi());
        context.Checkout(CartActivity.this,SaveVariable.user);
        model = new Model(this);
        model.loadData();
        cartModelList = SaveVariable.cartModelList;
        if (cartModelList==null){
            cartModelList = new ArrayList<>();
        }
        totalPrice = SaveVariable.TotalPrice();
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this,R.layout.cart_items,cartModelList);
        listView.setAdapter(cartAdapter);
        txtTotal.setText(String.valueOf(totalPrice));
        subTotal.setText(String.valueOf(totalPrice));
    }
    public void ConnectDatabase(){
        orderDao = DatabaseApplication.Instance().createOrderDao();
        orderItemDao = DatabaseApplication.Instance().createOrderItemDao();
        foodDao = DatabaseApplication.Instance().createFoodDao();
    }
    public void backHome(View view){
        startActivity(new Intent(CartActivity.this,HomeActivity.class));
    }
    public void AnhXa(){
        listView = findViewById(R.id.listview_cart);
        txtTotal=findViewById(R.id.tvTotalPrice);
        subTotal=findViewById(R.id.subtotal_cart);
        constraintLayoutSuccess = findViewById(R.id.wrapper_ordersuccess);
    }
    public static void setPrice(){
        txtTotal.setText(String.valueOf(SaveVariable.TotalPrice()));
        subTotal.setText(String.valueOf(SaveVariable.TotalPrice()));
    }
    public void CheckOut(View view){
        openDialog(Gravity.CENTER);
    }
    public void openDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_option_payment);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        ConstraintLayout paypal = dialog.findViewById(R.id.paypal);
        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentFlow();
                dialog.dismiss();
            }
        });
        ConstraintLayout mm = dialog.findViewById(R.id.momo);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutContext context = new CheckoutContext(new Razorpay());
                context.Checkout(CartActivity.this,SaveVariable.user);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if(paymentSheetResult instanceof PaymentSheetResult.Completed){
            Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show();
        }
    }
    private void PaymentFlow() {
        paymentSheet.presentWithPaymentIntent(
                SaveVariable.ClientSecret,new PaymentSheet.Configuration("Nguyen company",
                        new PaymentSheet.CustomerConfiguration(
                                SaveVariable.customerID,
                                SaveVariable.EphericalKey
                        ))
        );
    }

    @Override
    public void onPaymentSuccess(String s) {
        foodApp.ClickOrderFood();
        listView.setVisibility(View.INVISIBLE);
        constraintLayoutSuccess.setVisibility(View.VISIBLE);
        Toast.makeText(CartActivity.this,"Order success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}
