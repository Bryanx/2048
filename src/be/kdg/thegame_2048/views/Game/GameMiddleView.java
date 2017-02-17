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

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private Canvas canvas;
    private Image[] imgBlocks;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Create imgBlocks
        String path = "be/kdg/thegame_2048/views/img/blocks/block";
        String[] blockNumbers = {"E","2","4","8","16","32","64","128","256","512","1024","2048"};
        this.imgBlocks = new Image[12];
        for (int i = 0; i <= 11; i++) {
            this.imgBlocks[i] = new Image(path + blockNumbers[i] + ".png");
        }
        this.canvas = new Canvas(GameView.GAME_SIZE,GameView.GAME_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 10; i <= 340; i+=110) {
            gc.drawImage(imgBlocks[0],i,10,100,100);
        }
        for (int i = 10; i <= 340; i+=110) {
            gc.drawImage(imgBlocks[0],i,120,100,100);
        }
        for (int i = 10; i <= 340; i+=110) {
            gc.drawImage(imgBlocks[0],i,230,100,100);
        }
        for (int i = 10; i <= 340; i+=110) {
            gc.drawImage(imgBlocks[0],i,340,100,100);
        }
    }

    private void layoutNodes() {
        BorderPane middle = new BorderPane();
        BorderPane playground = new BorderPane();
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setCenter(canvas);
        playground.setBackground(new Background(new BackgroundFill(Color.rgb(187, 173, 160), new CornerRadii(5), Insets.EMPTY)));
        middle.setCenter(playground);
        this.setCenter(middle);
    }

    Image getBlock(int index) {
        return imgBlocks[index];
    }
}
