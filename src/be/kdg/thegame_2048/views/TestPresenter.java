package be.kdg.thegame_2048.views;

import be.kdg.thegame_2048.views.HighScores.HighScoreView;

/**
 * @author Jarne Van Aerde
 * @version 1.0 5/02/2017 18:49
 */
public class TestPresenter {
    //    private TestModel model;
    private HighScoreView view;

    public TestPresenter(HighScoreView view) {
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
    }
}
