public class Monster {
    private static int number;
    //这个方法用来初始化，随机放置怪物
    private static  int atk=2;//怪物的攻击力
    private static  int def=1;//怪物的防御力
    private static  int  hp=5;//怪物的血量
    private static int[][] monsters;//二维数组中每一维都存放着信息，前两个是坐标，第三个是存活状态（即血量）
    public Monster(int i,char[][] map) {
        monsters=new int[i][3];
        number=i;
        for (int count = 0; count < i; ) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map[0].length);
            if (map[x][y] == '　') {
                map[x][y] = '＃';
                monsters[count][0]=x;
                monsters[count][1]=y;
                monsters[count][2]=hp;
                count++;
            }
        }

    }
    //这个方法用来得到击杀怪物数
    public static int getNumber(char[][] map) {
        int num=0;
        for (int i = 0; i <map.length ; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                if (map[i][j]=='＃') {
                    num++;
                }
            }
        }
        num=number-num;
        return num;
    }
    //这个方法用来使每一个怪物动起来
    public static void move(char[][] map,int[] pos,Player player){
        int m = (int) (Math.random() * 4);
                        switch (m) {
                            case 0://w
                                if (map[ pos[0]- 1][pos[1]] == '▓') {
                                }
                                else if (map[pos[0] - 1][pos[1]] == '○') {
                                    if(atk>=player.getDef()) {
                                        player.setHp(1+atk-player.getDef());
                                    }
                                    else {
                                        player.setHp(1);
                                    }
                                    if (player.getHp()<=0) {
                                        map[pos[0] - 1][pos[1]] = '＃';
                                        map[pos[0]][pos[1]] = '　';
                                        System.out.println("你被怪物杀死了。");
                                        for (int i = 0; i < map.length; i++) {
                                            for (int j = 0; j < map[0].length; j++) {
                                                System.out.print("" + map[i][j]);
                                            }
                                            System.out.print("\n");
                                        }
                                        pos[0]--;
                                        System.exit(0);
                                    }
                                    else {
                                        System.out.println("你还剩"+player.getHp()+"滴血");
                                        for(int i=0;i<player.getHp();i++) {
                                            System.out.print("█");
                                        }
                                        System.out.print("\n");
                                    }
                                }
                                else {
                                    map[pos[0] - 1][pos[1]] = '＃';
                                    map[pos[0]][pos[1]] = '　';
                                    pos[0]--;
                                }
                                break;
                            case 1://a
                                if (map[pos[0]][pos[1]- 1] == '▓') {
                                }
                                else if (map[pos[0]][pos[1] - 1] == '○') {
                                    if(atk>=player.getDef()) {
                                        player.setHp(1+atk-player.getDef());
                                    }
                                    else {
                                        player.setHp(1);
                                    }
                                    if (player.getHp()<=0) {
                                        map[pos[0]][pos[1]- 1] = '＃';
                                        map[pos[0]][pos[1]] = '　';
                                        System.out.println("你被怪物杀死了。");
                                        for (int i = 0; i < map.length; i++) {
                                            for (int j = 0; j < map[0].length; j++) {
                                                System.out.print("" + map[i][j]);
                                            }
                                            System.out.print("\n");
                                        }
                                        pos[1]--;
                                        System.exit(0);}
                                    else {
                                        System.out.println("你还剩"+player.getHp()+"滴血");
                                        for(int i=0;i<player.getHp();i++) {
                                            System.out.print("█");
                                        }
                                        System.out.print("\n");
                                    }
                                }
                                else {
                                    map[pos[0]][pos[1] - 1] = '＃';
                                    map[pos[0]][pos[1]] = '　';
                                    pos[1]--;
                                }
                                break;
                            case 2://s
                                if (map[pos[0] + 1][pos[1]] == '▓') {
                                }
                                else if (map[pos[0] + 1][pos[1]] == '○') {
                                    if(atk>=player.getDef()) {
                                        player.setHp(1+atk-player.getDef());
                                    }
                                    else {
                                        player.setHp(1);
                                    }
                                    if (player.getHp()<=0) {
                                        map[pos[0] + 1][pos[1]] = '＃';
                                        map[pos[0]][pos[1]] = '　';
                                        System.out.println("你被怪物杀死了。");
                                        for (int i = 0; i < map.length; i++) {
                                            for (int j = 0; j < map[0].length; j++) {
                                                System.out.print("" + map[i][j]);
                                            }
                                            System.out.print("\n");
                                        }
                                        pos[0]++;
                                        System.exit(0);
                    }
                    else {
                        System.out.println("你还剩"+player.getHp()+"滴血");
                        for(int i=0;i<player.getHp();i++) {
                            System.out.print("█");
                        }
                        System.out.print("\n");
                    }


                } else {
                    map[pos[0] + 1][pos[1]] = '＃';
                    map[pos[0]][pos[1]] = '　';
                    pos[0]++;
                }
                break;
            case 3://d
                if (map[pos[0]][pos[1]+ 1] == '▓') {
                } else if (map[pos[0]][pos[1] + 1] == '○') {
                    if(atk>=player.getDef()) {
                        player.setHp(1+atk-player.getDef());
                    }
                    else {
                        player.setHp(1);
                    }
                    if (player.getHp()<=0) {
                    map[pos[0]][pos[1]+ 1] = '＃';
                    map[pos[0]][pos[1]] = '　';
                    System.out.println("你被怪物杀死了。");
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            System.out.print("" + map[i][j]);
                        }
                        System.out.print("\n");
                    }
                    pos[1]++;
                    System.exit(0);
                    }
                    else {
                        System.out.println("你还剩"+player.getHp()+"滴血");
                        for(int i=0;i<player.getHp();i++) {
                            System.out.print("█");
                        }
                        System.out.print("\n");
                    }

                } else {
                    map[pos[0]][pos[1] + 1] = '＃';
                    map[pos[0]][pos[1]] = '　';
                    pos[1]++;
                }
                break;
            default:
                break;
        }


    }
    //这个方法使所有怪物都动起来
    public static void moveAll(char[][] map,Player player) {
        for (int i = 0; i <monsters.length ; i++) {
            if (monsters[i][2]>0) {
                move(map,monsters[i],player);
            }

        }
    }
    public static int getAtk() {
        return atk;
    }
    public static int getDef(int x,int y) {
        int d=0;
        for (int i=0;i<monsters.length;i++) {
            if ((x==monsters[i][0]&&y==monsters[i][1])&&monsters[i][2]>0)  {
                d=def;
            }
        }
        return d;
    }
    public static int getHp(int x,int y) {
        int d=0;
        for (int i=0;i<monsters.length;i++) {
            if ((x==monsters[i][0]&&y==monsters[i][1])&&monsters[i][2]>0)  {
                d=monsters[i][2];
            }
        }
        return d ;
    }
    public static int setAtk(int value) {
        return atk=value;
    }
    public static int setDef(int value) {
        return def=value;
    }
    public static void setHp(int x,int y,int value) {
        for (int i=0;i<monsters.length;i++) {
            if ((x==monsters[i][0]&&y==monsters[i][1])&&monsters[i][2]>0)  {
                monsters[i][2]=monsters[i][2]-value;
            }
        }

    }
}
