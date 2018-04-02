import java.util.Scanner;
import java.util.Stack;

public class Player {
    public static char[][] map;
    public static String player = "";

    public Player(char[][] map0) {
        map = map0;
        map[1][1] = '○';
    }

    public static String getIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name: ");
        player = scanner.nextLine();
        return player;
    }

    public static int[] moveByPlayer(int x, int y, Monster monster, StackOfMap maps, char[][] map) {
        //从控制台获取一个char类型的输入
        int[] k = new int[3];
        k[0] = 0;
        k[1] = x;
        k[2] = y;
        System.out.println("请输入您的下一步操作： ");
        Scanner scanner1 = new Scanner(System.in);
        String m = scanner1.nextLine();
        char move = m.charAt(0);
        switch (move) {
            case 'w':
                if (map[x - 1][y] == '▓') {
                    System.out.println("Wrong:invalid.");
                } else if (map[x - 1][y] == '＃') {
                    System.out.println("你死了。");
                    k[0] = -2;
                    map[x][y] = '．';
                    x--;
                } else if (map[x - 1][y] == '％') {
                    map[x][y] = '．';
                    x--;
                    map[x][y] = '○';
                    System.out.println("你已经拾取了此处的宝物 ");
                    maps.push(Util.array2DCopy(map));

                } else {
                    map[x][y] = '．';
                    x--;
                    map[x][y] = '○';
                    maps.push(Util.array2DCopy(map));
                }

                break;
            case 'a':
                if (map[x][y - 1] == '▓') {
                    System.out.println("Wrong:invalid.");
                } else if (map[x][y - 1] == '＃') {
                    System.out.println("你死了。");
                    k[0] = -2;
                    map[x][y] = '．';
                    y--;
                } else if (map[x][y - 1] == '％') {
                    map[x][y] = '．';
                    y--;
                    map[x][y] = '○';
                    System.out.println("你已经拾取了此处的宝物 ");
                    maps.push(Util.array2DCopy(map));

                } else {
                    map[x][y] = '．';
                    y--;
                    map[x][y] = '○';
                    maps.push(Util.array2DCopy(map));
                }

                break;
            case 's':
                if (map[x + 1][y] == '▓') {
                    System.out.println("Wrong:invalid.");
                } else if (map[x + 1][y] == '＃') {
                    System.out.println("你死了。");
                    k[0] = -2;
                    map[x][y] = '．';
                    x++;
                } else if (map[x + 1][y] == '％') {
                    map[x][y] = '．';
                    x++;
                    map[x][y] = '○';
                    System.out.println("你已经拾取了此处的宝物 ");
                    maps.push(Util.array2DCopy(map));

                } else {
                    map[x][y] = '．';
                    x++;
                    map[x][y] = '○';
                    maps.push(Util.array2DCopy(map));
                }

                break;
            case 'd':
                if (map[x][y + 1] == '▓') {
                    System.out.println("Wrong:invalid.");
                } else if (map[x][y + 1] == '＃') {
                    System.out.println("你死了。");
                    k[0] = -2;
                    map[x][y] = '．';
                    y++;
                } else if (map[x][y + 1] == '％') {
                    map[x][y] = '．';
                    y++;
                    map[x][y] = '○';
                    System.out.println("你已经拾取了此处的宝物 ");
                    maps.push(Util.array2DCopy(map));
                } else {
                    map[x][y] = '．';
                    y++;
                    map[x][y] = '○';
                    maps.push(Util.array2DCopy(map));
                }

                break;
            case 'h':
                helpInformation();
                moveByPlayer(k[1], k[2],monster,maps,map);
                break;
            case 'q':
                k[0] = -2;
                break;
            case 'k':
                k[0] = 3;
                if (monster.killOne(map, k[1], k[2])) {
                    System.out.println("恭喜你加五分");
                }
                break;
            case 'l':
                playerInformation();
                break;
            case 'b':

                break;
            case 'x':
                k[0] = -2;
                break;
        }
        k[1] = x;
        k[2] = y;
        return k;
    }


    public static void helpInformation() {
        System.out.print(player + ",您好\n" + "如果陷入死局无法行走请您按r结束这次游戏，你可以再次重新开始探险之旅。\n"
                + "笑脸代表您，路上％,＃分别代表宝物和怪物，遇上怪物按k键即可杀死它，而想要拾取宝物只需到达宝物所在位置即可拾取\n"
                + "在您行走的过程中会留下足迹，并且按b键即可后退一步,直到退无可退\n" + "祝您游戏愉快！");
    }

    public static void playerInformation() {
        System.out.println("玩家：" + player);
    }
}
