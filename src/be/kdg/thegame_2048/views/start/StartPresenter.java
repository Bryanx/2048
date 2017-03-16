package be.kdg.thegame_2048.views.start;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.about.AboutPresenter;
import be.kdg.thegame_2048.views.about.AboutView;
import be.kdg.thegame_2048.views.credits.CreditsPresenter;
import be.kdg.thegame_2048.views.credits.CreditsView;
import be.kdg.thegame_2048.views.newOrExistingPlayer.PlayerPresenter;
import be.kdg.thegame_2048.views.newOrExistingPlayer.PlayerView;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Links the start/home view classes to the model classes.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    private static final Duration SCALE_DURATION = Duration.millis(100);
    private static final double BTN_MAX_SCALE = 1.15;
    private final PlayerManager model;
    private final StartView view;

    public StartPresenter(PlayerManager model, StartView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        //SCALE IN and SCALE OUT effects are applied to the buttons
        for (Button button : view.getAllButtons()) {
            button.setOnMouseEntered(event -> scale(button, 1,BTN_MAX_SCALE));
            button.setOnMouseExited(event -> scale(button, BTN_MAX_SCALE, 1));
        }

        view.getBtnNewPlayer().setOnAction(event -> {
            PlayerView playerView = new PlayerView();
            new PlayerPresenter(model, playerView, true);
            view.getScene().setRoot(playerView);
        });
        view.getBtnExistingPlayer().setOnAction(event -> {
            PlayerView playerView = new PlayerView();
            new PlayerPresenter(model, playerView, false);
            view.getScene().setRoot(playerView);
        });
        view.getBtnAbout().setOnAction(event -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(model, aboutView);
            view.getScene().setRoot(aboutView);
        });
        view.getBtnCredits().setOnAction(event -> {
            CreditsView settingsView = new CreditsView();
            new CreditsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
        });
    }

    private void scale(Button button, double fromXY, double toXY) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(fromXY);
        st.setFromY(fromXY);
        st.setToX(toXY);
        st.setToY(toXY);
        st.setDuration(SCALE_DURATION);
        st.play();
    }
}
