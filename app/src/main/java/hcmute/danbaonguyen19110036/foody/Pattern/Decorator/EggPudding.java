package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public class EggPudding extends DrinksDecorator{
    public EggPudding(IDrinks idrinks) {
        super(idrinks);
    }

    @Override
    public double Price() {
        return getIdrinks().Price()+12000;
    }
    public double getEggPuddingPrice(){
        return 12000;
    }
}
