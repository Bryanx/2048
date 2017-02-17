package be.kdg.thegame_2048.views.NewPlayer;

import be.kdg.thegame_2048.models.PlayerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14-02-17 14:24
 */
public class NewPlayerPresenter {
    private PlayerManager model;
    private NewPlayerView view;

    public NewPlayerPresenter(PlayerManager model, NewPlayerView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        view.getTfNewPlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    searchPlayer(event.getText());
                } else {
                    view.getNameExistsError().setVisible(false);
                }
            }
        });
    }

    private void searchPlayer(String name) {
        try {
            if (model.isUnique(name)) model.addPlayer(name);
        } catch (IllegalArgumentException iae) {
            view.getNameExistsError().setText(iae.getMessage());
            view.getNameExistsError().setVisible(true);
        }
        model.setPlayerNowPlaying(name);
    }
}
