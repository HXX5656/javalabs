import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {
    private static String a;
    public static void main(String[] args) throws FileNotFoundException {
        if (a==null) {
            System.out.print("1");
        }
        java.io.PrintWriter  save=new java.io.PrintWriter("save.txt");
        PrintWriter Maze1=new PrintWriter(save);
        Maze1.print("请你加油哦");
        Maze1.close();
    }
}
