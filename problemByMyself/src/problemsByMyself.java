public class problemsByMyself {
    //通过该程序输出五位同学的考试成绩
    public static void main(String[] args) {
        char[][] optionsAll={
                {'B','A','C','D','C'},
                {'C','D','A','C','D'},
                {'A','B','A','C','B'},
                {'D','C','B','B','A'},
                {'A','C','B','D','B'}
        };
        int posX=0;
        int [] marks={0,0,0,0,0};
        for ( ;posX<=4;posX++) {
            if (optionsAll[posX][0]=='A') {
                marks[posX]+=20;
            }
            if (optionsAll[posX][1]=='C') {
                marks[posX]+=20;
            }
            if (optionsAll[posX][2]=='B') {
                marks[posX]+=20;
            }
            if (optionsAll[posX][3]=='C') {
                marks[posX]+=20;
            }
            if (optionsAll[posX][4]=='B') {
                marks[posX]+=20;
            }
            System.out.println("第"+(posX+1)+ " 位同学的成绩是 "+marks[posX]);
        }
        System.exit(0);
    }
}
