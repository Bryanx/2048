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
        String namen = "Jeroen,123\n" +
                "Paul,324535\n" +
                "Henk,45\n" +
                "Vlaas,6\n" +
                "Mark,546\n" +
                "Jarne,567\n" +
                "Bryan,7\n" +
                "René,787";
        view.updateHighScore(namen);
    }
}
