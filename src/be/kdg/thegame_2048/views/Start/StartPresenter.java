package be.kdg.thegame_2048.views.Start;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.About.AboutPresenter;
import be.kdg.thegame_2048.views.About.AboutView;
import be.kdg.thegame_2048.views.ExistingPlayer.ExistingPlayerPresenter;
import be.kdg.thegame_2048.views.ExistingPlayer.ExistingPlayerView;
import be.kdg.thegame_2048.views.NewPlayer.NewPlayerPresenter;
import be.kdg.thegame_2048.views.NewPlayer.NewPlayerView;
import be.kdg.thegame_2048.views.Settings.SettingsPresenter;
import be.kdg.thegame_2048.views.Settings.SettingsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;

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
        view.getBtnNewPlayer().setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                new StartAnimations();
            }
        });
        view.getBtnNewPlayer().setOnAction(event -> {
            NewPlayerView playerView = new NewPlayerView();
            new NewPlayerPresenter(model, playerView);
            view.getScene().setRoot(playerView);
        });
        view.getBtnExistingPlayer().setOnAction(event -> {
            ExistingPlayerView playerView = new ExistingPlayerView();
            new ExistingPlayerPresenter(model, playerView);
            view.getScene().setRoot(playerView);
        });
        view.getBtnAbout().setOnAction(event -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(model, aboutView);
            view.getScene().setRoot(aboutView);
        });
        view.getBtnSettings().setOnAction(event -> {
            SettingsView settingsView = new SettingsView();
            new SettingsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
        });
    }

}
