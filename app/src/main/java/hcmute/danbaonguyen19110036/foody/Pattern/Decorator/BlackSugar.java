package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public class BlackSugar extends DrinksDecorator{
    public BlackSugar(IDrinks idrinks) {
        super(idrinks);
    }

    @Override
    public double Price() {
        return getIdrinks().Price()+8000;
    }
    public double getBlackSugarPrice(){
        return 8000;
    }
}
