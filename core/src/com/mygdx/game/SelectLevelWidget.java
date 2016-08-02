package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anton on 20.06.2016.
 */
public class SelectLevelWidget {

    private int mLevel;
    private int mWidthCamera;
    private int mHeightCamera;
    private GlobalSettings mSave;
    private int mTextureWidth;
    private int mTextureHeight;
    private Texture mTexture;


    public SelectLevelWidget(int mLevel, int mWidthCamera, int mHeightCamera, GlobalSettings mSave) {
        this.mLevel = mLevel;
        this.mWidthCamera = mWidthCamera;
        this.mHeightCamera = mHeightCamera;
        this.mSave = mSave;
        mTextureWidth= (new Texture("game_menu/GMENU_1_0.png")).getWidth();
        mTextureHeight= (new Texture("game_menu/GMENU_1_0.png")).getHeight();
    }

    public int getTextureWidth(){return mTextureWidth;}

    public int getmTextureHeight(){return mTextureHeight;}

    public void setNullTexture() {mTexture=null;}

    public Texture getTexture()
    {

        //if (mLevel==0) return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_3.png"):mTexture;

        switch (mSave.getComplitedPodlevel(mLevel))
        {
            case -1:
                if (mLevel!=0)
                    if (mSave.getComplitedPodlevel(mLevel-1)==2 || mSave.getComplitedPodlevel(mLevel-1)==3)
                        return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_0.png"):mTexture;
                    else
                        return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_0_0.png"):mTexture;
                else
                    return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_0.png"):mTexture;

            case 0: return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_1.png"):mTexture;

            case 1: return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_2.png"):mTexture;

            case 2: return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_3.png"):mTexture;

            case 3: return (mTexture==null)?mTexture=new Texture("game_menu/GMENU_1_4.png"):mTexture;

        }
        return null;
    }

    public int getX()
    {
        switch (mLevel)
        {
            case 0:case 2:
                return mWidthCamera/4 - mTextureWidth/2;
            case 1:case 3:
                return mWidthCamera/2 + (mWidthCamera/4 - mTextureWidth/2);
        }
        return -1;
    }

    public int getY()
    {
        switch (mLevel)
        {
            case 0:case 1:
            return 370;
            case 2:case 3:
            return 100;
        }
        return -1;
    }

    public Vector2 getXYText(Texture text)
    {
        Vector2 t = new Vector2();

        t.x = mTextureWidth / 2 - text.getWidth() / 2 + getX() + 5;
        t.y = 140 + text.getHeight() / 2 + getY();

        return t;
    }

    public int getLevel(int x, int y)
    {
        System.out.println(mLevel);

        if (x>=106 && x<=106+52) {
            if (mLevel == 0 || mLevel == 1) {
                if (y >= 140 && y <= 197)
                    return mLevel * 10;
            } else {
                if (y >= 125 && y <= 172)
                    return mLevel * 10;
            }
        }

        if (x>=184 && x<=184+52) {
            if (mLevel == 0 || mLevel == 1) {
                if (y >= 140 && y <= 197)
                    return mLevel * 10 + 1;
            } else {
                if (y >= 125 && y <= 172)
                    return mLevel * 10 + 1;
            }
        }

        if (x>=262 && x<=262+52) {
            if (mLevel == 0 || mLevel == 1) {
                if (y >= 140 && y <= 197)
                    return mLevel * 10 + 2;
            } else {
                if (y >= 125 && y <= 172)
                    return mLevel * 10 + 2;
            }
        }

        if (x>=327 && x<=327+52) {
            if (mLevel == 0 || mLevel == 1) {
                if (y >= 140 && y <= 197)
                    return mLevel * 10 + 3;
            } else {
                if (y >= 125 && y <= 172)
                    return mLevel * 10 + 3;
            }
        }

        if (x>=106 && x<=379) {
            if (mLevel == 0 || mLevel == 1) {
                if (y >= 37 && y <= 123)
                    return 100;
            } else {
                if (y >= 22 && y <= 109)
                    return 100;
            }
        }


    return -1;
    }

}
