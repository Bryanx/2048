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
    private int value;
    private Rectangle rect;
    private Text number;

    BlockView(int value) {
        this.value = value;
        initialiseNodes();
        changeAppearance();
        updateView();
    }

    private void initialiseNodes() {
        this.rect = new Rectangle(100,100);
        this.number = new Text();
    }

    private void updateView() {
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
        if (value > 4) number.setFill(Color.web("#f9f6f2"));
        if (value > 64) number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 43));
        if (value > 512) number.setFont(Font.font("Clear Sans", FontWeight.BOLD, 34));

        switch (value) {
            case 0:rect.setFill(Color.web("#cdc1b4"));break;
            case 2:rect.setFill(Color.web("#eee4da"));break;
            case 4:rect.setFill(Color.web("#ede0c8"));break;
            case 8:rect.setFill(Color.web("#f2b179"));break;
            case 16:rect.setFill(Color.web("#f59563"));break;
            case 32:rect.setFill(Color.web("#f67c5f"));break;
            case 64:rect.setFill(Color.web("#f65e3b"));break;
            case 128:rect.setFill(Color.web("#edcf72"));break;
            case 256:rect.setFill(Color.web("#edcc61"));break;
            case 512:rect.setFill(Color.web("#f1c85d"));break;
            case 1024:rect.setFill(Color.web("#edc53f"));break;
            case 2048:rect.setFill(Color.web("#edc22e"));break;
        }
    }

    int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
        changeAppearance();
    }
}
