package hcmute.danbaonguyen19110036.foody.Pattern.Decorator;

public abstract class DrinksDecorator implements IDrinks{
    private IDrinks idrinks;

    public DrinksDecorator(IDrinks idrinks) {
        this.idrinks = idrinks;
    }

    public IDrinks getIdrinks() {
        return idrinks;
    }

    public void setIdrinks(IDrinks idrinks) {
        this.idrinks = idrinks;
    }
}
