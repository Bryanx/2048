package be.kdg.thegame_2048.views.undo;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 08-03-17 16:21
 */
public class UndoPresenter {
    private final Game model;
    private final UndoView view;
    private final GameView gameView;
    private final GamePresenter gamePresenter;

    public UndoPresenter(Game model, UndoView view, GameView gameView, GamePresenter gamePresenter) {
        this.model = model;
        this.view = view;
        this.gameView = gameView;
        this.gamePresenter = gamePresenter;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnAccept().setOnAction(event -> updateViewUndo());
        view.getBtnCancel().setOnAction(event -> this.gameView.layoutNodes());
    }

    /**
     * Makes a move undone.
     * The method is handled on logical and graphical level.
     **/
    private void updateViewUndo() {
        this.model.setPlayingUndo(true);
        this.model.getScore().setScore(gamePresenter.getPrevScore());
        this.gamePresenter.updateViewScore(gamePresenter.getPrevScore());
        if (model.getLastMove() != null) this.model.goToLastMove();
        this.gamePresenter.updateView();
        this.gameView.layoutNodes();
        this.gamePresenter.disableUndoButton(true);
    }
}
