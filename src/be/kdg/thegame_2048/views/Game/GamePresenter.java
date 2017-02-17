package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.LoseView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game modelGame;
    private PlayerManager modelPlayerManager;
    private GameView view;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPlayerManager = modelPlayerManager;
        this.view = view;
        this.addEventHandlers();
    }

    //METHODEN
    private void addEventHandlers() {
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(modelGame, modelPlayerManager, hsView);
                view.getScene().setRoot(hsView);
            }
        });
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                StartPresenter presenter = new StartPresenter(modelPlayerManager, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.DOWN)) {
                    updateViewBlocksDown();
                } else if (event.getCode().equals(KeyCode.UP)) {
                    updateViewBlocksTop();
                } else if (event.getCode().equals(KeyCode.RIGHT)) {
                    updateViewBlocksRight();
                } else if (event.getCode().equals(KeyCode.LEFT)) {
                    updateViewBlocksLeft();
                } else {
                    event.consume();
                }
            }
        });

    }

    private void checkIfLostOrWin() {
        if (modelGame.hasLost()) {
            updateSceneToLost();
        } else if (modelGame.hasWon()) {
            updateSceneToWin();
        }
    }

    private void updateViewBlocksLeft() {
        modelGame.runGameCycle(Game.Direction.LEFT);
        checkIfLostOrWin();
    }

    private void updateViewBlocksRight() {
        modelGame.runGameCycle(Game.Direction.RIGHT);
        checkIfLostOrWin();
    }

    private void updateViewBlocksTop() {
        modelGame.runGameCycle(Game.Direction.TOP);
        checkIfLostOrWin();
    }

    private void updateViewBlocksDown() {
        modelGame.runGameCycle(Game.Direction.DOWN);
        checkIfLostOrWin();
    }

    private void updateSceneToLost() {
        LoseView loseView = new LoseView();
        view.getScene().setRoot(loseView);
        modelPlayerManager.getPlayerNowPlaying().setBestScore(modelGame.getScore().getScore());
        modelPlayerManager.setPlayerNowPlayingToNull();
    }

    private void updateSceneToWin() {
        
    }
}
