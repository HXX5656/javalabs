import javafx.scene.layout.GridPane;

import java.util.Timer;
import java.util.TimerTask;

public class Monster extends Entity implements Movable,HasEvent {
    public  int x;
    public  int y;
    private int isAlive;
    private int isMoving;
    public  Monster(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public  void killed() {
        this.isAlive=1;
    }
    public int getIsAlive() {
        return isAlive;
    }
    public boolean equals(int m,int n) {
        boolean b=false;
        if(m==this.x&&n==this.y) {
            b=true;
        }
        return b;
    }
    @Override
    public void move(Object e) {
        isMoving=0;
        int[][] map=MazeMap.map;
        int a=(int)(Math.random()*4);
        //0-w,1-a,2-s,3-d
        if(a==0&&map[x-1][y]==0) {
            map[x][y]=0;
            map[--x][y]=3;
            isMoving=1;
        }
        if(a==1&&map[x][y-1]==0) {
            map[x][y]=0;
            map[x][--y]=3;
            isMoving=1;
        }
        if(a==2&&map[x+1][y]==0) {
            map[x][y]=0;
            map[++x][y]=3;
            isMoving=1;
        }
        if(a==3&&map[x][y+1]==0) {
            map[x][y]=0;
            map[x][++y]=3;
            isMoving=1;
        }

        };


    public int getIsMoving() {
        return isMoving;
    }
    @Override

    public void event(Object player) {
                                        
         if(player instanceof Player) {
             if(isAlive==0) {
                 ((Player)player).killed();
                 MazeMap.map[this.x][this.y]=3;
                 MazeMap.map[((Player)player).x][((Player)player).y]=0;
                 ((Player)player).x=this.x;
                 ((Player)player).y=this.y;
             }
         }
    }
}
