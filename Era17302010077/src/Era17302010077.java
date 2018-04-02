
import javax.swing.*;
/**
 *将公元年转化为对应的干支纪年法的干支年
 * @M.hu
 * @version 1.8
 */

public class Era17302010077 {
    public static void main(String[] args) {
        //get the year
        String yearStr = JOptionPane.showInputDialog("please enter the year you want to ask: ");
        int year = Integer.parseInt(yearStr);
        int a = year-618;
        //求余
        int b=a%10;
        int c=a%12;
        //定义两个string数组
        String[] ganStr={"戊","己","庚","辛","壬","癸","甲","乙","丙","丁"};
        String[] zhiStr={"寅","卯","辰","巳","午","未","申","酉","戌","亥","子","丑"};
        //判断所输入年份与618的前后关系并进行相对应的操作
        int y=(a>=0)? 1:2;
        switch (y){
            case 1:
                break;
            default:
                b=b+10;c=c+12;
                break;
        }
        //用选择结构判断对应的干支
        String gan="";String zhi="";
        switch(b) {
            case 0: gan=ganStr[0];break;
            case 1: gan=ganStr[1];break;
            case 2: gan=ganStr[2];break;
            case 3: gan=ganStr[3];break;
            case 4: gan=ganStr[4];break;
            case 5: gan=ganStr[5];break;
            case 6: gan=ganStr[6];break;
            case 7: gan=ganStr[7];break;
            case 8: gan=ganStr[8];break;
            case 9: gan=ganStr[9];break;
            default:break;
        }
        switch (c) {
            case 0:zhi=zhiStr[0];break;
            case 1:zhi=zhiStr[1];break;
            case 2:zhi=zhiStr[2];break;
            case 3:zhi=zhiStr[3];break;
            case 4:zhi=zhiStr[4];break;
            case 5:zhi=zhiStr[5];break;
            case 6:zhi=zhiStr[6];break;
            case 7:zhi=zhiStr[7];break;
            case 8:zhi=zhiStr[8];break;
            case 9:zhi=zhiStr[9];break;
            case 10:zhi=zhiStr[10];break;
            case 11:zhi=zhiStr[11];break;
            default:break;
        }
        //输出结果并正常退出
        System.out.println("The year is "+gan+zhi);
        System.exit(0);
    }
}