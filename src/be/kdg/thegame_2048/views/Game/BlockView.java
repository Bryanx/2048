package be.kdg.thegame_2048.views.Game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */

class BlockView extends StackPane {
    private static final int BLOCK_SIZE = 100;
    private int value;
    private Rectangle rect;
    private Text number;

    BlockView(int value) {
        this.value = value;
        initialiseNodes();
        changeAppearance();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.rect = new Rectangle(BLOCK_SIZE,BLOCK_SIZE);
        this.number = new Text();
    }

    private void layoutNodes() {
        this.getChildren().add(rect);
        this.getChildren().add(number);
    }

    private void changeAppearance() {
        if (value == 0) {
            number.setText("");
            rect.setFill(Color.web("#cdc1b4"));
        }
        if (value != 0) {
            number.setText(Integer.toString(value));
            number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 55));
            number.setFill(Color.web("#776e65"));
        }
        if (value >= 8) number.setFill(Color.web("#f9f6f2"));
        if (value >= 128) number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 43));
        if (value >= 1024) number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 34));

        if (value >= 2048) {
            rect.setFill(Color.web("#edc22e"));
        } else if (value >= 1024) {
            rect.setFill(Color.web("#edc53f"));
        } else if (value >= 512) {
            rect.setFill(Color.web("#f1c85d"));
        } else if (value >= 256) {
            rect.setFill(Color.web("#edcc61"));
        } else if (value >= 128) {
            rect.setFill(Color.web("#edcf72"));
        } else if (value >= 64) {
            rect.setFill(Color.web("#f65e3b"));
        } else if (value >= 32) {
            rect.setFill(Color.web("#f67c5f"));
        } else if (value >= 16) {
            rect.setFill(Color.web("#f59563"));
        } else if (value >= 8) {
            rect.setFill(Color.web("#f2b179"));
        } else if (value >= 4) {
            rect.setFill(Color.web("#ede0c8"));
        } else if (value > 0) {
            rect.setFill(Color.web("#eee4da"));
        }
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
        changeAppearance();
    }
}
