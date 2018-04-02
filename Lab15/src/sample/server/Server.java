package sample.server;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.core.*;

import java.io.*;
import java.util.Scanner;

import static sample.core.Icon.*;

public class Server {

    private Icon[][] map;
    private int[] pos = {1, 1}; // y,x
    private static StackOfAction s=new StackOfAction();
    private static Player player;

    public void createGame() {
        Map m=new Map(15,20);
        m.randomMap(1,1);
        map=m.getMap();
        int[] pos={1,1};
        player=new Player(pos);
    }
    public StackOfAction getS() {
        return  s;
    }
    public Player getPlayer() {return player;}

    public Icon[][] getGameView() {
        Icon[][] tmp = new Icon[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        tmp[pos[0]][pos[1]] = Icon.HERO;
        return tmp;
    }
    public int[] getPos() {return pos;}
    public Icon[][] getMap() {return map;}


    public void saveGame() {
        try {
            checkDirectory("save");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./save/1.save"));
            oos.writeObject(map);
            oos.writeObject(pos);
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

