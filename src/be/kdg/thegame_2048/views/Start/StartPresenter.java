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
import javafx.animation.ScaleTransition;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.util.Duration;

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
        //SCALE IN EFFECTS
        view.getBtnNewPlayer().setOnMouseEntered(event -> {fadeIn(view.getBtnNewPlayer());});
        view.getBtnExistingPlayer().setOnMouseEntered(event -> {fadeIn(view.getBtnExistingPlayer());});
        view.getBtnAbout().setOnMouseEntered(event -> {fadeIn(view.getBtnAbout());});
        view.getBtnSettings().setOnMouseEntered(event -> {fadeIn(view.getBtnSettings());});

        //SCALE OUT EFFECTS
        view.getBtnNewPlayer().setOnMouseExited(event -> {fadeOut(view.getBtnNewPlayer());});
        view.getBtnExistingPlayer().setOnMouseExited(event -> {fadeOut(view.getBtnExistingPlayer());});
        view.getBtnAbout().setOnMouseExited(event -> {fadeOut(view.getBtnAbout());});
        view.getBtnSettings().setOnMouseExited(event -> {fadeOut(view.getBtnSettings());});

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

    private void fadeIn(Button button) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.2);
        st.setToY(1.2);
        st.setDuration(Duration.millis(100));
        st.play();
    }

    private void fadeOut(Button button) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(1.2);
        st.setFromY(1.2);
        st.setToX(1);
        st.setToY(1);
        st.setDuration(Duration.millis(100));
        st.play();
    }

}
