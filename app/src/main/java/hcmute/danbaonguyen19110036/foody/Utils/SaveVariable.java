package hcmute.danbaonguyen19110036.foody.Utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.User;

public class SaveVariable {
    public static String RAZOR_PAY_ID="rzp_test_BtPaDjeiqumbdB";
    public static String STRAPI_SECRET_KEY= "sk_test_51LBE2tGYjRpCLfFk4ujBumVXFeCnDiKQM519vEVKmy6PnAdS8C2BwsobtZcUt9zUtFVQgGfKp2y4ivKtgmDg3EGD00zuMilVsK";
    public static String STRAPI_PUBLISH_KEY="pk_test_51LBE2tGYjRpCLfFki4LXz8wwJWun3F5qGbVqMiDuZ2dspu3xuQt2c3hm3bqG3LJbUdTiVKZb956QUxXcXOfpyNlv00Cs9uYEDF";
    public static User user;
    public static Shop shop;
    public static Food food;
    public static String customerID;
    public static String EphericalKey;
    public static String ClientSecret;
    public static List<CartModel> cartModelList;
    public static List<NotificationModel> notificationModelList;
    public static int totalPrice;
    public static int TotalPrice(){
        totalPrice=0;
        for(int i=0;i<SaveVariable.cartModelList.size();i++){
            totalPrice = totalPrice + SaveVariable.cartModelList.get(i).getTotalPrice();
        }
        return totalPrice;
    }
    public static byte[] ImageView_To_Byte(ImageView img){
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
