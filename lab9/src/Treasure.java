public class Treasure {
    public static int num;
    public Treasure(char[][] map,int i) {
        num=i;
        for (int count=0;count<i;) {
            int x=(int)(Math.random()*map.length);
            int y=(int)(Math.random()*map[0].length);
            if (map[x][y]=='　') {
                map[x][y]='％';
                count++;
            }
        }
    }
    public static int getNum(char[][] map) {
        int num=0;
        for (int i=0;i<map.length;i++) {
            for (int j = 0; j <map[0].length ; j++) {
               if (map[i][j]=='％') {
                   num++;
               }
            }

        }
        return num;
    }
    public static void pickT () {
        num--;
    }

}

