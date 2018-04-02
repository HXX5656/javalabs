import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


//该程序实现了一层迷宫的窗口化，用wasd和上下左右均能控制人物行走,esc退出
public class Lab13 extends Application{
    private static int x=1;
    private static int y=1;
    private static int[][] map={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,0,0,0,0,1,0,1,2,0,0,1,0,0,0,0,0,1,2,1},
            {1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1},
            {1,1,1,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,1},
            {1,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1},
            {1,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,1,2,0,1},
            {1,0,1,1,1,0,1,1,0,0,0,0,1,1,1,1,0,1,0,0,1},
            {1,0,1,1,0,0,1,1,1,1,0,1,1,0,0,0,0,1,0,0,1},
            {1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
            {1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
            {1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,2,1,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1},
            {1,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,0,1,2,0,0,1,1,1,1,1,1,0,1},
            {1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public void start(Stage primary) {
        GridPane root=new GridPane();
        Scene scene=new Scene(root,940,940);
        show(root);
        primary.setScene(scene);
        root.requestFocus();
        primary.show();
        root.setOnKeyPressed(event ->
        {
            move(event.getCode(),root);
        });

        }
        public  void move(KeyCode e,GridPane root) {
            if(x==map.length-2&&y==map[0].length-1) {
                Stage ss=new Stage();
                ss.setTitle("Win");
                ss.setScene(new Scene(new TextArea("You  win!")));
                ss.show();
                System.exit(0);
            }
            else {
                switch (e) {
                    case UP:
                        if (map[x - 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            x--;
                            map[x][y] = 3;
                        }
                        break;
                    case DOWN:
                        if (map[x + 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            x++;
                            map[x][y] = 3;
                        }
                        break;
                    case LEFT:
                        if (map[x][y - 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            y--;
                            map[x][y] = 3;
                        }
                        break;
                    case RIGHT:
                        if (map[x][y + 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            y++;
                            map[x][y] = 3;
                        }
                        break;
                    case W:
                        if (map[x - 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            x--;
                            map[x][y] = 3;
                        }
                        break;
                    case S:
                        if (map[x + 1][y] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            x++;
                            map[x][y] = 3;
                        }
                        break;
                    case A:
                        if (map[x][y - 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            y--;
                            map[x][y] = 3;
                        }
                        break;
                    case D:
                        if (map[x][y + 1] == 1) {
                            Stage ss = new Stage();
                            ss.setTitle("Invalid");
                            ss.setScene(new Scene(new TextArea("您的操作无效，请你关闭该窗口继续操作！")));
                            ss.show();
                        } else {
                            map[x][y] = 0;
                            y++;
                            map[x][y] = 3;
                        }
                        break;
                    case ESCAPE:
                        System.exit(0);
                        break;
                    default:
                        break;

                }
                show(root);
                if(x==map.length-2&&y==map[0].length-1) {
                    Stage ss=new Stage();
                    ss.setTitle("Win");
                    ss.setScene(new Scene(new TextArea("Congratulations!You win!")));
                    ss.show();}
            }
        }
        public  void show(GridPane root) {
            for (int i=0;i<map.length;i++) {
                for (int j = 0; j <map[0].length; j++) {
                    Image img=null;
                    if (map[i][j]==3) {
                        img=new Image(getClass().getResourceAsStream("people.png"));
                    }
                    else if (map[i][j]==2) {
                        img=new Image(getClass().getResourceAsStream("treasure.png"));
                    }
                    else if(map[i][j]==1) {
                        img=new Image(getClass().getResourceAsStream("wall.png"));
                    }
                    else {
                        img=new Image(getClass().getResourceAsStream("space.png"));
                    }
                    ImageView imv=new ImageView();
                    imv.setImage(img);
                    imv.fitHeightProperty().setValue(32);
                    imv.fitWidthProperty().setValue(45);
                    root.add(imv,j,i);
                }
            }

        }
    }
