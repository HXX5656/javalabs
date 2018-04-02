public class RightTriangle extends Triangle implements ConsolePrint{
    @Override
    public void printName() {
        System.out.println("RightTriangle");
    }
    @Override
    public boolean isLegal() {
        int a1=(int)(edge1*100);
        double a=a1/100;
        int a2=(int)(edge2*100);
        double b=a2/100;
        int a3=(int)(edge3*100);
        double c=a3/100;
        if (((int)((a*a+b*b)*100))/100==((int)(c*c)*100)/100
                ||((int)((b*b+c*c)*100))/100==((int)(a*a)*100)/100
                ||((int)((a*a+c*c)*100))/100==((int)(b*b)*100)/100) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void printShape() {
        for (int i=1;i<=5;i++) {
            for (int j=1;j<=i;j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
