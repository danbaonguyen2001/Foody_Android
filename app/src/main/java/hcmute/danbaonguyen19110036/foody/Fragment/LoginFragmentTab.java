package hcmute.danbaonguyen19110036.foody.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.danbaonguyen19110036.foody.Database.DaoMaster;
import hcmute.danbaonguyen19110036.foody.Database.DaoSession;
import hcmute.danbaonguyen19110036.foody.Database.UserDao;
import hcmute.danbaonguyen19110036.foody.R;

public class LoginFragmentTab extends Fragment  {
    private Button btnLogin,btnRegister;
    private UserDao userDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //connectData();
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        btnLogin= (Button) view.findViewById(R.id.btnLogin);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("123");
//            }
//        });

        return view;
    }



//    private void connectData(){
//        userDao = createUserDao();
//    }
//    private UserDao createUserDao(){
//        DaoSession masterSession = getData("User");
//        return masterSession.getUserDao();
//    }
//    private DaoSession getData(String tablename){
//        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(getApplication(),tablename,null);
//        SQLiteDatabase db = master.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession masterSession = daoMaster.newSession();
//        return masterSession;
//    }
}
