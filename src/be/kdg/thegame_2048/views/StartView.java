package be.kdg.thegame_2048.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 5/02/2017 18:49
 */
public final class StartView extends BorderPane {
    private static final int OVERALL_PADDING = 50;
    private ImageView logo;
    private Button btnNewPlayer;
    private Button btnExistingPlayer;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Creates the logo at the top of the screen:
        this.logo = new ImageView("be/kdg/thegame_2048/views/img/logo.png");

        //Creates the New Player button and adds an icon:
        this.btnNewPlayer = new Button("New Player");
        btnNewPlayer.setGraphic(new ImageView("be/kdg/thegame_2048/views/img/ufo.png"));

        //Creates the Existing Player button and adds an icon:
        this.btnExistingPlayer = new Button("Existing Player");
        btnExistingPlayer.setGraphic(new ImageView("be/kdg/thegame_2048/views/img/user.png"));

        addStyles();
    }

    private void layoutNodes() {
        //topside of the StartView, contains the 2048 logo:
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING*2, 0));
        this.setTop(top);

        //Middle of the StartView, 2 buttons are stacked inside a GridPane:
        GridPane middle = new GridPane();
        middle.add(btnNewPlayer, 0, 0);
        middle.add(btnExistingPlayer, 0, 1);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);

        //Properties for the entire screen:
        this.setPadding(new Insets(OVERALL_PADDING));
    }

    private void addStyles() {
        btnNewPlayer.getStyleClass().add("btnStartView");
        btnExistingPlayer.getStyleClass().add("btnStartView");
    }
}