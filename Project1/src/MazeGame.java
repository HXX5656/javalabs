import java.io.IOException;
import java.util.Scanner;
public class MazeGame {
    private static int[] section=new int[1];
    private static char[][] map;
 public static void main(String[] args) throws IOException {
     for (int i = 0; i <args.length ; i++) {
         System.out.println(""+args[i]);
     }

     if (args[0].equals("mode")&&args[1].equals("sandbox")) {
            if (args[2].equals("map")) {
                section[0]=Integer.parseInt(args[3]);
            }
        }
        else {
            section[0]=1;
        }
        Player player=new Player();
        playGame(player);
 }
 public static void playGame(Player player) throws IOException {
     if (section[0]==1) {
         System.out.print("你是一位生长在民间的王子。\n"+"国王为了考验你是否具有担任下一任国王的能力，将你放入了四层迷宫里。\n");
         System.out.print("你需要靠你的智慧走出迷宫，在这一路上，有宝物，也有怪物。拾取宝物，你可以加五分；而杀死怪物，你也可以加五分\n");
         System.out.println("每一关结束我们都将重新统计你当前得分数。");
         System.out.println("努力前行吧，只有获得最高分你才能成为下一任国王！");
         System.out.println("你将进入的是第一层迷宫，你只需走到开口即可走出，并获得20分的闯关分。");
         int[] k={0,1,1};
         MazeMap map1=new MazeMap();
         map=map1.re(1);
         System.out.print(""+map.length +""+map[0].length);
         Monster nomonster=new Monster(0,map);
         player.print(k,nomonster,map,player,section);
         player.playerInformation();
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
             section[0]++;
         }
         else {
             System.out.println("您本局的游戏已经结束");
             player.playerInformation();
             System.exit(0);
         }


     }
     if (section[0]==2) {
         System.out.print("你是否觉得so easy呢？\n"+"别急！考验，才刚刚开始。\n");
         System.out.println("你将进入的是第二层迷宫，在那里，国王给你准备了礼物，请尽情收集吧！\n");
         int[] k={0,1,1};
         MazeMap map2=new MazeMap();
         map=map2.re(2);
         Monster nomonster=new Monster(0,map);
         player.print(k,nomonster,map,player,section);
         player.setScores(map2.getTreasureNumber(map),0);
         player.playerInformation();
         System.out.println("恭喜你走出了这一关的迷宫，请问你是否要继续？（输入p代表您进入下一关，其他输入您将退出）：");
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
             section[0]++;
         }
         else {
             System.out.println("您本局的游戏已经结束");
             player.playerInformation();
             System.exit(0);
         }


     }
     if (section[0]==3) {
         System.out.print("你出色的完成了前两局的任务，国王准备加大难度考验你，第三轮，你将会遇到虎视眈眈想要杀了你的怪物。\n");
         System.out.println("前路难行，请你拿起武器，用k键杀掉周围的低级怪物吧！\n");
         int[] k={0,1,1};
         MazeMap map3=new MazeMap();
         map=map3.re(3);
         Monster monster=new Monster(6,map);
         player.print(k,map,monster,section);
         player.setScores(map3.getTreasureNumber(map),monster.getNumber(map));
         player.playerInformation();
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
             section[0]++;
         }
         else {
             System.out.println("您本局的游戏已经结束");
             player.playerInformation();
             System.exit(0);
         }

     }
     if (section[0]==4) {
         System.out.println("走到这里激动吗？第四轮游戏请千万别大意");
         System.out.println("国王使出了他的杀手锏，这一轮，你遇到的怪物都是些会动的杀气很重的大怪物。");
         System.out.println("加油吧，胜利就在前方。");
         int[] k={0,1,1};
         MazeMap map4=new MazeMap();
         map=map4.re(4);
         Monster monster=new Monster(6,map);
         monster.setAtk(4);
         monster.setDef(4);
         player.print(k,monster,map,player,section);

         player.setScores(map4.getTreasureNumber(map),monster.getNumber(map));
         player.playerInformation();
         System.out.print("恭喜你走出了这一关的迷宫!");
         System.out.println("您本局的游戏已经结束");
         System.exit(0);
     }

     System.exit(0);
 }
}
