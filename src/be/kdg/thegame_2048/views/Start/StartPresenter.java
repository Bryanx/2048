package be.kdg.thegame_2048.views.Start;

import be.kdg.thegame_2048.views.NewPlayer.NewPlayerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    //ATTRIBUTES
    private StartView view;


    //CONSTRUCTORS
    public StartPresenter(StartView view) {
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        view.getBtnNewPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewPlayerView view = new NewPlayerView();


            }
        });
        view.getBtnExistingPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                setSceneToExistingPlayer();
            }
        });
    }

}
