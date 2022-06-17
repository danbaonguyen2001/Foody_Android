package hcmute.danbaonguyen19110036.foody.Pattern.Command;

public class OrderFood implements FoodOrderCommand{
    private FoodReceiver foodReceiver;

    public OrderFood(FoodReceiver foodReceiver) {
        this.foodReceiver = foodReceiver;
    }

    @Override
    public void execute() {
        foodReceiver.Order();
    }
}
