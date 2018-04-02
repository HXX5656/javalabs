package sample.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.client.Main;
import sample.server.Server;


import java.io.*;

import static sample.core.Icon.*;

/**
 *  @author WangShang
 *  @Date 2EMPTYWALL7/WALLEMPTY/TREASUREWALL
 *  @Time WALL6:WALL9
 */
public class Map implements Serializable{
    private  int  m;
    private  int  n;
    private int[][] map;
    private Monster[] monsters;
    private Treasure[] treasures;
    public static int[] section={1};
    private Icon[][] mapicon;
    private  int moncounts;
    private  int trecounts;
    private Player player;
    private boolean sl=false;
    public Map(int m,int n,Player player) {
        this.m=m;
        this.n=n;
        this.player=player;
        map=new int[2*m+1][2*n+1];
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                map[i][j]=1;
            }
        }
    }
    public void setSl(boolean value) {sl=value;}
    public static void setSection(int value) {
        section[0]=value;

    }
    public Monster[] getMonsters() {return monsters;}
    public Treasure[] getTreasures() {return treasures;}
    public static void setSection(int[] value) {
        section=value;
    }
    public  Object[][] deepClone() throws IOException, ClassNotFoundException {
        //将对象写到流里
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        for(int i=0;i<monsters.length;i++) {
            oo.writeObject(monsters[i]);
        }
        for (int j=0;j<treasures.length;j++) {
            oo.writeObject(treasures[j]);
        }
        //从流里读出来
        ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi=new ObjectInputStream(bi);
        Object[][] objects=new Object[2][];
        objects[0]=new Object[monsters.length];
        objects[1]=new Object[treasures.length];
        for(int i=0;i<objects.length;i++) {
            for(int j=0;j<objects[i].length;j++) {
                objects[i][j]=oi.readObject();
            }
        }
        return objects;
    }
    public void load(Object[][] objects) {
        if(sl==true) {
        for (int i=0;i<objects.length;i++) {
            for(int j=0;j<objects[i].length;j++) {
                if(i==0) {
                    monsters[j]=(Monster)(objects[i][j]);
                }
                else {
                    treasures[j]=(Treasure)(objects[i][j]);
                }
            }
        }
        monsters[0].move(500,monsters);
        sl=false;
        }
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
    public  int getMoncounts() {
        return moncounts;
    }
    public  int getTrecounts() {
        return trecounts;
    }
    public Icon[][] getMap() {
        randomMap(1,1);
        map[map.length-2][map[0].length-2]=4;
        if(section[0]==1) {
            monsters=new Monster[0];
            treasures=new Treasure[0];
        }
        else if(section[0]==2) {
            monsters=new Monster[0];
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
                    if(section[0]==4) {
                        monsters[count].setHp(20);
                        monsters[count].setAtk(4);
                        monsters[count].setDef(4);
                    }
                    count++;
                }
            }
        }
        this.mapicon=new Icon[map.length][map[0].length];
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
        if(monsters.length>0) {
            monsters[0].move(500,monsters);
        }
        return mapicon;
    }
    public void handleMonsterEvent(int[] pos) {
        for (int i=0;i<monsters.length;i++) {
            if(pos[0]==monsters[i].getX()&&pos[1]==monsters[i].getY()) {
                monsters[i].handleEvent(player);
            }
        }
    }
    public void handleTreasureEvent(int[] pos) {
        for(int i=0;i<treasures.length;i++) {
            if(pos[0]==treasures[i].x&&pos[1]==treasures[i].y) {
                treasures[i].handleEvent(player);
                trecounts+=20;
                player.setTrecounts(trecounts);
            }
        }
    }
    public  void killMonster(int[] pos,Player player) {
        boolean b=false;
        for (int i = 0; i <monsters.length ; i++) {
            if(monsters[i].getHp()>0) {
            int x=monsters[i].getX();
            int y=monsters[i].getY();
            if(pos[0]==x) {
                if(pos[1]+1==y||pos[1]-1==y) {
                    b=true;
                    if(player.getPlayeratk()>monsters[i].getDef()) {
                        monsters[i].setHp(monsters[i].getHp()-(player.getPlayeratk()-monsters[i].getDef()));
                    }
                    else {
                        monsters[i].setHp(monsters[i].getHp()-1);
                    }
                }
            }
            if(pos[1]==y) {
                if(pos[0]+1==x||pos[0]-1==x) {
                    b=true;
                    if(player.getPlayeratk()>monsters[i].getDef()) {
                        monsters[i].setHp(monsters[i].getHp()-(player.getPlayeratk()-monsters[i].getDef()));
                    }
                    else {
                        monsters[i].setHp(monsters[i].getHp()-1);
                    }
                }
            }
            if(b==true) {
                 final String URL1=new File("assets\\music\\wav\\304.wav").toURI().toString();
                Media media1=new Media(URL1);
                MediaPlayer mediaPlayer1=new MediaPlayer(media1);
                mediaPlayer1.play();
                if(monsters[i].getHp()<=0) {
                    moncounts+=20;
                    player.setMoncounts(moncounts);
                    final String URL2=new File("assets\\music\\wav\\7203.wav").toURI().toString();
                    Media media2=new Media(URL2);
                    MediaPlayer mediaPlayer2=new MediaPlayer(media2);
                    mediaPlayer2.play();
                    mapicon[x][y]=TREASURE;
                    Treasure mon=new Treasure(x,y);
                    surpriseTreasure(mon);
                }
            }
        }
        }
    }
    public void surpriseTreasure(Treasure tt) {
        Treasure[] temp=new Treasure[treasures.length+1];
        System.arraycopy(treasures,0,temp,0,treasures.length);
        temp[temp.length-1]=tt;
        treasures=temp;
    }
    class Monster implements HasEvent,Serializable{
        private int x;
        private int y;
        private int hp=10;
        private int atk=2;
        private int def=2;
        public Monster(int m,int n) {
            x=m;
            y=n;
        }
        //这个方法代表人物走到怪物所在的格子里触发的事件
        @Override
        public  void handleEvent(Object o) {
            if(o instanceof Player) {
             Server.getPlayer().setPlayerhp(Server.getPlayer().getPlayerhp()-4*atk);
             if(Server.getPlayer().getPlayerhp()<=0) {
                 final String URL2=new File("assets\\music\\wav\\8186.wav").toURI().toString();
                 Media media2=new Media(URL2);
                 MediaPlayer mediaPlayer2=new MediaPlayer(media2);
                 mediaPlayer2.play();
             }
            }
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
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void move(double mills,Monster[] monsters) {
            EventHandler<ActionEvent> eventHandler=event ->{
                for (int i = 0; i <monsters.length ; i++) {
                    monsters[i].move();
                }

            };
        Timeline animation=new Timeline(new KeyFrame(Duration.millis(mills),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        }
        public void move() {
            if(hp>0) {
                int b=(int) (Math.random()*4);
                int[] pos= Server.getPos();
                boolean attack=false;
            switch (b) {
                //w
                case 0:
                    if (mapicon[x-1][y]==EMPTY) {
                        mapicon[x][y]=EMPTY;
                        mapicon[--x][y]=MONSTER;
                    }
                    else if(x-1==pos[0]&&y==pos[1]) {
                       attack=true;
                    }
                    break;
                    //a
                case 1:
                    if(mapicon[x][y-1]==EMPTY) {
                        mapicon[x][y]=EMPTY;
                        mapicon[x][--y]=MONSTER;
                    }
                    else if(x==pos[0]&&y-1==pos[1]) {
                        attack=true;
                    }
                    break;
                    //s
                case 2:
                    if (mapicon[x+1][y]==EMPTY) {
                        mapicon[x][y]=EMPTY;
                        mapicon[++x][y]=MONSTER;
                    }
                    else if(x+1==pos[0]&&y==pos[1]) {
                        attack=true;
                    }
                    break;
                case 3:
                    if(mapicon[x][y+1]==EMPTY) {
                        mapicon[x][y]=EMPTY;
                        mapicon[x][++y]=MONSTER;
                    }
                    else if(x==pos[0]&&y+1==pos[1]) {
                        attack=true;
                    }
                    break;
            }
            if(attack==true) {
                int playerhp=Server.getPlayer().getPlayerhp();
                int playerdef=Server.getPlayer().getPlayerdef();
                if(atk>=playerdef) {
                    Server.getPlayer().setPlayerhp(playerhp-(atk-playerdef));
                }
                else {
                    Server.getPlayer().setPlayerhp(playerhp-1);
                }
                final String URL1=new File("assets\\music\\wav\\8333.wav").toURI().toString();
                Media media1=new Media(URL1);
                MediaPlayer mediaPlayer1=new MediaPlayer(media1);
                mediaPlayer1.play();
                if(Server.getPlayer().getPlayerhp()<=0) {
                    mapicon[pos[0]][pos[1]]=EMPTY;
                    Main.updateGameView();
                    final String URL2=new File("assets\\music\\wav\\8186.wav").toURI().toString();
                    Media media2=new Media(URL2);
                    MediaPlayer mediaPlayer2=new MediaPlayer(media2);
                    mediaPlayer2.play();
                }
            }
                Main.updateGameView();

        }
        }
    }
    class Treasure implements HasEvent,Serializable{
        private int x;
        private int y;
        private int istoken;
        public Treasure(int m,int n) {
            x=m;
            y=n;
            istoken=0;
        }
        @Override
        public void handleEvent(Object player) {
            if(player instanceof Player&&istoken==0) {
            setIstoken();
            mapicon[x][y]=EMPTY;
            Main.updateGameView();
            final String URL1=new File("assets\\music\\wav\\6125.wav").toURI().toString();
            Media media1=new Media(URL1);
            MediaPlayer mediaPlayer1=new MediaPlayer(media1);
            mediaPlayer1.play();
            int luck=(int)(Math.random()*3);
            //加hp，加atk，加def；
            switch (luck) {
                case 0:
                    ((Player) player).setPlayerhp(((Player) player).getPlayerhp()+2);
                    break;
                case 1:
                    ((Player) player).setPlayeratk(((Player) player).getPlayeratk()+1);
                    break;
                case 2:
                    ((Player) player).setPlayerdef(((Player) player).getPlayerdef()+1);
                    break;
            }
            }
        }
        public void setIstoken() {
            istoken=1;
        }
        public int getIstoken() {
            return istoken;
        }
    }

}