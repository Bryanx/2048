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
                    case DOWN:updateViewBlocks(Game.Direction.DOWN);break;
                    case UP:updateViewBlocks(Game.Direction.TOP);break;
                    case RIGHT:updateViewBlocks(Game.Direction.RIGHT);break;
                    case LEFT:updateViewBlocks(Game.Direction.LEFT);break;
                    default:event.consume();
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
        return midView.getBValue(x,y);
    }

    private ParallelTransition move(KeyCode dir) {
        int increment;
        boolean bool0, bool1, bool2;
        int x1, x2, x3, y1, y2, y3;
        ParallelTransition p = new ParallelTransition();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (dir.toString().equals("UP")) {
                    bool0 = y > 0; bool1 = y > 1; bool2 = y > 2;
                    increment = -110;
                    y1=-1;y2=-2;y3=-3;
                    x1=0;x2=0;x3=0;
                } else if (dir.toString().equals("DOWN")) {
                    bool0 = y < 3; bool1 = y < 2; bool2 = y < 1;
                    increment = 110;
                    y1=1;y2=2;y3=3;
                    x1=0;x2=0;x3=0;
                } else if (dir.toString().equals("LEFT")){
                    bool0 = x > 0; bool1 = x > 1; bool2 = x > 2;
                    increment = -110;
                    y1=0;y2=0;y3=0;
                    x1=-1;x2=-2;x3=-3;
                } else {
                    bool0 = x < 3; bool1 = x < 2; bool2 = x < 1;
                    increment = 110;
                    y1=0;y2=0;y3=0;
                    x1=1;x2=2;x3=3;
                }
                TranslateTransition tt = new TranslateTransition(Duration.millis(250), midView.getBlock(x, y));
                int thisBlock = BlockValue(x, y);
                if (!isMovable() && thisBlock != 0) {
                    if (bool0 && (BlockValue(x + x1, y + y1) == 0 || BlockValue(x + x1, y + y1) == thisBlock)) {
                        if (bool1 && (BlockValue(x + x2, y + y2) == 0 || BlockValue(x + x2, y + y2) == thisBlock)) {
                            if (bool2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == thisBlock)) {
                                if (BlockValue(x + x1, y + y1) == thisBlock && BlockValue(x + x2, y + y2) == BlockValue(x + x1, y + y1)) {
                                    increment *= 2;
                                } else {
                                    increment *= 3;
                                }
                            } else if (BlockValue(x + x1, y + y1) == thisBlock && BlockValue(x + x2, y + y2) == thisBlock) {
                                // do nothing
                            } else {
                                increment *= 2;
                            }
                        } else if (bool2 && (BlockValue(x + x3, y + y3) == 0 || BlockValue(x + x3, y + y3) == BlockValue(x + x2, y + y2))) {
                            increment *= 2;
                        }
                    } else if (bool1 && (BlockValue(x + x2, y + y2) == BlockValue(x + x1, y + y1) || BlockValue(x + x2, y + y2) == 0)) {
                        if (bool2 && (BlockValue(x + x3, y + y3) == BlockValue(x + x1, y + y1) || BlockValue(x + x2, y + y2) == 0)) {
                            if (BlockValue(x + x1, y + y1) != thisBlock) {
                                if (BlockValue(x + x3, y + y3) == 0) increment *= 3;
                            } else {
                                increment *= 2;
                            }
                        } else if (bool2 && (BlockValue(x + x3, y + y3) == 0)) {
                            increment *= 2;
                        }
                    } else if (bool2 && (BlockValue(x + x3, y + y3) == BlockValue(x + x2, y + y2) || BlockValue(x + x3, y + y3) == 0)) {
                        // do nothing
                    } else {
                        increment = 0;
                    }


                    if (dir.toString().equals("UP") || dir.toString().equals("DOWN")) {
                        tt.setToY(increment);
                    } else {
                        tt.setToX(increment);
                    }
                    midView.getBlock(x, y).toFront();
                }
                p.getChildren().addAll(tt);
            }

        }
        return p;
    }

    private void moveAnimation(KeyCode dir) {
        ParallelTransition p = new ParallelTransition(move(dir));
        p.play();
        p.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ParallelTransition p = new ParallelTransition();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        TranslateTransition tt = new TranslateTransition(Duration.millis(0.1), midView.getBlock(i,j));
                        if (midView.getBlock(i,j).getValue() != 0) {
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
