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
    //ATTRIBUTES
    private Game modelGame;
    private final PlayerManager modelPlayerMananger;
    private final GameView view;
    private final GameBottomView bottomView;
    private final GameMiddleView midView;
    private final GameTopView topView;
    private final AnimationView animationView;
    private boolean alreadyWon;
    private boolean firstRun;
    private boolean undoGreyedOut;
    private int prevScore;

    //CONSTRUCTORS
    public GamePresenter(Game modelGame, PlayerManager modelPlayerManager, GameView view) {
        this.modelGame = modelGame;
        this.modelPlayerMananger = modelPlayerManager;
        this.modelPlayerMananger.setCurrentPlayerScore(modelGame.getScore().getScore());
        this.view = view;
        this.bottomView = view.getBottomView();
        this.midView = view.getMiddleView();
        this.topView = view.getTopView();
        this.animationView = new AnimationView(topView, midView, this);
        this.firstRun = true;
        this.addEventHandlers();
        topView.getLblBestScoreInput().setText(String.valueOf(modelPlayerManager.getCurrentPlayer().getBestScore()));
        //eenmalige updateview
        updateView();
    }

    //METHODEN
    private void addEventHandlers() {
        bottomView.getBtnRestart().setOnAction(event -> {
            alreadyWon = false;
            firstRun = true;
            if (!modelGame.isPlayingUndo()) modelPlayerMananger.saveInfoCurrentPlayer();
            modelGame = new Game(modelPlayerMananger);
            topView.getLblScoreInput().setText("0");
            disableUndoButton(false);
            updateView();
        });

        bottomView.getBtnUndo().setOnAction(event -> {
            UndoView alert = new UndoView();
            alert.getLblMessage().setText("Are you sure you want to undo \nyour last animateMovement? " +
                    "Your score will no longer \nbe added to the highscores.");
            new UndoPresenter(modelGame, alert, view, this);
            view.setView(alert);
        });

        bottomView.getBtnHighScores().setOnAction(event -> {
            if (!modelGame.isPlayingUndo()) modelPlayerMananger.saveInfoCurrentPlayer();
            HighScoreView hsView = new HighScoreView();
            new HighScorePresenter(modelGame, modelPlayerMananger, hsView);
            view.getScene().setRoot(hsView);
        });

        bottomView.getBtnExit().setOnAction(event -> {
            if (!modelGame.isPlayingUndo()) modelPlayerMananger.saveInfoCurrentPlayer();
            modelPlayerMananger.setCurrentPlayerToNull();
            StartView startView = new StartView();
            new StartPresenter(modelPlayerMananger, startView);
            view.getScene().setRoot(startView);
        });

        view.setOnKeyPressed(event -> {
            if (animationView.getParallelTransition().getStatus() != Animation.Status.RUNNING) {
                final KeyCode direction = event.getCode();
                prevScore = modelGame.getScore().getScore();
                switch (direction) {
                    case DOWN:
                        updateViewBlocks(Game.Direction.DOWN);
                        break;
                    case UP:
                        updateViewBlocks(Game.Direction.TOP);
                        break;
                    case RIGHT:
                        updateViewBlocks(Game.Direction.RIGHT);
                        break;
                    case LEFT:
                        updateViewBlocks(Game.Direction.LEFT);
                        break;
                    default:
                        event.consume();
                }
                final int currScore = modelGame.getScore().getScore();
                animationView.animateMovement(direction);
                modelPlayerMananger.setCurrentPlayerScore(currScore);
                if (currScore - prevScore > 0) {
                    animationView.animateScore(currScore - prevScore);
                }

            }
        });

        animationView.getParallelTransition().setOnFinished(event -> {
            animationView.resetAnimation();
            updateView();
        });
    }

    public void updateView() {
        int randomblockX = modelGame.getCoordRandomBlockX();
        int randomblockY = modelGame.getCoordRandomBlockY();

        if (!firstRun && !isMovable()) {
            animationView.popIn(randomblockY, randomblockX);
            midView.putBlockOnGrid(2, randomblockX, randomblockY);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value;
                if (modelGame.getPiece(i, j) == null) {
                    value = 0;
                } else {
                    value = modelGame.getPieceValue(i, j);
                }
                if (firstRun) {
                    midView.putBlockOnGrid(value, i, j);
                    animationView.popIn(i, j);
                } else if (!(i == randomblockX && j == randomblockY)) {
                    midView.putBlockOnGrid(value, i, j);
                }
            }
        }
        this.firstRun = false;
    }

    private void updateViewBlocks(Game.Direction direction) {
        modelGame.runGameCycle(direction);
        updateViewScore(modelGame.getScore().getScore());
        checkIfLostOrWin();
    }

    public void updateViewScore(int score) {
        if (modelPlayerMananger.getCurrentPlayer().getBestScore() <= score) {
            topView.getLblBestScoreInput().setText(String.valueOf(score));
        }
        topView.getLblScoreInput().setText(String.valueOf(score));
    }


    private void checkIfLostOrWin() {
        if (alreadyWon) return;
        if (modelGame.hasLost() || modelGame.hasWon()) {
            alreadyWon = true;
            if (!modelGame.isPlayingUndo()) modelPlayerMananger.saveInfoCurrentPlayer();
            ResultView resultView = new ResultView();
            new ResultPresenter(modelPlayerMananger, resultView, modelGame, view);
            view.setView(resultView);
        }
    }

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