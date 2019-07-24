package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class BarrierBlock implements GameObject {
    private RectF mRect;
    private Point mScreenSize;
    public Size barrierBlockSize; //TODO: make this static?

    //Tells the game whether the object should still be in game
    private boolean isActive; //TODO: maybe this should be public? since isActive() only returns isActive
    private Paint mPaint;


    private static int durability = 1;

    BarrierBlock(Point ScreenSize){
        mScreenSize = ScreenSize;

        barrierBlockSize = new Size(mScreenSize.x / 20, mScreenSize.y / 40); //TODO: dynamically set better size
        isActive = true;

        mRect = new RectF();
        mPaint = new Paint();
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void update(long fps) {

    }

    public void reset(Point location) { //TODO: refactor reset to take no parameters?
    }


    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    public void collide(GameObject gameObject) {
        //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
        // but this is a good check to make sure the BarrierBlock class still works if the spaceInvadersApp
        // class changes
        //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
        //Collide only describes what the class should do when it is collided with
        if (gameObject instanceof Projectile) {
            removeDurability(1); //could implement projectile.damage in the future, for now just 1
            if(durability <= 0) {
                removeBarrierBlock();
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    private void removeDurability(int x){
        durability -= x;
    }

    private void removeBarrierBlock() {
        isActive = false;
    }

    //Set position of BarrierBlock, top left corner on x,y
    public void setPos(float x, float y) { //TODO: refactor with float point class
        mRect.left = x;
        mRect.top = y;
        mRect.right = mRect.left + barrierBlockSize.width;
        mRect.bottom = mRect.top + barrierBlockSize.height;
    }

}