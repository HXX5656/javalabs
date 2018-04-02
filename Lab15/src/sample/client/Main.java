package sample.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.core.Direction;
import sample.core.Icon;
import sample.server.Server;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private static final double TILE_SIZE = 20;
    private static Server server;
    private static ImageView[][] mapTiles;
    private GridPane map;
    private Stage stage;
    private  static Map<sample.core.Icon, Image> imageMap = new HashMap<>();

    public static void main(String[] args) {
        server = new Server();
        server.createGame();

        launch(args);
    }

    public Stage getStage() {
        return stage;
    }
    public static Server getServer() {return server;}
    public static void setImageMap(String path) {
        initResources(path);
        updateGameView();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("maze game");
        this.stage = primaryStage;

        // prepare resources
        initGameFrame();
        initResources("grass");
        addKeyControls();

        // show game view
        createAndShowGameView();
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

    static void updateGameView() {
        Icon[][] icons = server.getGameView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c].setImage(iconToImage(icons[r][c]));
            }
        }
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
            Direction d = null;
            int back=0;
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
                case ESCAPE:
                    System.exit(0);
                case B:
                    Direction before=server.getS().pop();
                    back=1;
                    switch (before) {
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
            }
            if (d != null) {
                server.getPlayer().movePlayer(d);
                if (back == 1) {
                    server.getS().pop();
                }
                server.getS().footpoint(server.getMap(),server.getPos()[0],server.getPos()[1]);
            }
            updateGameView();
        });
    }

    private void initGameFrame() {
        VBox root = new VBox();
        MenuBar menuBar = new MyMenuBar(server, this);
        map = new GridPane();
        root.getChildren().addAll(menuBar, map);
        this.stage.setScene(new Scene(root));
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
