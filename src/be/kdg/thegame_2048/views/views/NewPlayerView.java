package be.kdg.thegame_2048.views.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 17:03
 */
public class NewPlayerView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private static final Paint BG_COLOR = Color.rgb(236, 196, 0);
    private ImageView logo;
    private Label lblNewPlayer;
    private TextField tfNewPlayer;

    public NewPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Same as in the StartView class, maybe put inside an interface
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo.png");

        //Label:
        this.lblNewPlayer = new Label("");
        this.tfNewPlayer = new TextField();
    }

    private void layoutNodes() {
        //Same as in the StartView class, maybe put inside an interface
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, 100, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));
        this.setBackground(new Background(new BackgroundFill(BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}