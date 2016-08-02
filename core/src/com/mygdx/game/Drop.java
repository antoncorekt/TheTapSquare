package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Anton on 11.06.2016.
 */
public class Drop extends Game {
    SpriteBatch mBatch;
    BitmapFont mFont;

    @Override
    public void create() {
    mBatch =  new SpriteBatch();
        mFont = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        mBatch.dispose();
        mFont.dispose();
    }
}
