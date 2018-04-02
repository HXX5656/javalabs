package sample.server;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.client.Main;
import sample.core.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static sample.core.Icon.*;

public class Server implements Serializable{
    private Map m;
    private Icon[][] map;
    private static int[] pos = {1, 1}; // y,x
    private static StackOfAction s=new StackOfAction();
    private static Player player;
    private Main main;
    private Object[][] objects;
    private int cos=0;
    private boolean hasPlayer=false;
    public  Server() {
        player=new Player("默认名");
        Stage input=new Stage();
        GridPane pane=new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.add(new Label("your name:"),0,0);
        TextField t1=new TextField();
        pane.add(t1,1,0);
        Button button=new Button("Act!");
        pane.add(button,1,2);
        GridPane.setHalignment(button, HPos.RIGHT);
        Scene scene=new Scene(pane,400,400);
        input.setScene(scene);
        input.show();
        button.setOnAction(
                event -> {
                    player.changename(t1.getText().toString());
                    input.close();
                    hasPlayer=true;
                });


    }
    public boolean getHasPlayer() {return hasPlayer;}
    public void createGame(Main main) {
        this.main=main;
        m=new Map(15,20,player);
        map = m.getMap();
     }

     public static void showinformation() {
        player.showInformation();

    }
    public Icon[][] getGameView() {
        Icon[][] tmp = new Icon[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        if(player.getPlayerhp()>0) {
        tmp[pos[0]][pos[1]] = Icon.HERO;
        }
        return tmp;
    }
    public StackOfAction getS() {return s;}
    public static int[] getPos() {
        return pos;
    }
    public void killMonster() {
        m.killMonster(pos,player);
    }
    public static Player getPlayer() {return player;}

    public boolean movePlayer(Direction d) throws IOException {
        if (!(pos[0] == map.length - 2 && pos[1] == map[0].length - 2)) {
            switch (d) {
                case NORTH:
                    if (map[pos[0] - 1][pos[1]] == WALL) {
                    }
                    else if(map[pos[0] - 1][pos[1]] == MONSTER){
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.NORTH);
                        pos[0]--;
                        m.handleMonsterEvent(pos);
                    }
                    else if(map[pos[0] - 1][pos[1]] == TREASURE) {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.NORTH);
                        pos[0]--;
                        m.handleTreasureEvent(pos);
                    }
                    else {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.NORTH);
                        pos[0]--;
                    }
                    break;
                case SOUTH:
                    if (map[pos[0] + 1][pos[1]] == WALL) {
                    }
                    else if(map[pos[0] + 1][pos[1]] == MONSTER){
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.SOUTH);
                        pos[0]++;
                        m.handleMonsterEvent(pos);
                    }
                    else if(map[pos[0] + 1][pos[1]] == TREASURE ){
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.SOUTH);
                        pos[0]++;
                        m.handleTreasureEvent(pos);
                    }
                    else {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.SOUTH);
                        pos[0]++;
                    }
                    break;
                case EAST:
                    if (map[pos[0]][pos[1] + 1] == WALL) {
                    }
                    else if(map[pos[0]][pos[1] + 1] == MONSTER){
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.EAST);
                        pos[1]++;
                        m.handleMonsterEvent(pos);
                    }
                    else if(map[pos[0]][pos[1] + 1] == TREASURE) {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.EAST);
                        pos[1]++;
                        m.handleTreasureEvent(pos);
                    }
                    else {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push( Direction.EAST);
                        pos[1]++;
                    }
                    break;
                case WEST:
                    if (map[pos[0]][pos[1] - 1] == WALL) {
                    }
                    else if(map[pos[0]][pos[1] - 1] == MONSTER){
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.WEST);
                        pos[1]--;
                        m.handleMonsterEvent(pos);
                    }
                    else if(map[pos[0]][pos[1] - 1] ==TREASURE) {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.WEST);
                        pos[1]--;
                        m.handleTreasureEvent(pos);
                    }
                    else {
                        map[pos[0]][pos[1]]=EMPTY;
                        s.push(Direction.WEST);
                        pos[1]--;
                    }
                    break;
            }
            if (pos[0] == map.length - 2 && pos[1] == map[0].length - 2) {
                player.setSecconunts(player.getSecconunts()+100);
                main.getMediaPlayer().stop();
                Stage ss = new Stage();
                ss.setTitle("成功闯关");
                VBox end=new VBox();
                Text tt=new Text("恭喜你成功地闯过了这一关！");
                Button button=new Button("下一关");
                if(Main.getSection()!=4) {
                    end.getChildren().addAll(tt,button);
                }
                else {
                    end.getChildren().add(tt);
                    final String URL1=new File("assets\\music\\wav\\4221.wav").toURI().toString();
                    Media media1=new Media(URL1);
                    MediaPlayer mediaPlayer1=new MediaPlayer(media1);
                    mediaPlayer1.play();
                    BufferedWriter players=new BufferedWriter(new FileWriter(new File("players.txt"),true));
                    players.append(player.getPlayer()+" ");
                    players.append((player.getSecconunts()+m.getTrecounts()+m.getMoncounts())+" ");
                    players.close();
                    java.io.File file1=new java.io.File("players.txt");
                    Scanner output=new Scanner(file1);
                    int temp2[]=new int[1024];
                    String[] temp1=new String[1024];
                    for(int i=0;i<=temp1.length;i++) {
                        if (i>=temp1.length) {
                            String[] temp1plus=new String[temp1.length*2];
                            System.arraycopy(temp1,0,temp1plus,0,temp1.length);
                            temp1=temp1plus;
                            int[] temp2plus=new int[temp1.length*2];
                            System.arraycopy(temp2,0,temp2plus,0,temp2.length);
                            temp2=temp2plus;
                        }
                        if (!output.hasNext()) {
                            output.close();
                            break;
                        }
                        else {
                            temp1[i]=output.next();
                            temp2[i]=Integer.parseInt(output.next());
                        }
                    }
                    int[] temp21=new int[temp2.length];
                    System.arraycopy(temp2,0,temp21,0,temp2.length);
                    Arrays.sort(temp21);
                    int score=player.getSecconunts()+m.getTrecounts()+m.getMoncounts();
                    Text order=new Text("你的得分是"+score+",第"+(temp2.length-Arrays.binarySearch(temp21,score)+"名"));
                    end.getChildren().add(order);
                }
                ss.setScene(new Scene(end));
                ss.show();
                button.setOnAction(event -> {
                    main.setSection(main.getSection()+1);
                    pos[0]=1;
                    pos[1]=1;
                    s=new StackOfAction();
                    ss.close();
                });
            }
        }
        if(Main.getBack()==1) {
            s.pop();
            Main.setBack(0);
        }
        s.footpoint(map,pos[0],pos[1]);
        return true;
    }
    public static int getSize() {return s.getSize();}
    public void saveGame() throws ClassNotFoundException {
        try {
            checkDirectory("save");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./save/1.save"));
            oos.writeObject(map);
            oos.writeObject(pos);
            oos.writeObject(Main.deepClone(m));
            objects=m.deepClone();
            oos.writeObject(Main.deepClone(s));
            oos.writeObject(Main.deepClone(player));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkDirectory(String name) {
        File file = new File(name);
        if (!file.exists() || file.isFile())
            while (!file.mkdir()) {
                System.out.println("can not create directory: " + name);
            }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./save/1.save"));
            try {
                map = (Icon[][]) ois.readObject();
                pos = (int[]) ois.readObject();
                m=(Map)(ois.readObject());
                m.setSl(true);
                m.load(objects);
                s=(StackOfAction)(ois.readObject());
                player=(Player)(ois.readObject());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//0空地 1墙壁 2宝物 3怪物 4末尾 5玩家
    public boolean loadExternalMap(File file) {
        try {
            Scanner in = new Scanner(file);
            int height = in.nextInt();
            int width = in.nextInt();
            Icon[][] map = new Icon[height][width];
            int[] pos = new int[]{1, 1};
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int num = in.nextInt();
                    switch (num) {
                        case 1:
                            map[i][j] = WALL;
                            break;
                        case 0:
                            map[i][j] = EMPTY;
                            break;
                        case 2:
                            map[i][j] = TREASURE;
                            break;
                        case 3:
                            map[i][j] = MONSTER;
                            break;
                        case 4:
                            map[i][j] = END;
                            break;
                        case 5:
                            map[i][j] = EMPTY;
                            pos = new int[]{i, j};
                            break;
                    }
                }
            }
            this.map = map;
            this.pos = pos;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

