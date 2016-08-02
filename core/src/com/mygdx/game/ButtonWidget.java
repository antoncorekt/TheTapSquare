package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 21.06.2016.
 */
public class ButtonWidget {
    private Texture mTexture;
    private int mX,mY;
    private int mWidth, mHeight;

    public ButtonWidget(Texture mTexture, int mX, int mY, int mWidth, int mHeight) {
        this.mTexture = mTexture;
        this.mX = mX;
        this.mY = mY;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public void setTexture(Texture t){mTexture = t;}

    public boolean isTouch(int x, int y)
    {

        /*System.out.println(x*100/Gdx.graphics.getWidth());
        System.out.println( (mX+ mTexture.getWidth())*100/(mWidth ));
        System.out.println("----------------");
        System.out.println(y*100/Gdx.graphics.getHeight());
        System.out.println(100- ((mY+mTexture.getHeight())*100/(mHeight )));*/

        if (x*100/Gdx.graphics.getWidth() > mX*100/mWidth &&
            x*100/Gdx.graphics.getWidth() < (mX+ mTexture.getWidth())*100/(mWidth ) &&
            y*100/Gdx.graphics.getHeight() < 100-mY*100/mHeight &&
            y*100/Gdx.graphics.getHeight() > 100- ((mY+mTexture.getHeight())*100/(mHeight ))) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getX() {return mX;}
    public int getY() {return mY;}
    public Texture getTexture() {return mTexture;}
}
