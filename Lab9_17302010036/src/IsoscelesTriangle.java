public class IsoscelesTriangle extends Triangle implements ConsolePrint {
    @Override
    public void printName() {
        System.out.println("IsoscelesTriangle");
    }
    @Override
    public boolean isLegal() {
        int a1=(int)(edge1*100);
        double a=a1/100;
        int a2=(int)(edge2*100);
        double b=a2/100;
        int a3=(int)(edge3*100);
        double c=a3/100;
        if (a==b||a==c||b==c) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void printShape() {
        for (int i=1;i<=5;i++) {
            for (int j=1;j<=5-i;j++) {
                System.out.print(" ");
            }
            for (int j=1;j<=2*i-1;j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
