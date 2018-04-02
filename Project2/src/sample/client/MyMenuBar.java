package sample.client;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.core.Player;
import sample.server.Server;

import java.io.File;

/**
 * @author Duocai Wu
 * @Date 2017/11/13
 * @Time 16:58
 */
class MyMenuBar extends MenuBar {

    MyMenuBar(Server server, Main app) {
        super();
        // add file menu
        Menu menuFile = new Menu("File");
        MenuItem loadItem = new MenuItem("Load");
        loadItem.setOnAction(event -> {
            server.loadGame();
            app.createAndShowGameView();
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(event -> {
            try {
                server.saveGame();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("saved.");
            alert.showAndWait();
        });
        MenuItem loadExternal = new MenuItem("Load External Map");
        loadExternal.setOnAction(event -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            File file = fileChooser.showOpenDialog(app.getStage());
            if (file != null && file.isFile()) {
                boolean ok = server.loadExternalMap(file);
                if (ok)
                    app.createAndShowGameView();
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Not Valid Map File");
                    alert.showAndWait();
                }
            }
        });
        menuFile.getItems().addAll(loadItem, saveItem, loadExternal);

        // add settings menu
        Menu menuSetting = new Menu("Settings");
        Menu themeMenu = new Menu("Theme");
        ToggleGroup themeGroup = new ToggleGroup();
        RadioMenuItem snowTheme = new RadioMenuItem("snow");
        snowTheme.setToggleGroup(themeGroup);
        snowTheme.setOnAction(event -> {
             Main.setImageMap("snow");
        });
        RadioMenuItem grassTheme = new RadioMenuItem("grass");
        grassTheme.setOnAction(event -> {
            Main.setImageMap("grass");
        });
        grassTheme.setToggleGroup(themeGroup);
        RadioMenuItem waterTheme = new RadioMenuItem("water");
        waterTheme.setOnAction(event -> {
            Main.setImageMap("water");
        });
        waterTheme.setToggleGroup(themeGroup);
        RadioMenuItem starTheme = new RadioMenuItem("star");
        starTheme.setOnAction(event -> {
            Main.setImageMap("star");
        });
        starTheme.setToggleGroup(themeGroup);
        RadioMenuItem fireTheme = new RadioMenuItem("fire");
        fireTheme.setOnAction(event -> {
            Main.setImageMap("fire");
        });
        fireTheme.setToggleGroup(themeGroup);
        themeMenu.getItems().addAll(snowTheme, grassTheme,waterTheme,starTheme,fireTheme);
        menuSetting.getItems().add(themeMenu);
        // add help menu
        Menu menuHelp = new Menu("Help");
        MenuItem howToPlay = new MenuItem("How to Play");
        howToPlay.setOnAction(event -> {
            Stage ss = new Stage();
            ss.setTitle("HelpInformation");
            ss.setScene(new Scene(new TextArea("wasd控制人物移动\n"+"怪物会随机移动，如果你移动到怪物所在位置，会受重伤！\n"
                    +"关于游戏人物的信息均可在playerInformation里看到\n"+"精灵大陆由四个部落组成：水、火、星、雪。\n" +
                    "精灵们已经在这片大陆上生存了许久，过着安静祥和的生活。\n" +
                    "可是突然有一天，大陆上出现了许多暗黑精灵，他们用暗黑力量吞噬了这\n" +
                    "片大陆并在大陆上堆叠了四层迷宫以迷惑外人，从而阻止其它大陆的人前来救援。\n"+
                    "四层迷宫里游戏角色不同，宝物使你强大，怪物会主动攻击你，但战胜它，你就能得到宝物！\n"+
                    "你在行走时身后会留有两步足迹，走出所有迷宫你就能得知排行！\n"+
                    "菜单栏上的按钮可以告知信息，切换主题，存读档。\n"+
                    "游戏结束前你都可以在input窗口输入你的名字。")));
            ss.show();
        });
        MenuItem playerInformation=new MenuItem("playerInformation");
        playerInformation.setOnAction(event -> {
            server.showinformation();
        });
        menuHelp.getItems().addAll(howToPlay,playerInformation);
        this.getMenus().addAll(menuFile, menuSetting, menuHelp);
    }
}
