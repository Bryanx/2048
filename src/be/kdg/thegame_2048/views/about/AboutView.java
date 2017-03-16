package be.kdg.thegame_2048.views.about;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * A borderpane that contains the about (how to play) view
 *
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 17-02-17 12:11
 */
public class AboutView extends BorderPane {
    private static final ImageView IMG_ABOUT1 = new ImageView("be/kdg/thegame_2048/views/img/about1.png");
    private static final ImageView IMG_ABOUT2 = new ImageView("be/kdg/thegame_2048/views/img/about2.png");
    private static final ImageView IMG_ABOUT3 = new ImageView("be/kdg/thegame_2048/views/img/about3.png");
    private static final ImageView[] IMAGE_VIEWS = {IMG_ABOUT1, IMG_ABOUT2, IMG_ABOUT3};
    private static final double OVERALL_PADDING = 50;
    private Label lblExplanation1;
    private Label lblExplanation2;
    private Label lblExplanation3;
    private Button btnGoBack;
    private ToggleButton rbOption1;
    private ToggleButton rbOption2;
    private ToggleButton rbOption3;
    private Label lblHeader;

    public AboutView() {
        initialiseNodes();
        layoutNodes(0);
    }

    private void initialiseNodes() {
        //header
        this.lblHeader = new Label("How to play");

        //explanations
        this.lblExplanation1 = new Label("Use your arrow keys to moveAnimation the tiles. \n" +
                "Try to combine blocks with the same value.");
        this.lblExplanation2 = new Label("When two tiles with the same number touch,\n they merge into one!");
        this.lblExplanation3 = new Label("The goal of the game is to merge 1024 and 1024 " +
                "\nto get the final block: 2048. Good luck!");
        this.lblExplanation1.setAlignment(Pos.CENTER);
        this.lblExplanation2.setAlignment(Pos.CENTER);
        this.lblExplanation3.setAlignment(Pos.CENTER);
        this.btnGoBack = new Button();

        //radiobuttons
        rbOption1 = new ToggleButton();
        rbOption2 = new ToggleButton();
        rbOption3 = new ToggleButton();
        ToggleGroup options = new ToggleGroup();
        rbOption1.setToggleGroup(options);
        rbOption2.setToggleGroup(options);
        rbOption3.setToggleGroup(options);
        options.selectToggle(rbOption1);
        addStyles();
    }

    /**
     * @param i index of which img and explanation is selected
     **/
    void layoutNodes(int i) {
        //TOP
        BorderPane top = new BorderPane(lblHeader);
        this.setTop(new BorderPane(top));

        //MID
        Label[] explanations = {lblExplanation1, lblExplanation2, lblExplanation3};
        HBox hbox = new HBox(rbOption1, rbOption2, rbOption3);
        VBox vbox = new VBox(IMAGE_VIEWS[i], explanations[i], hbox);
        vbox.setPadding(new Insets(OVERALL_PADDING,0,0,0));
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        this.setCenter(vbox);

        //BOTTOM
        BorderPane bottom = new BorderPane(btnGoBack);
        this.setBottom(bottom);

        this.setPadding(new Insets(OVERALL_PADDING));
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        btnGoBack.getStyleClass().add("btnGoBack");
        lblHeader.getStyleClass().add("lblHeader");
        lblExplanation1.getStyleClass().add("lblExplanation");
        lblExplanation2.getStyleClass().add("lblExplanation");
        lblExplanation3.getStyleClass().add("lblExplanation");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
    ToggleButton getToggleButton(int index) {
        ToggleButton[] toggleButtons = {rbOption1, rbOption2, rbOption3};
        return toggleButtons[index];
    }
    static ImageView getImg(int i) {
        return IMAGE_VIEWS[i];
    }
}