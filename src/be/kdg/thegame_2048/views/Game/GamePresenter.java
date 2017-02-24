package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.Result.ResultPresenter;
import be.kdg.thegame_2048.views.Result.ResultView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
        updateView(KeyCode.A);

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
                updateView(event.getCode());
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
                updateView(KeyCode.A);
            }
        });
    }

    void moveAnimation(KeyCode dir) {
        ObservableList<Node> children = midView.getSectionGrid().getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (midView.getBlockValue(i) > 0) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(500), children.get(i));
                switch (dir.toString()) {
                    case ("UP"):
                        if (i > 4) {
                            tt.setToY(-110);
                        }
                        break;
                    case ("DOWN"):
                        if (i < 11) {
                            tt.setToY(110);
                        }
                        break;
                    case ("RIGHT"):
                        tt.setToX(110);
                        break;
                    case ("LEFT"):
                        tt.setToX(-110);
                        break;
                }
                tt.play();
                tt.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        updateView(dir);
                    }
                });
            }
        }

    }

    private void updateView(KeyCode dir) {
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

//    public Node getGridNode(int row, int column) {
//        Node result = null;
//        ObservableList<Node> childrens = view.getSectionGrid().getChildren();
//
//        for (Node node : childrens) {
//            if(view.getSectionGrid().getRowIndex(node) == row && view.getSectionGrid().getColumnIndex(node) == column) {
//                result = node;
//                break;
//            }
//        }
//
//        return result;
//    }

    private boolean isMovable() {
        if (modelGame.getLastMove() == null) {
            return true;
        } else {
            return modelGame.getLastMove().equals(modelGame.getCurrentMove());
        }
    }
}
