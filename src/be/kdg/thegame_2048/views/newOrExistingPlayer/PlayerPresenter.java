package be.kdg.thegame_2048.views.newOrExistingPlayer;

import be.kdg.thegame_2048.models.DataReaderWriter;
import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.scene.input.KeyCode;

/**
 * Links the new/existing player view to the model classes.
 * If a player is new it checks if the input is correct.
 * If a player is not new it searches in the database for the existing player.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 14-02-17 14:24
 */
public class PlayerPresenter {
    private static final int MIN_PLAYERNAME_LENGTH = 2;
    private static final int MAX_PLAYERNAME_LENGTH = 15;
    private static final String ERROR_MESSAGE = "Name has to be between " + MIN_PLAYERNAME_LENGTH +
            " and " + MAX_PLAYERNAME_LENGTH + " characters long";
    private final PlayerManager model;
    private final PlayerView view;
    private final boolean isNewPlayer;

    public PlayerPresenter(PlayerManager model, PlayerView view, boolean isNewPlayer) {
        this.model = model;
        this.view = view;
        this.isNewPlayer = isNewPlayer;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getTfPlayerName().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (isNewPlayer) {
                    checkInput(view.getTfPlayerName().getText());
                } else {
                    searchPlayer(view.getTfPlayerName().getText());
                }
            } else {
                view.getLblInputError().setVisible(false);
            }
        });
        view.getBtnGoBack().setOnAction(event -> {
            model.setCurrentPlayerToNull();
            StartView startView = new StartView();
            new StartPresenter(model, startView);
            view.getScene().setRoot(startView);
        });
    }

    private void updateView() {
        if (isNewPlayer) {
            view.getLblMessage().setText("What's your name?");
        } else {
            view.getLblMessage().setText("Enter your existing name");
        }
    }

    /**
     * Searches for a player name that is the same as the String parameter.
     * If the player exists, that player is made active.
     * If it doesn't exist the player will receive an error message.
     * This method is only used for existing players.
     **/
    private void searchPlayer(String name) {
        if (model.checkIfExists(name)) {
            model.setCurrentPlayer(name);
            updateScene();
        } else {
            view.getLblInputError().setText("Player doesn't exist.");
            view.getLblInputError().setVisible(true);
            IllegalArgumentException iea = new IllegalArgumentException("Name was already used.");
            DataReaderWriter.writeToLog(iea.getMessage());
        }
    }

    /**
     * Checks the input for errors.
     * Gives an error to the player if necessary.
     * This method is only used for new players.
     **/
    private void checkInput(String name) {
        if (model.checkIfExists(name)) {
            view.getLblInputError().setText("Name already exists.");
            view.getLblInputError().setVisible(true);

            IllegalArgumentException iae = new IllegalArgumentException("Name already exists.");
            DataReaderWriter.writeToLog(iae.getMessage());
        } else if (name.length() < MIN_PLAYERNAME_LENGTH || name.length() > MAX_PLAYERNAME_LENGTH) {
            view.getLblInputError().setText(ERROR_MESSAGE);
            view.getLblInputError().setVisible(true);

            IllegalArgumentException iae = new IllegalArgumentException("Name was to short or to long.");
            DataReaderWriter.writeToLog(iae.getMessage());
        } else {
            model.addPlayer(name);
            model.setCurrentPlayer(name);
            updateScene();
        }
    }

    /**
     * Switches scene's to the GameView.
     * Should only be used when input is ok.
     **/
    private void updateScene() {
        Game gameModel = new Game();
        GameView gameView = new GameView();
        new GamePresenter(gameModel, model, gameView);
        view.getScene().setRoot(gameView);
    }
}
