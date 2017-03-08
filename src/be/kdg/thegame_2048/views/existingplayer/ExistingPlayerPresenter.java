package be.kdg.thegame_2048.views.existingplayer;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.scene.input.KeyCode;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14/02/2017 15:12
 */
public class ExistingPlayerPresenter {
    //ATTRIBUTES
    private final PlayerManager model;
    private final ExistingPlayerView view;

    //CONSTRUCTORS
    public ExistingPlayerPresenter(PlayerManager model, ExistingPlayerView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        view.getBtnGoBack().setOnAction(event -> {
            model.setCurrentPlayerToNull();
            StartView startView = new StartView();
            StartPresenter presenter = new StartPresenter(model, startView);
            view.getScene().setRoot(startView);
        });
        view.getTfExistingPlayer().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                searchPlayer(view.getTfExistingPlayer().getText());
            } else {
                view.getNameDoesntExistError().setVisible(false);
            }
        });
    }

    private void searchPlayer(String name) {
        if (model.checkIfExists(name)) {
            model.setCurrentPlayer(name);
            updateScene();
        } else {
            new IllegalArgumentException("Name was already used.");
            //TODO: IMPLEMENT PROPER ERROR HANDLING.
            view.getNameDoesntExistError().setText("Player doesn't exist.");
            view.getNameDoesntExistError().setVisible(true);
        }
    }

    private void updateScene() {
        Game gameModel = new Game(model);
        GameView gameView = new GameView();
        new GamePresenter(gameModel, model, gameView);
        view.getScene().setRoot(gameView);
    }
}
