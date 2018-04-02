import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) throws FileNotFoundException,NumberFormatException,ArithmeticException {
        Scanner output=new Scanner(new File("task3.txt"));
        for(;output.hasNext();) {
            try {
                int a=Integer.parseInt(output.next());
                int b=Integer.parseInt(output.next());
                System.out.println(""+(a/b));
            } catch (NumberFormatException ex) {
                System.out.println("分母分子不是数字");
                ex.printStackTrace();

            } catch (ArithmeticException  ex) {
                System.out.println("分母不能为零");

            }

        }


    }
}
