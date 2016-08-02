package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Anton on 11.06.2016.
 */
public class MainMenuScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;

    SpriteBatch mBatch;
    Texture mFON;
    ButtonWidget mGAME;
    ButtonWidget mSETTINGS;
    ButtonWidget mABOUT;
    ButtonWidget mEXIT;

    Vector2 mGamePoint;
    Vector2 mSETTINGSPoint;
    Vector2 mABOUTPoint;
    Vector2 mEXITPoint;

    GlobalSettings mSave;

    public MainMenuScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1300,740);

        mBatch =  new SpriteBatch();

        mSave =  new GlobalSettings();

        int starty = 450, RAZN = 20;

        mFON = new Texture("fon.png");

        mGAME =new ButtonWidget(new Texture("main_menu/MAIN_GAME.png"),1300/2 - new Texture("main_menu/MAIN_GAME.png").getWidth()/2,starty,1300,740 );

        mSETTINGS =new ButtonWidget(new Texture("main_menu/MAIN_SETTINGS.png"),1300/2 - mGAME.getTexture().getWidth()/2,starty - mGAME.getTexture().getHeight() - RAZN,1300,740 );

        mABOUT =new ButtonWidget(new Texture("main_menu/MAIN_ABOUT.png"),1300/2 - mGAME.getTexture().getWidth()/2,starty - mGAME.getTexture().getHeight()*2 - RAZN*2,1300,740 );

        mEXIT =new ButtonWidget(new Texture("main_menu/MAIN_EXIT.png"),1300/2 - mGAME.getTexture().getWidth()/2,starty - mGAME.getTexture().getHeight()*3 - RAZN*3,1300,740 );


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.mBatch.setProjectionMatrix(camera.combined);
        game.mBatch.begin();

        game.mBatch.draw(mFON, 0, 0);
        game.mBatch.draw(mGAME.getTexture(), mGAME.getX(), mGAME.getY());
        game.mBatch.draw(mSETTINGS.getTexture(), mSETTINGS.getX(), mSETTINGS.getY());
        game.mBatch.draw(mABOUT.getTexture(), mABOUT.getX(), mABOUT.getY());
        game.mBatch.draw(mEXIT.getTexture(), mEXIT.getX(), mEXIT.getY());


        game.mBatch.end();

        if (Gdx.input.isTouched())
        {
            if (mGAME.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new GameMenu(game, mSave));
                dispose();
            }

            if (mABOUT.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new BannerAbout(game, mSave, null));
                dispose();
            }

            if (mSETTINGS.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new BannerSettings(game, mSave, null));
                dispose();
            }
            if (mEXIT.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new BannerExitSure(game,mSave,null));
                dispose();
            }

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
