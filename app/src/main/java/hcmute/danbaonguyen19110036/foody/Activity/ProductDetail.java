package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class ProductDetail extends AppCompatActivity {
    TextView textViewShopNameTitle,textViewShopName,textViewOpendoor,textViewShopAddress,textViewPriceRange;
    ImageView imageViewBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_detail);
        textViewShopNameTitle = findViewById(R.id.shop_name_title);
        textViewShopName = findViewById(R.id.shop_name);
        textViewOpendoor = findViewById(R.id.shop_opendoor);
        textViewPriceRange = findViewById(R.id.shop_pricerange);
        textViewShopAddress = findViewById(R.id.shop_address);
        imageViewBanner = findViewById(R.id.img_banner);
        imageViewBanner.setImageResource(SaveVariable.shop.getPath());
        textViewShopNameTitle.setText(SaveVariable.shop.getShopname());
        textViewShopName.setText(SaveVariable.shop.getShopname());
        textViewOpendoor.setText(SaveVariable.shop.getOpenDoor());
        textViewPriceRange.setText(SaveVariable.shop.getPricerange());
        textViewShopAddress.setText(SaveVariable.shop.getAddress());
    }
    public void backHome(View view){
        startActivity(new Intent(ProductDetail.this,HomeActivity.class));
    }
    public void Delivery(View view){
        startActivity(new Intent(ProductDetail.this,OrdersActivity.class));
    }
}