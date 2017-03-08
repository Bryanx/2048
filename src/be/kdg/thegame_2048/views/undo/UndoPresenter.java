package be.kdg.thegame_2048.views.undo;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.views.game.GameView;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-03-17 16:21
 */
public class UndoPresenter {
    private final Game model;
    private final UndoView view;
    private final GameView gameView;

    public UndoPresenter(Game model, UndoView view, GameView gameView) {
        this.model = model;
        this.view = view;
        this.gameView = gameView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnAccept().setOnAction(event -> {
            this.model.goToLastMove();
            this.gameView.layoutNodes();
        });
        view.getBtnCancel().setOnAction(event -> {
            this.gameView.layoutNodes();
        });

    }
    private void updateView() {
    }
}
