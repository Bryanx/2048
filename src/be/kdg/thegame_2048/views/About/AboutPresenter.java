package be.kdg.thegame_2048.views.About;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 12:17
 */
public class AboutPresenter {
    private PlayerManager model;
    private AboutView view;

    public AboutPresenter(PlayerManager model, AboutView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                new StartPresenter(model, startView);
                view.getScene().setRoot(startView);
            }
        });

    }
    private void updateView() {
    }
}