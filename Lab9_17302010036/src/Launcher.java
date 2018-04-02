import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new RightTriangle());
        triangles.add(new IsoscelesTriangle());
        for (int i = 0; i < triangles.size(); i++) {
            triangles.get(i).printName();
        }
    }

    public static List<GeometryObject> test2() {
        List<GeometryObject> geometryObjects = new ArrayList<>();
        Triangle triangle = new Triangle();
        triangle.setEdge(0, 1, 2);
        geometryObjects.add(triangle);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 10));

        triangle = new Triangle();
        triangle.setEdge(2.1, 3.2, 4.4);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 20));
        geometryObjects.add(triangle);

        triangle = new RightTriangle();
        triangle.setEdge(3.0, 4.0, 5.0);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 30));
        geometryObjects.add(triangle);

        triangle = new RightTriangle();
        triangle.setEdge(7.0, 8.0, 3.0);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 30));
        geometryObjects.add(triangle);

        triangle = new IsoscelesTriangle();
        triangle.setEdge(2.0, 2.0, 5.0);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 40));
        geometryObjects.add(triangle);


        triangle = new IsoscelesTriangle();
        triangle.setEdge(2.0, 7.0, 7.0);
        triangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 50));
        geometryObjects.add(triangle);


        Rectangle rectangle = new Rectangle();
        rectangle.setLength(5.0);
        rectangle.setWidth(4.0);
        rectangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 60));
        geometryObjects.add(rectangle);

        rectangle = new Rectangle();
        rectangle.setLength(2.0);
        rectangle.setWidth(4.0);
        rectangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 70));
        geometryObjects.add(rectangle);

        rectangle = new Rectangle();
        rectangle.setLength(-1.0);
        rectangle.setWidth(4.0);
        rectangle.setCreatedTime(new Date(2017, 12, 7, 1, 30, 80));
        geometryObjects.add(rectangle);

        for (int i = 0; i < geometryObjects.size(); i++) {
            GeometryObject object = geometryObjects.get(i);
            System.out.println("####No." + i + " GeometryObject Element , its name is :");
            object.printName();
            System.out.println("CreatedTime : " + object.getCreatedTime());
            if (!object.isLegal()) {
                System.out.println("The object is illegal");
                System.out.println();
                continue;
            }
            System.out.println("Area : " + object.getArea());
            System.out.println("Perimeter : " + object.getPerimeter());
            System.out.println();
        }


        return geometryObjects;
    }

    public static void test3() {
        List<GeometryObject> geometryObjects = test2();
        List<ConsolePrint> consolePrints = new ArrayList<>();
        for (int i = 0; i < geometryObjects.size(); i++) {
            GeometryObject object = geometryObjects.get(i);
            if (!object.isLegal()) {
                continue;
            }
            if (object instanceof ConsolePrint) {
                System.out.println("An GeometryObject object is converted to ConsolePrint object because the object is both an GeometryObject and a ConsolePrint object");
                consolePrints.add((ConsolePrint) object);
                ((ConsolePrint) object).printShape();
            }
        }

        for (int i = 0; i < consolePrints.size(); i++) {
            ConsolePrint consolePrint = consolePrints.get(i);
            System.out.println("A ConsolePrint object is printing its shape");
            consolePrint.printShape();

        }
    }
}
