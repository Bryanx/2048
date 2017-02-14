package be.kdg.thegame_2048;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.GameView;
import be.kdg.thegame_2048.views.StartView;
import be.kdg.thegame_2048.views.WinView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 04-02-17 18:38
 */
public class Main extends Application {
    private Scene scene;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        //MAKING CLASSES
        PlayerManager manager = new PlayerManager();
        Game game = new Game(manager);

        StartView view = new StartView();

        //PUTTING TOGETHER THE SCENE
        this.scene = new Scene(view);
        //add stylesheet:
        this.primaryStage = primaryStage;
        scene.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        this.primaryStage.setScene(scene);
        this.primaryStage.setMinHeight(750+20);
        this.primaryStage.setMinWidth(550);
        this.primaryStage.setTitle("2048");
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void setScene(Scene scene) {
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
}