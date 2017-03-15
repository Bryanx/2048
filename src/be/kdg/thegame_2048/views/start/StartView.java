package be.kdg.thegame_2048.views.start;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * The start/home page of the application.
 * Also contains a scale animation method to animate the buttons.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 5/02/2017 18:49
 */
public final class StartView extends BorderPane {
    private static final ImageView IMG_LOGO = new ImageView("be/kdg/thegame_2048/views/img/logo.png");
    private static final ImageView IMG_UFO = new ImageView("be/kdg/thegame_2048/views/img/ufo.png");
    private static final ImageView IMG_MAN = new ImageView("be/kdg/thegame_2048/views/img/user.png");
    private static final ImageView IMG_JOYSTICK = new ImageView("be/kdg/thegame_2048/views/img/about.png");
    private static final ImageView IMG_CREDITS = new ImageView("be/kdg/thegame_2048/views/img/credits.png");
    private static final String NEW_PLAYER_TEXT = "New Player";
    private static final String EXISTING_PLAYER_TEXT = "Existing Player";
    private static final String ABOUT_TEXT = "How to play";
    private static final String CREDITS_TEXT = "Credits";
    private static final Duration SCALE_DURATION = Duration.millis(100);
    private static final int OVERALL_PADDING = 50;
    private Button btnNewPlayer;
    private Button btnExistingPlayer;
    private Button btnAbout;
    private Button btnCredits;
    private Button[] allButtons;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnNewPlayer = new Button(
                NEW_PLAYER_TEXT,
                IMG_UFO);
        this.btnExistingPlayer = new Button(
                EXISTING_PLAYER_TEXT,
                IMG_MAN);
        this.btnAbout = new Button(
                ABOUT_TEXT,
                IMG_JOYSTICK);
        this.btnCredits = new Button(
                CREDITS_TEXT,
                IMG_CREDITS);
        this.allButtons = new Button[]{btnNewPlayer, btnExistingPlayer, btnAbout, btnCredits};
        addStyles();
    }

    private void layoutNodes() {
        //UP
        BorderPane top = new BorderPane(IMG_LOGO);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING*2, 0));
        this.setTop(top);

        //MIDDLE
        VBox vBox1 = new VBox(btnNewPlayer, btnAbout);
        VBox vBox2 = new VBox(btnExistingPlayer, btnCredits);
        HBox middle = new HBox(vBox1,vBox2);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        vBox1.setSpacing(30);
        vBox2.setSpacing(30);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
        this.setPadding(new Insets(OVERALL_PADDING));
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        for (Button button : allButtons) {
            button.getStyleClass().add("btnStartView");
        }
    }

    void scale(Button button, double fromXY, double toXY) {
        ScaleTransition st = new ScaleTransition();
        st.setNode(button);
        st.setFromX(fromXY);
        st.setFromY(fromXY);
        st.setToX(toXY);
        st.setToY(toXY);
        st.setDuration(SCALE_DURATION);
        st.play();
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
    Button getBtnCredits() {
        return btnCredits;
    }
    Button[] getAllButtons() {
        return allButtons;
    }
}