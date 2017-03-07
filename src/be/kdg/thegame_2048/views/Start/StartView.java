package be.kdg.thegame_2048.views.Start;

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
    private static final ImageView IMG_LOGO = new ImageView("be/kdg/thegame_2048/views/img/logo.png");
    private static final ImageView IMG_UFO = new ImageView("be/kdg/thegame_2048/views/img/ufo.png");
    private static final ImageView IMG_MAN = new ImageView("be/kdg/thegame_2048/views/img/user.png");
    private static final ImageView IMG_JOYSTICK = new ImageView("be/kdg/thegame_2048/views/img/about.png");
    private static final ImageView IMG_SETTINGS = new ImageView("be/kdg/thegame_2048/views/img/settings.png");
    private Button btnNewPlayer;
    private Button btnExistingPlayer;
    private Button btnAbout;
    private Button btnSettings;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Creates the New Player button and adds an icon:
        this.btnNewPlayer = new Button("New Player", IMG_UFO);

        //Creates the Existing Player button and adds an icon:
        this.btnExistingPlayer = new Button("Existing Player", IMG_MAN);

        //Creates the About button and adds an icon:
        this.btnAbout = new Button("How to play", IMG_JOYSTICK);

        //Creates the Settings button and adds an icon:
        this.btnSettings = new Button("Settings", IMG_SETTINGS);

        addStyles();
    }

    private void layoutNodes() {
        //topside of the StartView, contains the 2048 IMG_LOGO:
        BorderPane top = new BorderPane(IMG_LOGO);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING*2, 0));
        this.setTop(top);

        //Middle of the StartView, 2 buttons are stacked inside a GridPane:
        VBox vBox1 = new VBox(btnNewPlayer, btnAbout);
        VBox vBox2 = new VBox(btnExistingPlayer, btnSettings);
        HBox middle = new HBox(vBox1,vBox2);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        vBox1.setSpacing(30);
        vBox2.setSpacing(30);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
        this.setPadding(new Insets(OVERALL_PADDING));
    }

    private void addStyles() {
        btnNewPlayer.getStyleClass().add("btnStartView");
        btnExistingPlayer.getStyleClass().add("btnStartView");
        btnAbout.getStyleClass().add("btnStartView");
        btnSettings.getStyleClass().add("btnStartView");
    }

    Button getBtnNewPlayer() {
        return btnNewPlayer;
    }

    Button getBtnExistingPlayer() {
        return btnExistingPlayer;
    }

    Button getBtnAbout() {
        return btnAbout;
    }

    Button getBtnSettings() {
        return btnSettings;
    }
}