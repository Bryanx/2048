package be.kdg.thegame_2048.views.result;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.event.Event;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 21:57
 */
public class ResultPresenter {
    private final PlayerManager modelPM;
    private final ResultView view;
    private final GameView gameView;
    private Game modelGame;

    public ResultPresenter(PlayerManager model, ResultView view, Game modelGame, GameView gameView) {
        this.modelPM = model;
        this.view = view;
        this.modelGame = modelGame;
        this.gameView = gameView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnContinue().setOnAction(event -> gameView.layoutNodes());
        view.getBtnExit().setOnAction(event -> {
            modelPM.setCurrentPlayerToNull();
            StartView startView = new StartView();
            new StartPresenter(modelPM, startView);
            view.getScene().setRoot(startView);
        });
        view.getBtnRestart().setOnAction(event -> {
            gameView.layoutNodes();
            gameView.getLblScoreInput().setText("0");
            modelGame = new Game(modelPM);
            new GamePresenter(modelGame, modelPM, gameView);
        });
        //Keyboard is blocked when resultpresenter is active
        view.setOnKeyPressed(Event::consume);

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
