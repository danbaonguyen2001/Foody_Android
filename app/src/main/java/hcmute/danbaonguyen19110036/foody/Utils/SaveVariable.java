package hcmute.danbaonguyen19110036.foody.Utils;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.User;

public class SaveVariable {
    public static User user;
    public static Shop shop;
    public static Food food;
    public static List<CartModel> cartModelList;
    public static int totalPrice;
    public static int TotalPrice(){
        totalPrice=0;
        for(int i=0;i<SaveVariable.cartModelList.size();i++){
            totalPrice = totalPrice + SaveVariable.cartModelList.get(i).getTotalPrice();
        }
        return totalPrice;
    }
}
