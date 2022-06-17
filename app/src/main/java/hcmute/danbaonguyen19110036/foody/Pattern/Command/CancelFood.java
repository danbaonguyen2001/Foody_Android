package hcmute.danbaonguyen19110036.foody.Pattern.Command;

public class CancelFood implements FoodOrderCommand{
    private FoodReceiver foodReceiver;
    private Long orderItemId;
    public CancelFood(FoodReceiver foodReceiver,Long orderItemId) {
        this.foodReceiver = foodReceiver;
        this.orderItemId = orderItemId;
    }

    @Override
    public void execute() {
        foodReceiver.Cancel(orderItemId);
    }
}
