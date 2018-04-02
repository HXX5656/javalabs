public class MazeMap {
    private static int  m;
    private static int  n;
    public static int[][] map;
    public static  Treasure[] treasures;
    public static  Monster[]  monsters;
    public MazeMap(int m,int n) {
        this.m=m;
        this.n=n;
        map=new int[2*m+1][2*n+1];
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                map[i][j]=1;
            }
        }
    }
    public MazeMap() {
        this(10,10);
        m=10;
        n=10;
    }
    public static int[][] randommap() {
        getMap(1,1);
        Monster(6);
        Treasure(6);
        return map;
    }
    public static void getMap(int x,int y ) {
        int[][] direction={{-1,0},{0,-1},{1,0},{0,1}};
        map[x][y]=0;
        for(int i=0;i<4;i++) {
            int[] temp=new int[2];
            int b=(int)(Math.random()*4);
            System.arraycopy(direction[i],0,temp,0,2);
            System.arraycopy(direction[b],0,direction[i],0,2);
            System.arraycopy(temp,0,direction[b],0,2);
        }
        for(int i=0;i<4;i++) {
            int dx=direction[i][0];
            int dy=direction[i][1];
            if((x+2*dx>=0&&x+2*dx<map.length-1)&&(y+2*dy>=0&&y+2*dy<map[0].length-1)&&map[x+2*dx][y+2*dy]==1) {
                map[x+dx][y+dy]=0;
                getMap(x+2*dx,y+2*dy);
            }
        }
    }
    public static void Monster(int i) {
       monsters=new Monster[i];
        map[1][1]=4;
        map[map.length-2][map[0].length-2]=5;
        for (int count = 0; count < i; ) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map[0].length);
            if (map[x][y] ==0) {
                map[x][y] =3;
                monsters[count]=new Monster(x,y);
                count++;

            }
        }
    }
    public static void Treasure(int i) {
        treasures=new Treasure[i];
        for (int count = 0; count < i; ) {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map[0].length);
            if (map[x][y] ==0) {
                map[x][y] =2;
                treasures[count]=new Treasure(x,y);
                count++;
            }
        }
    }
}