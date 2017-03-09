package be.kdg.thegame_2048.views.undo;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.views.game.GamePresenter;
import be.kdg.thegame_2048.views.game.GameView;

/**
 * @author Bryan de Ridder
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
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBtnAccept().setOnAction(event -> {
            model.setPlayingUndo(true);
            if (model.getLastMove() != null) this.model.goToLastMove();
            this.gamePresenter.updateView();
            this.gameView.layoutNodes();
            gamePresenter.disablrButtom();
        });
        view.getBtnCancel().setOnAction(event -> this.gameView.layoutNodes());

    }
    private void updateView() {
    }
}
