package hcmute.danbaonguyen19110036.foody.Pattern.Strategy;

import android.app.Activity;

import com.stripe.android.paymentsheet.PaymentSheet;

public interface PaymentMethodStrategy {
    void checkout(Activity activity);
}
