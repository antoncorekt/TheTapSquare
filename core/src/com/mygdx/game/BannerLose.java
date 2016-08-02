package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Anton on 28.06.2016.
 */
public class BannerLose extends AbstractBanner {
    public BannerLose(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);


        mTitle = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_lose.png");
    }

    Texture mFon, mOkText, mExitText,mTitle;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/MINIBANNER.png"):mFon;
    }



    @Override
    public void otherRender() {
        game.mBatch.begin();

        game.mBatch.draw(mTitle, WIDTH / 2 - mTitle.getWidth() / 2 , HEIGHT / 2 + 80);

        game.mBatch.end();
    }

    @Override
    public Texture getOk() {
        return (mOkText ==null)? mOkText =new Texture("buttons/BUT_MENU.png"): mOkText;
    }

    @Override
    public Texture getExit() {
        return (mExitText ==null)? mExitText =new Texture("buttons/BUT_CANCEL.png"): mExitText;
    }

    @Override
    public Texture getText(){return null;}


    @Override
    void okCode() {
        game.setScreen(new GameMenu(game,mSave));
        dispose();
    }

    @Override
    void exitCode() {


        ((WinForNstep)(mGameProc.getmModel())).restartGame();
        game.setScreen(new GameProcess(mGameProc));
        dispose();
    }
}
