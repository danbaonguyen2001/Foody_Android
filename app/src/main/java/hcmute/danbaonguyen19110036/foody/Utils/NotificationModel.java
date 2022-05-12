package hcmute.danbaonguyen19110036.foody.Utils;

import hcmute.danbaonguyen19110036.foody.Database.Food;

public class NotificationModel {
    public NotificationModel(Food food, String description) {
        this.food = food;
        this.description = description;
    }
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Food food;
    public String description;


}
