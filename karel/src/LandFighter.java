public class LandFighter extends Fighter{
    private boolean isEmptyFrontOfMe=true;

@Override
    public void move() {
    if(isEmpty()) {
        byfoot();
        System.out.println("我小陆军动了。");
    }

    }
    public void move(char direction) {
         byfoot();
        System.out.println("我小陆军动了。");
    }
    public boolean isEmpty() {
        return isEmptyFrontOfMe;
    }
    public void byfoot() {

    }
    @Override
    public void fight() {
    if(getHp()>0) {
        byArm();
    }
    }
    public void byArm() {
    }
    }

