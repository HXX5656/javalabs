
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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Task4 extends Application{
    private static String answer="";
    private static String key="";
    private static String text="";
    private static String textdirectory="";

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane=new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.add(new Label("key:"),0,0);
        TextField t1=new TextField();
        pane.add(t1,1,0);
        pane.add(new Label("textdirectory:"),0,1);
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
                key=key.toUpperCase();
                textdirectory=t2.getText().trim();
                try {
                    travels(textdirectory);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    private static void decrypt(String path) throws FileNotFoundException {
        int l=0;
        int to=0;
        File file=new File(path);
        Scanner output=new Scanner(file);
        Scanner output1=new Scanner(file);
        java.io.PrintWriter input=new java.io.PrintWriter(file.getName()+"_plain.txt");
        PrintWriter s1=new PrintWriter(input);
        for(;output1.hasNext();) {
            String m=output1.next();
            for(int i=0;i<m.length();i++) {
                if(Character.isUpperCase(m.charAt(i))||Character.isLowerCase(m.charAt(i))) {
                    l++;
                }
            }
        }
        output1.close();
        int p = key.length();
        int n = key.length()+1;
        while (n<=l) {
            char m = key.charAt((n-1)% p);
            n++;
            key += m;
        }
        for(;output.hasNext();) {
            text=output.nextLine();
            int a = 0;int b = 0;int c = 0;
            String letterU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String letterL = "abcdefghijklmnopqrstuvwxyz";
            String decryptedMessage = "";

            for(int i = 0;i < text.length();i++) {

                if(Character.isUpperCase(text.charAt(i))) {
                    for(int j = 0;j < 26;j++) {

                        if(letterU.charAt(j) == text.charAt(i))
                            a = j;
                        if(letterU.charAt(j) == key.charAt(to))
                            b = j;

                    }
                    if(a >= b)
                        c = a - b;
                    if(a < b)
                        c = 26 - (b - a);
                    decryptedMessage += letterU.charAt(c);
                    to++;
                }
                else if(Character.isLowerCase(text.charAt(i))) {
                    for(int j = 0;j < 26;j++) {

                        if(letterL.charAt(j) == text.charAt(i))
                            a = j;
                        if(letterU.charAt(j) == key.charAt(to))
                            b = j;

                    }
                    if(a >= b)
                        c = a - b;
                    if(a < b)
                        c = 26 - (b - a);
                    decryptedMessage += letterL.charAt(c);
                    to++;
                }
                else {
                    decryptedMessage+=text.charAt(i);
                }
            }
            answer=decryptedMessage;
            s1.println(answer);
        }
        output.close();
        s1.close();


    }
    private static void travels(String path) throws FileNotFoundException {
        File file=new File(path);
        if(file.exists()) {
            File[] files=file.listFiles();
            if(files.length==0) {
                System.out.println("文件夹是空的！");
                return;
            }
            else {
                for(int i=0;i<files.length;i++) {
                    if(files[i].isDirectory()) {
                      travels(files[i].getAbsolutePath());
                    }
                    else {
                        decrypt(files[i].getAbsolutePath());
                    }
                }
            }
        }
        else {
            System.out.println("文件不存在。");
        }

    }
    private static boolean isEnglish(String s) {
        boolean b=true;
        for(int i=0;i<s.length();i++) {
            if(!(Character.isLowerCase(text.charAt(i))||Character.isUpperCase(text.charAt(i)))) {
                b=false;
            }
        }
        return b;
    }

}
