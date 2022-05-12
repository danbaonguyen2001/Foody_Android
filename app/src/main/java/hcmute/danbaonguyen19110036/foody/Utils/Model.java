package hcmute.danbaonguyen19110036.foody.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Model {
    public Context context;
    public SharedPreferences sharedPreferences;

    public Model(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("name", 0);
    }

    public void loadData(){
        sharedPreferences = context.getSharedPreferences("listCart",0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart list",null);
        Type type = new TypeToken<ArrayList<CartModel>>(){}.getType();
        SaveVariable.cartModelList = gson.fromJson(json,type);
        if (SaveVariable.cartModelList==null){
            SaveVariable.cartModelList = new ArrayList<>();
        }
    }
    public void saveItemCart(){
        sharedPreferences = context.getSharedPreferences("listCart",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SaveVariable.cartModelList);
        editor.putString("cart list",json);
        editor.apply();
    }

}