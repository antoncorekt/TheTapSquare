package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Anton on 23.06.2016.
 */
public abstract class AbstractBanner implements Screen {
    final Drop game;
    private OrthographicCamera mCamera;

    protected final int WIDTH=1280,HEIGHT=720;
    protected GlobalSettings mSave;

    Texture mFon,mText;
    protected ButtonWidget mOk;
    protected ButtonWidget mExit;
    protected GameProcess mGameProc;

    Texture mBack;

    //public void SetDrop(final Drop t){ this.game = t;}

    public AbstractBanner(final Drop game, GlobalSettings mSave, GameProcess mGameProc) {
        this.game = game;
        this.mSave = mSave;
        this.mGameProc = mGameProc;
        mCamera = new OrthographicCamera();
        if (mGameProc!=null)
            mCamera.setToOrtho(false,mGameProc.getmModel().getRealWidth(),mGameProc.getmModel().getRealHeight());
        else
            mCamera.setToOrtho(false,1280,720);
        mFon = getFon();
        if (getOk()!=null){
            mOk = new ButtonWidget(getOk(),WIDTH/2-mFon.getWidth()/2 + mFon.getWidth() - 100 - getOk().getWidth(),
                                           HEIGHT/2-mFon.getHeight()/2 +25,
                    WIDTH,HEIGHT);
        }
        else {
            mOk = null;
        }
        if (getExit()!=null){
            mExit = new ButtonWidget(getExit(),WIDTH/2-mFon.getWidth()/2 + 100,
                    HEIGHT/2-mFon.getHeight()/2 +25,
                    WIDTH,HEIGHT);
        }
        else {
            mExit = null;
        }
        mText = getText();
        mBack = new Texture("fon.png");
    }

    abstract public Texture getFon();
    abstract public void otherRender();
    abstract public Texture getOk();
    abstract public Texture getExit();
    abstract public Texture getText();

    @Override
    public void show() {

    }

    abstract void okCode();
    abstract void exitCode();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        

        mCamera.update();

        game.mBatch.setProjectionMatrix(mCamera.combined);
        game.mBatch.begin();


        game.mBatch.draw(mBack, 0,0);

        if (mGameProc!=null) {
            game.mBatch.draw(mGameProc.mBACK.getTexture(), mGameProc.mBACK.getX(), mGameProc.mBACK.getY());
            game.mBatch.draw(mGameProc.mRETURN.getTexture(), mGameProc.mRETURN.getX(), mGameProc.mRETURN.getY());
            game.mBatch.draw(mGameProc.mINFO.getTexture(), mGameProc.mINFO.getX(), mGameProc.mINFO.getY());
            game.mBatch.draw(mGameProc.mVOLUMN.getTexture(), mGameProc.mVOLUMN.getX(), mGameProc.mVOLUMN.getY());

            for (int i = 0; i < mGameProc.getmModel().Size(); i++) {
                for (int j = 0; j < mGameProc.getmModel().Size(); j++) {
                    if (mGameProc.getmModel().getState(i, j))
                        game.mBatch.draw(mGameProc.mTrueImage, mGameProc.getmModel().getRealX(i, j), mGameProc.getmModel().getRealY(i, j));
                    else
                        game.mBatch.draw(mGameProc.mFalseImage, mGameProc.getmModel().getRealX(i, j), mGameProc.getmModel().getRealY(i, j));

                }
            }

        }
            game.mBatch.draw(mFon, WIDTH / 2 - mFon.getWidth() / 2, HEIGHT / 2 - mFon.getHeight() / 2);

        if (mOk!=null)game.mBatch.draw(mOk.getTexture(), mOk.getX(), mOk.getY());
        if (mExit!=null)game.mBatch.draw(mExit.getTexture(), mExit.getX(), mExit.getY());

        game.mBatch.end();


        otherRender();

        if (Gdx.input.justTouched() ) {


            if (mOk!=null && mOk.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                okCode();
            }

            if (mExit!= null &&mExit.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                exitCode();
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
