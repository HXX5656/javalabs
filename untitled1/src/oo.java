
import java.util.Scanner;

public class oo{
        public void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个小于16的整数：");
        int inner = input.nextInt();

        int l, r, m;
        if (inner <= 15) {
            for (l = 1; l <= inner; l++) {
                for (r = inner; r >= 1; r--) {
                    if (l >= r) {
                        if(r < 10){
                            System.out.printf("  " + r);
                        }
                        else
                            System.out.printf(" " + r);
                    }
                    else
                        System.out.print("   ");
                }
                for (m = 2; m <= inner; m++) {
                    if (l >= m) {
                        if(m <= 10){
                            System.out.printf("  " + m);
                        }
                        else
                            System.out.printf(" " + m);
                    } else
                        System.out.print("    ");
                }
                System.out.println();
            }
        }
        else
            System.out.println("您所输入的数字大于15");
    }
}

