package be.kdg.thegame_2048.views.HighScores;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Game.GamePresenter;
import be.kdg.thegame_2048.views.Game.GameView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 09:46
 */
public class HighScorePresenter {
    private PlayerManager modelPlayerManager;
    private Game modelGame;
    private HighScoreView view;

    public HighScorePresenter(Game modelGame, PlayerManager modelPlayerManager, HighScoreView view) {
        this.modelGame = modelGame;
        this.modelPlayerManager = modelPlayerManager;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView startView = new GameView();
                GamePresenter presenter = new GamePresenter(modelGame, modelPlayerManager, startView);
                view.getScene().setRoot(startView);
            }
        });

    }
    private void updateView() {
    }
}
