package be.kdg.thegame_2048.views.About;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.Start.StartPresenter;
import be.kdg.thegame_2048.views.Start.StartView;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 12:17
 */
public class AboutPresenter {
    private PlayerManager model;
    private AboutView view;
    private int currentIndex;

    public AboutPresenter(PlayerManager model, AboutView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getToggleButton(0).setOnAction(event -> {
            moveAnimation(currentIndex, 0);
            currentIndex = 0;
        });
        view.getToggleButton(1).setOnAction(event -> {
            moveAnimation(currentIndex, 1);
            currentIndex = 1;
        });
        view.getToggleButton(2).setOnAction(event -> {
            moveAnimation(currentIndex, 2);
            currentIndex = 2;
        });
        view.getBtnGoBack().setOnAction(event -> {
            StartView startView = new StartView();
            new StartPresenter(model, startView);
            view.getScene().setRoot(startView);

        });

    }

    private void updateView() {
        moveAnimation(0, 0);
    }

    private void moveAnimation(int oldIndex, int newIndex) {
        int[] indexes = {oldIndex, newIndex};
        for (int index : indexes) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(150), AboutView.getImg(index));
            if (newIndex > oldIndex) {
                if (index == newIndex) {
                    tt.setToX(-500);
                } else {
                    tt.setToX(500);
                }
            } else if (newIndex == oldIndex) {
                tt.setToX(0);
                if (index == oldIndex) {
                    tt.setDuration(Duration.millis(1));
                }
            } else {
                if (index == newIndex) {
                    tt.setToX(500);
                } else {
                    tt.setToX(-500);
                }
            }
            tt.play();
            if (index == newIndex) {
                //TODO: add to eventhandlers()
                //move next slide into view
                tt.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TranslateTransition tt2 = new TranslateTransition(Duration.millis(150), AboutView.getImg(newIndex));
                        if (newIndex > oldIndex) {
                            tt2.setFromX(-500);
                        } else if (newIndex == oldIndex) {
                            tt2.setToX(0);
                        } else {
                            tt2.setFromX(500);
                        }
                        tt2.setToX(0);
                        tt2.play();
                        view.layoutNodes(newIndex);
                    }
                });
            }
        }


    }
}