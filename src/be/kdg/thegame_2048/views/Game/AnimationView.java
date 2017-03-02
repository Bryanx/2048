package be.kdg.thegame_2048.views.Game;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bryan de Ridder
 * @version 1.0 02-03-17 06:18
 */
class AnimationView {
    private static final Duration DURATION = Duration.millis(100);
    private int increment = 110;
    private ParallelTransition parallelTransition;
    private List<TranslateTransition> tt;
    private GameMiddleView midView;
    private GamePresenter gamePresenter;

    AnimationView(GameMiddleView midView, GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
        this.midView = midView;
        initialiseNodes();
    }

    private void initialiseNodes() {
        this.parallelTransition = new ParallelTransition();
        this.tt = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            this.tt.add(new TranslateTransition(DURATION));
        }
    }

    void moveUp() {
        int index = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int thisBlock = BlockValue(x, y);
                gettTransitions(index).setNode(midView.getBlock(x,y));
                if (!gamePresenter.isMovable() && thisBlock != 0) {
                    this.increment = 110;
                    if (y > 0 && (BlockValue(x, y - 1) == 0 || BlockValue(x, y - 1) == thisBlock)) {
                        if (y > 1 && (BlockValue(x, y - 2) == 0 || BlockValue(x, y - 2) == thisBlock)) {
                            if (y > 2 && (BlockValue(x, y - 3) == 0 || BlockValue(x, y - 3) == thisBlock)) {
                                if (BlockValue(x, y - 1) == thisBlock &&
                                        (BlockValue(x, y - 1) == BlockValue(x, y - 2) ||
                                                BlockValue(x, y - 2) == thisBlock)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x, y - 1) == thisBlock && BlockValue(x, y - 2) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (y > 2 && (BlockValue(x, y - 3) == 0 || BlockValue(x, y - 3) == BlockValue(x, y - 2))) {
                            increment *= 2;
                        }
                    } else if (y > 1 && (BlockValue(x, y - 2) == 0 || BlockValue(x, y - 2) == BlockValue(x, y - 1))) {
                        if (y > 2 && (BlockValue(x, y - 3) == 0 || BlockValue(x, y - 3) == BlockValue(x, y - 1))) {
                            increment *= 2;
                        }
                    } else if (y > 2 && (BlockValue(x, y - 3) == 0 || BlockValue(x, y - 3) == BlockValue(x, y - 2))) {
                        //do nothing
                    } else {
                        increment = 0;
                    }
                    System.out.println(-increment);
                    gettTransitions(index).setToY(-increment);
                    midView.getBlock(x, y).toFront();
                    parallelTransition.getChildren().addAll(gettTransitions(index));
                    index++;
                }
            }
        }
    }

    void moveDown() {
        int index = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int thisBlock = BlockValue(x, y);
                if (!gamePresenter.isMovable() && thisBlock != 0) {
                    gettTransitions(index).setNode(midView.getBlock(x,y));
                    this.increment = 110;
                    if (y < 3 && (BlockValue(x, y + 1) == 0 || BlockValue(x, y + 1) == thisBlock)) {
                        if (y < 2 && (BlockValue(x, y + 2) == 0 || BlockValue(x, y + 2) == thisBlock)) {
                            if (y < 1 && (BlockValue(x, y + 3) == 0 || BlockValue(x, y + 3) == thisBlock)) {
                                if (BlockValue(x, y + 1) == thisBlock &&
                                        (BlockValue(x, y + 1) == BlockValue(x, y + 2) ||
                                                BlockValue(x, y + 2) == thisBlock)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x, y + 1) == thisBlock && BlockValue(x, y + 2) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (y < 1 && (BlockValue(x, y + 3) == 0 || BlockValue(x, y + 3) == BlockValue(x, y + 2))) {
                            increment *= 2;
                        }
                    } else if (y < 2 && (BlockValue(x, y + 2) == 0 || BlockValue(x, y + 2) == BlockValue(x, y + 1))) {
                        if (y < 1 && (BlockValue(x, y + 3) == 0 || BlockValue(x, y + 3) == BlockValue(x, y + 1))) {
                            increment *= 2;
                        }
                    } else if (y < 1 && (BlockValue(x, y + 3) == 0 || BlockValue(x, y + 3) == BlockValue(x, y + 2))) {
                        //do nothing
                    } else {
                        increment = 0;
                    }
                    gettTransitions(index).setToY(increment);
                    midView.getBlock(x, y).toFront();
                    parallelTransition.getChildren().addAll(gettTransitions(index));
                    index++;
                }
            }
        }
    }

    void moveRight() {
        int index = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int thisBlock = BlockValue(x, y);
                if (!gamePresenter.isMovable() && thisBlock != 0) {
                    gettTransitions(index).setNode(midView.getBlock(x,y));
                    this.increment = 110;
                    if (x < 3 && (BlockValue(x + 1, y) == 0 || BlockValue(x + 1, y) == thisBlock)) {
                        if (x < 2 && (BlockValue(x + 2, y) == 0 || BlockValue(x + 2, y) == thisBlock)) {
                            if (x < 1 && (BlockValue(x + 3, y) == 0 || BlockValue(x + 3, y) == thisBlock)) {
                                if (BlockValue(x + 1, y) == thisBlock &&
                                        (BlockValue(x + 1, y) == BlockValue(x + 2, y) ||
                                                BlockValue(x + 2, y) == thisBlock)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x + 1, y) == thisBlock && BlockValue(x + 2, y) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (x < 1 && (BlockValue(x + 3, y) == 0 || BlockValue(x + 3, y) == BlockValue(x + 2, y))) {
                            increment *= 2;
                        }
                    } else if (x < 2 && (BlockValue(x + 2, y) == 0 || BlockValue(x + 2, y) == BlockValue(x + 1, y))) {
                        if (x < 1 && (BlockValue(x + 3, y) == 0 || BlockValue(x + 3, y) == BlockValue(x + 1, y))) {
                            increment *= 2;
                        }
                    } else if (x < 1 && (BlockValue(x + 3, y) == 0 || BlockValue(x + 3, y) == BlockValue(x + 2, y))) {
                        //do nothing
                    } else {
                        increment = 0;
                    }
                    gettTransitions(index).setToX(increment);
                    midView.getBlock(x, y).toFront();
                    parallelTransition.getChildren().addAll(gettTransitions(index));
                    index++;
                }
            }
        }
    }

    void moveLeft() {
        int index = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int thisBlock = BlockValue(x, y);
                if (!gamePresenter.isMovable() && thisBlock != 0) {
                    gettTransitions(index).setNode(midView.getBlock(x,y));
                    this.increment = 110;
                    if (x > 0 && (BlockValue(x - 1, y) == 0 || BlockValue(x - 1, y) == thisBlock)) {
                        if (x > 1 && (BlockValue(x -2 , y) == 0 || BlockValue(x - 2, y) == thisBlock)) {
                            if (x > 2 && (BlockValue(x - 3, y) == 0 || BlockValue(x - 3, y) == thisBlock)) {
                                if (BlockValue(x - 1, y) == thisBlock &&
                                        (BlockValue(x - 1, y) == BlockValue(x - 2, y) ||
                                                BlockValue(x - 2, y) == thisBlock)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x - 1, y) == thisBlock && BlockValue(x-2, y) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (x > 2 && (BlockValue(x-3, y) == 0 || BlockValue(x-3, y) == BlockValue(x-2, y))) {
                            increment *= 2;
                        }
                    } else if (x > 1 && (BlockValue(x-2, y) == 0 || BlockValue(x-2, y) == BlockValue(x-1, y))) {
                        if (x > 2 && (BlockValue(x-3, y) == 0 || BlockValue(x-3, y) == BlockValue(x-1, y))) {
                            increment *= 2;
                        }
                    } else if (x > 2 && (BlockValue(x-3, y) == 0 || BlockValue(x-3, y) == BlockValue(x-2, y))) {
                        //do nothing
                    } else {
                        increment = 0;
                    }
                    gettTransitions(index).setToX(-increment);
                    midView.getBlock(x, y).toFront();
                    parallelTransition.getChildren().addAll(gettTransitions(index));
                    index++;
                }
            }
        }
    }

    private int BlockValue(int x, int y) {
        return midView.getBValue(x, y);
    }

    ParallelTransition getParallelTransition() {
        return parallelTransition;
    }

    void resetAnimation() {
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

        this.parallelTransition.getChildren().removeAll(tt);
        this.tt.clear();
        for (int i = 0; i < 16; i++) {
            this.tt.add(new TranslateTransition(DURATION));
        }
    }

    private TranslateTransition gettTransitions(int i) {
        return tt.get(i);
    }
}
