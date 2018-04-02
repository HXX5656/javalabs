import java.text.DecimalFormat;
import java.util.Scanner;
import java.math.*;
//求sinx的定积分
public class Integral17302010077 {
    public static void main(String[]  args) {
        //获取积分上下限
        Scanner input= new Scanner(System.in);
        System.out.println("请输入积分下限和积分上限： " );
        //解决积分上下限的大小问题
        double a0= input.nextDouble();
        double b0= input.nextDouble();
        double a=Math.min(a0,b0);double b=Math.max(a0,b0);
        //求矩形面积来算定积分
         double result=0;
        double dx=(Math.abs(b-a))/100000;
        for (int i=1;i<=100000;i++) {
            a += dx;
            result += (Math.sin(a))*dx;

        }
        //利用整型使计算结果保留四位小数
        result=(int)(result*10000);
        result=result/10000;
        //将积分上下限大小的问题分别处理
        if (a0<=b0) {
            result=result;
        }
        else {
            result=-result;
        }
        //输出结果

        System.out.println("积分结果是 " + result );

        System.exit(0);



    }
}
