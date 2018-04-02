public class Test {
    public static void main(String[] args ) {
        Maze map=new Maze(15,20) ;
        int[][] maps=map.randommap();
        for (int i=0;i<maps.length;i++) {
            for (int j=0;j<maps[0].length;j++) {
                if(maps[i][j]==1) {
                    System.out.print("▓");
                }
                else {
                    System.out.print("　");
                }
            }
            System.out.println();
        }
    }
}
