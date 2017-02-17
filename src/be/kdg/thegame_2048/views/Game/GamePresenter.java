package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.views.HighScores.HighScorePresenter;
import be.kdg.thegame_2048.views.HighScores.HighScoreView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game model;
    private GameView view;

    //CONSTRUCTORS
    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    //METHODEN
    private void addEventHandlers() {
        view.getBtnHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HighScoreView hsView = new HighScoreView();
                new HighScorePresenter(model,hsView);
                view.getScene().setRoot(hsView);
            }
        });
    }

}
