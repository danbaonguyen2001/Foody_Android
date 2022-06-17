package hcmute.danbaonguyen19110036.foody.Pattern.Strategy;

import android.app.Activity;

import com.stripe.android.paymentsheet.PaymentSheet;

public class CheckoutContext {
    public PaymentMethodStrategy paymentMethodStrategy;
    public CheckoutContext(PaymentMethodStrategy paymentMethodStrategy) {
        this.paymentMethodStrategy = paymentMethodStrategy;
    }
    public void Checkout(Activity activity){
        paymentMethodStrategy.checkout(activity);
    }
}
