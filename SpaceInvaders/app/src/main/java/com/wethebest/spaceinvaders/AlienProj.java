package com.wethebest.spaceinvaders;

import android.graphics.PointF;

/*@AlienProj
* Represents the projectiles shot from each alien.
* Moves vertically down in a straight line with constant velocity.
* Collides only with the player cannon and barrier blocks.
* Instantiated by Alien
 */
public class AlienProj extends GameObject{
    AlienProj(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }

    public void playAudio() { }

    public void update(long fps){
        mHitBox.moveVertically(mHitBox.velocity / fps);
        checkBounds();
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof SimpleCannon || gameObject instanceof BarrierBlock)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}