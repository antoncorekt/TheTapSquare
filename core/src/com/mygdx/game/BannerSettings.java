package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 02.07.2016.
 */
public class BannerSettings extends AbstractBanner {
    public BannerSettings(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);

        System.out.println("lol");

        mTitle = new Texture("en/en_settings_eng.png");
        mInfoStyle = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_set_style.png");
        mInfoLang = new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_language.png");

        mStyle = new ButtonWidget(new Texture("sq/sq_"+mSave.getSqStyle()+"_"+"144"+"_1.png"),470,240,WIDTH,HEIGHT);
        mLang = new ButtonWidget(new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_language_but.png"),795,280,WIDTH,HEIGHT);

    }

    Texture mInfoStyle,mInfoLang,mTitle;

    ButtonWidget mStyle,mLang;

    Texture mFon, mOkText,mText, mExit;

    @Override
    public Texture getFon() {
        return (mFon==null)?mFon=new Texture("banners/MINIBANNER.png"):mFon;
    }

    @Override
    public void otherRender() {
        game.mBatch.begin();
        game.mBatch.draw(mTitle, WIDTH / 2 - mTitle.getWidth() / 2 , HEIGHT / 2 +140 );

        game.mBatch.draw(mInfoStyle, WIDTH / 2 - mInfoStyle.getWidth() / 2 - 120 , HEIGHT / 2 + 50 );
        game.mBatch.draw(mInfoLang, WIDTH / 2 - mInfoLang.getWidth() / 2 + 230 , HEIGHT / 2 + 50 );

        game.mBatch.draw(mStyle.getTexture(),mStyle.getX(),mStyle.getY());
        game.mBatch.draw(mLang.getTexture(),mLang.getX(),mLang.getY());


        game.mBatch.end();

        if (Gdx.input.justTouched())
        {
            System.out.println("touch");
            if (mStyle.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                System.out.println("mStyle");
                if (mSave.getSqStyle().equals("NONE"))
                    mSave.setSqStyle(1);
                else
                {
                    if (mSave.getSqStyle().equals("BORDER"))
                        mSave.setSqStyle(2);
                    else
                        if (mSave.getSqStyle().equals("BUT"))
                            mSave.setSqStyle(0);
                }
                mStyle.setTexture(new Texture("sq/sq_"+mSave.getSqStyle()+"_"+"144"+"_1.png"));
            }

            if (mLang.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                mSave.setLanguage();
                mLang.setTexture(new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_language_but.png"));
            }
        }


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
        game.setScreen(new MainMenuScreen(game));
        dispose();
    }

    @Override
    void exitCode() {

    }
}
