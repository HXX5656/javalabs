public class Triangle extends GeometryObject{
    protected double edge1;
    protected double edge2;
    protected double edge3;
    public void setEdge(double edge1,double edge2,double edge3) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }
    @Override
    public void printName() {
        System.out.println("Triangle");
    }
    public double getEdge1() {
        return edge1;
    }
    public double getEdge2() {
        return edge2;
    }
    public double getEdge3() {
        return edge3;
    }
    public void setEdge2(double edge) {
        edge2=edge;
    }
    public void setEdge1(double edge) {
        edge1=edge;
    }
    public void setEdge3(double edge) {
        edge3=edge;
    }
    @Override
    public  boolean isLegal(){
        int a1=(int)(edge1*100);
        double a=a1/100;
        int a2=(int)(edge2*100);
        double b=a2/100;
        int a3=(int)(edge3*100);
        double c=a3/100;
        if ((a+b>c&&a+c>b)&&b+c>a) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public double getPerimeter(){
        int a1=(int)(edge1*100);
        double a=a1/100;
        int a2=(int)(edge2*100);
        double b=a2/100;
        int a3=(int)(edge3*100);
        double c=a3/100;
        return ((int)((a+b+c)*100))/100;

    }
    @Override
    public double getArea() {
        int a1=(int)(edge1*100);
        double a=a1/100;
        int a2=(int)(edge2*100);
        double b=a2/100;
        int a3=(int)(edge3*100);
        double c=a3/100;
        double p=getPerimeter()/2;
        double x=p*(p-a)*(p-b)*(p-c);
        x=((int)(x*100))/100;
        return ((int)(Math.sqrt(x)*100))/100;
    }


}
