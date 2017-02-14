package be.kdg.thegame_2048;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 04-02-17 18:38
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //MAKING CLASSES
        PlayerManager manager = new PlayerManager();
        Game game = new Game(manager);

        StartView startView = new StartView();
        NewPlayerView newPlayerView = new NewPlayerView();

        StartPresenter Startpresenter = new StartPresenter(startView);
        NewPlayerPresenter NPpresenter = new NewPlayerPresenter(manager, newPlayerView);
        Startpresenter.setPrevStage(primaryStage);

        //PUTTING TOGETHER THE SCENE
        Scene scene = new Scene(startView);

        //add stylesheet:
        scene.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(750+20);
        primaryStage.setMinWidth(550);
        primaryStage.setTitle("2048");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}