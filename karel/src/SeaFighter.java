public class SeaFighter extends Fighter {
    private boolean hasBullet;
    @Override
    public void move() {
        byship();
        System.out.println("我小海军动了。");
    }
    public void byship() {

    }
    @Override
    public void fight() {
        if (isHasBullet()) {
            fire();
        }
    }

    public void fire() {
    }

    public boolean isHasBullet() {
        return hasBullet;
    }
}