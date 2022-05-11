package hcmute.danbaonguyen19110036.foody.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import org.greenrobot.greendao.query.WhereCondition;
import java.util.List;

import hcmute.danbaonguyen19110036.foody.Activity.ProductDetail;
import hcmute.danbaonguyen19110036.foody.Adapter.ShopHomeAdapter;
import hcmute.danbaonguyen19110036.foody.Database.Category;
import hcmute.danbaonguyen19110036.foody.Database.CategoryDao;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.Database.FoodDao;
import hcmute.danbaonguyen19110036.foody.Database.Shop;
import hcmute.danbaonguyen19110036.foody.Database.ShopDao;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class HomeFragment extends Fragment {
    private GridView gridView;
    private ShopHomeAdapter shopHomeAdapter;
    private ShopDao shopDao;
    private CategoryDao categoryDao;
    private FoodDao foodDao;
    TextView textViewFood,textViewDrinks,textViewSnack,textViewVegetable;
    private List<Shop> shopList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConnectDatabase();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.grid_shop);
        AnhXa(view);
        shopList = shopDao.loadAll();
        shopHomeAdapter = new ShopHomeAdapter(getActivity(),R.layout.layout_shop_home,shopList);
        gridView.setAdapter(shopHomeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SaveVariable.shop = shopList.get(i);
                startActivity(new Intent(getActivity(), ProductDetail.class));
            }
        });
        textViewFood.setTextColor(Color.RED);
        ChangeColor(textViewDrinks,textViewSnack,textViewVegetable);
        textViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewFood.setTextColor(Color.RED);
                ChangeColor(textViewDrinks,textViewSnack,textViewVegetable);
                Category category = getCategory(textViewFood.getText().toString());
                List<Food> foods = getListFood(category.getId());
                shopHomeAdapter = new ShopHomeAdapter(getActivity(),R.layout.layout_shop_home,shopList);
                gridView.setAdapter(shopHomeAdapter);
            }
        });
        textViewDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewDrinks.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewSnack,textViewVegetable);
            }
        });
        textViewSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewSnack.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewDrinks,textViewVegetable);
            }
        });
        textViewVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewVegetable.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewSnack,textViewDrinks);
            }
        });
        return view;
    }
    public void AnhXa(View view){
        textViewFood = view.findViewById(R.id.home_food);
        textViewDrinks = view.findViewById(R.id.home_drinks);
        textViewSnack = view.findViewById(R.id.home_snacks);
        textViewVegetable = view.findViewById(R.id.home_vegetable);
    }
    public void ChangeColor(TextView view1,TextView view2,TextView view3){
        view1.setTextColor(Color.GRAY);
        view2.setTextColor(Color.GRAY);
        view3.setTextColor(Color.GRAY);
    }
    public void ConnectDatabase(){
        foodDao = DatabaseApplication.Instance().createFoodDao();
        categoryDao = DatabaseApplication.Instance().createCategoryDao();
        shopDao = DatabaseApplication.Instance().createShopDao();
    }
    public Category getCategory(String categoryName){
        List<Category> categories = categoryDao.queryBuilder().where(CategoryDao.Properties.Categoryname.eq(textViewFood.getText())).list();
        return categories.get(0);
    }
    public List<Food> getListFood(Long id){
        List<Food> foods = foodDao.queryBuilder().where(FoodDao.Properties.CategoryId.eq(id)).list();
        return foods;
    }
}