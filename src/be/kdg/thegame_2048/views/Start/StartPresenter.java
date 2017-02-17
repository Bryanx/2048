package be.kdg.thegame_2048.views.Start;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.About.AboutPresenter;
import be.kdg.thegame_2048.views.About.AboutView;
import be.kdg.thegame_2048.views.ExistingPlayer.ExistingPlayerPresenter;
import be.kdg.thegame_2048.views.ExistingPlayer.ExistingPlayerView;
import be.kdg.thegame_2048.views.NewPlayer.NewPlayerPresenter;
import be.kdg.thegame_2048.views.NewPlayer.NewPlayerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    //ATTRIBUTES
    private PlayerManager model;
    private StartView view;


    //CONSTRUCTORS
    public StartPresenter(PlayerManager model, StartView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        view.getBtnNewPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewPlayerView playerView = new NewPlayerView();
                NewPlayerPresenter presenter = new NewPlayerPresenter(model, playerView);
                view.getScene().setRoot(playerView);
            }
        });
        view.getBtnExistingPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ExistingPlayerView playerView = new ExistingPlayerView();
                ExistingPlayerPresenter presenter = new ExistingPlayerPresenter(model, playerView);
                view.getScene().setRoot(playerView);
            }
        });
        view.getBtnAbout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutView aboutView = new AboutView();
                AboutPresenter presenter = new AboutPresenter(model, aboutView);
                view.getScene().setRoot(aboutView);
            }
        });
    }

}
