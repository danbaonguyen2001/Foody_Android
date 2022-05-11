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
import java.util.List;
import hcmute.danbaonguyen19110036.foody.Activity.HomeActivity;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class LoginFragmentTab extends Fragment  {
    private static EditText nameUser,passUser;
    public String username="",password="";
    public Button buttonLogin;
    public UserDao userDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ConnectData();
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        ConnectData();
        nameUser=view.findViewById(R.id.edtUsername);
        passUser=view.findViewById(R.id.edtPassword);
        buttonLogin = view.findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(username.isEmpty()||password.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter your username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login();
            }
        });
        return view;
    }

    public void getValue(){
        username=nameUser.getText().toString();
        password=passUser.getText().toString();
    }
    public void Login(){
        List<User> isExistingUser = userDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).list();
        if (isExistingUser.size()==0){
            Toast.makeText(getActivity(), "Username not exist", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(isExistingUser.get(0).getPassword())){
            SaveVariable.user=isExistingUser.get(0);
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
        else {
            Toast.makeText(getActivity(), "Password not correct", Toast.LENGTH_SHORT).show();
        }
        return;
    }
    private void ConnectData(){
        userDao = DatabaseApplication.Instance().createUserDao();
    }
}
