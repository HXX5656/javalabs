import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Player extends Entity implements Movable,HasEvent {
    public static int x;
    public static int y;
    private static int isAlive;
    public static int[][] map=MazeMap.map;
    private static String player;
    public  Player(String player) {
        this.x=1;
        this.y=1;
        this.player=player;
    }

    public static void killed() {
        isAlive=1;
    }
    @Override
    public void move(Object e) {
        if(isAlive==0) {
        if(e instanceof KeyCode) {
            if(x==map.length-2&&y==map[0].length-1) {
                Stage ss=new Stage();
                ss.setTitle("Win");
                ss.setScene(new Scene(new TextArea("You  win!")));
                ss.show();
            }
            else {
                switch ((KeyCode)e) {
                    case UP:
                        if (map[x - 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x-1][y]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x-1,y) ) {
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x-1][y]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                               
                                if( MazeMap.monsters[i].equals(x-1,y) ) {

                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            x--;
                            map[x][y] = 4;
                        }
                        break;
                    case DOWN:
                        if (map[x + 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x+1][y]==2) {

                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x+1,y) ) {
                                     
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x+1][y]==3) {

                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                               
                                if( MazeMap.monsters[i].equals(x+1,y) ) {
                                     
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            x++;
                            map[x][y] = 4;
                        }
                        break;
                    case LEFT:
                        if (map[x][y - 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x][y-1]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                               if( MazeMap.treasures[i].equals(x,y-1) ) {
                                    
                                   MazeMap.treasures[i].event(this);
                               }
                            }

                        }  else if(map[x][y-1]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                               
                                if( MazeMap.monsters[i].equals(x,y-1) ) {
                                     
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            y--;
                            map[x][y] = 4;
                        }
                        break;
                    case RIGHT:
                        if (map[x][y + 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x][y+1]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x,y+1) ) {
                                     
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x][y+1]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                               
                                if( MazeMap.monsters[i].equals(x,y+1) ) {
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        }else {
                            map[x][y] = 0;
                            y++;
                            map[x][y] = 4;
                        }
                        break;
                    case W:
                        if (map[x - 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x-1][y]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x-1,y) ) {
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x-1][y]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                                if( MazeMap.monsters[i].equals(x-1,y) ) {
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            x--;
                            map[x][y] = 4;
                        }
                        break;
                    case S:
                        if (map[x + 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x+1][y]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x+1,y) ) {
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x+1][y]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                                if( MazeMap.monsters[i].equals(x+1,y) ) {
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            x++;
                            map[x][y] = 4;
                        }
                        break;
                    case A:
                        if (map[x][y - 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x][y-1]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x,y-1) ) {
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x][y-1]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                                if( MazeMap.monsters[i].equals(x,y-1) ) {
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        } else {
                            map[x][y] = 0;
                            y--;
                            map[x][y] = 4;
                        }
                        break;
                    case D:
                        if (map[x][y + 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else if(map[x][y+1]==2) {
                            for (int i = 0; i <MazeMap.treasures.length ; i++) {
                                if( MazeMap.treasures[i].equals(x,y+1) ) {
                                    MazeMap.treasures[i].event(this);
                                }
                            }

                        }  else if(map[x][y+1]==3) {
                            for (int i = 0; i <MazeMap.monsters.length ; i++) {
                                if( MazeMap.monsters[i].equals(x,y+1) ) {
                                    MazeMap.monsters[i].event(this);
                                }
                            }

                        }else {
                            map[x][y] = 0;
                            y++;
                            map[x][y] = 4;
                        }
                        break;
                    case ESCAPE:
                        System.exit(0);
                        break;
                    case K:

                    default:
                        break;

                }
                if(x==map.length-2&&y==map[0].length-1) {
                    Stage ss=new Stage();
                    ss.setTitle("Win");
                    ss.setScene(new Scene(new TextArea("Congratulations!You win!")));
                    ss.show();
                }
                
            }
            }
        }
        else {
            Stage ss=new Stage();
            ss.setTitle("You die");
            ss.setScene(new Scene(new TextArea("I am sorry to tell you that you have died!")));
            ss.show();
        }
    }

    
    public void event(Object monster){
        if(monster instanceof Monster) {
            if(isAlive==0) {
                ((Monster)monster).killed();
                MazeMap.map[this.x][this.y]=4;
            }
        }

    }
}
