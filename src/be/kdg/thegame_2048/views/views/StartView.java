package be.kdg.thegame_2048.views.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 5/02/2017 18:49
 */
public class StartView extends BorderPane {
    private ImageView logo;
    private ImageView iconNewPlayer;
    private ToggleButton tBtnNewPlayer;
    private ImageView iconExistingPlayer;
    private ToggleButton tBtnExistingPlayer;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo.png");
        this.iconNewPlayer = new ImageView("be/kdg/thegame_2048/views/views/img/ufo.png");
        this.tBtnNewPlayer = new ToggleButton("New Player");
        this.iconExistingPlayer = new ImageView("be/kdg/thegame_2048/views/views/img/user.png");
        this.tBtnExistingPlayer = new ToggleButton("Existing Player");
    }

    private void layoutNodes() {
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0,0,100,0));

        GridPane middle = new GridPane();
        middle.add(iconNewPlayer, 0, 0);
        middle.add(tBtnNewPlayer, 1, 0);
        middle.add(iconExistingPlayer, 0, 1);
        middle.add(tBtnExistingPlayer, 1, 1);
        middle.setAlignment(Pos.TOP_CENTER);
        this.setTop(top);
        this.setCenter(middle);
        this.setPadding(new Insets(50));
    }
}