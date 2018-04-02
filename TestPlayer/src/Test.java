import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;

public class Test  extends  Application{
private MediaPlayer mediaPlayer=new MediaPlayer(new Media(new File("music\\Galantis - Water.mp3").toURI().toString()));
    public  void  start (Stage ss){
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaPlayer.play();
    }
}
