public  class Fighter{
    private double atk;
    private double def;
    private double hp;
    private static Object home;
    public Fighter() {
        System.out.println("一个战士诞生了。");
    }
    public void move() {
         System.out.println("我移动了一下");
    }
    public  void fight() {
        getPrepared();
        isFighting();
    }
    private void getPrepared() {

    }
    public void isFighting() {

    }
    public double getAtk() {
        return atk;
    }
    public double getDef() {
        return def;
    }
    public double getHp() {
        return hp;
    }
    public void setAtk(double value) {
        atk=value;
    }
    public void setDef(double value) {
        def=value;
    }
    public void setHp(double value) {
        hp=value;
    }
}
