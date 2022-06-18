package hcmute.danbaonguyen19110036.foody.Pattern.Strategy;

import android.app.Activity;


import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class CheckoutContext {
    public PaymentMethodStrategy paymentMethodStrategy;
    public CheckoutContext(PaymentMethodStrategy paymentMethodStrategy) {
        this.paymentMethodStrategy = paymentMethodStrategy;
    }
    public void Checkout(Activity activity, User user){
        paymentMethodStrategy.checkout(activity, user);
    }

}
