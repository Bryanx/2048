package be.kdg.thegame_2048.views.about;

import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * Presenter for the about (how to play) view
 * Handles the animation part of the view.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 17-02-17 12:17
 */
public class AboutPresenter {
    private static final double MOVE_RIGHT = 500;
    private static final double MOVE_LEFT = -500;
    private static final Duration ANIMATION_DURATION = Duration.millis(150);
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

    /**
     * Animates the images according to their index.
     **/
    private void moveAnimation(int oldIndex, int newIndex) {
        int[] indexes = {oldIndex, newIndex};
        for (int index : indexes) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(ANIMATION_DURATION);
            tt.setNode(AboutView.getImg(index));
            tt.setInterpolator(Interpolator.EASE_BOTH);
            if (newIndex > oldIndex) {
                if (index == newIndex) {
                    tt.setToX(MOVE_RIGHT);
                } else {
                    tt.setToX(MOVE_LEFT);
                }
            } else if (newIndex == oldIndex) {
                tt.setToX(0);
                tt.setDuration(Duration.millis(1));
            } else {
                if (index == newIndex) {
                    tt.setToX(MOVE_LEFT);
                } else {
                    tt.setToX(MOVE_RIGHT);
                }
            }
            tt.play();
            if (index == newIndex) {
                tt.setOnFinished(event -> {
                    TranslateTransition tt2 = new TranslateTransition();
                    tt2.setDuration(ANIMATION_DURATION);
                    tt2.setNode(AboutView.getImg(index));
                    tt2.setInterpolator(Interpolator.EASE_BOTH);
                    if (newIndex > oldIndex) {
                        tt2.setFromX(MOVE_RIGHT);
                    } else if (newIndex == oldIndex) {
                        tt2.setToX(0);
                    } else {
                        tt2.setFromX(MOVE_LEFT);
                    }
                    tt2.setToX(0);
                    tt2.play();
                    view.layoutNodes(newIndex);
                });
            }
        }
    }
}