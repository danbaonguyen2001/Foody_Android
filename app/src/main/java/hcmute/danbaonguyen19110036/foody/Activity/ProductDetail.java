package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import hcmute.danbaonguyen19110036.foody.R;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_detail);
    }
    public void backHome(View view){
        startActivity(new Intent(ProductDetail.this,HomeActivity.class));
    }
    public void Delivery(View view){
        startActivity(new Intent(ProductDetail.this,OrdersActivity.class));
    }
}