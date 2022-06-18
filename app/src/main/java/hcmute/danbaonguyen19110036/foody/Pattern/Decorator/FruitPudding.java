package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public class FruitPudding extends DrinksDecorator{
    public FruitPudding(IDrinks idrinks) {
        super(idrinks);
    }
    @Override
    public double Price() {
        return getIdrinks().Price()+5000;
    }
    public double getFruitPrice(){
        return 5000;
    }
}
