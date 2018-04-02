import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLife {


    /**
     * 生命游戏入口
     *
     * @param cells 表示细胞图的二位数组
     */
    public static void start(int[][] cells) {
        Scanner scanner = new Scanner(System.in);
        int generation = 1;
        while (true) {
            System.out.println("generation:" + generation);
            printCells(cells);
            System.out.println("输入任意键产生下一代。。。");
            String input = scanner.nextLine();
            cells = transform(cells);
            generation++;
        }
    }

    /**
     * 统计cells[x][y]细胞周围活着的个数
     *
     * @param cells 细胞结构数组
     * @param x     横坐标
     * @param y     纵坐标
     * @return cells[x][y]细胞周围活着的个数
     */
    public static int findLifedNum(int[][] cells, int x, int y) {
        //输入一些值，count为周围活细胞的个数，并给这些值初始化
        int count = 0;
        //构造posX，posY数组来对于边角问题进行解决，使只需要一个循环来判断个数
        //根据不同情况来判断
        int[] posX ;
        int[] posY ;
        if (x==0) {
            posX=new int[2];
            posX[0]=0;
            posX[1]=1;
        }
        else if (x==cells.length-1) {
            posX=new int[2];
            posX[0]=x-1;
            posX[1]=x;
        }
        else {
            posX=new int[3];
            posX[0]=x-1;
            posX[1]=x;
            posX[2]=x+1;
        }
        if (y==0) {
            posY=new int[2];
            posY[0]=0;
            posY[1]=1;
        }
        else if (y==cells[x].length-1) {
            posY=new int[2];
            posY[0]=y-1;
            posY[1]=y;
        }
        else {
            posY=new int[3];
            posY[0]=y-1;
            posY[1]=y;
            posY[2]=y+1;
        }
        //使用循环来遍历cells[x][y]及周围的矩形块
        for (int m=0;m<posX.length;m++) {
            for (int n=0;n<posY.length;n++) {
                if (cells[posX[m]][posY[n]]==1) {
                    count++;
                }
            }
        }
        //因为之前的遍历包括了cells[x][y]，故需要删除其本身
        if (cells[x][y]==1) {
            count--;
        }

        return count;
    }

    /**
     * 产生下一代
     *
     * @param cells 细胞结构数组
     * @return 新一代的细胞结构
     */
    public static int[][] transform(int[][] cells) {

        int[][] number=new int[cells.length][cells[0].length];
        for (int m=0;m<cells.length;m++) {

            for (int n=0;n<cells[m].length;n++) {
                number[m][n]=findLifedNum(cells,m,n);
            }
        }
        for (int m=0;m<cells.length;m++) {
            for (int n=0;n<cells[m].length;n++) {
                if (number[m][n]==3) {
                    cells[m][n]=1;
                }
                else  if (number[m][n]==2) {
                    cells[m][n]=cells[m][n];
                }
                else {
                    cells [m][n]=0;
                }
            }
        }

        return cells;
    }


    /**
     * 在控制台输出细胞结构
     *
     * @param cells 表示细胞图的二位数组
     */
    public static void printCells(int[][] cells) {
        for (int[] line : cells) {
            for (int cell : line) {
                System.out.print(cell == 0 ? "□" : "█");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int[][] cells = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        start(cells);
    }
}

