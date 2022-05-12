package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.material.tabs.TabLayout;
import hcmute.danbaonguyen19110036.foody.Adapter.LoginAdapter;
import hcmute.danbaonguyen19110036.foody.R;


public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LoginAdapter loginAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        tabLayout=findViewById(R.id.tab_layout_login);
        viewPager=findViewById(R.id.view_pager_login);
        loginAdapter=new LoginAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(loginAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //Tab Register
    }
    public void ForgotPassword(View view){
        startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
    }

}