public class Wall extends Place {
    public Wall(int row,int col) {
        super.row=row;
        super.col=col;
    }
    @Override
    public boolean isWalkable() {
        return false;
    }
    @Override
    public char getIcon() {
        return '▓';
    }
}
