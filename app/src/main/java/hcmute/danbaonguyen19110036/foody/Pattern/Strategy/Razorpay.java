package hcmute.danbaonguyen19110036.foody.Pattern.Strategy;

import android.app.Activity;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.stripe.android.paymentsheet.PaymentSheet;

import org.json.JSONException;
import org.json.JSONObject;

import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class Razorpay implements PaymentMethodStrategy {
    @Override
    public void checkout(Activity activity, User user) {
        Checkout checkout = new Checkout();
        checkout.setKeyID(SaveVariable.RAZOR_PAY_ID);
        checkout.setImage(R.drawable.ic_launcher_background);
        JSONObject object= new JSONObject();
        try {
            object.put("name",user.getUsername());
            object.put("description","Test payment");
            object.put("currency","INR");
            object.put("amount","100");
            object.put("prefill.contact",user.getPhone());
            object.put("prefill.email","giangnguyen3246@gmail.com");
            checkout.open(activity,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
