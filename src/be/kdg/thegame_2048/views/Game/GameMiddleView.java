package be.kdg.thegame_2048.views.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    GridPane sectionGrid;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
    }

    private void layoutNodes() {
        //MIDDLE
        this.sectionGrid = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sectionGrid.add(getBlock(0), i, j);
            }
        }
        sectionGrid.setVgap(10);
        sectionGrid.setHgap(10);
        sectionGrid.setAlignment(Pos.CENTER);

        BorderPane playground = new BorderPane(sectionGrid);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(new BackgroundFill(Color.web("#bbada0"), new CornerRadii(5), Insets.EMPTY)));
        this.setCenter(new BorderPane(playground));
    }

    private StackPane getBlock(int value) {
        //TODO: Refactoren, misschien met een map?
        Rectangle rect = new Rectangle(100,100);
        Text number = new Text("");
        if (value == 0)
            rect.setFill(Color.web("#cdc1b4"));
        if (value != 0) {
            number = new Text(Integer.toString(value));
            number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 55));
            number.setFill(Color.web("#776e65"));
        }
        if (value > 4)
            number.setFill(Color.web("#f9f6f2"));
        if (value > 64)
            number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 43));
        if (value > 512)
            number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 34));

        switch (value) {
            case 0: rect.setFill(Color.web("#cdc1b4"));break;
            case 2: rect.setFill(Color.web("#eee4da"));break;
            case 4: rect.setFill(Color.web("#ede0c8"));break;
            case 8: rect.setFill(Color.web("#f2b179"));break;
            case 16: rect.setFill(Color.web("#f59563"));break;
            case 32: rect.setFill(Color.web("#f67c5f"));break;
            case 64: rect.setFill(Color.web("#f65e3b"));break;
            case 128: rect.setFill(Color.web("#edcf72"));break;
            case 256: rect.setFill(Color.web("#edcc61"));break;
            case 512: rect.setFill(Color.web("#f1c85d"));break;
            case 1024: rect.setFill(Color.web("#edc53f"));break;
            case 2048: rect.setFill(Color.web("#edc22e"));break;
        }
        return new StackPane(rect, number);
    }

    void setBlock(int value, int x, int y) {
        this.sectionGrid.add(getBlock(value), y, x);
    }
}
