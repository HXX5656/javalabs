public class Space extends Place {
    private static int row;
    private static int col;
    public Space(int row,int col){
        this.row=row;
        this.col=col;
    }
    @Override
    public boolean isWalkable() {
        return true;
    }
    @Override
    public char getIcon() {
        return '　';
    }
}
