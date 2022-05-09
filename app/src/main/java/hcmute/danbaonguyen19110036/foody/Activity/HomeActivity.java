package hcmute.danbaonguyen19110036.foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import hcmute.danbaonguyen19110036.foody.Adapter.FragmentAdapter;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.UserToken;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    TabLayout tabLayout;
    FragmentAdapter fragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        viewPager=findViewById(R.id.vpg1);
        tabLayout = findViewById(R.id.tbl1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager,getLifecycle());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_favorite_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_assignment_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_notifications_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_account_circle_24));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    public void OnClickCart(View view){
        startActivity(new Intent(HomeActivity.this,CartActivity.class));
    }
    public void OnClickLogout(View view){
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }
    public void OnClickSearch(View view){
        startActivity(new Intent(HomeActivity.this,SearchActivity.class));
    }
}
