package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Anton on 22.06.2016.
 */
public class GameProcess implements Screen {
    final Drop game;
    final private int mLevel;
    private int mPodLevel;
    private OrthographicCamera mCamera;

    public Texture mTrueImage;
    public Texture mFalseImage;
    private Texture mFon;

    public ButtonWidget mBACK;
    public ButtonWidget mRETURN;
    public ButtonWidget mVOLUMN;
    public ButtonWidget mINFO;

    private IModel mModel;
    Vector3 mTouchPos;

    private GlobalSettings mSave;

    Sound mDropSound;
    private float delta;
    Sound sound, soundWin, soundOpen;

    public GameProcess(final Drop game, final int mLevel, final int mPodLevel, GlobalSettings mSave) {
        this.game = game;
        this.mLevel = mLevel;
        this.mPodLevel = mPodLevel;
        this.mSave = mSave;

        switch (mPodLevel)
        {
            case 0: mModel = new SimpleGame(mLevel,mPodLevel,1280,720);
                    break;
            case 1:case 2:mModel = new WinForNstep(mLevel,mPodLevel,1280,720);
                break;
            case 3:mModel = new RepeatGame(mLevel,mPodLevel,1280,720);
                break;

        }
        mTouchPos =  new Vector3();


        mRETURN = new ButtonWidget(new Texture("buttons/BUT_MENUEXIT.png"),mModel.getRealWidth()-new Texture("buttons/BUT_MENUEXIT.png").getWidth(), 720-new Texture("buttons/BUT_BACKMENU.png").getHeight()-15,mModel.getRealWidth(),mModel.getRealHeight());
        mBACK = new ButtonWidget(new Texture("buttons/BUT_BACKHOD.png"),mRETURN.getX(), mRETURN.getY()-mRETURN.getTexture().getHeight()-10,mModel.getRealWidth(),mModel.getRealHeight());
        mINFO = new ButtonWidget(new Texture("buttons/BUT_INFO.png"),0, 720-new Texture("buttons/BUT_INFO.png").getHeight()-15,mModel.getRealWidth(),mModel.getRealHeight());

        String s="";
        if (mSave.isVolumnON())
            s="ON";
        else
            s="OFF";

        mVOLUMN = new ButtonWidget(new Texture("buttons/BUT_VOLUMN_"+s+".png"),0, mINFO.getY()-mINFO.getTexture().getHeight()-10,mModel.getRealWidth(),mModel.getRealHeight());

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false,mModel.getRealWidth(),mModel.getRealHeight());
        sound = Gdx.audio.newSound(Gdx.files.internal("zvuk.mp3"));

        soundWin = Gdx.audio.newSound(Gdx.files.internal("vin.mp3"));
        soundOpen = Gdx.audio.newSound(Gdx.files.internal("open.mp3"));

