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
import java.util.ArrayList;
import java.util.List;
import hcmute.danbaonguyen19110036.foody.Activity.ProductDetail;
import hcmute.danbaonguyen19110036.foody.Adapter.ShopHomeAdapter;
import hcmute.danbaonguyen19110036.foody.Database.Category;
import hcmute.danbaonguyen19110036.foody.Database.CategoryDao;
import hcmute.danbaonguyen19110036.foody.Pattern.Singleton.DatabaseApplication;
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
    TextView textViewFood,textViewDrinks,textViewSnack,textViewVegetable,textViewAll;
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
        textViewAll.setTextColor(Color.RED);
        ChangeColor(textViewFood,textViewDrinks,textViewSnack,textViewVegetable);
        textViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewAll.setTextColor(Color.RED);
                ChangeColor(textViewDrinks,textViewSnack,textViewVegetable,textViewFood);
                shopList = shopDao.loadAll();
                shopHomeAdapter = new ShopHomeAdapter(getActivity(),R.layout.layout_shop_home,shopList);
                gridView.setAdapter(shopHomeAdapter);
            }
        });
        textViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewFood.setTextColor(Color.RED);
                ChangeColor(textViewDrinks,textViewSnack,textViewVegetable,textViewAll);
                loadShop(textViewFood.getText().toString());
            }
        });
        textViewDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewDrinks.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewSnack,textViewVegetable,textViewAll);
                loadShop(textViewDrinks.getText().toString());
            }
        });
        textViewSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewSnack.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewDrinks,textViewVegetable,textViewAll);
                loadShop(textViewSnack.getText().toString());
            }
        });
        textViewVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewVegetable.setTextColor(Color.RED);
                ChangeColor(textViewFood,textViewSnack,textViewDrinks,textViewAll);
                loadShop(textViewVegetable.getText().toString());
            }
        });
        return view;
    }
    public void AnhXa(View view){
        textViewFood = view.findViewById(R.id.home_food);
        textViewDrinks = view.findViewById(R.id.home_drinks);
        textViewSnack = view.findViewById(R.id.home_snacks);
        textViewVegetable = view.findViewById(R.id.home_vegetable);
        textViewAll = view.findViewById(R.id.home_all);
    }
    public void ChangeColor(TextView view1,TextView view2,TextView view3,TextView view4){
        view1.setTextColor(Color.GRAY);
        view2.setTextColor(Color.GRAY);
        view3.setTextColor(Color.GRAY);
        view4.setTextColor(Color.GRAY);
    }
    public void ConnectDatabase(){
        foodDao = DatabaseApplication.Instance().createFoodDao();
        categoryDao = DatabaseApplication.Instance().createCategoryDao();
        shopDao = DatabaseApplication.Instance().createShopDao();
    }
    public Category getCategory(String categoryName){
        List<Category> categories = categoryDao.queryBuilder().where(CategoryDao.Properties.Categoryname.eq(categoryName)).list();
        return categories.get(0);
    }
    public List<Shop> getShop(Category category){
        List<Long> ids = new ArrayList<>();
        List<Food> foodList = foodDao.queryBuilder().where(FoodDao.Properties.CategoryId.eq(category.getId())).list();
        List<Shop> shopList =shopDao.loadAll();
        for(int i=0;i<foodList.size();i++){
            boolean val = ids.contains(foodList.get(i).getShopId());
            if(val==false){
                ids.add(foodList.get(i).getShopId());
            }
        }
        List<Shop> shopListByCategory = shopDao.queryBuilder().where(ShopDao.Properties.Id.in(ids)).list();
        return shopListByCategory;
    }
    public void loadShop(String categoryName){
        Category category = getCategory(categoryName);
        shopList = getShop(category);
        shopHomeAdapter = new ShopHomeAdapter(getActivity(),R.layout.layout_shop_home,shopList);
        gridView.setAdapter(shopHomeAdapter);
    }
}