package be.kdg.thegame_2048.views;

import be.kdg.thegame_2048.models.PlayerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder
 * @version 1.0 14-02-17 14:24
 */
public class NewPlayerPresenter {
    private PlayerManager model;
    private NewPlayerView view;
    private Stage prevStage;

    public NewPlayerPresenter(PlayerManager model, NewPlayerView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnToStart();
            }
        });
        view.getTfNewPlayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("hello");
            }
        });
    }

    private void returnToStart() {
        StartView view2 = new StartView();
        Scene scene2 = new Scene(view2);
        Stage stage2 = new Stage();
        scene2.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        stage2.setScene(scene2);
        stage2.setMinHeight(750+20);
        stage2.setMinWidth(550);
        stage2.setTitle("2048");
        StartPresenter.prevStage.close();
        stage2.show();
    }
}
