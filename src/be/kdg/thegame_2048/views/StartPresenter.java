package be.kdg.thegame_2048.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    //ATTRIBUTES
    private StartView view;

    //CONSTRUCTORS
    public StartPresenter(StartView view) {
        this.view = view;
    }

    //METHODS
    private void addEventHandles() {
        view.getBtnNewPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        view.getBtnExistingPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSceneToExistingPlayer();
            }
        });
    }

    private void setSceneToExistingPlayer() {
    }

    private void setSceneToNewPlayer() {

    }
}
