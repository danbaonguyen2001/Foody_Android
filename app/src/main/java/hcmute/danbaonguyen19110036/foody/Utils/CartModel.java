package hcmute.danbaonguyen19110036.foody.Utils;
import hcmute.danbaonguyen19110036.foody.Database.Food;

public class CartModel {
    public int quantity;
    public int totalPrice;
    public Food food;

    public CartModel(int quantity, int totalPrice, Food food) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return quantity*food.getPrice();
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
