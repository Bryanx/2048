package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.DataReaderWriter;
import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Alert.AlertView;
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
import javafx.scene.control.Alert;
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
    private AnimationView animationView;
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
        this.animationView = new AnimationView(topView, midView, this);
        this.firstRun = true;
        this.addEventHandlers();
        topView.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
        //eenmalige updateview
        updateView();
    }

    //METHODEN
    private void addEventHandlers() {
        bottomView.getBtnRestart().setOnAction(event -> {
            alreadyWon = false;
            firstRun = true;
            modelPlayerMananger.saveInfoCurrentPlayer();
            modelGame = new Game(modelPlayerMananger);
            topView.getLblScoreInput().setText("0");
            updateView();
        });
        bottomView.getBtnUndo().setOnAction(event -> {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Are you sure you want to undo your last move?\n" +
//                    "Your score will no longer be added to the highscores.");
//            alert.setTitle("Undo");
//            alert.showAndWait();
            AlertView alert = new AlertView();
            alert.getLblMessage().setText("Are you sure you want to undo \nyour last move? " +
                    "Your score will no longer \nbe added to the highscores.");
            view.setView(alert);
        });
        bottomView.getBtnHighScores().setOnAction(event -> {
            modelPlayerMananger.saveInfoCurrentPlayer();
            HighScoreView hsView = new HighScoreView();
            new HighScorePresenter(modelGame, modelPlayerMananger, hsView);
            view.getScene().setRoot(hsView);
        });
        bottomView.getBtnExit().setOnAction(event -> {
            modelPlayerMananger.saveInfoCurrentPlayer();
            modelPlayerMananger.setCurrentPlayerToNull();
            StartView startView = new StartView();
            new StartPresenter(modelPlayerMananger, startView);
            view.getScene().setRoot(startView);
        });
        view.setOnKeyPressed(event -> {
            final int prevScore = modelGame.getScore().getScore();
            try {
                switch (event.getCode()) {
                    case DOWN:updateViewBlocks(Game.Direction.DOWN);
                        animationView.moveDown();break;
                    case UP:updateViewBlocks(Game.Direction.TOP);
                        animationView.moveUp();break;
                    case RIGHT:updateViewBlocks(Game.Direction.RIGHT);
                        animationView.moveRight();break;
                    case LEFT:updateViewBlocks(Game.Direction.LEFT);
                        animationView.moveLeft();break;
                    default:event.consume();
                }
            } catch (IllegalArgumentException e) {
                //TODO: IMPLEMENT PROPER ERROR HANDLING.
            }
            final int currScore = modelGame.getScore().getScore();
            if (currScore-prevScore > 0) {
                animationView.animateScore(currScore-prevScore);
            }

            animationView.getParallelTransition().play();
            modelPlayerMananger.setCurrentPlayerScore(currScore);
        });
        animationView.getParallelTransition().setOnFinished(event ->  {
                animationView.resetAnimation();
                updateView();
        });
    }

    private void updateView() {
        int randomblockX = modelGame.getCoordRandomBlockX();
        int randomblockY = modelGame.getCoordRandomBlockY();

        if (!firstRun && !isMovable()) {
            animationView.popIn(randomblockY, randomblockX);
            midView.putBlockOnGrid(2, randomblockX, randomblockY);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value;
                if (modelGame.getPiece(i, j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i, j);
                }
                if (firstRun) {
                    midView.putBlockOnGrid(value, i, j);
                    animationView.popIn(i, j);
                } else if (!(i == randomblockX && j == randomblockY)) {
                    midView.putBlockOnGrid(value, i, j);
                }
            }
        }
        this.firstRun = false;
    }

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

    boolean isMovable() {
        return modelGame.getLastMove() == null ||
                modelGame.getLastMove().equals(modelGame.getCurrentMove());
    }
}
