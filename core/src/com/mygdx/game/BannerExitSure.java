package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 04.07.2016.
 */
public class BannerExitSure extends BannerExit {
    public BannerExitSure(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);


    }

    @Override
    public Texture getText() {
        return (mText==null)?mText=new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_sure.png"):mText;
    }

    @Override
    void okCode() {
        Gdx.app.exit();
    }

    @Override
    void exitCode() {
        game.setScreen(new MainMenuScreen(game));
        dispose();
    }
}
