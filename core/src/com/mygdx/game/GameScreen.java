package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


import java.awt.Point;
import java.util.Iterator;


public class GameScreen implements Screen {
	final Drop game;
	final private int mLevel;

	OrthographicCamera mCamera;

	Texture mTrueImage;
	Texture mFalseImage;
	Texture mBackground;
	ButtonWidget mBACK;
	ButtonWidget mRETURN;

	Sound mDropSound;
	Music mRainMusic;
	long mTimeDelay;

	Vector3 mTouchPos;

	private Model mModel;

	public GameScreen(final Drop drop,final int mLevel, final int mPodLevel) {
		this.mLevel = mLevel;
		mModel = new Model(mLevel,mPodLevel,1280,720);
		mCamera = new OrthographicCamera();



		mCamera.setToOrtho(false,mModel.getRealWidth(),mModel.getRealHeight());

		this.game = drop;

		mBACK = new ButtonWidget(new Texture("buttons/BUT_BACKMENU.png"),1280-new Texture("buttons/BUT_BACKMENU.png").getWidth(), 720-new Texture("buttons/BUT_BACKMENU.png").getHeight()-15,1280,720);
		mRETURN = new ButtonWidget(new Texture("buttons/BUT_RESTART.png"),0, mBACK.getY(),1280,720);



		mTouchPos = new Vector3();

		mTrueImage = new Texture("sq/sq_"+"NONE"+"_"+mModel.getTextureNameSize()+"_1.png");
		mFalseImage = new Texture("sq/sq_"+"NONE"+"_"+mModel.getTextureNameSize()+"_0.png");
		mBackground = new Texture("backgroundScreen.png");


	}

	public GameScreen(GameScreen t) {
		this.mLevel = t.mLevel;
		mModel = t.mModel;
		mCamera = new OrthographicCamera();



		mCamera.setToOrtho(false,mModel.getRealWidth(),mModel.getRealHeight());

		this.game = t.game;

		mBACK = t.mBACK;
		mRETURN = t.mRETURN;



		mTouchPos = t.mTouchPos;

		mTrueImage = t.mTrueImage;
		mFalseImage = t.mFalseImage;
		mBackground = t.mBackground;

	}


	@Override
	public void show() {
		//mRainMusic.play();
	}

	Vector2 mTouchPoint;
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mCamera.update();

		game.mBatch.setProjectionMatrix(mCamera.combined);
		game.mBatch.begin();

		game.mBatch.draw(mBackground, 0, 0);

		for (int i = 0; i < mModel.Size(); i++) {
			for (int j = 0; j < mModel.Size(); j++) {
					if (mModel.getState(i, j))
						game.mBatch.draw(mTrueImage, mModel.getRealXY(i, j).x, mModel.getRealXY(i, j).y);
					else
						game.mBatch.draw(mFalseImage, mModel.getRealXY(i, j).x, mModel.getRealXY(i, j).y);

			}
		}

		game.mBatch.end();



		if (Gdx.input.justTouched()) {

			mTouchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			mCamera.unproject(mTouchPos);

			mTouchPoint = mModel.getPointFromRealCoord(Gdx.input.getX()*100/Gdx.graphics.getWidth(),Gdx.input.getY()*100/Gdx.graphics.getHeight());

			if (mTouchPoint!=null)
			{
				mModel.setState((int)(mTouchPoint.x),Math.abs((int)(mTouchPoint.y)-mModel.Size()+1));
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
	public void dispose()
	{
		//super.dispose();
		//mDropSound.dispose();
		//mBatch.dispose();
		//mRainMusic.dispose();
		//mDropSound.dispose();
	}
}
