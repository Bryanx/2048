package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.Result.ResultPresenter;
import be.kdg.thegame_2048.views.Result.ResultView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game modelGame;
    private PlayerManager modelPlayerMananger;
    private GameView view;
    private GameBottomView bottomView;
    private GameMiddleView midView;
    private GameTopView topView;
    private boolean alreadyWon;
    private boolean firstRun;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPlayerMananger = modelPlayerManager;
        this.modelPlayerMananger.setCurrentPlayerScore(modelGame.getScore().getScore());
        this.view = view;
        this.bottomView = view.getBottomView();
        this.midView = view.getMiddleView();
        this.topView = view.getTopView();
        this.firstRun = true;
        this.addEventHandlers();
        topView.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
        //eenmalige updateview
        updateView();

    }

    //METHODEN
    private void addEventHandlers() {
        bottomView.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelPlayerMananger.saveInfoCurrentPlayer();
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(modelGame, modelPlayerMananger, hsView);
                view.getScene().setRoot(hsView);
            }
        });
        bottomView.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelPlayerMananger.saveInfoCurrentPlayer();
                modelPlayerMananger.setCurrentPlayerToNull();
                StartView startView = new StartView();
                new StartPresenter(modelPlayerMananger, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        updateViewBlocks(Game.Direction.DOWN);
                        break;
                    case UP:
                        updateViewBlocks(Game.Direction.TOP);
                        break;
                    case RIGHT:
                        updateViewBlocks(Game.Direction.RIGHT);
                        break;
                    case LEFT:
                        updateViewBlocks(Game.Direction.LEFT);
                        break;
                    default:
                        event.consume();
                }
                moveAnimation(event.getCode());
                modelPlayerMananger.setCurrentPlayerScore(modelGame.getScore().getScore());
            }
        });
        bottomView.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alreadyWon = false;
                firstRun = true;
                modelPlayerMananger.saveInfoCurrentPlayer();
                modelGame = new Game(modelPlayerMananger);
                topView.getLblScoreInput().setText("0");
                updateView();
            }
        });
    }

    private int BlockValue(int x, int y) {
        return midView.getBValue(x, y);
    }

    private ParallelTransition move(KeyCode dir) {
        int increment = 0;
        boolean ygt0 = false, ygt1 = false, ygt2 = false;
        int x1 = 0, x2 = 0, x3 = 0, y1 = 0, y2 = 0, y3 = 0;
        ParallelTransition p = new ParallelTransition();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (dir.toString().equals("UP")) {
                    ygt0 = y > 0;
                    ygt1 = y > 1;
                    ygt2 = y > 2;
                    increment = -110;
                    y1 = -1;
                    y2 = -2;
                    y3 = -3;
                } else if (dir.toString().equals("DOWN")) {
                    ygt0 = y < 3;
                    ygt1 = y < 2;
                    ygt2 = y < 1;
                    increment = 110;
                    y1 = 1;
                    y2 = 2;
                    y3 = 3;
                } else if (dir.toString().equals("LEFT")) {
                    ygt0 = x > 0;
                    ygt1 = x > 1;
                    ygt2 = x > 2;
                    increment = -110;
                    x1 = -1;
                    x2 = -2;
                    x3 = -3;
                } else if (dir.toString().equals("RIGHT")) {
                    ygt0 = x < 3;
                    ygt1 = x < 2;
                    ygt2 = x < 1;
                    increment = 110;
                    x1 = 1;
                    x2 = 2;
                    x3 = 3;
                } else {
                    //do nothing
                }
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), midView.getBlock(x, y));
                int thisBlock = BlockValue(x, y);
                if (!isMovable() && thisBlock != 0) {
                    if (ygt0 && (BlockValue(x + x1, y + y1) == 0 || BlockValue(x + x1, y + y1) == thisBlock)) {
                        if (ygt1 && (BlockValue(x + x2, y + y2) == 0 || BlockValue(x + x2, y + y2) == thisBlock)) {
                            if (ygt2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == thisBlock)) {
                                if (BlockValue(x + x1, y + y1) == thisBlock &&
                                        (BlockValue(x + x1, y + y1) == BlockValue(x + x2, y + y2) ||
                                                BlockValue(x + x2, y + y2) == thisBlock)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x + x1, y + y1) == thisBlock && BlockValue(x + x2, y + y2) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (ygt2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == BlockValue(x + x2, y + y2))) {
                            increment *= 2;
                        }
                    } else if (ygt1 && (BlockValue(x + x2, y + y2) == 0 || BlockValue(x + x2, y + y2) == BlockValue(x + x1, y + y1))) {
                        if (ygt2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == BlockValue(x + x1, y + y1))) {
                            increment *= 2;
                        }
                    } else if (ygt2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == BlockValue(x + x2, y + y2))) {
                        //do nothing
                    } else {
                        increment = 0;
                    }


                    if (dir.toString().equals("UP") || dir.toString().equals("DOWN")) {
                        tt.setToY(increment);
                    } else {
                        tt.setToX(increment);
                    }
                    midView.getBlock(x, y).toFront();
                    midView.getBlock(x,y).setCache(true);
                    midView.getBlock(x,y).setCacheShape(true);
                    midView.getBlock(x,y).setCacheHint(CacheHint.SPEED);
                }
                p.getChildren().addAll(tt);
            }

        }
        return p;
    }

    private void moveAnimation(KeyCode dir) {
        ParallelTransition p = new ParallelTransition(move(dir));
        //        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                TranslateTransition tt = new TranslateTransition(Duration.millis(100), midView.getBlock(x,y));
//                int thisBlock = BlockValue(x,y);
//                if (!isMovable()) {
//                    if (thisBlock != 0) {
//                        switch (dir.toString()) {
//                            case ("UP"):
//                                if (y > 0 && (BlockValue(x, y - 1) == 0 ||
//                                        BlockValue(x, y - 1) == thisBlock)) {
//                                    if (y > 1 && (BlockValue(x, y - 2) == 0 ||
//                                            BlockValue(x, y - 2) == thisBlock)) {
//                                        if (y > 2 && (BlockValue(x, y - 3) == 0 ||
//                                                BlockValue(x, y - 3) == thisBlock)) {
//                                            if (BlockValue(x, y - 1) == thisBlock &&
//                                                    BlockValue(x, y - 2) == BlockValue(x, y - 1)) {
//                                                tt.setToY(-220);
//                                            } else {
//                                                tt.setToY(-330);
//                                            }
//                                        } else if (BlockValue(x, y - 1) == thisBlock &&
//                                                BlockValue(x, y - 2) == thisBlock) {
//                                            tt.setToY(-110);
//                                        } else {
//                                            tt.setToY(-220);
//                                        }
//                                    } else if (y > 2 && (BlockValue(x, y - 3) == 0 ||
//                                            BlockValue(x, y - 3) == BlockValue(x, y - 2))) {
//                                        tt.setToY(-220);
//                                    } else {
//                                        tt.setToY(-110);
//                                    }
//                                } else if (y > 1 && (BlockValue(x, y - 2) == BlockValue(x, y - 1) ||
//                                        BlockValue(x, y - 2) == 0)) {
//                                    if (y > 2 && (BlockValue(x, y - 3) == BlockValue(x, y - 1) ||
//                                            BlockValue(x, y - 2) == 0)) {
//                                        if (BlockValue(x, y - 1) != thisBlock) {
//                                            if (BlockValue(x, y - 3) == 0) {
//                                                tt.setToY(-220);
//                                            } else {
//                                                tt.setToY(-110);
//                                            }
//                                        } else {
//                                            tt.setToY(-220);
//                                        }
//                                    } else if (y > 2 && (BlockValue(x, y - 3) == 0)) {
//                                        tt.setToY(-220);
//                                    } else {
//                                        tt.setToY(-110);
//                                    }
//                                } else if (y > 2 && (BlockValue(x, y - 3) == BlockValue(x, y - 2) ||
//                                        BlockValue(x, y - 3) == 0)) {
//                                    tt.setToY(-110);
//                                }midView.getBlock(x, y).toFront();
//                                break;
//                                case ("DOWN"):
//                                    if (y < 3 && (BlockValue(x, y + 1) == BlockValue(x, y) ||
//                                            BlockValue(x, y + 1) == 0)) {
//                                        if (y < 2 && (BlockValue(x, y + 2) == 0 ||
//                                                BlockValue(x, y + 2) == BlockValue(x, y))) {
//                                            if (y < 1 && (BlockValue(x, y + 3) == 0 ||
//                                                    BlockValue(x, y + 3) == BlockValue(x, y))) {
//                                                if (BlockValue(x, y + 1) == BlockValue(x, y) &&
//                                                        BlockValue(x, y + 2) == BlockValue(x, y + 1)) {
//                                                    tt.setToY(+220);
//                                                } else {
//                                                    tt.setToY(+330);
//                                                }
//                                            } else {
//                                                if (BlockValue(x, y + 1) == BlockValue(x, y) &&
//                                                        BlockValue(x, y + 2) == BlockValue(x, y)) {
//                                                    tt.setToY(+110);
//                                                } else {
//                                                    tt.setToY(+220);
//                                                }
//                                            }
//                                        } else if (y < 1 && (BlockValue(x, y + 3) == 0 ||
//                                                BlockValue(x, y + 3) == BlockValue(x, y + 2))) {
//                                            tt.setToY(+220);
//                                        } else {
//                                            tt.setToY(+110);
//                                        }
//                                    } else if (y < 2 && (BlockValue(x, y + 2) == BlockValue(x, y + 1) ||
//                                            BlockValue(x, y + 2) == 0)) {
//                                        if (y < 1 && (BlockValue(x, y + 3) == BlockValue(x, y + 1) ||
//                                                BlockValue(x, y + 2) == 0)) {
//                                            if (BlockValue(x, y + 1) != BlockValue(x, y)) {
//                                                if (BlockValue(x, y + 3) == 0) {
//                                                    tt.setToY(+220);
//                                                } else {
//                                                    tt.setToY(+110);
//                                                }
//                                            } else {
//                                                tt.setToY(+220);
//                                            }
//                                        } else {
//                                            if (y < 1 && (BlockValue(x, y + 3) == 0)) {
//                                                tt.setToY(+220);
//                                            } else {
//                                                tt.setToY(+110);
//                                            }
//                                        }
//                                    } else if (y < 1 && (BlockValue(x, y + 3) == BlockValue(x, y + 2) ||
//                                            BlockValue(x, y + 3) == 0)) {
//                                        tt.setToY(+110);
//                                    }midView.getBlock(x, y).toFront();
//                                    break;
//                            case ("RIGHT"):
//                                if (x < 3 && (BlockValue(x+1, y) == BlockValue(x, y) ||
//                                        BlockValue(x+1,y) == 0)) {
//                                    if (x < 2 && (BlockValue(x+2,y) == 0 ||
//                                            BlockValue(x+2, y) == BlockValue(x, y))) {
//                                        if (x < 1 && (BlockValue(x+3, y) == 0 ||
//                                                BlockValue(x+3, y) == BlockValue(x, y))) {
//                                            if (BlockValue(x+1, y) == BlockValue(x, y) &&
//                                                    BlockValue(x+2, y) == BlockValue(x+1, y)) {
//                                                tt.setToX(+220);
//                                            } else {
//                                                tt.setToX(+330);
//                                            }
//                                        } else {
//                                            if (BlockValue(x+1, y) == BlockValue(x, y) &&
//                                                    BlockValue(x+2, y) == BlockValue(x, y)) {
//                                                tt.setToX(+110);
//                                            } else {
//                                                tt.setToX(+220);
//                                            }
//                                        }
//                                    } else if (x < 1 && (BlockValue(x+3, y) == 0 ||
//                                            BlockValue(x+3, y) == BlockValue(x+2, y))) {
//                                        tt.setToX(+220);
//                                    } else {
//                                        tt.setToX(+110);
//                                    }
//                                } else if (x < 2 && (BlockValue(x+2, y) == BlockValue(x+1, y) ||
//                                        BlockValue(x+2, y) == 0)) {
//                                    if (x < 1 && (BlockValue(x+3, y) == BlockValue(x+1, y) ||
//                                            BlockValue(x+2, y) == 0)) {
//                                        if (BlockValue(x+1, y) != BlockValue(x, y)) {
//                                            if (BlockValue(x+3, y) == 0) {
//                                                tt.setToX(+220);
//                                            } else {
//                                                tt.setToX(+110);
//                                            }
//                                        } else {
//                                            tt.setToX(+220);
//                                        }
//                                    } else {
//                                        if (x < 1 && (BlockValue(x+3, y) == 0)) {
//                                            tt.setToX(+220);
//                                        } else {
//                                            tt.setToX(+110);
//                                        }
//                                    }
//                                } else if (x < 1 && (BlockValue(x+3, y) == BlockValue(x+2, y) ||
//                                        BlockValue(x+3, y) == 0)) {
//                                    tt.setToX(+110);
//                                }midView.getBlock(x, y).toFront();
//                                break;
//                            case ("LEFT"):
//                                if (x > 0 && (BlockValue(x-1, y) == BlockValue(x, y) ||
//                                        BlockValue(x-1,y) == 0)) {
//                                    if (x > 1 && (BlockValue(x-2,y) == 0 ||
//                                            BlockValue(x-2, y) == BlockValue(x, y))) {
//                                        if (x > 2 && (BlockValue(x-3, y) == 0 ||
//                                                BlockValue(x-3, y) == BlockValue(x, y))) {
//                                            if (BlockValue(x-1, y) == BlockValue(x, y) &&
//                                                    BlockValue(x-2, y) == BlockValue(x-1, y)) {
//                                                tt.setToX(-220);
//                                            } else {
//                                                tt.setToX(-330);
//                                            }
//                                        } else {
//                                            if (BlockValue(x-1, y) == BlockValue(x, y) &&
//                                                    BlockValue(x-2, y) == BlockValue(x, y)) {
//                                                tt.setToX(-110);
//                                            } else {
//                                                tt.setToX(-220);
//                                            }
//                                        }
//                                    } else if (x > 2 && (BlockValue(x-3, y) == 0 ||
//                                            BlockValue(x-3, y) == BlockValue(x-2, y))) {
//                                        tt.setToX(-220);
//                                    } else {
//                                        tt.setToX(-110);
//                                    }
//                                } else if (x > 1 && (BlockValue(x-2, y) == BlockValue(x-1, y) ||
//                                        BlockValue(x-2, y) == 0)) {
//                                    if (x > 2 && (BlockValue(x-3, y) == BlockValue(x-1, y) ||
//                                            BlockValue(x-2, y) == 0)) {
//                                        if (BlockValue(x-1, y) != BlockValue(x, y)) {
//                                            if (BlockValue(x-3, y) == 0) {
//                                                tt.setToX(-220);
//                                            } else {
//                                                tt.setToX(-110);
//                                            }
//                                        } else {
//                                            tt.setToX(-220);
//                                        }
//                                    } else {
//                                        if (x > 2 && (BlockValue(x-3, y) == 0)) {
//                                            tt.setToX(-220);
//                                        } else {
//                                            tt.setToX(-110);
//                                        }
//                                    }
//                                } else if (x > 2 && (BlockValue(x-3, y) == BlockValue(x-2, y) ||
//                                        BlockValue(x-3, y) == 0)) {
//                                    tt.setToX(-110);
//                                }midView.getBlock(x, y).toFront();
//                                break;
//                        }
//                    }
//                }
//                p.getChildren().addAll(tt);
//            }
//        }
        p.play();
        p.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ParallelTransition p = new ParallelTransition();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        TranslateTransition tt = new TranslateTransition(Duration.millis(0.1), midView.getBlock(i, j));
                        if (midView.getBlock(i, j).getValue() != 0) {
                            tt.setToX(0);
                            tt.setToY(0);
                        }
                        p.getChildren().addAll(tt);
                    }
                }
                p.play();
                updateView();
            }
        });
    }

    private void updateView() {
        int randomblockX = modelGame.getCoordRandomBlockX();
        int randomblockY = modelGame.getCoordRandomBlockY();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value;
                if (modelGame.getPiece(i, j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i, j);
                }
                if (firstRun) {
                    midView.putBlockOnGrid(value, i, j, true);
                } else if (!(i == randomblockX && j == randomblockY)) {
                    midView.putBlockOnGrid(value, i, j, false);
                }
            }
        }
        if (!firstRun) {
            if (!isMovable()) {
                midView.putBlockOnGrid(2, randomblockX, randomblockY, true);
            } else {
                midView.putBlockOnGrid(2, randomblockX, randomblockY, false);
            }
        }
        this.firstRun = false;
    }

    /**
     * Zo laten staan AUB
     **/
    private void updateViewBlocks(Game.Direction direction) {
        modelGame.runGameCycle(direction);
        int score = modelGame.getScore().getScore();
        if (modelPlayerMananger.getCurrentPlayer().getBestScore() <= score) {
            topView.getLblBestScoreInput().setText(String.valueOf(score));
        }
        topView.getLblScoreInput().setText(String.valueOf(score));
        checkIfLostOrWin();
    }

    private void checkIfLostOrWin() {
        if (alreadyWon) return;
        if (modelGame.hasLost() || modelGame.hasWon()) {
            alreadyWon = true;
            modelPlayerMananger.saveInfoCurrentPlayer();
            ResultView resultView = new ResultView();
            new ResultPresenter(modelPlayerMananger, resultView, modelGame, view);
            view.setView(resultView);
        }
    }

    private boolean isMovable() {
        return modelGame.getLastMove() == null ||
                modelGame.getLastMove().equals(modelGame.getCurrentMove());
    }
}
