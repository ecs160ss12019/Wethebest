package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class SimpleCannon extends GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.player;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.player_invincible;

    private final int INVICIBLE_SECONDS = 2; //how long cannon is invincible
    public final int MAX_LIVES = 3;
    public int lives = MAX_LIVES;

    private boolean playShoot = false;

    private boolean invincible = false;
    private long frameCount = 0;

    SimpleCannon(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }

    public void update(long fps) {
        if(((SpaceInvaders)app.context).yAcceleration >= .08f || ((SpaceInvaders)app.context).yAcceleration <= -.08f) { //tilt thresholds for cannon to stay still
            mHitBox.moveHorizontally(((SpaceInvaders)app.context).yAcceleration * 10); //change this multiplying constant to change movement speed
            mHitBox.horizontalStayInBounds();
        }

        if(invincible && frameCount <= 0) {
            frameCount = fps * INVICIBLE_SECONDS;
        }
        else {
            frameCount--;
            if(frameCount <= 0) {
                invincible = false;
                mHitBox.setBitmap(SPRITE_ID);
            }
        }
    }

    public void playAudio(){
        if(playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
    }

    public void collide(GameObject gameObject) {
        if(gameObject instanceof AlienProj) {
            if(!invincible) {
                lives -= 1;
                reset();
                invincible = true;
                mHitBox.setBitmap(INVINCIBLE_SPRITE_ID);
            }
        }
    }

    public GameObject shoot() {
        GameObject mProj = GameObjectFactory.getGameObject("PlayerProj");
        mProj.setPosition(mHitBox.centerTop());
        playShoot = true;
        return mProj;
    }
}