        mTrueImage = new Texture("sq/sq_"+mSave.getSqStyle()+"_"+mModel.getTextureNameSize()+"_1.png");
        mFalseImage = new Texture("sq/sq_"+mSave.getSqStyle()+"_"+mModel.getTextureNameSize()+"_0.png");
        mFon = new Texture("fon.png");

    }

    public GameProcess(GameProcess t) {
        this.mLevel = t.mLevel;
        mModel = t.mModel;
        mCamera = new OrthographicCamera();

        this.mSave = t.mSave;

        mCamera.setToOrtho(false,mModel.getRealWidth(),mModel.getRealHeight());

        this.game = t.game;

        mBACK = t.mBACK;
        mRETURN = t.mRETURN;
        mVOLUMN = t.mVOLUMN;
        mINFO = t.mINFO;


        mTouchPos = t.mTouchPos;

        mTrueImage = t.mTrueImage;
        mFalseImage = t.mFalseImage;
        mFon= t.mFon;

        sound = Gdx.audio.newSound(Gdx.files.internal("zvuk.mp3"));
        soundWin = Gdx.audio.newSound(Gdx.files.internal("vin.mp3"));
        soundOpen = Gdx.audio.newSound(Gdx.files.internal("open.mp3"));

    }


    @Override
    public void show() {

    }

    public IModel getmModel(){return mModel;}

    Vector2 mTouchPoint;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mCamera.update();

        game.mBatch.setProjectionMatrix(mCamera.combined);
        game.mBatch.begin();

        game.mBatch.draw(mFon, 0, 0);
        game.mBatch.draw(mBACK.getTexture(), mBACK.getX(), mBACK.getY());
        game.mBatch.draw(mRETURN.getTexture(), mRETURN.getX(), mRETURN.getY());
        game.mBatch.draw(mINFO.getTexture(), mINFO.getX(), mINFO.getY());
        game.mBatch.draw(mVOLUMN.getTexture(), mVOLUMN.getX(), mVOLUMN.getY());

        for (int i = 0; i < mModel.Size(); i++) {
            for (int j = 0; j < mModel.Size(); j++) {
                if (mModel.getState(i, j))
                    game.mBatch.draw(mTrueImage, mModel.getRealX(i, j), mModel.getRealY(i, j));
                else
                    game.mBatch.draw(mFalseImage, mModel.getRealX(i, j), mModel.getRealY(i, j));

            }
        }

        game.mBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            mSave.saveLevelOnPreference(mLevel,mPodLevel);
            System.out.println(mLevel + "" + mPodLevel);
            game.setScreen(new BannerWin(game,mSave, this));
            dispose();
        }

        if (Gdx.input.justTouched()) {
            if (mBACK.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                mModel.BackState();
            }

            if (mRETURN.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                if (mSave.isVolumnON())
                    soundOpen.play(2.0f);
                game.setScreen(new BannerExit(game,mSave, this));
                dispose();
            }


            if (mINFO.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                if (mModel instanceof RepeatGame)
                {
                    game.setScreen(new BannerInfoRepeater(game,mSave, this,((RepeatGame) mModel).getRepeaterGame()));
                    dispose();
                }


                if (mSave.isVolumnON())
                    soundOpen.play(1.0f);

                if (mModel instanceof WinForNstep)
                {
                    game.setScreen(new BannerInfoSelectLevel(game,mSave, this));
                    dispose();
                }

                if (mModel instanceof SimpleGame)
                {
                    game.setScreen(new BannerInfoSimple(game,mSave, this));
                    dispose();
                }
            }

            if (mVOLUMN.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                String s="";
                mSave.switchVolumn();
                if (mSave.isVolumnON()) {
                    s = "ON";
                    System.out.println("was been on");
                }
                else {
                    System.out.println("was been off");
                    s = "OFF";
                }

                mVOLUMN.setTexture(new Texture("buttons/BUT_VOLUMN_"+s+".png"));

            }

            mTouchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            mCamera.unproject(mTouchPos);

            mTouchPoint = mModel.getPointFromRealCoord(Gdx.input.getX()*100/Gdx.graphics.getWidth(),Gdx.input.getY()*100/Gdx.graphics.getHeight());




            if (mTouchPoint!=null)
            {
                System.out.println(""+mTouchPoint.x+"  "+mTouchPoint.y);
                if (mSave.isVolumnON())
                    sound.play(1.0f);
                mModel.setState((int)(mTouchPoint.x),Math.abs((int)(mTouchPoint.y)-mModel.Size()+1));
            }

            if (mModel.isWin()){
                if (mModel instanceof RepeatGame) mPodLevel=3;

                if (mModel instanceof WinForNstep) mPodLevel =((WinForNstep)(mModel)).getPodLevel();

                mSave.saveLevelOnPreference(mLevel,mPodLevel);
                System.out.println(mLevel + "" + mPodLevel);
                if (mSave.isVolumnON())
                    soundWin.play(0.3f);
                game.setScreen(new BannerWin(game,mSave, this));
                dispose();

            }

            if (mModel.isLose()){
                System.out.println("LOSE");
                if (mModel instanceof WinForNstep)
                {
                    game.setScreen(new BannerLose(game,mSave, this));
                    dispose();
                }
            }

        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            mModel.BackState();
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
        sound.dispose();
        soundWin.dispose();
        soundOpen.dispose();
    }
}
