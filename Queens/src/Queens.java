public class Queens {
    private static int count;

    public static  void main(String[] args) {
        int n=8;
        char[][] board = new char[8][8];
        for (int i=0;i<n;i++) {
             for(int j=0;j < n ;j++) {
                 board[i][j]='.';
             }
        }
        solve(board,0);
        System.out.print(""+count);
        System.exit(0);
    }
    private static void solve(char[][] board,int level) {
        if (level==8) {
            count++;
            return;
        }
        for (int i=0;i<board[level].length;i++) {
             if (isLegal(board,level,i)) {
                 board[level][i]='Q';
                 solve(board,level+1);
                 board[level][i]='.';
             }
        }
    }
    private static boolean isLegal(char[][] board,int row,int col) {
              int n=board.length;
              for (int i=0;i<row;i++) {
                  for (int j = 0; j < n; j++) {
                      if (board[i][j]=='Q'&&(j==col||i+j==row+col||i+col==j+row)) {
                          return false;
                      }

                  }
              }
              return true;
    }
}
