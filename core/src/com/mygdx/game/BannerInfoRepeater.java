package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 24.06.2016.
 */
public class BannerInfoRepeater extends AbstractBanner {
    public BannerInfoRepeater(Drop game, GlobalSettings mSave, GameProcess mGameProc, IModel mModel) {
        super(game, mSave, mGameProc);
        this.mModel = mModel;

        mOk = new ButtonWidget(getOk(),WIDTH/2-mFon.getWidth()/2-45 + mFon.getWidth() - 100 - getOk().getWidth(),
                HEIGHT/2-mFon.getHeight()/2 +25,
                WIDTH,HEIGHT);

        mTrueImage = new Texture("sq/sq_"+"NONE"+"_"+mModel.getTextureNameSize()+"_1.png");
        mFalseImage = new Texture("sq/sq_"+"NONE"+"_"+mModel.getTextureNameSize()+"_0.png");
        mTitle = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_target.png");
        mInfo = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_lv2.png");

    }

    Texture mInfo,mTitle;


    Texture mTrueImage,mFalseImage;
    IModel mModel;
    Texture mFon, mOkText, mExitText,mText;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/MINIBANNER.png"):mFon;
    }

    @Override
    public void otherRender() {
        game.mBatch.begin();
        //game.mBatch.draw(mText, WIDTH / 2 - mText.getWidth() / 2 , HEIGHT / 2 + 60 );

        game.mBatch.draw(mTitle, WIDTH / 2 - mText.getWidth() / 2  + 320, HEIGHT / 2 + 110);
        game.mBatch.draw(mInfo, WIDTH / 2 - mText.getWidth() / 2  + 300, HEIGHT / 2 -40);

        for (int i = 0; i < mModel.Size(); i++) {
            for (int j = 0; j < mModel.Size(); j++) {
                if (mModel.getState(i, j))
                    game.mBatch.draw(mTrueImage, mModel.getRealX(i, j)+WIDTH/2-mModel.getRealWidth()+10, mModel.getRealY(i, j)+HEIGHT/2-mModel.getRealHeight()/2);
                else
                    game.mBatch.draw(mFalseImage, mModel.getRealX(i, j)+WIDTH/2-mModel.getRealWidth()+10, mModel.getRealY(i, j)+HEIGHT/2-mModel.getRealHeight()/2);

            }
        }


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
