package be.kdg.thegame_2048.views.Result;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Game.GamePresenter;
import be.kdg.thegame_2048.views.Game.GameView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 21:57
 */
public class ResultPresenter {
    private PlayerManager modelPM;
    private Game modelGame;
    private ResultView view;
    private GameView gameView;

    public ResultPresenter(PlayerManager model, ResultView view, Game modelGame, GameView gameView) {
        this.modelPM = model;
        this.view = view;
        this.modelGame = modelGame;
        this.gameView = gameView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameView.layoutNodes();
            }
        });
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                new StartPresenter(modelPM, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameView.layoutNodes();
                gameView.getLblScoreInput().setText("0");
                modelGame = new Game(modelPM);
                new GamePresenter(modelGame, modelPM, gameView);
            }
        });
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Keyboard is blocked when resultpresenter is active
                event.consume();
            }
        });

    }
    private void updateView() {
        int score = modelGame.getScore().getScore();
        view.getLblScoreInput().setText(score + "");
        if (modelGame.hasLost()){
            view.setLblResult("You lose!");
        } else if (modelGame.hasWon()){
            view.setLblResult("You win!");
            view.addContinueBtn();
        }
    }
}
