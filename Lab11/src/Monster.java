import java.util.Scanner;

 public class Monster extends Mob {
     private static int row;
     private static int col;
     private static char[][] map;
     private static Object[][] map0;
     public Monster(int row,int col,Object[][] mapo) {
         this.row=row;
         this.col=col;
         map0=mapo;
     }
     public void getMap(char[][] map0) {
         map=map0;
     }
     public void move() {
         int m = (int) (Math.random() * 4);
         switch (m) {
             case 0://w
                 if (map[row-1][col] == '▓') {
                 }
                 else if(map[row-1][col] == '○') {
                     System.out.print("很遗憾你被怪物杀死了。");
                     System.exit(0);
                 }
                 else {
                     map0[row][col]=new Space(row,col);
                     map[row][col]='　';
                     map[row-1][col]='＃';
                     row--;
                     map0[row][col]=new Monster(row,col,map0);
                 }
                 break;
             case 1://a
                 if (map[row][col-1] == '▓') {
                 }
                 else if(map[row][col-1] == '○') {
                     System.out.print("很遗憾你被怪物杀死了。");
                     System.exit(0);
                 }
                 else {
                     map0[row][col]=new Space(row,col);
                     map[row][col]='　';
                     map[row][col-1]='＃';
                     col--;
                     map0[row][col]=new Monster(row,col,map0);
                 }
                 break;
             case 2://s
                 if (map[row+1][col] == '▓') {
                 }
                 else if(map[row+1][col] == '○') {
                     System.out.print("很遗憾你被怪物杀死了。");
                     System.exit(0);
                 }
                 else {
                     map0[row][col]=new Space(row,col);
                     map[row][col]='　';
                     map[row+1][col]='＃';
                     row++;
                     map0[row][col]=new Monster(row,col,map0);
                 }
                 break;
             case 3://d
                 if(col+1<map[0].length) {
                 if (map[row][col+1] == '▓') {
                 }
                 else if(map[row][col+1] == '○') {
                     System.out.print("很遗憾你被怪物杀死了。");
                     System.exit(0);
                 }
                 else {
                     map0[row][col]=new Space(row,col);
                     map[row][col]='　';
                     map[row][col+1]='＃';
                     col++;
                     map0[row][col]=new Monster(row,col,map0);
                 }
                 break;
                 }
                 break;
             default:
                 break;
     }
     }
    public void handleEvent() {
         System.out.println("你来到了怪物的老巢。");
    }
    public char getIcon() {
        return '＃';
    }

}
