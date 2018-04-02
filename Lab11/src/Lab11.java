import java.io.IOException;
import java.util.Scanner;

//这个程序用抽象类和接口实现迷宫游戏，并非所有怪物都可以移动，游戏分四层，有故事模式和沙盒模式,每一局最多设置一个会移动的怪物，且怪物的移动随机不等距
public class Lab11 {
    private static int section;
    private static int[][] map1={
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
        {1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
        {1,1,1,0,1,0,0,1,1,1,1,1,0,0,0,0,0,1,0,1,0,1},
        {1,0,1,0,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1},
        {1,0,1,0,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,1},
        {1,0,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,1,1,0,1},
        {1,0,1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,1,0,0,0,1},
        {1,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,0,1,0,0,0,1},
        {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1},
        {1,0,1,1,1,1,1,1,1,0,1,1,1,1,0,1,0,0,0,1,0,1},
        {1,0,0,1,1,1,1,0,1,1,0,0,0,0,0,1,0,0,1,1,0,1},
        {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,1,0,0,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
};
    private static int[][] map4={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1},
            {1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,1,0,1,0,0,2,1,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,1,0,2,0,0,0,1,2,0,0,1},
            {1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
            {1,0,1,0,1,2,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1},
            {1,2,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,2,1,0,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private static int[][] map2={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,1,2,0,0,1,0,0,0,0,0,1,2,1},
            {1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1},
            {1,1,1,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,1},
            {1,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1},
            {1,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,1,2,0,1},
            {1,0,1,1,1,0,1,1,0,0,0,0,1,1,1,1,0,1,0,0,1},
            {1,0,1,1,0,0,1,1,1,1,0,1,1,0,0,0,0,1,0,0,1},
            {1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
            {1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
            {1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,2,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1},
            {1,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,0,1,2,0,0,1,1,1,1,1,1,0,1},
            {1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}


    };
    private static int[][] map3={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1},
            {1,2,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,2,0,0,0,1,0,0,0,0,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,2,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,2,1,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,2,1,0,1,1,1,0,1,1,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,1,1,1,1,1,1,2,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private static Player playerNow;
    private static Object[][] map;
    public static void main(String[] args) throws IOException {
        if (args[0].equals("mode")&&args[1].equals("sandbox")) {
            if (args[2].equals("map")) {
                section=Integer.parseInt(args[3]);
            }
        }
        else {
            section=1;
        }
        playGame();
    }
    public static void playGame() throws IOException {
        Player player;
        if (section == 4) {
            System.out.println("走到这里激动吗？第四轮游戏请千万别大意");
            System.out.println("国王使出了他的杀手锏，这一轮，你遇到的怪物都是些会动的杀气很重的大怪物。");
            System.out.println("加油吧，胜利就在前方。");
            map = new Object[map4.length][map4[0].length];
            for (int count = 0; count < 6; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map4[x][y] == 0) {
                    map4[x][y] = 3;
                    count++;
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map4[i][j] == 0) {
                        map[i][j] = new Space(i, j);
                    } else if (map4[i][j] == 1) {
                        map[i][j] = new Wall(i, j);
                    } else if (map4[i][j] == 2) {
                        map[i][j] = new Treasure(i, j);
                    } else {
                        map[i][j] = new Monster(i, j, map);
                    }
                }
            }
            player=new Player(map);
            map[1][1] = player;
            player.print();
            System.out.println("你已经结束了这次游戏，恭喜你成为继承人");
            System.exit(0);
        }

        if (section == 3) {
            System.out.print("你出色的完成了前两局的任务，国王准备加大难度考验你，第三轮，你将会遇到虎视眈眈想要杀了你的怪物。\n");
            System.out.println("前路难行，请你拿起武器，用k键杀掉周围的低级怪物吧！\n");
            map = new Object[map3.length][map3[0].length];
            for (int count = 0; count < 6; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map3[x][y] == 0) {
                    map3[x][y] = 3;
                    count++;
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map3[i][j] == 0) {
                        map[i][j] = new Space(i, j);
                    } else if (map3[i][j] == 1) {
                        map[i][j] = new Wall(i, j);
                    } else if (map3[i][j] == 2) {
                        map[i][j] = new Treasure(i, j);
                    } else {
                        map[i][j] = new Monster(i, j, map);
                    }
                }
            }
            player=new Player(map);
            map[1][1] = player;
            player.print();
            System.out.print("恭喜你走出了这一关的迷宫，请问你是否要继续？（输入p代表您进入下一关，其他输入您将退出）：");
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
                section++;
                playGame();
            }
            else {
                System.out.println("您本局的游戏已经结束");
                player.playerInformation();
                System.exit(0);
            }
        } else if (section == 2) {
            System.out.print("你是否觉得so easy呢？\n"+"别急！考验，才刚刚开始。\n");
            System.out.println("你将进入的是第二层迷宫，在那里，国王给你准备了礼物，请尽情收集吧！\n");
            map = new Object[map2.length][map2[0].length];
            for (int count = 0; count < 6; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map2[x][y] == 0) {
                    map2[x][y] = 3;
                    count++;
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map2[i][j] == 0) {
                        map[i][j] = new Space(i, j);
                    } else if (map2[i][j] == 1) {
                        map[i][j] = new Wall(i, j);
                    } else if (map2[i][j] == 2) {
                        map[i][j] = new Treasure(i, j);
                    } else {
                        map[i][j] = new Monster(i, j, map);
                    }
                }
            }
            player=new Player(map);
            map[1][1] = player;
            player.print();
            System.out.print("恭喜你走出了这一关的迷宫，请问你是否要继续？（输入p代表您进入下一关，其他输入您将退出）：");
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
                section++;
                playGame();
            }
            else {
                System.out.println("您本局的游戏已经结束");
                player.playerInformation();
                System.exit(0);
            }
        }
        else {
            System.out.print("你是一位生长在民间的王子。\n"+"国王为了考验你是否具有担任下一任国王的能力，将你放入了四层迷宫里。\n");
            System.out.print("你需要靠你的智慧走出迷宫，在这一路上，有宝物，也有怪物。拾取宝物，你可以加五分；而杀死怪物，你也可以加五分\n");
            System.out.println("每一关结束我们都将重新统计你当前得分数。");
            System.out.println("努力前行吧，只有获得最高分你才能成为下一任国王！");
            System.out.println("你将进入的是第一层迷宫，你只需走到开口即可走出，并获得20分的闯关分。");
            map = new Object[map1.length][map1[0].length];
            for (int count = 0; count < 6; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map1[x][y] == 0) {
                    map1[x][y] = 3;
                    count++;
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map1[i][j] == 0) {
                        map[i][j] = new Space(i, j);
                    } else if (map1[i][j] == 1) {
                        map[i][j] = new Wall(i, j);
                    } else if (map1[i][j] == 2) {
                        map[i][j] = new Treasure(i, j);
                    } else {
                        map[i][j] = new Monster(i, j, map);
                    }
                }
            }
            player=new Player(map);
            map[1][1] = player;
            player.print();
            System.out.print("恭喜你走出了这一关的迷宫，请问你是否要继续？（输入p代表您进入下一关，其他输入您将退出）：");
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
                section++;
                playGame();
            }
            else {
                System.out.println("您本局的游戏已经结束");
                player.playerInformation();
                System.exit(0);
            }
        }
    }
}