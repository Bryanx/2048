package be.kdg.thegame_2048.views;

import be.kdg.thegame_2048.models.PlayerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14-02-17 14:24
 */
public class NewPlayerPresenter {
    private PlayerManager model;
    private NewPlayerView view;

    public NewPlayerPresenter(PlayerManager model, NewPlayerView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("text");
            }
        });
        view.getTfNewPlayer().setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String letter = event.getCharacter();

                if (letter.contains("asd")) event.consume();
                System.out.println("hello");
            }
        });
    }
}
