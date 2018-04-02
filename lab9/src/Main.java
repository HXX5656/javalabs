import java.util.Stack;

public class Main {
    private static String players;
    private static int x = 1;
    private static int y = 1;
    private static char move;
    public static char[][] map0;
    private static int i = 0;

    public static void main(String[] args) {
        StackOfMap maps = new StackOfMap();
        MazeMap map = new MazeMap();
        map.map0();
        map.search();
        map0 = map.re();
        char[][] map00 = new char[21][21];
        Player player = new Player(map0);
        players = player.getIn();
        Monster monster = new Monster(6, map0);
        Treasure treasure = new Treasure(map0, 6);
        int[][] level=new int[map0.length][map0[0].length];
        for (int j = 0; j <map0.length ; j++) {
            for (int k=0;k<map0[0].length;k++) {
                if (map0[j][k]=='▓') {
                    level[j][k]=1;
                }
                else if (map0[j][k]=='％') {
                    level[j][k]=2;
                }
                else {
                    level[j][k]=0;
                }
                System.out.print(level[j][k]+",");
            }
            System.out.print("\n");

        }
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                map00[i][j] = map0[i][j];
            }
        }
        maps.push(map00); // 这个是拷贝过的
        int[] k = {0, 1, 1};
        print(k, player, monster, maps);
    }

    public static void print(int[] k, Player player, Monster monster, StackOfMap maps) {
        map0 = Util.array2DCopy(maps.peek());

        for (int i = 0; i < map0.length; i++) {
            for (int j = 0; j < map0[0].length; j++) {
                System.out.print("" + map0[i][j]);
            }
            System.out.print("\n");
        }

        if (k[0] == -2) {
            System.exit(0);
        } else if (k[0] == -1) {
            k = player.moveByPlayer(k[1], k[2], monster, maps, map0);
            monster.moveAll(map0);

        } else if (k[1] == map0.length - 2) {
            System.out.println("You win!");
            System.exit(0);
        } else {
            k = player.moveByPlayer(k[1], k[2], monster, maps, map0);
            monster.moveAll(map0);
        }
        print(k, player, monster, maps);
    }


}
