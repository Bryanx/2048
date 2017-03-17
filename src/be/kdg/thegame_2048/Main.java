package be.kdg.thegame_2048;

import be.kdg.thegame_2048.models.DataReaderWriter;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 04-02-17 18:38
 */
public class Main extends Application {
    //the minheight is increased with 20px because the window top border is 20px.
    private static final double STAGE_MIN_HEIGHT = 750 + 20;
    private static final double STAGE_MIN_WIDTH = 550;
    private static final String STAGE_TITLE = "2048";
    private static final String STYLE_SHEET_PATH = "be/kdg/thegame_2048/views/css/stylesheet.css";
    private static final String FONT_CLEAR_SANS = "views/fonts/ClearSans-Bold.TTF";
    private static final double FONT_SIZE = 12;

    @Override
    public void start(Stage primaryStage) {
        //MAKING CLASSES
        PlayerManager model = new PlayerManager();
        model.getPlayerList().addAll(DataReaderWriter.loadPlayerData());
        StartView view = new StartView();
        new StartPresenter(model, view);

        //PUTTING TOGETHER THE SCENE
        Scene scene = new Scene(view);

        //add stylesheet:
        scene.getStylesheets().add(STYLE_SHEET_PATH);
        //add custom font:
        Font.loadFont(Main.class.getResource(FONT_CLEAR_SANS).toExternalForm(), FONT_SIZE);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(STAGE_MIN_HEIGHT);
        primaryStage.setMinWidth(STAGE_MIN_WIDTH);
        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.getIcons().add(new Image("be/kdg/thegame_2048/views/img/logo.png"));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            if (model.getCurrentPlayer() != null) model.saveInfoCurrentPlayer();
            System.out.println(model.getPlayerList().toString());
            DataReaderWriter.savePlayerData(model.getPlayerList());
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}