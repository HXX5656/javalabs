import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Player extends Mob {
    private static Object[][] map;
    private static int row;
    private static int col;
    private static char icon='○';
    private static StackOfAction s=new StackOfAction();
    private static String player;
    private static int score1;
    private static int score2;
    private static int score3;
    public Player(Object[][] map0) {
        row=1;
        col=1;
        map=map0;
    }
    @Override
    public void move() {
        System.out.println("请输入您的下一步操作： ");
        Scanner scanner1 = new Scanner(System.in);
        String m = scanner1.nextLine();
        for (int ll=0;ll>=0;) {
            if (m.equals("")) {
                System.out.print("您的输入有误，请您重新输入：");
                Scanner scanner2 = new Scanner(System.in);
                m= scanner2.nextLine();
            }
            else {
                ll=-1;
            }
        }
        char move = m.charAt(0);
        switch (move) {
            case 'a':
                if(map[row][col-1]instanceof Wall) {
                    System.out.println("Wrong:invalid.");
                }
                else if(map[row][col-1]instanceof Monster) {
                    ((Monster)(map[row][col-1])).handleEvent();
                    System.out.println("你已经被暗黑之力吞噬，你死了！");
                    icon='　';
                    s.push('a');
                }
                else if (map[row][col-1]instanceof Treasure) {
                    ((Treasure)(map[row][col-1])).handleEvent();
                    map[row][col]=new Space(row,col);
                    col--;
                    s.push('a');
                    map[row][col]=this;
                }
                else {
                    map[row][col]=new Space(row,col);
                    col--;
                    s.push('a');
                    map[row][col]=this;
                }
                break;
            case 'd':
                if(map[row][col+1]instanceof Wall) {
                    System.out.println("Wrong:invalid.");
                }
                else if(map[row][col+1]instanceof Monster) {
                    ((Monster)(map[row][col+1])).handleEvent();
                    System.out.println("你已经被暗黑之力吞噬，你死了！");
                    icon='　';
                    s.push('d');
                }
                else if (map[row][col+1]instanceof Treasure) {
                    ((Treasure)(map[row][col+1])).handleEvent();
                    map[row][col]=new Space(row,col);
                    col++;
                    s.push('d');
                    map[row][col]=this;
                }
                else {
                    map[row][col]=new Space(row,col);
                    col++;
                    s.push('d');
                    map[row][col]=this;
                }
                break;
            case 'w':
                if(map[row-1][col]instanceof Wall) {
                    System.out.println("Wrong:invalid.");
                }
                else if(map[row-1][col]instanceof Monster) {
                    ((Monster)(map[row-1][col])).handleEvent();
                    System.out.println("你已经被暗黑之力吞噬，你死了！");
                    icon='　';
                    s.push('w');
                }
                else if (map[row-1][col]instanceof Treasure) {
                    ((Treasure)(map[row-1][col])).handleEvent();
                    map[row][col]=new Space(row,col);
                    row--;
                    s.push('w');
                    map[row][col]=this;
                }
                else {
                    map[row][col]=new Space(row,col);
                    row--;
                    s.push('w');
                    map[row][col]=this;
                }
                break;
            case 's':
                if(map[row+1][col]instanceof Wall) {
                    System.out.println("Wrong:invalid.");
                }
                else if(map[row+1][col]instanceof Monster) {
                    ((Monster)(map[row+1][col])).handleEvent();
                    System.out.println("你已经被暗黑之力吞噬，你死了！");
                    icon='　';
                    s.push('s');
                }
                else if (map[row+1][col]instanceof Treasure) {
                    ((Treasure)(map[row+1][col])).handleEvent();
                    map[row][col]=new Space(row,col);
                    row++;
                    s.push('s');
                    map[row][col]=this;
                }
                else {
                    map[row][col]=new Space(row,col);
                    row++;
                    s.push('s');
                    map[row][col]=this;
                }
                break;
            case 'h':
                helpInformation();
                break;
            case 'x':
                System.out.println("你已经主动结束游戏。");
                System.exit(0);
                break;
            case 'q':
                System.out.println("你已经主动结束游戏。");
                System.exit(0);
                break;
            case 'b':
                char n=s.pop();
                switch (n) {
                    case 'd':
                        if(map[row][col-1]instanceof Wall) {
                            System.out.println("Wrong:invalid.");
                        }
                        else if(map[row][col-1]instanceof Monster) {
                            ((Monster)(map[row-1][col])).handleEvent();
                            System.out.println("你已经被暗黑之力吞噬，你死了！");
                            icon='　';
                        }
                        else if (map[row][col-1]instanceof Treasure) {
                            ((Treasure)(map[row][col-1])).handleEvent();
                            map[row][col]=new Space(row,col);
                            col--;
                            map[row][col]=this;
                        }
                        else {
                            map[row][col]=new Space(row,col);
                            col--;
                            map[row][col]=this;
                        }
                        break;
                    case 'a':
                        if(map[row][col+1]instanceof Wall) {
                            System.out.println("Wrong:invalid.");
                        }
                        else if(map[row][col+1]instanceof Monster) {
                            ((Monster)(map[row-1][col])).handleEvent();
                            System.out.println("你已经被暗黑之力吞噬，你死了！");
                            icon='　';
                        }
                        else if (map[row][col+1]instanceof Treasure) {
                            ((Treasure)(map[row][col+1])).handleEvent();
                            map[row][col]=new Space(row,col);
                            col++;
                            map[row][col]=this;
                        }
                        else {
                            map[row][col]=new Space(row,col);
                            col++;
                            map[row][col]=this;
                        }
                        break;
                    case 's':
                        if(map[row-1][col]instanceof Wall) {
                            System.out.println("Wrong:invalid.");
                        }
                        else if(map[row-1][col]instanceof Monster) {
                            ((Monster)(map[row-1][col])).handleEvent();
                            System.out.println("你已经被暗黑之力吞噬，你死了！");
                            icon='　';
                        }
                        else if (map[row-1][col]instanceof Treasure) {
                            ((Treasure)(map[row-1][col])).handleEvent();
                            map[row][col]=new Space(row,col);
                            row--;
                            map[row][col]=this;
                        }
                        else {
                            map[row][col]=new Space(row,col);
                            row--;
                            map[row][col]=this;
                        }
                        break;
                    case 'w':
                        if(map[row+1][col]instanceof Wall) {
                            System.out.println("Wrong:invalid.");
                        }
                        else if(map[row+1][col]instanceof Monster) {
                            ((Monster)(map[row-1][col])).handleEvent();
                            System.out.println("你已经被暗黑之力吞噬，你死了！");
                            icon='　';
                        }
                        else if (map[row+1][col]instanceof Treasure) {
                            ((Treasure)(map[row+1][col])).handleEvent();
                            map[row][col]=new Space(row,col);
                            row++;
                            map[row][col]=this;
                        }
                        else {
                            map[row][col]=new Space(row,col);
                            row++;
                            map[row][col]=this;
                        }
                        break;
                }
                break;
            case 'k':
                if(killOne()) {
                    System.out.println("恭喜你成功杀死了一个怪物。");
                }
                break;
            default:
                System.out.println("Wrong:invalid!");
                break;
        }
    }
    public  void  print() {
        int signal=0;
        char[][] mapreal=new char[map.length][map[0].length];
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                if(map[i][j]instanceof Space){
                    mapreal[i][j]=((Space)(map[i][j])).getIcon();
                }
                else if(map[i][j]instanceof Wall) {
                    mapreal[i][j]=((Wall)(map[i][j])).getIcon();
                }
                else if(map[i][j]instanceof Treasure) {
                    mapreal[i][j]=((Treasure)(map[i][j])).getIcon();
                }
                else if(map[i][j]instanceof Monster){
                    mapreal[i][j]=((Monster)(map[i][j])).getIcon();
                }
                else {
                    mapreal[i][j]=getIcon();
                }
            }
            System.out.print("\n");
        }
        s.footpoint(mapreal,row,col);
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                if (map[i][j]instanceof Monster) {
                    ((Monster)(map[i][j])).getMap(mapreal);
                    ((Monster)(map[i][j])).move();
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("" + mapreal[i][j]);
            }
            System.out.print("\n");
        }
        if(icon=='　') {
            System.exit(0);
        }
        else if (row==map.length-2&&col==map[0].length-1) {
            System.out.println("You win!");
            score1 = score1 + 20;
            signal = -1;
        }
        else {
            move();
        }
        if (signal==0) {
            print();
        }
        }
    public void handleEvent() {
            System.out.print("很遗憾你被怪物杀死了。");
            map[row][col]=new Space(row,col);
    }
    public  char getIcon() {
        return icon;
    }
    public static void helpInformation() {
        System.out.println(",您好\n" + "如果陷入死局无法行走请您按q或x结束这次游戏，你可以再次重新开始探险之旅。\n"
                + "圆圈代表您，路上％,＃分别代表宝物和怪物，遇上怪物按k键进行攻击，而想要拾取宝物只需到达宝物所在位置即可拾取\n"
                +"请注意怪物分低等和高等，而且你只能在怪物周围进行攻击，移动到怪物所在位置就会被黑暗之力吞噬而死亡。\n"
                +"低等怪物能力有限，而高等怪物不仅可以移动，移动的过程中还能攻击你。\n"
                + "在您行走的过程中会留下足迹，并且按b键即可后退一步,直到退无可退\n" + "祝您游戏愉快！");
    }
    public static void playerInformation() throws IOException {
        System.out.println("玩家："+player);
        System.out.println("闯关得分：" +score1);
        System.out.println("捡宝得分："+score2);
        System.out.println("杀怪得分："+score3);
        System.out.println("总得分：" +(score1+score2+score3));
        System.out.println("总步数: "+(s.getSize()));
        System.out.println("你是否愿意将当前分数存档？（按p代表是，其他则否）：");
        Scanner scanner1 = new Scanner(System.in);
        String select = scanner1.nextLine();
        for (int ll=0;ll>=0;) {
            if (select.equals("")) {
                System.out.print("您的输入有误，请您重新输入：");
                Scanner scanner2 = new Scanner(System.in);
                select = scanner2.nextLine();
            }
            else {
                ll=-1;
            }
        }
        char selection=select.charAt(0);
        if (selection=='p') {
            BufferedWriter players=new BufferedWriter(new FileWriter(new File("players.txt"),true));
            players.append(player+" ");
            players.append((score1+score2+score3)+" ");
            players.close();
            java.io.File file1=new java.io.File("players.txt");
            Scanner output=new Scanner(file1);
            int temp2[]=new int[1024];
            String[] temp1=new String[1024];
            for(int i=0;i<=temp1.length;i++) {
                if (i>=temp1.length) {
                    String[] temp1plus=new String[temp1.length*2];
                    System.arraycopy(temp1,0,temp1plus,0,temp1.length);
                    temp1=temp1plus;
                    int[] temp2plus=new int[temp1.length*2];
                    System.arraycopy(temp2,0,temp2plus,0,temp2.length);
                    temp2=temp2plus;
                }
                if (!output.hasNext()) {
                    output.close();
                    break;
                }
                else {
                    temp1[i]=output.next();
                    temp2[i]=Integer.parseInt(output.next());
                }
            }
            int[] temp21=new int[temp2.length];
            System.arraycopy(temp2,0,temp21,0,temp2.length);
            Arrays.sort(temp21);
            System.out.println("你是第"+(temp2.length-Arrays.binarySearch(temp21,(score1+score2+score3)))+"名。");
        }
    }
    public static boolean killOne() {
        boolean b=true;
        if (map[row-1][col]instanceof Monster) {
            System.out.println("恭喜你成功杀死了一个怪物。");
            map[row-1][col]=new Space(row-1,col) ;
        }
        else if(map[row+1][col]instanceof Monster) {
            System.out.println("恭喜你成功杀死了一个怪物。");
            map[row+1][col]=new Space(row+1,col);
        }
        else if (map[row][col-1]instanceof Monster) {
            System.out.println("恭喜你成功杀死了一个怪物。");
            map[row][col-1]=new Space(row,col-1);
        }
        else if (map[row][col+1]instanceof Monster) {
            System.out.println("恭喜你成功杀死了一个怪物。");
            map[row][col+1]=new Space(row,col+1);
        }
        else {
            b=false;
            System.out.println("你的操作有误，周围没有怪物的啊。");
        }
        return b;
    }
}
