package be.kdg.thegame_2048.views.ExistingPlayer;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.GameView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 15:12
 */
public class ExistingPlayerPresenter {
    //ATTRIBUTES
    private PlayerManager model;
    private ExistingPlayerView view;

    //CONSTRUCTORS
    public ExistingPlayerPresenter(PlayerManager model, ExistingPlayerView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.setPlayerNowPlayingToNull();
                StartView startView = new StartView();
                StartPresenter presenter = new StartPresenter(model, startView);
                view.getScene().setRoot(startView);
            }
        });
        view.getTfExistingPlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    searchPlayer(view.getTfExistingPlayer().getText());
                    GameView gameView = new GameView();
                } else {
                    view.getNameDoesntExistError().setVisible(false);
                }
            }
        });
    }

    private void searchPlayer(String name) {
        if (model.checkIfExists(name)) {
            view.getNameDoesntExistError().setText("Player bestaat!");
            view.getNameDoesntExistError().setVisible(true);
            model.setPlayerNowPlaying(name);
        } else {
            view.getNameDoesntExistError().setText("Player bestaat niet, maak een nieuwe aan!");
            view.getNameDoesntExistError().setVisible(true);
        }
    }
}
