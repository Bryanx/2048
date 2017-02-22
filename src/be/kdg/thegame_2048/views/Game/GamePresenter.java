package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import be.kdg.thegame_2048.views.Result.ResultPresenter;
import be.kdg.thegame_2048.views.Result.ResultView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game modelGame;
    private PlayerManager modelPM;
    private GameView view;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPM = modelPlayerManager;
        this.view = view;
        this.addEventHandlers();
        view.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
        //eenmalige updateview
        updateView();
    }

    //METHODEN
    private void addEventHandlers() {
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveInfo();
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(modelGame, modelPM, hsView);
                view.getScene().setRoot(hsView);
            }
        });
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveInfo();
                modelPM.setCurrentPlayerToNull();
                StartView startView = new StartView();
                new StartPresenter(modelPM, startView);
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
                updateView();
            }
        });
        view.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveInfo();
                modelGame = new Game(modelPM);
                view.getLblScoreInput().setText("0");
                updateView();
            }
        });

    }

    private void updateView() {
        view.getSectionGrid().getChildren().clear();
        view.resetGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value;
                if (modelGame.getPiece(i, j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i, j);
                }
                view.setBlock(value, i, j);
            }
        }
    }

    private void saveInfo() {
        int bestScore = modelPM.getCurrentPlayer().getBestScore();
        int currentScore = modelGame.getScore().getScore();

        if (currentScore > bestScore) {
            modelPM.getCurrentPlayer().setBestScore(currentScore);
        }
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
        if (modelGame.hasLost() || modelGame.hasWon()) {
            saveInfo();
            ResultView resultView = new ResultView();
            new ResultPresenter(modelPM, resultView, modelGame, view);
            view.setView(resultView);
        }
    }
}
