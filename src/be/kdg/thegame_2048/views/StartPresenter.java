package be.kdg.thegame_2048.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jarne Van Aerde
 * @version 1.0 14/02/2017 12:18
 */
public class StartPresenter {
    //ATTRIBUTES
    private StartView view;
    public static Stage prevStage;

    //CONSTRUCTORS
    public StartPresenter(StartView view) {
        this.view = view;
        addEventHandlers();
    }

    //METHODS
    private void addEventHandlers() {
        view.getBtnNewPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSceneToNewPlayer();
            }
        });
        view.getBtnExistingPlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSceneToExistingPlayer();
            }
        });
    }

    private void setSceneToExistingPlayer() {
        ExistingPlayerView view2 = new ExistingPlayerView();
        Scene scene2 = new Scene(view2);
        Stage stage2 = new Stage();
        scene2.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        stage2.setScene(scene2);
        stage2.setMinHeight(750+20);
        stage2.setMinWidth(550);
        stage2.setTitle("2048");
        prevStage.close();
        stage2.show();
    }

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    private void setSceneToNewPlayer() {
        NewPlayerView view2 = new NewPlayerView();
        Scene scene2 = new Scene(view2);
        Stage stage2 = new Stage();
        scene2.getStylesheets().add("be/kdg/thegame_2048/css/stylesheet.css");
        stage2.setScene(scene2);
        stage2.setMinHeight(750+20);
        stage2.setMinWidth(550);
        stage2.setTitle("2048");
        prevStage.close();
        prevStage = stage2;
        stage2.show();
    }

}
