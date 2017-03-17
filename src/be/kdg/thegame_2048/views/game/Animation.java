package be.kdg.thegame_2048.views.game;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains animations used in the game view.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 02-03-17 06:18
 */
final class Animation {
    private static final Duration SCORE_MOVE_DURATION = Duration.millis(1500);
    private static final Duration SCORE_FADE_DURATION = Duration.millis(1250);
    private static final Duration MOVE_DURATION = Duration.millis(100);
    private static final Duration POPIN_DURATION = Duration.millis(200);
    private final GameTopView topView;
    private final GameMiddleView midView;
    private final GamePresenter gamePresenter;

    private ParallelTransition parallelTransition;
    private List<TranslateTransition> translateTransitions;
    private ScaleTransition scaleTransition;

    Animation(GameTopView topView, GameMiddleView midView, GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
        this.midView = midView;
        this.topView = topView;
        initialiseNodes();
    }

    private void initialiseNodes() {
        this.parallelTransition = new ParallelTransition();
        this.translateTransitions = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            this.translateTransitions.add(new TranslateTransition(MOVE_DURATION));
            this.translateTransitions.get(i).setInterpolator(Interpolator.EASE_BOTH);
        }
    }

    void animateMovement(KeyCode direction) {
        int index = 0;
        int increment;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gamePresenter.isMovable() && getBlockValue(x, y) != 0) {
                    getTTransitions(index).setNode(midView.getBlock(x, y));

                    increment = getMoveIncrement(x, y, direction);

                    switch (direction) {
                        case UP : getTTransitions(index).setToY(-increment); break;
                        case DOWN : getTTransitions(index).setToY(increment); break;
                        case RIGHT : getTTransitions(index).setToX(increment); break;
                        case LEFT : getTTransitions(index).setToX(-increment); break;
                    }

                    midView.getBlock(x, y).toFront();
                    this.parallelTransition.getChildren().addAll(getTTransitions(index));
                    index++;
                }
            }
        }
        this.parallelTransition.play();
    }

    /**
     * @return If the x or y parameter is within the boundaries of the game.
     * @param boundary indicates which boundary the method needs to check
     * @param x position of the block
     * @param y position of the block
     * @param direction direction the block should be moving in.
     **/
    private boolean isGreaterThan(int x, int y, KeyCode direction, int boundary) {
        switch (direction) {
            case UP : return y > boundary;
            case DOWN : return y < Math.abs(boundary-3);
            case RIGHT : return x < Math.abs(boundary-3);
            case LEFT : return x > boundary;
        }
        return false;
    }

    /**
     * @return The value the block it needs to look at.
     * @param lookat indicates the position of the block it needs to look at.
     * @param x position of the block
     * @param y position of the block
     * @param direction direction the block should be moving in.
     **/
    private int BlockValueLookatDir(int x, int y, KeyCode direction, int lookat) {
        if (isGreaterThan(x, y, direction, lookat - 1)) {
            switch (direction) {
                case UP : return getBlockValue(x, y - lookat);
                case DOWN : return getBlockValue(x, y + lookat);
                case RIGHT : return getBlockValue(x + lookat, y);
                case LEFT : return getBlockValue(x - lookat, y);
            }
        }
        return 0;
    }

    /**
     * @return  The appropriate amount the block needs to move across the screen without
     * going out of boundaries or overlapping other blocks. Checks all possible conditions.
     * @param x position of the block
     * @param y position of the block
     * @param direction direction the block should be moving in.
     **/
    private int getMoveIncrement(int x , int y, KeyCode direction) {
        int thisBlock = getBlockValue(x, y);
        boolean greaterThan0 = isGreaterThan(x, y, direction, 0);
        boolean greaterThan1 = isGreaterThan(x, y, direction, 1);
        boolean greaterThan2 = isGreaterThan(x, y, direction, 2);
        int block1 = BlockValueLookatDir(x, y, direction, 1);
        int block2 = BlockValueLookatDir(x, y, direction, 2);
        int block3 = BlockValueLookatDir(x, y, direction, 3);
        int incr = 110;

        if (greaterThan0 && (block1 == 0 || block1 == thisBlock)) {
            if (greaterThan1 && (block2 == 0 || block2 == thisBlock)) {
                if (greaterThan2 && (block3 == 0 || block3 == thisBlock)) {
                    if (block1 == thisBlock && (block1 == block2)) {
                        incr *= 2;
                    } else {
                        incr *= 3;
                    }
                } else if (block1 == thisBlock && block2 == thisBlock) {
                    // do nothing
                } else {
                    incr *= 2;
                }
            } else if (greaterThan2 && (block3 == block2)) {
                incr *= 2;
            }
        } else if (greaterThan1 && (block2 == 0 || block2 == block1)) {
            if (greaterThan2 && (block3 == 0 || block3 == block1)) {
                incr *= 2;
            }
        } else if (greaterThan2 && (block3 == 0 || block3 == block2)) {
            //do nothing
        } else {
            incr = 0;
        }
        return incr;
    }

void popIn(int x, int y) {
        this.scaleTransition = new ScaleTransition(POPIN_DURATION, midView.getBlock(x, y));
        scaleTransition.setFromX(0.0);
        scaleTransition.setFromY(0.0);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }

    /**
     * Animates a Label on top of the currentScore.
     * Can be used to animate score increase value.
     * @param score The score amount that should be animated.
     **/
    void animateScore(int score) {
        topView.getLblScoreAnimation().setText("+" + score);
        topView.getLblScoreAnimation().setVisible(true);
        Label lblScore = topView.getLblScoreAnimation();

        FadeTransition ft = new FadeTransition();
        ft.setDuration(SCORE_FADE_DURATION);
        ft.setNode(lblScore);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        TranslateTransition ttScore = new TranslateTransition();
        ttScore.setNode(lblScore);
        ttScore.setDuration(SCORE_MOVE_DURATION);
        ttScore.setFromY(0);
        ttScore.setToY(-100);
        ttScore.setCycleCount(2);
        ttScore.setAutoReverse(true);
        ttScore.setInterpolator(Interpolator.EASE_BOTH);
        ttScore.play();
    }

    /**
     * Resets all move animations to the original position.
     * Clears all move animations.
     * Reinitializes all move animations.
     **/
    void resetMoveAnimation() {
        ParallelTransition p = new ParallelTransition();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                TranslateTransition temp = new TranslateTransition(Duration.millis(0.1), midView.getBlock(i, j));
                if (midView.getBlock(i, j).getValue() != 0) {
                    temp.setToX(0);
                    temp.setToY(0);
                }
                p.getChildren().addAll(temp);
            }
        }
        p.play();

        this.parallelTransition.getChildren().removeAll(translateTransitions);
        this.translateTransitions.clear();
        for (int i = 0; i < 16; i++) {
            this.translateTransitions.add(new TranslateTransition(MOVE_DURATION));
        }
    }

    private int getBlockValue(int x, int y) {
        return midView.getBlockValue(x, y);
    }
    ParallelTransition getParallelTransition() {
        return parallelTransition;
    }
    private TranslateTransition getTTransitions(int i) {
        return translateTransitions.get(i);
    }

}
