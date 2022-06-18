package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public class FoodDrinks implements IDrinks{
    private double totalPrice;
    public FoodDrinks(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public double Price() {
        return this.totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
