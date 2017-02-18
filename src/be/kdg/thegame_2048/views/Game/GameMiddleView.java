package be.kdg.thegame_2048.views.Game;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private Canvas canvas;
    private Map<Integer, Image> imgBlocks;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Create imgBlocks
        this.imgBlocks = new HashMap<>();
        imgBlocks.put(0, new Image("be/kdg/thegame_2048/views/img/blocks/E.png"));
        for (int i = 2; i <= 2048; i*=2) {
            imgBlocks.put(i, new Image("be/kdg/thegame_2048/views/img/blocks/" + Integer.toString(i) + ".png"));
        }

        this.canvas = new Canvas(GameView.GAME_SIZE,GameView.GAME_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 10; i <= 340; i+=110) {
            for (int j = 10; j <= 340; j+=110) {
                gc.drawImage(imgBlocks.get(0), i, j, 100, 100);
            }
        }
    }

    private void layoutNodes() {
        BorderPane playground = new BorderPane(canvas);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(new BackgroundFill(Color.rgb(187, 173, 160), new CornerRadii(5), Insets.EMPTY)));
        this.setCenter(new BorderPane(playground));
    }

    Image getImgBlock(int value) {
        return imgBlocks.get(value); //value 0 = Empty
    }
}
