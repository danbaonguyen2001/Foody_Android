package hcmute.danbaonguyen19110036.foody.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import hcmute.danbaonguyen19110036.foody.Activity.SplashActivity;
import hcmute.danbaonguyen19110036.foody.Database.Category;
import hcmute.danbaonguyen19110036.foody.Database.CategoryDao;
import hcmute.danbaonguyen19110036.foody.Database.DatabaseApplication;
import hcmute.danbaonguyen19110036.foody.Database.Food;
import hcmute.danbaonguyen19110036.foody.R;
import hcmute.danbaonguyen19110036.foody.Utils.CartModel;
import hcmute.danbaonguyen19110036.foody.Utils.Model;
import hcmute.danbaonguyen19110036.foody.Utils.SaveVariable;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CartModel> cartModelList;
    private CategoryDao categoryDao;
    public SharedPreferences sharedPreferences;

    public CartAdapter(Context context, int layout, List<CartModel> cartModelList) {
        this.context = context;
        this.layout = layout;
        this.cartModelList = cartModelList;
    }
    @Override
    public int getCount() {
        return cartModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        public TextView foodName,foodCategory,foodQuantity,foodPrice;
        public ImageView foodImage;
        public Button btnAdd,btnSub,btnDelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        ConnectDatabase();
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            AnhXa(holder,view);
//            holder.foodImage = (ImageView) view.findViewById(R.id.img_cart);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        CartModel cartModel = cartModelList.get(i);
        int quantity = cartModel.getQuantity();
        List<Category> category = categoryDao.queryBuilder().where(CategoryDao.Properties.Id.eq(cartModel.getFood().getCategoryId())).list();
        holder.foodName.setText(cartModel.getFood().getFoodname());
        holder.foodCategory.setText(category.get(0).getCategoryname());
        holder.foodQuantity.setText(String.valueOf(cartModel.getQuantity()));
        holder.foodPrice.setText(String.valueOf(cartModel.getTotalPrice()));
        Model model = new Model(context);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity =Integer.parseInt(holder.foodQuantity.getText().toString());
                quantity++;
                sharedPreferences = context.getSharedPreferences("listCart",context.MODE_PRIVATE);
                cartModel.setQuantity(quantity);
                holder.foodQuantity.setText(String.valueOf(cartModel.getQuantity()));
                holder.foodPrice.setText(String.valueOf(cartModel.getTotalPrice()));
                SaveVariable.cartModelList.set(i,cartModel);
                model.saveItemCart();
            }
        });
        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity =Integer.parseInt(holder.foodQuantity.getText().toString());
                if (quantity==1){
                    return;
                }
                quantity--;
                cartModel.setQuantity(quantity);
                holder.foodQuantity.setText(String.valueOf(cartModel.getQuantity()));
                holder.foodPrice.setText(String.valueOf(cartModel.getTotalPrice()));
                SaveVariable.cartModelList.set(i,cartModel);
                model.saveItemCart();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveVariable.cartModelList.remove(i);
                model.saveItemCart();
                notifyDataSetChanged();
            }
        });
        return view;
    }
    public void ConnectDatabase(){
        categoryDao = DatabaseApplication.Instance().createCategoryDao();
    }
    public void AnhXa(ViewHolder holder,View view){
        holder.foodName = (TextView) view.findViewById(R.id.food_name_cart);
        holder.foodCategory = (TextView) view.findViewById(R.id.category_cart);
        holder.foodQuantity = (TextView) view.findViewById(R.id.quantity_cart);
        holder.foodPrice = (TextView) view.findViewById(R.id.price_card);
        holder.btnAdd = (Button) view.findViewById(R.id.btn_add_cart);
        holder.btnSub =(Button) view.findViewById(R.id.btn_sub_cart);
        holder.btnDelete = (Button) view.findViewById(R.id.btn_delete_cart);
    }
}
