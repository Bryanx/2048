package be.kdg.thegame_2048.views.HighScores;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.Player;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Game.GamePresenter;
import be.kdg.thegame_2048.views.Game.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.*;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 09:46
 */
public class HighScorePresenter {
    private PlayerManager modelPM;
    private Game modelGame;
    private HighScoreView view;

    public HighScorePresenter(Game modelGame, PlayerManager modelPM, HighScoreView view) {
        this.modelGame = modelGame;
        this.modelPM = modelPM;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getGoBack().setOnAction(event -> { updateScene();});
    }

    private void updateScene() {
        GameView gameView = new GameView();
        gameView.getLblScoreInput().setText(String.valueOf(modelGame.getScore()));
        gameView.getLblBestScoreInput().setText(String.valueOf(modelPM.getCurrentPlayer().getBestScore()));
        new GamePresenter(modelGame, modelPM, gameView);
        if (modelGame.getScore().getScore() >= modelPM.getCurrentPlayer().getBestScore()) {
            gameView.getLblBestScoreInput().setText(String.valueOf(modelGame.getScore().getScore()));
        }
        view.getScene().setRoot(gameView);
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
        System.out.println(playernames.toString());
        System.out.println(playerBestScores.toString());

        String currentPlayer = modelPM.getCurrentPlayer().getName();

        view.updateHighScore(playernames, playerBestScores);
        view.highlightPlayer(currentPlayer);
    }
}
