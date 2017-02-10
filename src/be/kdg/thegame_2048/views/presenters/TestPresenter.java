package be.kdg.thegame_2048.views.presenters;

import be.kdg.thegame_2048.views.views.HighScoreView;

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
        String[] namen = {"Jeroen", "Paul","Henk", "Vlaas","Mark", "Jarne","Bryan", "René"};
        int[] bestScores = {123, 324535,45,6,546,567,6,787};
        view.displayCurrentHighScore(namen, bestScores);
    }
}
