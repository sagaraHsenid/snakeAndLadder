package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

public Tile(){

    setWidth(SnakeLadder.Tile_size);
    setHeight(SnakeLadder.Tile_size);

    setFill(Color.PINK);
    setStroke(Color.BLACK);
}

public Tile(int x,int y){

    setX(x);
    setY(y);
    setWidth(SnakeLadder.Tile_size);
    setHeight(SnakeLadder.Tile_size);

    setFill(Color.GRAY);
    setStroke(Color.BLACK);


}

}
