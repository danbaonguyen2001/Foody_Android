package hcmute.danbaonguyen19110036.foody.Database;

import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Database extends AppCompatActivity {
    private UserDao userDao;
    private CategoryDao categoryDao;
    public Database(){
        userDao = createUserDao();
    }
    public void initDatabase(){
        userDao = createUserDao();
    }
    private UserDao createUserDao(){
        DaoSession masterSession = createTable("User");
        return masterSession.getUserDao();
    }
    private CategoryDao createCategoryDao(){

        DaoSession masterSession = createTable("Category");
        return masterSession.getCategoryDao();
    }
    private DaoSession createTable(String tablename){
        DaoMaster.DevOpenHelper master = new DaoMaster.DevOpenHelper(getApplication(),tablename,null);
        SQLiteDatabase db = master.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession masterSession = daoMaster.newSession();
        return masterSession;
    }
}
