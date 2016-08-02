package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 23.06.2016.
 */
public class BannerExit extends AbstractBanner {
    public BannerExit(Drop game,GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);
    }

    Texture mFon,mOk,mExit,mText;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/MINIBANNER.png"):mFon;
    }

    @Override
    public void otherRender() {
        game.mBatch.begin();
        game.mBatch.draw(mText, WIDTH / 2 - mText.getWidth() / 2 , HEIGHT / 2 + 60 );
        game.mBatch.end();
    }

    @Override
    public Texture getOk() {
        return (mOk==null)?mOk=new Texture("buttons/BUT_OK.png"):mOk;
    }

    @Override
    public Texture getExit() {
        return (mExit==null)?mExit=new Texture("buttons/BUT_NO.png"):mExit;
    }

    @Override
    public Texture getText() {
        return (mText==null)?mText=new Texture("banners/TEXT_WANTEXIT.png"):mText;
    }

    @Override
    void okCode() {
        game.setScreen(new GameMenu(game,mSave));
        dispose();
    }

    @Override
    void exitCode() {
        game.setScreen(new GameProcess(mGameProc));
        dispose();
    }
}
