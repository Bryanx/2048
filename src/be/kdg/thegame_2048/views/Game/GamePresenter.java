package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.SwipeEvent;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game modelGame;
    private PlayerManager playerManager;
    private GameView view;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager playerManager, GameView view) {
        this.modelGame = modelGame;
        this.playerManager = playerManager;
        this.view = view;
        this.addEventHandlers();
    }

    //METHODEN
    private void addEventHandlers() {
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(modelGame, playerManager, hsView);
                view.getScene().setRoot(hsView);
            }
        });
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                StartPresenter presenter = new StartPresenter(playerManager, startView);
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

    private void updateViewBlocksLeft() {
        System.out.println("Naar links!");
    }

    private void updateViewBlocksRight() {
        System.out.println("Naar rechts!");
    }

    private void updateViewBlocksTop() {
        System.out.println("Naar boven!");
    }

    private void updateViewBlocksDown() {
        System.out.println("Naar beneden!");
    }
}
