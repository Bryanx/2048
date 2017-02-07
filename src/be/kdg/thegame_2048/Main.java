package be.kdg.thegame_2048;

import be.kdg.thegame_2048.views.views.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 04-02-17 18:38
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //KLASSEN AANAMKEN
        StartView startView = new StartView();

        //SCENE SAMENSTELLEN
        Scene scene = new Scene(startView);
        primaryStage.setScene(scene);
        //add stylesheet:
        //scene.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(450);
        primaryStage.setTitle("2048");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}