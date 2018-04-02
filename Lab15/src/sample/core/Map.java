package sample.core;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.client.Main;

import static sample.core.Icon.*;

/**
 *  @author WangShang
 *  @Date 2EMPTYWALL7/WALLEMPTY/TREASUREWALL
 *  @Time WALL6:WALL9
 */
public class Map {
    private  int  m;
    private  int  n;
    private int[][] map;
    private Monster[] monsters;
    private Treasure[] treasures;
    private static int section=3;
    private static Player player;
    public Map(int m,int n) {
        this.m=m;
        this.n=n;
        map=new int[2*m+1][2*n+1];
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                map[i][j]=1;
            }
        }
        int[] pos={1,1};
        player=new Player(pos);
    }

    public void randomMap(int x,int y) {
        int[][] direction={{-1,0},{0,-1},{1,0},{0,1}};
        map[x][y]=0;
        for(int i=0;i<4;i++) {
            int[] temp=new int[2];
            int b=(int)(Math.random()*4);
            System.arraycopy(direction[i],0,temp,0,2);
            System.arraycopy(direction[b],0,direction[i],0,2);
            System.arraycopy(temp,0,direction[b],0,2);
        }
        for(int i=0;i<4;i++) {
            int dx=direction[i][0];
            int dy=direction[i][1];
            if((x+2*dx>=0&&x+2*dx<map.length-1)&&(y+2*dy>=0&&y+2*dy<map[0].length-1)&&map[x+2*dx][y+2*dy]==1) {
                map[x+dx][y+dy]=0;
                randomMap(x+2*dx,y+2*dy);
            }
        }
    }
    public Player getPlayer() {return player;}
    public void getranMap() {
        randomMap(1,1);
        map[map.length-2][map[0].length-2]=4;
        if(section==1) {
        }
        else if(section==2) {
            int i=6;
            treasures=new Treasure[i];
            for (int count = 0; count < i; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map[x][y] ==0) {
                    map[x][y] =2;
                    treasures[count]=new Treasure(x,y);
                    count++;
                }
            }
        }
        else {
            int i=6;
            treasures=new Treasure[i];
            for (int count = 0; count < i; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map[x][y] ==0) {
                    map[x][y] =2;
                    treasures[count]=new Treasure(x,y);
                    count++;
                }
            }
            monsters=new Monster[i];
            for (int count = 0; count < i; ) {
                int x = (int) (Math.random() * map.length);
                int y = (int) (Math.random() * map[0].length);
                if (map[x][y] ==0) {
                    map[x][y] =3;
                    monsters[count]=new Monster(x,y);
                    count++;
                }
            }
        }
        getMap();

    }
    public Icon[][] getMap() {
        Icon[][] mapicon=new Icon[map.length][map[0].length];
        for (int i=0;i<map.length;i++) {
            for(int j=0;j<map[0].length;j++) {
                if(map[i][j]==1) {
                    mapicon[i][j]=WALL;
                }
                else if(map[i][j]==2){
                    mapicon[i][j]=TREASURE;
                }
                else if(map[i][j]==3) {
                    mapicon[i][j]=MONSTER;
                }
                else if(map[i][j]==4) {
                    mapicon[i][j]=END;
                }
                else if(map[i][j]==5) {
                    mapicon[i][j]=HERO;
                }
                else {
                    mapicon[i][j]=EMPTY;
                }
            }
        }
        return mapicon;
    }
    class Monster implements HasEvent{
        private int x;
        private int y;
        private int hp;
        private int atk;
        private int def;
        public Monster(int m,int n) {
            x=m;
            y=n;
        }
        public int  getHp() {
            return hp;
        }
        public void setHp(int value) {
            hp=value;
        }
        public int getAtk() {
            return atk;
        }
        public void setAtk(int value) {
            atk=value;
        }
        public int getDef() {
            return def;
        }
        public void setDef(int value) {
            def=value;
        }

        @Override
        public void handleEvent(Object player) {

        }
    }
    class Treasure {
        private int x;
        private int y;
        private int istoken;
        public Treasure(int m,int n) {
            x=m;
            y=n;
            istoken=0;
        }
        public void setIstoken() {
            istoken=1;
        }
        public int getIstoken() {
            return istoken;
        }
    }


}