public class Treasure extends Entity implements HasEvent {
    public  int x;
    public  int y;
    private  int isToken;
    public Treasure(int x,int y ) {
        this.x=x;
        this.y=y;
    }
    public void setIsToken(int value) {
        if(value==0||value==1) {
            isToken=value;
        }
    }
    public int getIsToken() {
        return isToken;
    }
    //这个方法用来判断是否是同一个宝物
    public boolean equals(int m,int n) {
        boolean b=false;
        if(m==this.x&&n==this.y) {
            b=true;
        }
        return b;
    }
    @Override
    public void event(Object player) {
                                         
        if(player instanceof Player) {
            if(isToken==0) {
                isToken=1;
                MazeMap.map[this.x][this.y]=4;
                MazeMap.map[((Player)player).x][((Player)player).y]=0;
                ((Player)player).x=this.x;
                ((Player)player).y=this.y;
            }
        }
    }
}
