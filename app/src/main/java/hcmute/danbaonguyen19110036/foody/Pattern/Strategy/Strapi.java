package hcmute.danbaonguyen19110036.foody.Pattern.Strategy;

import android.app.Activity;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hcmute.danbaonguyen19110036.foody.Database.User;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class Strapi implements PaymentMethodStrategy{
    private Activity activity1;
    @Override
    public void checkout(Activity activity, User user) {
        activity1 = activity;
        execute();
    }
    public void execute(){
        PaymentConfiguration.init(activity1, SaveVariable.STRAPI_PUBLISH_KEY);
        StringRequest stringRequest= new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            SaveVariable.customerID=object.getString("id");
                            getEphericalKey(SaveVariable.customerID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header= new HashMap<>();
                header.put("Authorization","Bearer "+SaveVariable.STRAPI_SECRET_KEY);
                return header;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(activity1);
        requestQueue.add(stringRequest);
    }
    private void getEphericalKey(String customerID) {
        StringRequest stringRequest= new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            SaveVariable.EphericalKey=object.getString("id");
                            getClientSecret(SaveVariable.customerID,SaveVariable.EphericalKey);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header= new HashMap<>();
                header.put("Authorization","Bearer "+SaveVariable.STRAPI_SECRET_KEY);
                header.put("Stripe-Version","2020-08-27");
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("customer",SaveVariable.customerID);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(activity1);
        requestQueue.add(stringRequest);
    }

    private void getClientSecret(String customerID, String ephericalKey) {
        StringRequest stringRequest= new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            SaveVariable.ClientSecret=object.getString("client_secret");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header= new HashMap<>();
                header.put("Authorization","Bearer "+SaveVariable.STRAPI_SECRET_KEY);
                return header;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("customer",SaveVariable.customerID);
                params.put("amount","1000"+"00");
                params.put("currency","usd");
                params.put("automatic_payment_methods[enabled]" +
                        "","true");
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(activity1);
        requestQueue.add(stringRequest);
    }
}
