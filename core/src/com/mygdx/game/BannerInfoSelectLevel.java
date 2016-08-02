package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 28.06.2016.
 */
public class BannerInfoSelectLevel extends AbstractBanner {
    public BannerInfoSelectLevel(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);

        mOk = new ButtonWidget(getOk(),WIDTH/2-mFon.getWidth()/2-45 + mFon.getWidth() - 100 - getOk().getWidth(),
                HEIGHT/2-mFon.getHeight()/2 +25,
                WIDTH,HEIGHT);


        mTitle = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_target.png");
        mInfo = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_lv1.png");
        mCislo = new Texture("banners/"+((WinForNstep)mGameProc.getmModel()).getmStep()+".png");

    }

    Texture mInfo,mTitle,mCislo;
    Texture mFon, mOkText, mExitText,mText;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/MINIBANNER.png"):mFon;
    }


    @Override
    public void otherRender() {
        game.mBatch.begin();

        game.mBatch.draw(mTitle, WIDTH / 2 - mText.getWidth() / 2  + 220, HEIGHT / 2 + 110);
        game.mBatch.draw(mInfo, WIDTH / 2 - mText.getWidth() / 2  + 170, HEIGHT / 2 -100);

        game.mBatch.draw(mCislo, WIDTH / 2 - mText.getWidth() / 2  -95, HEIGHT / 2 -95);


        game.mBatch.end();
    }

    @Override
    public Texture getOk() {
        return (mOkText ==null)? mOkText =new Texture("buttons/BUT_OK.png"): mOkText;
    }

    @Override
    public Texture getExit() {
        return null;
    }

    @Override
    public Texture getText() {
        return (mText==null)?mText=new Texture("banners/TEXT_WANTEXIT.png"):mText;
    }

    @Override
    void okCode() {
        game.setScreen(new GameProcess(mGameProc));
        dispose();
    }

    @Override
    void exitCode() {

    }
}
