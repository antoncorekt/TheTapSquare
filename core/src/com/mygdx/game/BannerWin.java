package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 25.06.2016.
 */
public class BannerWin extends AbstractBanner {
    public BannerWin(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);
    }

    Texture mTrueImage,mFalseImage;
    IModel mModel;
    Texture mFon, mOkText, mExitText,mText;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/WINBANNER.png"):mFon;
    }

    @Override
    public void otherRender() {
        game.mBatch.begin();
        game.mBatch.draw(mText, WIDTH / 2 - mText.getWidth() / 2-120 , HEIGHT / 2 + 60-100 );
        game.mBatch.end();
    }

    @Override
    public Texture getOk() {
        return (mOkText ==null)? mOkText =new Texture("buttons/BUT_MENU.png"): mOkText;
    }

    @Override
    public Texture getExit() {
        return null;
    }

    @Override
    public Texture getText() {
        return (mText==null)?mText=new Texture("banners/TEXT_WIN.png"):mText;
    }

    @Override
    void okCode() {
        game.setScreen(new GameMenu(game,mSave));
        dispose();
    }

    @Override
    void exitCode() {

    }
}
