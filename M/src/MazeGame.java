import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class MazeGame extends Application{
    public static int[][] map;
    public void start(Stage primary) {
        MazeMap maps=new MazeMap(10,10);
        map=maps.randommap();
        Player player=new Player("hu");
        GridPane root=new GridPane();
        Scene scene=new Scene(root,940,940);
        show(root);
        primary.setScene(scene);
        root.requestFocus();
        primary.show();
        for(int i=0;i<MazeMap.monsters.length;i++) {
            int count=i;
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    if(MazeMap.monsters[count].getIsAlive()==0) {
                        MazeMap.monsters[count].move(this);
                        show(root);
                    }
                }
            };
            Timer timer=new Timer();
            long delay=0;
            long intevalPeriod=1*100;
            timer.scheduleAtFixedRate(task,delay,intevalPeriod);
        }
        root.setOnKeyPressed( e->{
            for(int i=0;i<MazeMap.monsters.length;i++) {
                MazeMap.monsters[i].move(this);
            }
            player.move(e.getCode());
            show(root);
        });

    }

    public  void show(GridPane root) {
        root.getChildren().clear();
        for (int i=0;i<map.length;i++) {
            for (int j = 0; j <map[0].length; j++) {
                Image img=null;
                if (map[i][j]==4) {
                    img=new Image(getClass().getResourceAsStream("people.png"));
                }
                else if (map[i][j]==2) {
                    img=new Image(getClass().getResourceAsStream("treasure.png"));
                }
                else if(map[i][j]==1) {
                    img=new Image(getClass().getResourceAsStream("wall.png"));
                }
                else if(map[i][j]==0){
                    img=new Image(getClass().getResourceAsStream("space.png"));
                }
                else if(map[i][j]==3){
                    img=new Image(getClass().getResourceAsStream("monster.png"));
                }
                else {
                    img=new Image(getClass().getResourceAsStream("end.png"));
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
