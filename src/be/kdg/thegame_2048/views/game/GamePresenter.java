package be.kdg.thegame_2048.views.game;

import be.kdg.thegame_2048.models.Game;
import be.kdg.thegame_2048.models.PlayerManager;
import be.kdg.thegame_2048.views.undo.UndoPresenter;
import be.kdg.thegame_2048.views.undo.UndoView;
import be.kdg.thegame_2048.views.highscores.HighScorePresenter;
import be.kdg.thegame_2048.views.highscores.HighScoreView;
import be.kdg.thegame_2048.views.result.ResultPresenter;
import be.kdg.thegame_2048.views.result.ResultView;
import be.kdg.thegame_2048.views.start.StartPresenter;
import be.kdg.thegame_2048.views.start.StartView;
import javafx.animation.Animation;
import javafx.scene.input.KeyCode;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 17/02/2017 9:28
 */
public class GamePresenter {
    private Game modelGame;
    private final PlayerManager modelPM;
    private final GameView view;
    private final GameBottomView bottomView;
    private final GameMiddleView midView;
    private final GameTopView topView;
    private final AnimationView animationView;
    private boolean alreadyWon;
    private boolean firstRun;
    private int prevScore;
    private int currentScore;

    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPM = modelPlayerManager;
        this.view = view;
        this.bottomView = view.getBottomView();
        this.midView = view.getMiddleView();
        this.topView = view.getTopView();
        this.animationView = new AnimationView(topView, midView, this);
        this.firstRun = true;
        this.addEventHandlers();
        updateViewScore(currentScore);
        updateView();
    }

    private void addEventHandlers() {
        this.bottomView.getBtnRestart().setOnAction(event -> {
            this.alreadyWon = false;
            this.firstRun = true;
            if (!modelGame.isPlayingUndo()) modelPM.saveInfoCurrentPlayer();
            this.modelGame = new Game();
            this.topView.getLblCurrentScoreInput().setText("0");
            disableUndoButton(false);
            updateView();
        });

        this.bottomView.getBtnUndo().setOnAction(event -> {
            UndoView alert = new UndoView();
            new UndoPresenter(modelGame, alert, view, this);
            this.view.setView(alert);
        });

        this.bottomView.getBtnHighScores().setOnAction(event -> {
            if (!modelGame.isPlayingUndo()) modelPM.saveInfoCurrentPlayer();
            HighScoreView hsView = new HighScoreView();
            new HighScorePresenter(modelGame, modelPM, hsView);
            this.view.getScene().setRoot(hsView);
        });

        this.bottomView.getBtnExit().setOnAction(event -> {
            if (!modelGame.isPlayingUndo()) modelPM.saveInfoCurrentPlayer();
            modelPM.setCurrentPlayerToNull();
            StartView startView = new StartView();
            new StartPresenter(modelPM, startView);
            this.view.getScene().setRoot(startView);
        });

        this.view.setOnKeyPressed(event -> {
            if (animationView.getParallelTransition().getStatus() != Animation.Status.RUNNING) {
                final KeyCode direction = event.getCode();
                this.prevScore = modelGame.getScore();
                switch (direction) {
                    case DOWN:
                        updateViewBlocks(Game.Direction.DOWN);
                        animationView.animateMovement(direction);
                        break;
                    case UP:
                        updateViewBlocks(Game.Direction.UP);
                        animationView.animateMovement(direction);
                        break;
                    case RIGHT:
                        updateViewBlocks(Game.Direction.RIGHT);
                        animationView.animateMovement(direction);
                        break;
                    case LEFT:
                        updateViewBlocks(Game.Direction.LEFT);
                        animationView.animateMovement(direction);
                        break;
                    default:
                        event.consume();
                }
                this.currentScore = modelGame.getScore();
                if (currentScore - prevScore > 0) {
                    this.animationView.animateScore(currentScore - prevScore);
                }
            }
        });

        this.animationView.getParallelTransition().setOnFinished(event -> {
            this.animationView.resetMoveAnimation();
            updateView();
        });
    }

    /**
     * Transfers all calculated model output to the GameView.
     * Also decides which block should get a popIn animation.
     **/
    public void updateView() {
        int randomblockX = modelGame.getCoordRandomBlockX();
        int randomblockY = modelGame.getCoordRandomBlockY();

        if (!firstRun && !isMovable()) {
            animationView.popIn(randomblockY, randomblockX);
            midView.changeBlockValue(2, randomblockX, randomblockY);
        }

        for (int i = 0; i < GameMiddleView.GRID_SIZE; i++) {
            for (int j = 0; j < GameMiddleView.GRID_SIZE; j++) {
                int value;
                if (modelGame.getPiece(i, j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i, j);
                }
                if (firstRun) {
                    midView.changeBlockValue(value, i, j);
                    animationView.popIn(i, j);
                } else if (!(i == randomblockX && j == randomblockY)) {
                    midView.changeBlockValue(value, i, j);
                }
            }
        }
        this.firstRun = false;
    }

    /**
     * After a move key is pressed, first animates blocks into a certain direction.
     * Then transfers its direction to the model classes.
     * @param direction should contain the direction for the game model class.
     **/
    private void updateViewBlocks(Game.Direction direction) {
        modelGame.runGameCycle(direction);
        updateViewScore(currentScore);
        checkIfLostOrWin();
    }

    /**
     * Scores on the top of the view are updated.
     * @param score can contain the current score or any other score.
     **/
    public void updateViewScore(int score) {
        this.modelPM.setCurrentPlayerScore(score);
        int bestScore = modelPM.getCurrentPlayer().getBestScore();
        this.topView.getLblBestScoreInput().setText(String.valueOf(bestScore));
        if (score >= bestScore) {
            this.topView.getLblBestScoreInput().setText(String.valueOf(score));
        }
        this.topView.getLblCurrentScoreInput().setText(String.valueOf(score));
    }

    /**
     * Model classes check if the player has won or lost.
     * If so, the resultView must be shown.
     **/
    private void checkIfLostOrWin() {
        if (!modelGame.hasLost() && alreadyWon) return;
        if (modelGame.hasLost() || modelGame.hasWon()) {
            alreadyWon = true;
            if (!modelGame.isPlayingUndo()) modelPM.saveInfoCurrentPlayer();
            ResultView resultView = new ResultView();
            new ResultPresenter(modelPM, resultView, modelGame, view);
            view.setView(resultView);
        }
    }

    /**
     * Checks if there are any moves left.
     * @return boolean
     **/
    boolean isMovable() {
        return modelGame.getLastMove() == null ||
                modelGame.getLastMove().equals(modelGame.getCurrentMove());
    }

    public int getPrevScore() {
        return prevScore;
    }
    public void disableUndoButton(boolean bool) {
        bottomView.getBtnUndo().setDisable(bool);
    }
}