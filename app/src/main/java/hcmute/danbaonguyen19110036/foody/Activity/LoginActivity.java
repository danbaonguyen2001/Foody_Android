package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import hcmute.danbaonguyen19110036.foody.Adapter.LoginAdapter;
import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.Fragment.SignupFragmentTab;
import hcmute.danbaonguyen19110036.foody.R;


public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LoginAdapter loginAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout=findViewById(R.id.tab_layout_login);
        viewPager=findViewById(R.id.view_pager_login);

        loginAdapter=new LoginAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(loginAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //Tab Register
    }

}