package com.wethebest.spaceinvaders;

import android.graphics.Color;

/*
    This state represents the core game play where the player shoots down an advancing wave of
    aliens
 */
public class WaveState implements GameState {
    private long mFPS;
    private SpaceInvadersApp mApp;

    public WaveState(SpaceInvadersApp app) {
        mApp = app;
    }

    @Override
    public void changeState(SpaceInvadersApp app, State nextState) {
        switch(nextState) {
            case PAUSE:
                app.setState(new PauseState(app));
            case GAMEOVER:
                app.setState(new GameOverState());
        }
    }

    @Override
    public void run(SpaceInvadersApp app) {
        long frameStartTime = System.currentTimeMillis();

        if(app.shootNow) {
            //This seems needs to change
            //Maybe playercannon refactor to fix this
            app.mGameObjectManager.add(app.mGameObjectManager.mPlayer.shoot());
            app.shootNow = false;
        }

        app.mGameObjectManager.updateGameObjectStates(mFPS);
        app.mGameUI.update(app.score);

        draw();

        long timeThisFrame = System.currentTimeMillis() - frameStartTime;

        //TODO: ensure that timeThisFrame isn't ridiculously high
        if (timeThisFrame > 0) {
            int MILLIS_IN_SECOND = 1000;
            mFPS = MILLIS_IN_SECOND / timeThisFrame;
        }

        if (isGameOver(app)) { changeState(app, State.GAMEOVER); }
    }

    private void draw() {
        if (mApp.mOurHolder.getSurface().isValid()) {
            mApp.mCanvas = mApp.mOurHolder.lockCanvas();

            mApp.mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            mApp.mGameObjectManager.displayGameObjects(mApp.mCanvas);
            mApp.mGameUI.draw(mApp.mCanvas);

            mApp.mOurHolder.unlockCanvasAndPost(mApp.mCanvas);
        }
    }

    private boolean isGameOver(SpaceInvadersApp app) {
        return app.mGameObjectManager.mPlayer.lives == 0;
    }
}
