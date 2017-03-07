package be.kdg.thegame_2048;

import be.kdg.thegame_2048.models.DataReaderWriter;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 04-02-17 18:38
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        //MAKING CLASSES
        DataReaderWriter drw = new DataReaderWriter();
        PlayerManager model = new PlayerManager();
        model.getPlayerList().addAll(drw.loadPlayerData());
        StartView view = new StartView();
        StartPresenter presenter = new StartPresenter(model, view);

        //PUTTING TOGETHER THE SCENE
        Scene scene = new Scene(view);

        //add stylesheet:
        scene.getStylesheets().add("be/kdg/thegame_2048/views/css/stylesheet.css");
        //add fonts:
        Font.loadFont(Main.class.getResource("views/fonts/ClearSans-Bold.TTF").toExternalForm(), 12);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(750 + 20);
        primaryStage.setMinWidth(550);
        primaryStage.setTitle("2048");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            if (model.getCurrentPlayer() != null) model.saveInfoCurrentPlayer();
            System.out.println(model.getPlayerList().toString());
            drw.savePlayerData(model.getPlayerList());
            drw.writeToLog();
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}