package hcmute.danbaonguyen19110036.foody.Pattern.State;

public class StatusContext {
    private State state;
    public void setState(State state){
        this.state = state;
    }
    public void applyState(){
        this.state.handleRequest();
    }
}
