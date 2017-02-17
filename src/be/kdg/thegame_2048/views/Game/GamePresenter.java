package be.kdg.thegame_2048.views.Game;

import be.kdg.thegame_2048.models.Game;

/**
 * @author Jarne Van Aerde
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    //ATTRIBUTES
    private Game model;
    private GameView view;

    //CONSTRUCTORS
    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
    }

    //METHODEN
}
