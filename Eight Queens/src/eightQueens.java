import javax.swing.*;
import java.awt.*;
//此程序的作用是输出八皇后问题的一种解法
public class eightQueens {
    public static void main (String[] args) {
        //初始化棋盘
        int[][]  map ={
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        //row为第几行，从0开始，因为第零行的皇后位置随意，故从第一行开始循环即可
        //k【】这个数组共有9个元素，第一个元素是信号元素，用来判断是否需要回溯
        //一到九的元素分别代表0到8行的皇后所在的列数
        int row=1;
        int i=0;
        int[] k=new int[9];
        //随机将第一行的皇后放在某一列上
        k[1]=(int)(Math.random()*8);
        map[0][k[1]]=1;
        //该循环是为了利用trySituations方法来一行一行的放置皇后并在对应情况下实现回溯
        for (;row<8;row++) {
        //实现回溯
            if(trySituations(k,map,i,row)[0]==-1) {
                int j = row - 1;
                for (; j >= 0; j--) {
                    i = k[j+1] + 1;
                    map[j][i - 1] = 0;
                    if (trySituations(k,map,i,j)[0] != -1) {
                        break;
                    } else {
                        i = 0;
                    }
                }
                //回溯完了继续下一行
                i = 0;
                row=j;
            }

        }
        //打印出最终情况，以黑格子代表皇后，白格子代表空的棋盘格
        for (int m=0;m<8;m++) {
            for (int n=0;n<8;n++) {
                if (map[m][n]==1) {
                    System.out.print("■");
                }else {
                    System.out.print("□");
                }
            }
            System.out.print("\n");
        }
        System.exit(0);
            }


   //该方法实现了每一行皇后的合理放置
    public static int[] trySituations (int[] k,int[][]map,int i,int row) {
        //从此处跳出循环
        label: {
            //从第0列尝试放置皇后
        for (;i<8;i++) {
            map[row][i]=1;
            //从前几行的皇后判断当前列放置皇后合不合适
            for (int j=0;j<row;j++) {

                if (map[j][i]==1) {
                    map[row][i]=0;
                    break;
                }
                if(i+row-j<8) {
                    if (map[j][i+row-j]==1){
                    map[row][i]=0;
                    break;
                    }
                }
                if(i-row+j>=0&i-row+j<8) {
                    if (map[j][i-row+j]==1) {
                    map[row][i]=0;
                    break;
                    }
                }
                //合适直接跳出循环
                if (j==row-1) {
                    k[0]=1;
                    k[row+1]=i;
                    break label;
                }
            }
            //判断该行有无皇后，如果皇后无列可放，就返回相应的信号元素，以便回溯判断
            if (i==7) {
                if (map[row][i]==0) {
                    k[0]=-1;

                }
                else {
                    k[0]=1;
                    k[row+1]=i;
                }
            }
            }
        }
        return k;
    }

}


