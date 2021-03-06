package hcmute.danbaonguyen19110036.foody.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.greenrobot.greendao.query.QueryBuilder;
import hcmute.danbaonguyen19110036.foody.Activity.LoginActivity;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;

public class SignupFragmentTab extends Fragment {
    private EditText nameUser,phoneUser,passUser,confirmpassUser;
    public String username="",phoneNumber="",password="",confirmPassword="";
    public Button buttonSignup;
    public UserDao userDao;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_tab_fragment, container, false);
        nameUser=view.findViewById(R.id.edtFullname);
        phoneUser=view.findViewById(R.id.edtPhone);
        passUser=view.findViewById(R.id.edtPassword);
        confirmpassUser=view.findViewById(R.id.edtConfirmPass);
        buttonSignup = view.findViewById(R.id.btnSignup);
        ConnectData();
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();

                if (username.isEmpty()||phoneNumber.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                Register();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }
    public void ConnectData(){
        userDao = DatabaseApplication.Instance().createUserDao();
    }
    public void getValue(){
        username = nameUser.getText().toString();
        phoneNumber = phoneUser.getText().toString();
        password = passUser.getText().toString();
        confirmPassword = confirmpassUser.getText().toString();
    }
    public void Register(){
        QueryBuilder<User> isExistingUser = userDao.queryBuilder().where(UserDao.Properties.Username.eq(username));
        if (isExistingUser.list().size()!=0){
            Toast.makeText(getActivity(), "UserName existing. Please Choose new UserName", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(confirmPassword)){
            User user = new User(null,username,phoneNumber,password);
            userDao.insert(user);
            Toast.makeText(getActivity(), "SignUp success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
        }
        return;
    }
    public void OnClickReturnLogin(View view){

    }
}
