package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.Lose.LosePresenter;
import be.kdg.thegame_2048.views.Lose.LoseView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import be.kdg.thegame_2048.views.WinView;
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
        view.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
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
                System.out.println(modelPlayerManager.getCurrentPlayer().getName());
                saveInfo();
                StartView startView = new StartView();
                StartPresenter presenter = new StartPresenter(modelPlayerManager, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN : updateViewBlocks(Game.Direction.DOWN); break;
                    case UP : updateViewBlocks(Game.Direction.TOP); break;
                    case RIGHT : updateViewBlocks(Game.Direction.RIGHT); break;
                    case LEFT : updateViewBlocks(Game.Direction.LEFT); break;
                    default : event.consume();
                }
                updateView(event.getCode());
        }});
    }

    private void updateView(KeyCode dir) {
        view.getSectionGrid().getChildren().clear();
        view.resetGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value;
                if (modelGame.getPiece(i,j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i,j);
                }
                view.setBlock(value,i,j, dir);
            }
        }
    }

    private void saveInfo() {
        modelPlayerManager.getCurrentPlayer().setBestScore(modelGame.getScore().getScore());
        modelPlayerManager.setCurrentPlayerToNull();
    }

    private void updateViewBlocks(Game.Direction direction) {
        modelGame.runGameCycle(direction);
        if (Integer.parseInt(view.getLblScoreInput().getText()) >= Integer.parseInt(view.getLblBestScoreInput().getText())) {
            view.getLblBestScoreInput().setText(String.valueOf(modelGame.getScore().getScore()));
        }
        view.getLblScoreInput().setText(String.valueOf(modelGame.getScore().getScore()));
        checkIfLostOrWin();
    }

    private void checkIfLostOrWin() {
        if (modelGame.hasLost()) {
            updateSceneToLost();
        } else if (modelGame.hasWon()) {
            updateSceneToWin();
        }
    }

    private void updateSceneToLost() {
        //Bij saveInfo soms NullPointerException... hoe?
        saveInfo();
        LoseView loseView = new LoseView();
        //TODO:
        new LosePresenter(modelPlayerManager,loseView);
        view.getScene().setRoot(loseView);
    }

    private void updateSceneToWin() {
        WinView winView = new WinView();
        view.getScene().setRoot(winView);
    }
}
