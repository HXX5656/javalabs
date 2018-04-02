package sample.core;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.client.Main;
import sample.server.Server;

import static sample.core.Icon.WALL;

public class Player implements HasEvent{
    private int[] pos;
    private int hp;
    private int atk;
    private int def;
    private Server server;
    public Player(int[] pos) {
        this.pos=pos;
        server=Main.getServer();

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
    public boolean movePlayer(Direction d) {
        Icon[][] map=server.getMap();
        StackOfAction s= Main.getServer().getS();
        if (!(pos[0] == map.length - 2 && pos[1] == map[0].length - 2)) {
            switch (d) {
                case NORTH:
                    if (map[pos[0] - 1][pos[1]] == WALL) {
                    } else {
                        map[pos[0]][pos[1]]=Icon.EMPTY;
                        s.push(Direction.NORTH);
                        pos[0]--;
                    }
                    break;
                case SOUTH:
                    if (map[pos[0] + 1][pos[1]] == WALL) {

                    } else {
                        map[pos[0]][pos[1]]=Icon.EMPTY;
                        s.push(Direction.SOUTH);
                        pos[0]++;
                    }
                    break;
                case EAST:
                    if (map[pos[0]][pos[1] + 1] == WALL) {
                    } else {
                        map[pos[0]][pos[1]]=Icon.EMPTY;
                        s.push(Direction.EAST);
                        pos[1]++;
                    }
                    break;
                case WEST:
                    if (map[pos[0]][pos[1] - 1] == WALL) {

                    } else {
                        map[pos[0]][pos[1]]=Icon.EMPTY;
                        s.push(Direction.WEST);
                        pos[1]--;
                    }
                    break;
            }
            if (pos[0] == map.length - 2 && pos[1] == map[0].length - 2) {
                Stage ss = new Stage();
                ss.setTitle("Win");
                ss.setScene(new Scene(new TextArea("You Win!")));
                ss.show();
            }
        }
        return true;
    }


    @Override
    public void handleEvent(Object monster) {

    }
}
