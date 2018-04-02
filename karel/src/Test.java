public class Test {
    public static void main(String[] args) {
        Fighter[] fighters=new Fighter[2];
        fighters[0]=new LandFighter();
        fighters[1]=new SeaFighter();
        for (int i=0;i<fighters.length;i++) {
            fighters[i].move();
        }
    }

}
