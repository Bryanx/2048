package be.kdg.thegame_2048.views.settings;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */
public class SettingsPresenter {
    private final PlayerManager model;
    private final SettingsView view;
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
