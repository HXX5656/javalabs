public class Treasure extends Entity {
    private static int row;
    private static int col;
    private static char icon='％';
    public Treasure(int row,int col) {
        this.row=row;
        this.col=col;
    }
    //触发事件
    public void handleEvent() {
       System.out.println("恭喜你，成功拾取了一个宝物！");
       icon='　';
    }
    public char getIcon() {
        return icon;
    }
    public Place re(){
        return place;
    }
}
