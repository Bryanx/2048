package be.kdg.thegame_2048.views;

import be.kdg.thegame_2048.models.PlayerManager;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Jarne Van Aerde
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
        view.getTfExistingPlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    searchPlayer(event.getText());
                } else {
                    view.getNameDoesntExistError().setVisible(false);
                }
            }
        });
    }

    private void searchPlayer(String name) {
        try {
            model.checkIfExists(name);
        } catch (IllegalArgumentException iae) {
            view.getNameDoesntExistError().setText(iae.getMessage());
            view.getNameDoesntExistError().setVisible(true);
        }
        model.setPlayerNowPlaying(name);
    }
}
