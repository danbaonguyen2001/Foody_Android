package hcmute.danbaonguyen19110036.foody.Pattern.Command;

public class FoodApp {
    private FoodOrderCommand command;

    public FoodApp(FoodOrderCommand command) {
        this.command = command;
    }
    public void ClickCancelFood(){
        command.execute();
    }
    public void ClickOrderFood(){
        command.execute();
    }
}
