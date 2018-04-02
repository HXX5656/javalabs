public class MazeMap {
    static int[][] map;
    static int row=10;
    static int column=10;
    static char pe='○';
    static char E ='　';
    static char T ='％';
    static char M ='＃';
    static char F ='．';
    static char W ='▓';
    // 问题在哪里
    public MazeMap() {
        map=new int[21][21];
        row=10;
        column=10;
    }
    public MazeMap(int r,int col) {
        row=r;
        column=col;
        map=new int[2*row+1][2*column+1];
    }
    public static void map0() {
        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {//0可以通过，1反之
                if ((i+1)%2==0&&(j+1)%2==0) {
                    map[i][j]=0;
                }
                else {
                    map[i][j]=1;
                }
            }
        }
    }
    Acc acc=new Acc();
    NoAcc noAcc=new NoAcc();
    public  void search() {
        acc.addPosition(noAcc.getposition());//随机选取一个格子作为当前正在访问的格子
        int a=acc.see();
        for (;noAcc.noaccNum()!=0;) {
            int b=noAcc.getRandomNoAccPosition(a);
            if (b==-1) {
                a=acc.getRandomAccPosition();
            }
            else {

                a++;

                switch (b) {
                    case 0: {
                        if (a%column==0) {
                            map[2*(a/column)-2][2*column-1] = 0;
                        }
                       else {
                            map[((a-a%column)/column)*2][2*(a%column)-1] = 0;
                        }
                        a-=column;
                        break;
                    }
                    case 1: {
                        if (a%column==0) {
                            map[2*(a/column)][2*column-1] = 0;
                        }
                        else {
                            map[((a-a%column)/column+1)*2][2*(a%column)-1] = 0;
                        }

                        a+=column;
                        break;
                    }
                    case 2: {
                        if (a%column==0) {
                            map[2*(a/column)-1][2*column-2] = 0;
                        }
                        else {
                        map[((a-a%column)/column)*2+1][2*(a%column-1)] = 0;
                        }
                        a--;
                        break;
                    }
                    case 3: {
                        if (a%column==0) {
                            map[2*(a/column)-1][2*column] = 0;
                        }
                        else {
                            map[((a - a % column) / column) * 2 + 1][2 * (a % column)] = 0;
                        }
                        a++;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                a--;
                acc.addPosition(a);
            }

        }

    }
    public static char[][] re() {
        char[][] map0=new char[map.length][map[0].length];
        for (int i=0;i<map.length;i++) {
            map[i][0]=1;
            map[i][2*column]=1;
        }
        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                if (map[i][j]==0) {
                    map0[i][j]=E;
                }
                else {
                    map0[i][j]=W;
                }
            }
        }
        map0[1][1]=pe;
        return map0;
    }

    static  class Acc {
        private static int[] accPosition;
        private static  int size;
        public Acc() {
            accPosition=new int[100];
            size=0;
        }


        public static void addPosition(int position) {
            if (size>=accPosition.length)  {
                int temp[]=new int[accPosition.length+1];
                System.arraycopy(accPosition,0,temp,0,accPosition.length);
                accPosition=temp;
            }
            accPosition[size]=position;
            size++;
        }
        public static int getRandomAccPosition() {
            int ran=(int)(Math.random()*size);
            return accPosition[ran];
        }
        public static int see() {
            return accPosition[size-1];
        }


    }
    static class NoAcc {
        private  static int[] noaccPosition;
        public NoAcc() {
            noaccPosition=new int[row*column];
            for (int i = 0; i <row*column ; i++) {
                //赋值为1说明这格子未被访问，0反之。
                noaccPosition[i]=1;
            }
        }
        public static int getRandomNoAccPosition (int pos) {
            int count=0;
            int[] roundFour={-column,column,-1,1};
            int re=0;
            for (int i=0;i<4;i++) {
                int round=pos+roundFour[i];
                if (!((round>=0&&round<row*column)&&noaccPosition[round]==1)) {
                    count++;
                }
                if (count==4) {
                        re=-1;
                        break;
                }
            }
            if (count!=4) {
                for (int i=0;i>=0;) {
                int j=(int)(Math.random()*4);
                int round=pos+roundFour[j];
                if ((round>=0&&round<row*column)&&noaccPosition[round]==1) {
                    noaccPosition[round]=0;
                i=-1;
                re=j;
                }
            }
            }
            return re;
        }
        public static int getposition() {
            int i=(int)(Math.random()*(row*column));
            noaccPosition[i]=0;
            return i;
        }
        public static int noaccNum( ) {
            int num=0;
            for (int i=0;i<row*column;i++) {
                if (noaccPosition[i]==1) {
                    num++;
                }
            }
            return num;
        }


    }

}
