package be.kdg.thegame_2048.views.Lose;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Game.GamePresenter;
import be.kdg.thegame_2048.views.Game.GameView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 21:57
 */
public class LosePresenter {
    private PlayerManager modelPlayerManager;
    private LoseView view;

    public LosePresenter(PlayerManager model, LoseView view) {
        this.modelPlayerManager = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                new StartPresenter(modelPlayerManager, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                GameView gameView = new GameView();
//                Game gameModel = new Game(modelPlayerManager);
//                new GamePresenter(gameModel, modelPlayerManager, gameView);
//                view.getScene().setRoot(gameView);
            }
        });

    }
    private void updateView() {
    }
}
