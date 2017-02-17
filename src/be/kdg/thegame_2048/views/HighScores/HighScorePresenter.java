package be.kdg.thegame_2048.views.HighScores;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.Player;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Game.GamePresenter;
import be.kdg.thegame_2048.views.Game.GameView;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.*;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 09:46
 */
public class HighScorePresenter {
    private PlayerManager modelPlayerManager;
    private Game modelGame;
    private HighScoreView view;

    public HighScorePresenter(Game modelGame, PlayerManager modelPlayerManager, HighScoreView view) {
        this.modelGame = modelGame;
        this.modelPlayerManager = modelPlayerManager;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getGoBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateScene();
            }
        });

    }

    private void updateScene() {
        GameView gameView = new GameView();
        gameView.getLblScoreInput().setText(String.valueOf(modelGame.getScore()));
        gameView.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getPlayerNowPlaying().getBestScore()));
        GamePresenter presenter = new GamePresenter(modelGame, modelPlayerManager, gameView);
        view.getScene().setRoot(gameView);
    }

    private void updateView() {
        Map<Integer, String> playerMap = new HashMap<>();
        List<Player> playerList = modelPlayerManager.getPlayerList();
        Collections.sort(playerList);

        //OPSPLITSEN IN 2 APART LISTS
        List<String> playernames = new ArrayList<>();
        List<Integer> playerBestScores = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {
            playernames.add(playerList.get(i).getName());
            playerBestScores.add(playerList.get(i).getBestScore());
        }
        System.out.println(playernames.toString());
        System.out.println(playerBestScores.toString());

        view.updateHighScore(playernames, playerBestScores);
    }
}
