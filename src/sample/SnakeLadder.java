package sample;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeLadder extends Application {

    private int rand;
    private Label randResult;
    private int direction = 1;

    // public int cirPos[][]=new int[8][8];

    public static final int Tile_size = 80;
    private static final int width = 8;
    private static final int height = 8;

    private Circle player;

    private static int playerXPos = 600;
    private static int playerYPos = 600;

    private boolean gameStart = false;
    Text text;
    public Button gameButton;

    private Group tileGroup = new Group();

    Map<Integer,Tile> map=new HashMap();
    //private Map<Integer, Integer> SNAKES;
    //private Map<Integer, Integer> LADDERS;

    Tile[] tiles=new Tile[64];

    private int currPos=1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContent(), 800, 890);
        primaryStage.setTitle("Snake and ladder");
        primaryStage.setScene(scene);
        //  primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void createGrid(){

        for(int j=0;j<=7;j++){

            if(j%2==0){

                for(int i=0;i<=7;i++){

                    tiles[i]=new Tile(560-i*80,560-j*80);
                    map.put((j*8)+(i+1),tiles[i]);

                    tileGroup.getChildren().addAll(tiles[i]);

                }
            }

            if(j%2==1) {

                for (int k = 7; k >= 0; k--) {

                    tiles[k] = new Tile(560 - k * 80, 560-j*80);
                    map.put((j*8)+(8-k), tiles[k]);
                    tileGroup.getChildren().addAll(tiles[k]);

                }
            }

        }


//second row


    }

    private Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(width * Tile_size, (height * Tile_size));
        root.getChildren().add(tileGroup);

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//
//                Tile tile = new Tile();
//                tile.setTranslateX(j * Tile_size);
//                tile.setTranslateY(i * Tile_size);
//                tileGroup.getChildren().addAll(tile);
//
//            }
//
//        }
        createGrid();

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                text = new Text("" + i);
//
//                text.setTranslateX(40);
//                text.setTranslateY(300);
//                tileGroup.getChildren().addAll(text);
//
//            }
//
//        }

        player = new Circle(40);
        player.setId("player");
        player.getStyleClass().add("style.css");
        player.setTranslateX(playerXPos);
        player.setTranslateY(playerYPos);

        Button playButton = new Button("roll dice");
        playButton.setTranslateX(700);
        playButton.setTranslateY(40);
        playButton.setGraphic(new ImageView(new Image("dice.png")));
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                getDiceValue();
                movePlayer2();
               // tileGroup.getChildren().addAll(player);
                System.out.println(rand);
                // translatePlayer(playerXPos,playerYPos,player);
                if(rand==1){
                    playButton.setGraphic(new ImageView(new Image("dice1.png")));

                }else if (rand==2){

                    playButton.setGraphic(new ImageView(new Image("dice2.png")));
                }else if(rand==3){

                    playButton.setGraphic(new ImageView(new Image("dice3.png")));
                }else if(rand==4){

                    playButton.setGraphic(new ImageView(new Image("dice4.png")));
                }else if(rand==5){
                    playButton.setGraphic(new ImageView(new Image("dice5.png")));

                }else if(rand==6){

                    playButton.setGraphic(new ImageView(new Image("dice6.png")));
                }




            }
        });


        // tileGroup.getChildren().add(player);

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(660);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gameButton.setText("Game Started");
                playerXPos = 40;
                playerYPos = 600;

                player.setTranslateX(playerXPos);
                player.setTranslateY(playerYPos);


            }
        });


//
//        Image img=new Image("sample/snake.png");
//        ImageView bgImage=new ImageView();
//        bgImage.setImage(img);
//        bgImage.setFitHeight(160);
//        bgImage.setFitWidth(40);
//        bgImage.setX(120);
//        bgImage.setY(120);


        //ladder creation on game board
        Line ladder1 = new Line(280, 360, 280, 600);
        ladder1.setStrokeWidth(20);
        ladder1.setStroke(Color.RED);

        Line ladder2 = new Line(440, 40, 440, 120);
        ladder2.setStrokeWidth(20);
        ladder2.setStroke(Color.RED);

        //snakes creation on game board

        Line snake1 = new Line(120, 120, 120, 280);
        snake1.setStrokeWidth(20);
        snake1.setStroke(Color.GREEN);


        Line snake2 = new Line(600, 280, 600, 440);
        snake2.setStrokeWidth(20);
        snake2.setStroke(Color.GREEN);


        tileGroup.getChildren().addAll(gameButton,player, ladder1, ladder2, snake1, snake2, playButton);

        return root;

    }

    private void getDiceValue() {

       //rand =(int)(Math.random()*6+1);
        rand = (int) ThreadLocalRandom.current().nextInt(1, 6);

    }

//    public void movePlayer() {
//
//        for (int i = 0; i < rand; i++) {
//
//            if (playerXPos == 40 && playerYPos == 40) {
//
//                break;
//            }
//
//            if (playerXPos == 600) {
//
//                direction = -1;
//                playerYPos -= 80;
//            }
//            if (playerXPos == 40) {
//
//                direction = 1;
//                playerYPos -= 80;
//            }
//            playerXPos += (playerXPos * direction);
//
//        }
//
//    }

    public void movePlayer2() {
        currPos+=rand;

        if(currPos==5 ){
            currPos=28;

        }
        if(currPos==51){
            currPos=62;
        }
        if(currPos==56){

            currPos=39;

        }
        if(currPos==33){
            currPos=17;
        }
        if(currPos>64){
            currPos=64;
        }


        Tile newPos=map.get(currPos);
       // player.setCenterX(newPos.getX()+40);
        //player.setCenterY(newPos.getY()+40);
        translatePlayer((int) newPos.getX()+40, (int) newPos.getY()+40,player);

    }

    private void translatePlayer(int x, int y, Circle b) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
