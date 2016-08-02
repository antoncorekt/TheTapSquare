package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Anton on 19.06.2016.
 */
public class GameMenu implements Screen {
    final Drop game;
    OrthographicCamera camera;

    SpriteBatch mBatch;
    Texture mFON, mTextEASY,mTextNORMAL, mTextHARD, mTextUNREAL;
    ButtonWidget mBACK;
    ButtonWidget mRETURN;
    SelectLevelWidget mEASY;
    SelectLevelWidget mNORMAL;
    SelectLevelWidget mHARD;
    SelectLevelWidget mUNREAL;
    ArrayList<SelectLevelWidget> mWidgets;
    ArrayList<Texture> mTexts;


    final int WIDTH=1300, HEIGHT=740;

    GlobalSettings mSave;

    public GameMenu(Drop game, GlobalSettings mSave) {
        this.game = game;
        this.mSave = mSave;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        mBatch =  new SpriteBatch();

        mFON = new Texture("fon.png");
        mBACK = new ButtonWidget(new Texture("buttons/BUT_BACKMENU.png"),WIDTH-new Texture("buttons/BUT_BACKMENU.png").getWidth(), HEIGHT-new Texture("buttons/BUT_BACKMENU.png").getHeight()-15,WIDTH,HEIGHT);
        mRETURN = new ButtonWidget(new Texture("buttons/BUT_RESTART.png"),0, mBACK.getY(),WIDTH,HEIGHT);

        mTexts = new ArrayList<Texture>();
        mTexts.add(new Texture("game_menu/TEXT_easy.png"));
        mTexts.add(new Texture("game_menu/TEXT_normal.png"));
        mTexts.add(new Texture("game_menu/TEXT_hard.png"));
        mTexts.add(new Texture("game_menu/TEXT_unreal.png"));

        mWidgets = new ArrayList<SelectLevelWidget>();
        for (int i = 0; i < 4; i++) {
            mWidgets.add(new SelectLevelWidget(i,WIDTH,HEIGHT,mSave));
        }
    }

    @Override
    public void show() {

    }

    public void updateWidgets()
    {
        for (int i = 0; i < 4; i++) {
            mWidgets.get(i).setNullTexture();
            mWidgets.get(i).getTexture();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.mBatch.setProjectionMatrix(camera.combined);
        game.mBatch.begin();

        game.mBatch.draw(mFON, 0, 0);
        game.mBatch.draw(mBACK.getTexture(), mBACK.getX(), mBACK.getY());
        game.mBatch.draw(mRETURN.getTexture(), mRETURN.getX(), mRETURN.getY());


        for (int i = 0; i < 4; i++) {
            game.mBatch.draw(mWidgets.get(i).getTexture(),  mWidgets.get(i).getX(), mWidgets.get(i).getY());
            game.mBatch.draw(mTexts.get(i), mWidgets.get(i).getXYText(mTexts.get(i)).x, mWidgets.get(i).getXYText(mTexts.get(i)).y);
        }



        game.mBatch.end();

        if (Gdx.input.justTouched())
        {

            if (mBACK.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }

            if (mRETURN.isTouch(Gdx.input.getX(),Gdx.input.getY()))
            {
                game.setScreen(new BannerExitSureToGameMenu(game,mSave,null));
                dispose();
            }



            for (int i = 0; i < 4; i++) {
                if ((Gdx.input.getX()*100/Gdx.graphics.getWidth()>mWidgets.get(i).getX()*100/WIDTH
                        && Gdx.input.getX()*100/Gdx.graphics.getWidth()<((mWidgets.get(i).getX()+mWidgets.get(i).getTextureWidth())*100/WIDTH)) &&
                        (Gdx.input.getY()*100/Gdx.graphics.getHeight()>mWidgets.get(i).getY()*100/HEIGHT &&
                       Gdx.input.getY()*100/Gdx.graphics.getHeight()<((mWidgets.get(i).getY()+mWidgets.get(i).getmTextureHeight())*100/HEIGHT)))
                    {
                    int t = levelAdapter(mWidgets.get(i).getLevel(
                            (WIDTH/100*(Gdx.input.getX()*100/Gdx.graphics.getWidth())-mWidgets.get(i).getX()),
                            (HEIGHT/100*Gdx.input.getY()*100/Gdx.graphics.getHeight()-mWidgets.get(i).getY()))
                    );
                        if (t==80) {// если нажали
                            //System.out.println("comlevel -> " + mSave.getComplitedPodlevel((i==1)?3:Math.abs(i-2)));
                            switch (mSave.getComplitedPodlevel(((i==1)?3:Math.abs(i-2))))
                            {
                                case -1:
                                    if (mSave.isLevelAvaible(((i==1)?3:Math.abs(i-2)), 0))
                                        game.setScreen(new GameProcess(game,((i==1)?3:Math.abs(i-2)),0,mSave));
                                    break;
                                case 0:
                                    if (mSave.isLevelAvaible(((i==1)?3:Math.abs(i-2)), 1))
                                        game.setScreen(new GameProcess(game,((i==1)?3:Math.abs(i-2)),1,mSave));
                                    break;
                                case 1:
                                    if (mSave.isLevelAvaible(((i==1)?3:Math.abs(i-2)), 2))
                                        game.setScreen(new GameProcess(game,((i==1)?3:Math.abs(i-2)),2,mSave));

                                    break;
                                case 2:
                                    if (mSave.isLevelAvaible(((i==1)?3:Math.abs(i-2)), 3))
                                        //mSave.saveLevelOnPreference(((i==1)?3:Math.abs(i-2)), 3);
                                    game.setScreen(new GameProcess(game,((i==1)?3:Math.abs(i-2)),3,mSave));
                                    break;
                            }
                            dispose();
                        }
                        else {
                            if (mSave.isLevelAvaible(t / 10, t - (t / 10) * 10)) {
                                //mSave.saveLevelOnPreference(t / 10, t - (t / 10) * 10);
                                game.setScreen(new GameProcess(game, t / 10, t - (t / 10) * 10, mSave));
                                dispose();
                            }
                        }



                }
            }
            updateWidgets();

        }
    }

    // метод адаптед для информации о нажимаемом уровне, в связи с тем что координата Y в прорисовки виджета снизу вв верх
    // а в методах Gdx.graphics снизу вверх, но 0,1 и 2,3 расположены зеркально. Метод получвает правильные значения уровней
    public int levelAdapter(int t)
    {
        if (t/10==1)
            return 3*10+t-(t/10)*10;
        else
            return Math.abs(t/10-2)*10+t-(t/10)*10;
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
