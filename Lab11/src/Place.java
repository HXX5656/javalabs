public abstract class Place implements HasIcon {
    protected int row,col;
    public abstract boolean isWalkable();
    public boolean equals(Place place) {
        if(this.col==place.col&&this.row==place.col) {
            return true;
        }
        else {
            return false;
        }
    }
    public char getIcon;
    public Place re(){
        return this;
    }


}
