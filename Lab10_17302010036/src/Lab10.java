import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class Lab10 extends Application{
    private static String answer="";
    private static String key="";
    private static String text="";

    public void start(Stage primaryStage) {
        GridPane pane=new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.add(new Label("key:"),0,0);
        TextField t1=new TextField();
        pane.add(t1,1,0);
        pane.add(new Label("text:"),0,1);
        TextField t2=new TextField();
        pane.add(t2,1,1);
        Button button=new Button("Act!");
        pane.add(button,1,2);
        GridPane.setHalignment(button, HPos.RIGHT);
        Scene scene=new Scene(pane,400,400);
        primaryStage.setTitle("www");
        primaryStage.setScene(scene);
        primaryStage.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            //anonymous class,so that variables outside can be reused
            @Override
            public void handle(ActionEvent event) {
                key=t1.getText().trim();
                text=t2.getText().trim();
                decrypt();
                Stage newStage=new Stage();
                newStage.setTitle("Savor");
                newStage.setScene(new Scene(new TextArea(answer)));
                newStage.show();
            }
        });
    }
    private static void decrypt() {
        key=key.toUpperCase();
        text=text.toUpperCase();
        if (!Pattern.matches("^[A-Z]+$", text) || !Pattern.matches("^[A-Z]+$", key)) {
            answer="Wrong:invalid input.Please input some letters in English.";
        }
        else {
        int a = 0;int b = 0;int c = 0;
        String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String decryptedMessage = " ";
        int p = key.length();
        int n = key.length()+1;
        while (n<=text.length()) {
            char m = key.charAt((n-1)% p);
            n++;
            key += m;
        }
        for(int i = 0;i < text.length();i++) {
            for(int j = 0;j < 26;j++) {

                if(letter.charAt(j) == text.charAt(i))
                    a = j;
                if(letter.charAt(j) == key.charAt(i))
                    b = j;}
            if(a >= b)
                c = a - b;
            if(a < b)
                c = 26 - (b - a);
            decryptedMessage += letter.charAt(c);
        }
        answer=decryptedMessage;
        }

    }

}
