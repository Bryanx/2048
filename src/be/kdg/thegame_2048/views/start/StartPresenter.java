package be.kdg.thegame_2048.views.start;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.about.AboutPresenter;
import be.kdg.thegame_2048.views.about.AboutView;
import be.kdg.thegame_2048.views.existingplayer.ExistingPlayerPresenter;
import be.kdg.thegame_2048.views.existingplayer.ExistingPlayerView;
import be.kdg.thegame_2048.views.newPlayer.NewPlayerPresenter;
import be.kdg.thegame_2048.views.newPlayer.NewPlayerView;
import be.kdg.thegame_2048.views.credits.CreditsPresenter;
import be.kdg.thegame_2048.views.credits.CreditsView;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    //ATTRIBUTES
    private final PlayerManager model;
    private final StartView view;


    //CONSTRUCTORS
    public StartPresenter(PlayerManager model, StartView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        //FADE IN EFFECTS
        view.getBtnNewPlayer().setOnMouseEntered(event -> scaleIn(view.getBtnNewPlayer()));
        view.getBtnExistingPlayer().setOnMouseEntered(event -> scaleIn(view.getBtnExistingPlayer()));
        view.getBtnAbout().setOnMouseEntered(event -> scaleIn(view.getBtnAbout()));
        view.getBtnSettings().setOnMouseEntered(event -> scaleIn(view.getBtnSettings()));

        //SCALE OUT EFFECTS
        view.getBtnNewPlayer().setOnMouseExited(event -> scaleOut(view.getBtnNewPlayer()));
        view.getBtnExistingPlayer().setOnMouseExited(event -> scaleOut(view.getBtnExistingPlayer()));
        view.getBtnAbout().setOnMouseExited(event -> scaleOut(view.getBtnAbout()));
        view.getBtnSettings().setOnMouseExited(event -> scaleOut(view.getBtnSettings()));

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
            CreditsView settingsView = new CreditsView();
            new CreditsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
        });
    }

    /**
     * Makes a specific button grow in size.
     **/
    private void scaleIn(Button button) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.15);
        st.setToY(1.15);
        st.setDuration(Duration.millis(100));
        st.play();
    }

    /**
     * Makes a specific button decrease in size.
     **/
    private void scaleOut(Button button) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(1.15);
        st.setFromY(1.15);
        st.setToX(1);
        st.setToY(1);
        st.setDuration(Duration.millis(100));
        st.play();
    }

}
