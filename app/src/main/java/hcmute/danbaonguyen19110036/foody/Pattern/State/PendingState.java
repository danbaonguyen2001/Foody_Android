package hcmute.danbaonguyen19110036.foody.Pattern.State;

import hcmute.danbaonguyen19110036.foody.Pattern.Command.CancelFood;
import hcmute.danbaonguyen19110036.foody.Pattern.Command.FoodReceiver;

public class PendingState implements State {
    private Long foodId;
    public PendingState(Long foodId){
        this.foodId = foodId;
    }
    @Override
    public void handleRequest() {
        FoodReceiver foodReceiver = new FoodReceiver();
        CancelFood cancelFood = new CancelFood(foodReceiver,foodId);
        cancelFood.execute();
    }
}
