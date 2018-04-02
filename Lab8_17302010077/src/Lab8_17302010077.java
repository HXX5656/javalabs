import java.util.Scanner;

public class Lab8_17302010077 {
    static char Ｗ = '▓';
    static char Ｐ = '＊';
    static char Ｅ = '　';
    static char Ｖ = '＃';
    static Treasure treasuresNow=new Treasure(6);
    public static   void main(String[] args){
        int[] a={0};
        int[] b=new int[1];
        System.arraycopy(a,0,b,0,1);
        a[0]=2;
        System.out.print(""+b[0]);
        System.exit(0);
        char[][] map=treasuresNow.getMap();
        Scanner scanner= new Scanner(System.in);
        System.out.println("please enter your name: ");
        String playerNow = scanner.nextLine();
        //做一些准备工作
        //将这些变量以数组元素的形式定义，以便其能从其他方法中改变,不用返回值
        int[]  l={1};int[] i={1};int [] j={1};
        //告知玩家游戏规则
        System.out.print(playerNow  +  " ,你准备好了吗？本迷宫游戏需要你跨越重重阻隔。\n"
                +"从出发点到达＃处，那里有能将你带出去的神秘力量。请加油哦！\n"
                + "＊代表您，请用wasd分别控制你的左、上、下、右的行走。\n"
                +"在你探寻迷宫的过程中将会有6个宝物❉等着你，你移动到宝物处即可拾取。"
                +"如果陷入死局，按wasd，enter以外的其它任何键均可退出。请注意不能直接按enter键。\n"
                + "请注意不能撞墙哦!\n"
                +"祝您游戏愉快！\n");
        //打印出初始的地图
        for (int q=0;q<=17;q++){
            for (int n=0;n<=22;n++) {
                System.out.print("" + map[q][n]);
            }
            System.out.print("\n");
        }
        //根据玩家操作来输出不同的信息
        for (int n=1;n>=1;n++) {
            moveByPlayer(map, l,i,j);
            if (map[16][21] == Ｐ) {
                n = -1;
                System.out.println(playerNow + " ,恭喜你获取了神秘力量，顺利走出了迷宫!");
                if (treasuresNow.getNumbers(map)==0) {
                    System.out.print("拾取了6个宝物！你真优秀！");
                }
            } else if (l[0] == 0) {
                System.out.println(playerNow + " ,您已主动结束游戏。很遗憾您未能走出迷宫。");
                n = -1;
            } else if (l[0] == -1) {
                System.out.println(playerNow + " ,Wrong:invalid");
                l[0] = 1;
            } else {
                System.out.println(playerNow + " ，请继续加油哦!\n"+"你已经拾取了"
                        +(6-treasuresNow.getNumbers(map))+"个宝物\n"
                        +"你还有" +treasuresNow.getNumbers(map)+"个宝物等待拾取");
            }


        }
        //让程序正常的退出
        System.exit(0);

    }

    //写一个方法，对应用户的每一步操作
    private static void moveByPlayer( char[][] map,int[] k ,int[] i,int[] j){
        //从控制台获取一个char类型的输入

        System.out.println("请输入您的下一步操作： ");
        Scanner scanner1= new Scanner(System.in);
        String  m = scanner1.nextLine();
        char move=m.charAt(0);
        //用选择结构对应用户输入的不同情况
        switch (move) {
            case 'w' :
                j[0]--;
                if (map[j[0]][i[0]]==Ｗ) {
                    k[0]=-1;
                    j[0]++;

                }
                else {
                    j[0]++;
                    map [j[0]][i[0]] = Ｅ;
                    j[0]--;
                    map [j[0]][i[0]] = Ｐ;
                }
                break;
            case 'a':

                i[0]--;
                if (map[j[0]][i[0]]==Ｗ) {
                    k[0]=-1;
                    i[0]++;
                }
                else {
                    i[0]++;
                    map [j[0]][i[0]] = Ｅ;
                    i[0]--;
                    map [j[0]][i[0]] = Ｐ;
                }
                break;
            case 'd' :
                i[0]++;
                if (map[j[0]][i[0]]==Ｗ) {
                    k[0]=-1;
                    i[0]--;
                }
                else {
                    i[0]--;
                    map [j[0]][i[0]] = Ｅ;
                    i[0]++;
                    map [j[0]][i[0]] =Ｐ;
                }
                break;
            case 's' :
                j[0]++;
                if (map[j[0]][i[0]]==Ｗ) {
                    k[0]=-1;
                    j[0]--;
                }
                else {
                    j[0]--;
                    map[j[0]][i[0]] = Ｅ;
                    j[0]++;
                    map[j[0]][i[0]] = Ｐ;
                }
                break;
            default :
                k[0]=0;
                break;

        }


        for (int q=0;q<=17;q++){
            for (int n=0;n<=22;n++) {
                System.out.print("" + map[q][n]);
            }
            System.out.print("\n");
        }

    }


    }



