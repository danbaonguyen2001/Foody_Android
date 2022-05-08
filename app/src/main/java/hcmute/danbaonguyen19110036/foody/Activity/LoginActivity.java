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
import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;


public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LoginAdapter loginAdapter;

    private EditText nameUserR,phoneUserR,passUserR,confirmpassUserR;


    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectData();

        tabLayout=findViewById(R.id.tab_layout_login);
        viewPager=findViewById(R.id.view_pager_login);

        loginAdapter=new LoginAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(loginAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Tab Register
        nameUserR=(EditText)findViewById(R.id.edtFullname);
        phoneUserR=(EditText)findViewById(R.id.edtPhone);
        passUserR=(EditText)findViewById(R.id.edtPassword);
        confirmpassUserR=(EditText)findViewById(R.id.edtConfirmPass);



    }
    public void LoginOnclick(View view){
        System.out.println("Login");

    }
    public void RegisterOnclick(View view){
        System.out.println(nameUserR);
        createUser(nameUserR.getText().toString(),
                phoneUserR.getText().toString(),
                passUserR.getText().toString());

//        if(checkConfirmPass()){
//            System.out.println("123");
//
//        }

    }
    private void createUser(String username,String phone, String password){

        User user = new User(
                null,
                username,
                phone,
                password
        );

        userDao.insert(user);
    }

    private boolean checkConfirmPass(){
        System.out.println(passUserR.getText().toString());
        if(passUserR.getText().toString().equals(confirmpassUserR.getText().toString())){
            return true;
        }
        else {
            return false;
        }
    }

    private void connectData(){
        userDao = createUserDao();
    }
    private UserDao createUserDao(){
        DaoSession masterSession = getData("User");
        return masterSession.getUserDao();
    }
    private DaoSession getData(String tablename){
        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(getApplication(),tablename,null);
        SQLiteDatabase db = master.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession masterSession = daoMaster.newSession();
        return masterSession;
    }

}