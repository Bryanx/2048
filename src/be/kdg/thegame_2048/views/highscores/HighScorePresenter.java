package be.kdg.thegame_2048.views.highscores;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.Player;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.*;

/**
 * Links the high score view to the model classes.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 17-02-17 09:46
 */
public class HighScorePresenter {
    private final PlayerManager modelPM;
    private final Game modelGame;
    private final HighScoreView view;

    public HighScorePresenter(Game modelGame, PlayerManager modelPM, HighScoreView view) {
        this.modelGame = modelGame;
        this.modelPM = modelPM;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnGoBack().setOnAction(event -> updateScene());
    }

    /**
     * Returns the player to the GameView.
     **/
    private void updateScene() {
        GameView gameView = new GameView();
        GamePresenter gp = new GamePresenter(modelGame, modelPM, gameView);
        view.getScene().setRoot(gameView);

        if (modelGame.isPlayingUndo()) gp.disableUndoButton(true);
    }

    private void updateView() {
        List<Player> playerList = modelPM.getPlayerList();
        Collections.sort(playerList);

        //OPSPLITSEN IN 2 APART LISTS
        List<String> playernames = new ArrayList<>();
        List<Integer> playerBestScores = new ArrayList<>();
        for (Player p : playerList) {
            playernames.add(p.getName());
            playerBestScores.add(p.getBestScore());
        }

        String currentPlayer = modelPM.getCurrentPlayer().getName();

        view.updateHighScore(playernames, playerBestScores);
        view.highlightPlayer(currentPlayer);
    }
}
