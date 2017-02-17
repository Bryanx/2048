package be.kdg.thegame_2048.views;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 14-02-17 14:39
 */
public abstract class SuperView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private ImageView logo;


    protected SuperView() {
        initialiseNodes();
        layoutNodes();
    }

    protected void initialiseNodes() {
        //Same as in the StartView class, maybe put inside an interface
        this.logo = new ImageView("be/kdg/thegame_2048/views/img/logo.png");
    }

    protected void layoutNodes() {
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING * 2, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));
    }
}
