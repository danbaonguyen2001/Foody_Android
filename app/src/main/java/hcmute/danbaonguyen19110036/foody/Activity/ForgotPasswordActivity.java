package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayoutSendCode,constraintLayoutNotification;
    private EditText editTextEnterEmail;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);
        AnhXa();
        ConnectDatabase();
    }
    public void backLogin(View view){
        startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
    }
    public void AnhXa(){
        constraintLayoutSendCode = findViewById(R.id.wrapper_forgotpassword);
        constraintLayoutNotification = findViewById(R.id.notification_forgotpassword);
        editTextEnterEmail = findViewById(R.id.edt_enter_email);
    }
    public void ConnectDatabase(){
        userDao = DatabaseApplication.Instance().createUserDao();
    }
    public void SendCode(View view){
        if(editTextEnterEmail.getText().toString().isEmpty()){
            Toast.makeText(ForgotPasswordActivity.this,"Please enter your Email !",Toast.LENGTH_SHORT).show();
            return;
        }
        List<User> users = userDao.queryBuilder().where(UserDao.Properties.Username.eq(editTextEnterEmail.getText().toString())).list();
        if(users.size()==0){
            Toast.makeText(ForgotPasswordActivity.this,"Email is not registed",Toast.LENGTH_SHORT).show();
            return;
        }
        constraintLayoutSendCode.setVisibility(View.INVISIBLE);
        constraintLayoutNotification.setVisibility(View.VISIBLE);
    }
}