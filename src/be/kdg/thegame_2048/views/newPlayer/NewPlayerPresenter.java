package be.kdg.thegame_2048.views.newPlayer;

import be.kdg.thegame_2048.models.DataReaderWriter;
import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.scene.input.KeyCode;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14-02-17 14:24
 */
public class NewPlayerPresenter {
    private final PlayerManager model;
    private final NewPlayerView view;

    public NewPlayerPresenter(PlayerManager model, NewPlayerView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnGoBack().setOnAction(event -> {
            model.setCurrentPlayerToNull();
            StartView startView = new StartView();
            StartPresenter presenter = new StartPresenter(model, startView);
            view.getScene().setRoot(startView);
        });
        view.getTfNewPlayer().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println(model.checkIfExists("hallo"));
                checkInput(view.getTfNewPlayer().getText());
            } else {
                view.getLblInputError().setVisible(false);
            }
        });
    }

    private void checkInput(String name) {
        if (model.checkIfExists(name)) {
            view.getLblInputError().setText("Name already exists");
            view.getLblInputError().setVisible(true);
        } else if (name.length() < 3 || name.length() > 15) {
            view.getLblInputError().setText("Name has to be between 3 and 15 characters long");
            view.getLblInputError().setVisible(true);
            IllegalArgumentException iae = new IllegalArgumentException("Name was to short or to long.");
            DataReaderWriter.writeToLog(iae.getMessage());
        } else {
            model.addPlayer(name);
            model.setCurrentPlayer(name);
            updateScene();
        }
    }

    private void updateScene() {
        GameView gameView = new GameView();
        Game gameModel = new Game();
        new GamePresenter(gameModel, model, gameView);
        view.getScene().setRoot(gameView);
    }
}
