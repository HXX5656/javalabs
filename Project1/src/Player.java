
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Player {
    private static int num;
    private static char  pe;
    private static StackOfAction s=new StackOfAction();
    private static String player;
    private static int score1;
    private static int score2;
    private static int score3;
    private static int atk=1;
    private static int def=1;
    private static int hp=5;
    public Player() {
        pe = '○';
        System.out.println("请输入您的名字(不能输入为空)： ");
        Scanner scanner1 = new Scanner(System.in);
        player = scanner1.nextLine();
        for (int ll=0;ll>=0;) {
            if (player.equals("")) {
                System.out.print("您的输入有误，请您重新输入：");
                Scanner scanner2 = new Scanner(System.in);
                player= scanner2.nextLine();
            }
            else {
                ll=-1;
            }
        }

    }
    public static int[] moveByPlayer(int x, int y, char[][] map,Monster monster,int[] section) throws IOException {
        //从控制台获取一个char类型的输入
        int[] k = new int[3];
        k[0] = 0;
        k[1] = x;
        k[2] = y;
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
                case 'w':
                    if (map[x - 1][y] == '▓') {
                        System.out.println("Wrong:invalid.");
                    } else if (map[x - 1][y] == '＃') {
                        s.push('w');
                        System.out.println("你死了。");
                        k[0] = -2;
                        map[x][y] = '　';
                        x--;
                    } else if (map[x - 1][y] == '％') {
                        s.push('w');
                        map[x][y] = '　';
                        x--;
                        map[x][y] = '○';
                        atk++;
                        System.out.println("你已经拾取了此处的宝物 ");
                        System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                    } else {
                        s.push('w');
                        map[x][y] = '　';
                        x--;
                        map[x][y] = '○';
                    }

                    break;
                case 'a':
                    if (map[x][y - 1] == '▓') {
                        System.out.println("Wrong:invalid.");
                    } else if (map[x][y - 1] == '＃') {
                        s.push('a');
                        System.out.println("你死了。");
                        k[0] = -2;
                        map[x][y] = '　';
                        y--;
                    } else if (map[x][y - 1] == '％') {
                        s.push('a');
                        map[x][y] = '　';
                        y--;
                        map[x][y] = '○';
                        atk++;
                        System.out.println("你已经拾取了此处的宝物 ");
                        System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                    } else {
                        s.push('a');
                        map[x][y] = '　';
                        y--;
                        map[x][y] = '○';
                    }

                    break;
                case 's':
                    if (map[x + 1][y] == '▓') {
                        System.out.println("Wrong:invalid.");
                    } else if (map[x + 1][y] == '＃') {
                        s.push('s');
                        System.out.println("你死了。");
                        k[0] = -2;
                        map[x][y] = '　';
                        x++;
                    } else if (map[x + 1][y] == '％') {
                        s.push('s');
                        map[x][y] = '　';
                        x++;
                        map[x][y] = '○';
                        atk++;
                        System.out.println("你已经拾取了此处的宝物 ");
                        System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                    } else {
                        s.push('s');
                        map[x][y] = '　';
                        x++;
                        map[x][y] = '○';
                    }

                    break;
                case 'd':
                    if (map[x][y + 1] == '▓') {
                        System.out.println("Wrong:invalid.");
                    } else if (map[x][y + 1] == '＃') {
                        s.push('d');
                        System.out.println("你死了。");
                        k[0] = -2;
                        map[x][y] = '　';
                        y++;
                    } else if (map[x][y + 1] == '％') {
                        s.push('d');
                        map[x][y] = '　';
                        y++;
                        map[x][y] = '○';
                        atk++;
                        System.out.println("你已经拾取了此处的宝物 ");
                        System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);
                    } else {
                        s.push('d');
                        map[x][y] = '　';
                        y++;
                        map[x][y] = '○';
                    }
                    break;
            case 'h':
                helpInformation();
                break;
            case 'q':
                k[0] = -2;
                break;
            case 'x':
                k[0] = -2;
                break;
            case 'k':
                k[0] = 3;
                if (killOne(map, k[1], k[2],monster)) {

                    System.out.println("请继续加油哦。");
                }

                break;
            case 'l':
                playerInformation();
                break;
            case 'b':
                char n=s.pop();
                switch (n) {
                    case 's':
                        if (map[x - 1][y] == '▓') {
                            System.out.println("Wrong:invalid.");
                        } else if (map[x - 1][y] == '＃') {
                            System.out.println("你死了。");
                            k[0] = -2;
                            map[x][y] = '　';
                            x--;
                        } else if (map[x - 1][y] == '％') {
                            map[x][y] = '　';
                            x--;
                            map[x][y] = '○';
                            System.out.println("你已经拾取了此处的宝物 ");
                            System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                        } else {
                            map[x][y] = '　';
                            x--;
                            map[x][y] = '○';
                        }

                        break;
                    case 'd':
                        if (map[x][y - 1] == '▓') {
                            System.out.println("Wrong:invalid.");
                        } else if (map[x][y - 1] == '＃') {
                            System.out.println("你死了。");
                            k[0] = -2;
                            map[x][y] = '　';
                            y--;
                        } else if (map[x][y - 1] == '％') {
                            map[x][y] ='　';
                            y--;
                            map[x][y] = '○';
                            System.out.println("你已经拾取了此处的宝物 ");
                            System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                        } else {
                            map[x][y] = '　';
                            y--;
                            map[x][y] = '○';
                        }

                        break;
                    case 'w':
                        if (map[x + 1][y] == '▓') {
                            System.out.println("Wrong:invalid.");
                        } else if (map[x + 1][y] == '＃') {
                            System.out.println("你死了。");
                            k[0] = -2;
                            map[x][y] = '　';
                            x++;
                        } else if (map[x + 1][y] == '％') {
                            map[x][y] = '　';
                            x++;
                            map[x][y] = '○';
                            System.out.println("你已经拾取了此处的宝物 ");
                            System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);

                        } else {
                            map[x][y] = '　';
                            x++;
                            map[x][y] = '○';
                        }

                        break;
                    case 'a':
                        if (map[x][y + 1] == '▓') {
                            System.out.println("Wrong:invalid.");
                        } else if (map[x][y + 1] == '＃') {
                            System.out.println("你死了。");
                            k[0] = -2;
                            map[x][y] = '　';
                            y++;
                        } else if (map[x][y + 1] == '％') {
                            map[x][y] = '　';
                            y++;
                            map[x][y] = '○';
                            System.out.println("你已经拾取了此处的宝物 ");
                            System.out.println("这件宝物使你的攻击力加一，所以你的攻击力是 "+atk);
                        } else {
                            map[x][y] ='　';
                            y++;
                            map[x][y] = '○';
                        }
                        break;
                }
                break;
            case 'i' ://存档
                java.io.PrintWriter input=new java.io.PrintWriter("input.txt");
                PrintWriter s1=new PrintWriter(input);
                s1.println(section[0]);//存进去第几关
                s1.println(score1);//闯关得分
                s1.println(score2);//捡宝得分
                s1.println(score3);//杀怪得分
                s1.println((s.getSize()));//总步数
                s1.println(atk);//攻击力
                s1.println(def);//防御力
                s1.close();
                break;


            default:
                System.out.println("Wrong:invalid!");
        }
        k[1] = x;
        k[2] = y;
        return k;
    }
    public static int print(int[] k,char[][] map,Monster monster,int[] section) throws IOException {
           int signal=0;
           s.footpoint(map,k[1],k[2]);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("" + map[i][j]);
            }
            System.out.print("\n");
        }

            if (k[0] == -2) {
                System.exit(0);

            } else if (k[1] == map.length - 2 && k[2] == map[0].length - 1) {
                System.out.println("You win!");
                score1 = score1 + 20;
                signal = -1;

            } else {
                k = moveByPlayer(k[1], k[2], map, monster,section);

            }
            if (signal == 0) {
                print(k, map, monster,section);
            }
            return signal;

    }
    public static int print(int[] k,Monster monster,char[][] map,Player player,int[] section) throws IOException {
        monster.moveAll(map,player);
        s.footpoint(map,k[1],k[2]);
        int signal=0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("" + map[i][j]);
            }
            System.out.print("\n");
        }

        if (k[0] == -2) {
            System.exit(0);
        } else if (k[0] == -1) {
            k = moveByPlayer(k[1], k[2], map,monster,section);

        } else if (k[1] == map.length - 2&&k[2]==map[0].length-1) {
            System.out.println("You win!");
            score1=score1+20;
            signal=-1;
        } else {
            k = moveByPlayer(k[1], k[2],map,monster,section);
        }
        if (signal==0) {
            print(k, monster,map,player,section);
        }
        return signal;
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
        System.out.println("攻击力："+atk);
        System.out.println("防御力："+def);
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
    public static void setScores(int num2,int num3) {
        score2+=num2*5;
        score3+=num3*5;
    }

    public static boolean killOne(char[][] map,int x,int y,Monster monster) {
        boolean b=true;
    if (map[x-1][y]=='＃') {
        System.out.print("怪物原本"+monster.getHp(x-1,y)+"滴血");
        for(int i=0;i<monster.getHp(x-1,y);i++) {
            System.out.print("█");
        }
        System.out.print("\n");
        if(atk>=monster.getDef(x-1,y)) {
            monster.setHp(x-1,y,1+atk-monster.getDef(x-1,y));
        }
        else {
            monster.setHp(x-1,y,1);
        }
        if (monster.getHp(x-1,y)<=0) {
            map[x-1][y]='　';
        System.out.println("你成功地杀掉了一个怪物！");
        def++;
        System.out.println("杀死怪物后你得到了宝物：这件宝物使你的防御力加一，所以你的防御力现在是："+def);
        }
        else {
            System.out.print("怪物还剩"+monster.getHp(x-1,y)+"滴血");
            for(int i=0;i<monster.getHp(x-1,y);i++) {
                System.out.print("█");
            }
            System.out.print("\n");
        }
    }
    else if (map[x+1][y]=='＃') {
        System.out.print("怪物原本"+monster.getHp(x+1,y)+"滴血");
        for(int i=0;i<monster.getHp(x+1,y);i++) {
            System.out.print("█");
        }
        System.out.print("\n");
        if(atk>=monster.getDef(x+1,y)) {
            monster.setHp(x+1,y,1+atk-monster.getDef(x+1,y));
        }
        else {
            monster.setHp(x+1,y,1);
        }
        if (monster.getHp(x+1,y)<=0) {
            map[x+1][y]='　';
            System.out.println("你成功地杀掉了一个怪物！");
            def++;
            System.out.println("杀死怪物后你得到了宝物：这件宝物使你的防御力加一，所以你的防御力现在是："+def);
        }
        else {
            System.out.print("怪物还剩"+monster.getHp(x+1,y)+"滴血");
            for(int i=0;i<monster.getHp(x+1,y);i++) {
                System.out.print("█");
            }
            System.out.print("\n");
        }
    }
    else if (map[x][y-1]=='＃') {
        System.out.print("怪物原本"+monster.getHp(x,y-1)+"滴血:");
        for(int i=0;i<monster.getHp(x,y-1);i++) {
            System.out.print("█");
        }
        System.out.print("\n");
        if(atk>=monster.getDef(x,y-1)) {
            monster.setHp(x,y-1,1+atk-monster.getDef(x,y-1));
        }
        else {
            monster.setHp(x,y-1,1);
        }
        if (monster.getHp(x,y-1)<=0) {
            map[x][y-1]='　';
            System.out.println("你成功地杀掉了一个怪物！");
            def++;
            System.out.println("杀死怪物后你得到了宝物：这件宝物使你的防御力加一，所以你的防御力现在是："+def);
        }
        else {
            System.out.print("怪物还剩"+monster.getHp(x,y-1)+"滴血");
            for(int i=0;i<monster.getHp(x,y-1);i++) {
                System.out.print("█");
            }
            System.out.print("\n");
        }
    }
    else if (map[x][y+1]=='＃') {
        System.out.print("怪物原本"+monster.getHp(x,y+1)+"滴血,");
        for(int i=0;i<monster.getHp(x,y+1);i++) {
            System.out.print("█");
        }
        System.out.print("\n");
        if(atk>=monster.getDef(x,y+1)) {
            monster.setHp(x,y+1,1+atk-monster.getDef(x,y+1));
        }
        else {
            monster.setHp(x,y+1,1);
        }
        if (monster.getHp(x,y+1)<=0) {
            map[x][y+1]='　';
            System.out.println("你成功地杀掉了一个怪物！");
            def++;
            System.out.println("杀死怪物后你得到了宝物：这件宝物使你的防御力加一，所以你的防御力现在是："+def);
        }
        else {
            System.out.print("怪物还剩"+monster.getHp(x,y+1)+"滴血");
            for(int i=0;i<monster.getHp(x,y+1);i++) {
                System.out.print("█");
            }
            System.out.print("\n");
        }
    }
    else {
        System.out.println("您的操作有误，周围没有怪物！");
        b=false;
    }
    return b;
    }
    public static int getDef() {
        return def;
    }
    public static void setHp(int value) {
        hp=hp-value;
    }
    public static int getHp() {
        return hp;
    }



}
