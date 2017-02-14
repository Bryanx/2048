package be.kdg.thegame_2048.views;

import be.kdg.thegame_2048.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

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
                setSceneToNewPlayer();
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
        NewPlayerView view = new NewPlayerView();
        Scene scene = new Scene(view);
        Main main = new Main();
        main.setScene(scene);
    }
}
