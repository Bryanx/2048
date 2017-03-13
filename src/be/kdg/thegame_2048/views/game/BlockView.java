package be.kdg.thegame_2048.views.game;

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
    private static final String COLOR_TEXT_8_TO_2048 = "#f9f6f2";
    private static final String COLOR_TEXT_0_TO_8 = "#776e65";
    private static final String COLOR_BLOCK_2 = "#eee4da";
    private static final String COLOR_BLOCK_4 = "#ede0c8";
    private static final String COLOR_BLOCK_0 = "#cdc1b4";
    private static final String COLOR_BLOCK_8 = "#f2b179";
    private static final String COLOR_BLOCK_16 = "#f59563";
    private static final String COLOR_BLOCK_32 = "#f67c5f";
    private static final String COLOR_BLOCK_64 = "#f65e3b";
    private static final String COLOR_BLOCK_128 = "#edcf72";
    private static final String COLOR_BLOCK_256 = "#edcc61";
    private static final String COLOR_BLOCK_52 = "#f1c85d";
    private static final String COLOR_BLOCK_1024 = "#edc53f";
    private static final String COLOR_BLOCK_2048 = "#edc22e";
    private static final int BLOCK_VALUE_2048 = 2048;
    private static final int BLOCK_VALUE_1024 = 1024;
    private static final int BLOCK_VALUE_512 = 512;
    private static final int BLOCK_VALUE_256 = 256;
    private static final int BLOCK_VALUE_128 = 128;
    private static final int BLOCK_VALUE_64 = 64;
    private static final int BLOCK_VALUE_32 = 32;
    private static final int BLOCK_VALUE_16 = 16;
    private static final int BLOCK_VALUE_8 = 8;
    private static final int BLOCK_VALUE_4 = 4;
    private static final int TEXT_SIZE_LARGE = 55;
    private static final int TEXT_SIZE_MEDIUM = 43;
    private static final int TEXT_SIZE_SMALL = 32;
    private static final int BLOCK_SIZE = 100;
    private int value;
    private Rectangle rect;
    private Text number;

    BlockView(int value) {
        this.value = value;
        initialiseNodes();
        changeBlockAppearance();
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

    /**
     * Changes the block's appearance block according to the appropriate value.
     **/
    private void changeBlockAppearance() {
        number.setText(getTextInput());
        number.setFont(Font.font("Clear Sans", FontWeight.BOLD, getTextSize()));
        number.setFill(Color.web(getTextColor()));
        rect.setFill(Color.web(getRectFill()));
    }

    /**
     * Changes the block's text input according to the appropriate value.
     **/
    private String getTextInput() {
        if (value == 0) return "";
        return Integer.toString(value);
    }

    /**
     * Changes the block's text size according to the appropriate value.
     **/
    private int getTextSize() {
        if (value >= BLOCK_VALUE_1024) return TEXT_SIZE_SMALL;
        if (value >= BLOCK_VALUE_128) return TEXT_SIZE_MEDIUM;
        return TEXT_SIZE_LARGE;
    }

    /**
     * Changes the block's text color block according to the appropriate value.
     **/
    private String getTextColor() {
        if (value >= BLOCK_VALUE_8) return COLOR_TEXT_8_TO_2048;
        return COLOR_TEXT_0_TO_8;
    }

    /**
     * Changes the block's background color block according to the appropriate value.
     **/
    private String getRectFill() {
        if (value >= BLOCK_VALUE_2048) return COLOR_BLOCK_2048;
        if (value >= BLOCK_VALUE_1024) return COLOR_BLOCK_1024;
        if (value >= BLOCK_VALUE_512) return COLOR_BLOCK_52;
        if (value >= BLOCK_VALUE_256) return COLOR_BLOCK_256;
        if (value >= BLOCK_VALUE_128) return COLOR_BLOCK_128;
        if (value >= BLOCK_VALUE_64) return COLOR_BLOCK_64;
        if (value >= BLOCK_VALUE_32) return COLOR_BLOCK_32;
        if (value >= BLOCK_VALUE_16) return COLOR_BLOCK_16;
        if (value >= BLOCK_VALUE_8) return COLOR_BLOCK_8;
        if (value >= BLOCK_VALUE_4) return COLOR_BLOCK_4;
        if (value > 0) return COLOR_BLOCK_2;
        return COLOR_BLOCK_0;
    }

    /**
     * Returns the block value.
     * This method runs on a graphical level.
     **/
    int getValue() {
        return value;
    }

    /**
     * Changes the block value to the parameter value.
     * This method runs on a graphical level.
     **/
    void setValue(int value) {
        this.value = value;
        changeBlockAppearance();
    }
}
