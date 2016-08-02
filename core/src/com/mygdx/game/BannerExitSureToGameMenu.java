package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 04.07.2016.
 */
public class BannerExitSureToGameMenu extends BannerExit {
    public BannerExitSureToGameMenu(Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        super(game, mSave, mGameProc);
    }

    @Override
    public Texture getText() {
        return (mText==null)?mText=new Texture(mSave.getLanguage()+"/"+mSave.getLanguage()+"_sure.png"):mText;
    }

    @Override
    void okCode() {
        mSave.clearSave();
        game.setScreen(new GameMenu(game,mSave));
        dispose();
    }

    @Override
    void exitCode() {
        game.setScreen(new GameMenu(game,mSave));
        dispose();
    }

}
