package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public class Buble extends DrinksDecorator{
    public Buble(IDrinks idrinks) {
        super(idrinks);
    }
    @Override
    public double Price() {
        return getIdrinks().Price()+10000;
    }
    public double getBublePrice(){
        return 10000;
    }
}
