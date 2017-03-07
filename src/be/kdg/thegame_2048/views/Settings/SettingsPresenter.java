package be.kdg.thegame_2048.views.Settings;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */
public class SettingsPresenter {
    private PlayerManager model;
    private SettingsView view;
    private int currentIndex;

    public SettingsPresenter(PlayerManager model, SettingsView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnGoBack().setOnAction(event -> {
            StartView startView = new StartView();
            new StartPresenter(model, startView);
            view.getScene().setRoot(startView);
        });
    }

    private void updateView() {
    }
}
