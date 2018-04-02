public class Rectangle extends GeometryObject implements ConsolePrint{
    private double length;
    private double width;
    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
    public void setLength(double a) {
        length=a;
    }
    public void setWidth(double a) {
        width=a;
    }
    @Override
    public boolean isLegal() {
        length=((int)(length*100))/100;
        width=((int)(width*100))/100;
        if(length>=width) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public double getPerimeter() {
        return ((int)(length+width)*200)/100;
    }
    @Override
    public double getArea() {
        return ((int)(length*width)*100)/100;
    }
    @Override
    public void printName() {
        System.out.println("Rectangle");
    }
    @Override
    public void printShape() {
        for(int i=1;i<=5;i++) {
            for (int j=1;j<=6;j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
