package sample.core;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.server.Server;

import java.io.Serializable;


public class Player implements Serializable{
    private  int playerhp=10;
    private  int playeratk=2;
    private  int playerdef=2;
    private  String player;
    private int secconunts;
    private int moncounts;
    private int trecounts;
    public Player(String name) {
        this.player=name;
    }
    public Player() {this.player="";}
    public void changename(String name) {this.player=name;}
    public String getPlayer() {return player;}
    public int getPlayerhp() {return playerhp;}
    public int getPlayeratk() {return playeratk;}
    public int getPlayerdef() {return playerdef;}
    public int getSecconunts() {return secconunts;}
    public void setPlayerhp(int value) {
        playerhp=value;
    }
    public void setPlayeratk(int value) {
        playeratk=value;
    }
    public void setPlayerdef(int value) {
        playerdef=value;
    }
    public void setSecconunts(int value) {
        secconunts=value;
    }
    public void setMoncounts(int value) {
        moncounts=value;
    }
    public void setTrecounts(int value) {
        trecounts=value;
    }
    public void showInformation() {
           Stage information=new Stage();
           information.setTitle("PlayerInformation");
           String b="";
           String b1="玩家名称："+player;
           String b0="血量："+playerhp;
           String b2="攻击力："+playeratk;
           String b3="防御力："+playerdef;
           String b4="总步数："+ Server.getSize();
           String b5="闯关得分:"+(secconunts);
           String b6="怪物击杀数(杀一个得20分）:"+(moncounts/20);
           String b7="宝物拾取数(捡一个得20分):"+(trecounts/20);
           String scores="总得分："+(secconunts+moncounts+trecounts);
           b=b1+"\n"+b0+"\n"+b2+"\n"+b3+"\n"+b4+"\n"+b5+"\n"+b6+"\n"+b7+"\n"+scores+"\n";
           TextArea textArea=new TextArea(b);
           VBox hh=new VBox();
           hh.getChildren().add(textArea);
           information.setScene(new Scene(hh));
           information.show();

    }
 }
