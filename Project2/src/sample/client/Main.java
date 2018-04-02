package sample.client;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.core.Direction;
import sample.core.Icon;
import sample.core.StackOfAction;
import sample.server.Server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private static final double TILE_SIZE = 20;
    private static Server server;
    private static ImageView[][] mapTiles;
    private GridPane map;
    private Stage stage;
    private  static Map<sample.core.Icon, Image> imageMap = new HashMap<>();
    private static int back=0;
    private static VBox root=new VBox();
    private static int[] section=new int[1];
    private static VBox flood;
    private boolean modestory=false;
    private Media media;
    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }
    public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
        //将对象写到流里
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        oo.writeObject(object);
        //从流里读出来
        ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi=new ObjectInputStream(bi);
        return(oi.readObject());
    }
    public Stage getStage() {
        return stage;
    }
    public static void setImageMap(String path) {
        initResources(path);
        updateGameView();
    }
    public MediaPlayer getMediaPlayer() {return mediaPlayer;}
    public  void setSection(int value) {
        if(modestory==true) {
            showstory(value);
        }
            section[0] = value;
            sample.core.Map.setSection(section);
            root.getChildren().clear();
            server.createGame(this);
            // prepare resources
            initGameFrame();
            String the = "";
            if (value == 1) {
                the = "water";
            } else if (value == 2) {
                the = "star";
            } else if (value == 3) {
                the = "snow";
            } else {
                the = "fire";
            }
            initResources(the);
            media = new Media(new File("assets\\music\\" + the + ".mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);
            mediaPlayer.play();
            addKeyControls();

            // show game view
            createAndShowGameView();
    }
    
    public static int getSection() {return section[0];}

    @Override
    public void start(Stage primaryStage) throws Exception {
        server = new Server();
            primaryStage.setTitle("maze game");
            ImageView start=new ImageView("file:assets\\img\\start.png");
            this.stage = primaryStage;
            Button s1 = new Button("第一关");
            Button s2 = new Button("第二关");
            Button s3 = new Button("第三关");
            Button s4 = new Button("第四关");
            Button story = new Button("故事模式");
            start.setFitWidth(850);
            start.setFitHeight(650);
            HBox hh=new HBox();
            root.getChildren().add(start);
            hh.setPadding(new Insets(0,10,10,50));
            s1.setPadding(new Insets(10,100,10,10));
            s2.setPadding(new Insets(10,100,10,10));
            s3.setPadding(new Insets(10,100,10,10));
            s4.setPadding(new Insets(10,100,10,10));
            story.setPadding(new Insets(10,100,10,10));
            hh.getChildren().addAll(s1, s2, s3, s4, story);
            root.getChildren().add(hh);
            this.stage.setScene(new Scene(root,820,690));
            stage.show();
            s1.setOnAction(event -> {
                showstory(1);
                setSection(1);
            });
            s2.setOnAction(event -> {
                showstory(2);
                setSection(2);
            });
            s3.setOnAction(event -> {
                showstory(3);
                setSection(3);
            });
            s4.setOnAction(event -> {
                showstory(4);
                setSection(4);
            });
            story.setOnAction(event -> {
                showstory(1);
                setSection(1);
                modestory = true;
            });
        }
        // show game view


    public boolean getModestory() {return modestory;}
    public void showstory(int value) {
        Stage story=new Stage();
        story.setTitle("story");
        HBox picture=new HBox();
        String path="file:assets\\img\\story\\"+value+".jpg";
        ImageView img=new ImageView(path);
        img.setFitHeight(700);
        img.setFitWidth(650);
        picture.getChildren().add(img);
        story.setScene(new Scene(picture));
        story.show();
        picture.setOnMousePressed(evevt->{story.close();});
    }
    void createAndShowGameView() {
        Icon[][] icons = server.getGameView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        mapTiles = new ImageView[numRows][numCols];
        map.getChildren().removeAll(map.getChildren());
        for (int r = 0; r < numRows; r++) {
            // maze contents
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c] = newImageView(icons[r][c]);
                map.add(mapTiles[r][c], c + 1, r + 1);
            }
        }
        this.stage.show();
        map.requestFocus();
    }

    public static void updateGameView() {
        Icon[][] icons = server.getGameView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c].setImage(iconToImage(icons[r][c]));
            }
        }
        flood.getChildren().clear();
        String ll="血条：";
        for (int i = 0; i <Server.getPlayer().getPlayerhp() ; i++) {
            ll+="█";
        }
        String atk="攻击力："+Server.getPlayer().getPlayeratk();
        String def="防御力："+Server.getPlayer().getPlayerdef();
        Text floods=new Text(ll+"  "+atk+"  "+def);
        flood.getChildren().addAll(floods);
    }

    private ImageView newImageView(Icon icon) {
        ImageView imageView = new ImageView(iconToImage(icon));
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        return imageView;
    }

    private static Image iconToImage(Icon icon) {
        Image image = imageMap.get(icon);
        if (image == null)
            throw new IllegalArgumentException(icon.toString());
        return image;
    }

    private void addKeyControls() {

            map.setOnKeyPressed(e -> {
                if(Server.getPlayer().getPlayerhp()>0) {
                    Direction d = null;
                    switch (e.getCode()) {
                        case W:
                            d = Direction.NORTH;
                            break;
                        case A:
                            d = Direction.WEST;
                            break;
                        case S:
                            d = Direction.SOUTH;
                            break;
                        case D:
                            d = Direction.EAST;
                            break;
                        case B:
                            back = 1;
                            StackOfAction s = server.getS();
                            Direction m = s.pop();
                            switch (m) {
                                case NORTH:
                                    d = Direction.SOUTH;
                                    break;
                                case SOUTH:
                                    d = Direction.NORTH;
                                    break;
                                case WEST:
                                    d = Direction.EAST;
                                    break;
                                case EAST:
                                    d = Direction.WEST;
                                    break;
                            }
                            break;
                        case K:
                            server.killMonster();
                            break;
                        case ESCAPE:
                            System.exit(0);
                    }
                    if (d != null)
                        try {
                            server.movePlayer(d);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    // repaint gui
                    updateGameView();
                }

                });

    }
    public static int getBack() {return back;}
    public static void setBack(int value) {
        back=value;
    }
    private void initGameFrame() {
        root.getChildren().clear();
        MenuBar menuBar = new MyMenuBar(server, this);
        flood=new VBox();
        String ll="血条：";
        for (int i = 0; i <Server.getPlayer().getPlayerhp() ; i++) {
            ll+="█";
        }
        String atk="攻击力："+Server.getPlayer().getPlayeratk();
        String def="防御力："+Server.getPlayer().getPlayerdef();
        Text floods=new Text(ll+"  "+atk+"  "+def);
        flood.getChildren().addAll(floods);
        flood.setPadding(new Insets(11.5,12.5,11.5,14.5));
        map = new GridPane();
        root.getChildren().addAll(menuBar,flood, map);
    }

    private static void initResources(String path) {
        path="file:assets\\img\\"+path+"\\";
        Image WALL_IMAGE = new Image( path+"wall.png");
        Image SPACE_IMAGE = new Image(path+  "space.png");
        Image HERO_IMAGE = new Image( path+ "hero.png");
        Image END_IMAGE = new Image(  path+"end.png");
        Image FOOTPRINT_IMAGE = new Image(  path+"footprint.png");
        Image MONSTER_IMAGE = new Image( path+"monster.png");
        Image TREASURE_IMAGE = new Image(  path+"treasure.png");

        // add to image map
        imageMap.put(Icon.EMPTY, SPACE_IMAGE);
        imageMap.put(Icon.WALL, WALL_IMAGE);
        imageMap.put(Icon.HERO, HERO_IMAGE);
        imageMap.put(Icon.FOOTPRINT, FOOTPRINT_IMAGE);
        imageMap.put(Icon.MONSTER, MONSTER_IMAGE);
        imageMap.put(Icon.END, END_IMAGE);
        imageMap.put(Icon.TREASURE, TREASURE_IMAGE);
    }
}
