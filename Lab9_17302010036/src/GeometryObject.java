import java.util.Date;

public abstract class GeometryObject {
    protected Date createdTime;
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date a) {
        createdTime=a;
    }
    public abstract boolean isLegal();
    public abstract double getPerimeter();
    public abstract double getArea();
    public abstract void printName();

}
