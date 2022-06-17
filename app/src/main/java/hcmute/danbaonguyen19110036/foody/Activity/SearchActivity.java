package hcmute.danbaonguyen19110036.foody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import hcmute.danbaonguyen19110036.foody.Adapter.ShopAdapter;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class SearchActivity extends AppCompatActivity {
    private ShopDao shopDao;
    private EditText editTextSearch;
    private List<Shop> shopArrayList;
    private ListView listViewShop;
    private ShopAdapter shopAdapter;
    private ConstraintLayout constraintLayoutWarning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        ConnectDatabase();
        listViewShop = findViewById(R.id.listview_food_search);
        editTextSearch = findViewById(R.id.edt_searchShop);
        constraintLayoutWarning = findViewById(R.id.wrapper_search_warning);
        shopArrayList = shopDao.loadAll();
        shopAdapter = new ShopAdapter(SearchActivity.this,R.layout.layout_search,shopArrayList);
        listViewShop.setAdapter(shopAdapter);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String q = editTextSearch.getText().toString();
                shopArrayList = shopDao.queryBuilder().whereOr(ShopDao.Properties.Shopname.like("%"+q+"%"),
                        ShopDao.Properties.Address.like("%"+q+"%")).list();
                if (shopArrayList.size()==0){
                    listViewShop.setVisibility(View.INVISIBLE);
                    constraintLayoutWarning.setVisibility(View.VISIBLE);
                }
                else {
                    listViewShop.setVisibility(View.VISIBLE);
                    constraintLayoutWarning.setVisibility(View.INVISIBLE);
                    shopAdapter = new ShopAdapter(SearchActivity.this,R.layout.layout_search,shopArrayList);
                    listViewShop.setAdapter(shopAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SaveVariable.shop=shopArrayList.get(i);
                startActivity(new Intent(SearchActivity.this,ProductDetail.class));
            }
        });
    }
    public void backHome(View view){
        startActivity(new Intent(SearchActivity.this,HomeActivity.class));
    }
    public void ConnectDatabase(){
        shopDao = DatabaseApplication.Instance().createShopDao();
    }
}