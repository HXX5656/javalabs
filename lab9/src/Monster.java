public class Monster {
    private static int num;
    private static int[][] pos;
    private static int s = -4;
    private static int[] k = {0};

    public Monster(int i, char[][] map) {
        num = i;
        pos = new int[num][3];
        newMon(map);
    }

    public static void newMon(char[][] map) {
        for (int count = 0; count < num; ) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map[0].length);
            if (map[x][y] == '　') {
                map[x][y] = '＃';
                pos[count][0] = x;
                pos[count][1] = y;
                pos[count][2] = 0;
                count++;
            }
        }
    }

    public static int getNum() {
        return num;
    }

    public static boolean killOne(char[][] map, int x, int y) {
        int s = -4;
        for (int i = 0; i < num; i++) {
            if (x - 1 == pos[i][0] && y == pos[i][1]) {
                pos[i][2] = -1;//-1说明怪物被杀了
                num--;
                map[pos[i][0]][pos[i][1]] = '　';
                break;
            } else if (i == num - 1) {
                s++;
            }
        }
        for (int i = 0; i < num; i++) {
            if (x == pos[i][0] && y - 1 == pos[i][1]) {
                pos[i][2] = -1;//-1说明怪物被杀了
                num--;
                map[pos[i][0]][pos[i][1]] = '　';
                break;
            } else if (i == num - 1) {
                s++;
            }
        }
        for (int i = 0; i < num; i++) {
            if (x + 1 == pos[i][0] && y == pos[i][1]) {
                pos[i][2] = -1;//-1说明怪物被杀了
                num--;
                map[pos[i][0]][pos[i][1]] = '　';
                break;
            } else if (i == num - 1) {
                s++;
            }
        }
        for (int i = 0; i < num; i++) {
            if (x == pos[i][0] && y + 1 == pos[i][1]) {
                pos[i][2] = -1;//-1说明怪物被杀了
                num--;
                map[pos[i][0]][pos[i][1]] = '　';
                break;
            } else if (i == num - 1) {
                s++;
            }
        }
        return s == 0;
    }

    public static int gets() {
        return s;
    }

    public static int getk() {
        return k[0];
    }

    public static void moveAll(char[][] map) {
        for (int i = 0; i < pos.length; i++) {
            if (pos[i][2] == 0) {
                int[] temp = move(map, pos[i][0], pos[i][1]);
                pos[i][0] = temp[0];
                pos[i][1] = temp[1];

            }

        }
    }

    public static int[] move(char[][] map, int x, int y) {
        int[] pos = new int[2];
        int m = (int) (Math.random() * 4);
        switch (m) {
            case 0://w
                if (map[x - 1][y] == '▓') {
                } else if (map[x - 1][y] == '○') {
                    map[x - 1][y] = '＃';
                    map[x][y] = '　';
                    k[0] = -1;
                    System.out.println("你被怪物杀死了。");
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            System.out.print("" + map[i][j]);
                        }
                        System.out.print("\n");
                    }
                    x--;
                    System.exit(0);
                } else {
                    map[x - 1][y] = '＃';
                    map[x][y] = '　';
                    x--;
                }
                break;
            case 1://a
                if (map[x][y - 1] == '▓') {
                } else if (map[x][y - 1] == '○') {
                    map[x][y - 1] = '＃';
                    map[x][y] = '　';
                    k[0] = -1;
                    System.out.println("你被怪物杀死了。");
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            System.out.print("" + map[i][j]);
                        }
                        System.out.print("\n");
                    }
                    y--;
                    System.exit(0);
                } else {
                    map[x][y - 1] = '＃';
                    map[x][y] = '　';
                    y--;
                }
                break;
            case 2://s
                if (map[x + 1][y] == '▓') {
                } else if (map[x + 1][y] == '○') {
                    map[x + 1][y] = '＃';
                    map[x][y] = '　';
                    k[0] = -1;
                    System.out.println("你被怪物杀死了。");
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            System.out.print("" + map[i][j]);
                        }
                        System.out.print("\n");
                    }
                    x++;
                    System.exit(0);
                } else {
                    map[x + 1][y] = '＃';
                    map[x][y] = '　';
                    x++;
                }
                break;
            case 3://d
                if (map[x][y + 1] == '▓') {
                } else if (map[x][y + 1] == '○') {
                    map[x][y + 1] = '＃';
                    map[x][y] = '　';
                    k[0] = -1;
                    System.out.println("你被怪物杀死了。");
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            System.out.print("" + map[i][j]);
                        }
                        System.out.print("\n");
                    }
                    y++;
                    System.exit(0);
                } else {
                    map[x][y + 1] = '＃';
                    map[x][y] = '　';
                    y++;
                }
                break;
            default:
                break;
        }

        pos[0] = x;
        pos[1] = y;
        return pos;

    }
}
