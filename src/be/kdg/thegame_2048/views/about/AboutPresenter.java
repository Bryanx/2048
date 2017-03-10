package be.kdg.thegame_2048.views.about;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 17-02-17 12:17
 */
public class AboutPresenter {
    private final PlayerManager model;
    private final AboutView view;
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
                tt.setOnFinished(event -> {
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
                });
            }
        }


    }
}