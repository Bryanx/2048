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
    private Game model;
    private HighScoreView view;

    public HighScorePresenter(Game model, HighScoreView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView startView = new GameView();
                GamePresenter presenter = new GamePresenter(model, startView);
                view.getScene().setRoot(startView);
            }
        });

    }
    private void updateView() {
    }
}
