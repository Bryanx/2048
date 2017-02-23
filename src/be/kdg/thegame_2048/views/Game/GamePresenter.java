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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.xml.soap.Text;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game modelGame;
    private PlayerManager modelPlayerMananger;
    private GameView view;
    private boolean alreadyWon;
    private boolean firstRun;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPlayerMananger = modelPlayerManager;
        this.modelPlayerMananger.setCurrentPlayerScore(modelGame.getScore().getScore());
        this.view = view;
        this.firstRun = true;
        this.addEventHandlers();
        view.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
        //eenmalige updateview
        updateView(KeyCode.A);
    }

    //METHODEN
    private void addEventHandlers() {
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelPlayerMananger.saveInfoCurrentPlayer();
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(modelGame, modelPlayerMananger, hsView);
                view.getScene().setRoot(hsView);
            }
        });
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
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
                modelPlayerMananger.setCurrentPlayerScore(modelGame.getScore().getScore());
//                if (firstRun) {
                    updateView(event.getCode());
//                } else {
//                    move(event.getCode());
//                }
            }
        });
        view.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alreadyWon = false;
                firstRun = true;
                modelPlayerMananger.saveInfoCurrentPlayer();
                modelGame = new Game(modelPlayerMananger);
                view.getLblScoreInput().setText("0");
                updateView(KeyCode.A);
            }
        });
    }

    void move(KeyCode dir) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), view.getSectionGrid());
        switch (dir.toString()) {
            case ("UP"):tt.setToY(-110);break;
            case ("DOWN"):tt.setToY(110);break;
            case ("RIGHT"):tt.setToX(110);break;
            case ("LEFT"):tt.setToX(-110);break;
        }
        tt.play();
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.resetGrid();
                updateView(dir);
            }
        });
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
                    view.putBlockOnGrid(value, i, j, true);
                } else if (!(i == randomblockX && j == randomblockY)) {
                    view.putBlockOnGrid(value, i, j, false);
                }
            }
        }
        if (!firstRun) {
            if (!modelGame.getLastMove().equals(modelGame.getCurrentMove())) {
                view.putBlockOnGrid(2, randomblockX, randomblockY, true);
            } else {
                view.putBlockOnGrid(2, randomblockX, randomblockY, false);
            }
        }
        this.firstRun = false;
    }

    private void updateViewBlocks(Game.Direction direction) {
        modelGame.runGameCycle(direction);
        int score = modelGame.getScore().getScore();
        if (Integer.parseInt(view.getLblScoreInput().getText()) >= Integer.parseInt(view.getLblBestScoreInput().getText())) {
            view.getLblBestScoreInput().setText(score + "");
        }
        view.getLblScoreInput().setText(score + "");
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
}
