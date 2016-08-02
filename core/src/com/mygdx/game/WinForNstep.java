package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anton on 24.06.2016.
 */
public class WinForNstep extends AbstractModel {

    private int mStep;
    private final int PODLEVEL_2=3,PODLEVEL_3=(new Random().nextInt(2)==1)?15:14;

    public WinForNstep(int mLevel, int mPodLevel, int mWidth, int mHeight) {
        super(mLevel, mPodLevel, mWidth, mHeight);

        if (mPodLevel==1)
            mStep=PODLEVEL_2;
        else
            mStep=PODLEVEL_3;
            //mStep=4;
        initMass();

        boolean[][] b = new boolean[Size()][Size()];
        for (int i = 0; i < Size(); i++)
            for (int j = 0; j < Size(); j++) {
                b[i][j] = mMass[i][j];
            }

        mMassState = new ArrayList<boolean[][]>();
        mMassState.add(b.clone());
    }

    // мутод возращает какому из подуравней пренадлежит обьект класса
    public int getPodLevel()
    {
        return (mStep==PODLEVEL_2)?1:2;
    }

    public WinForNstep(AbstractModel t) {
        super(t);
    }

    // метод возращает сколько ходов осталось
    public int getmStep(){
        if (mMassState.size()-1<mStep)
            return mStep-(mMassState.size()-1);
        else
            return 0;
    }

    @Override
    public void initMass() {

        //Генерация начального состояния для выигрыша (рандомно, либо 1 либо 0)
        boolean b = (new Random().nextInt(2)==1);
        for (int i = 0; i < this.Size(); i++)
            for (int j = 0; j < this.Size(); j++)
                mMass[i][j] = b;

        //Начальное состояние поля. Для под уровня 2 - mStep изменений, для 1 - 7
        int t = (getPodLevel()==1)?mStep:7;

        for (int l = 0; l < mStep; l++) {
            setState(new Random().nextInt(this.Size()),new Random().nextInt(this.Size()));
        }
    }

    @Override
    public boolean isWin() {
        if (!isLose()) {
            boolean etalon = mMass[0][0];
            for (int i = 0; i < this.Size(); i++) {
                for (int j = 0; j < this.Size(); j++) {
                    if (mMass[i][j] != etalon)
                        return false;
                }
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean isLose() {
        return mMassState.size()-1>mStep;
    }

    @Override
    public void Info() {

    }

    public void restartGame()
    {
        for (int i = 0; i < this.Size(); i++) {
            for (int j = 0; j < this.Size(); j++) {
                mMass[i][j] = mMassState.get(0)[i][j];

            }
        }
        boolean[][] b = new boolean[Size()][Size()];
        for (int i = 0; i < Size(); i++)
            for (int j = 0; j < Size(); j++) {
                b[i][j] = mMass[i][j];
            }

        mMassState = new ArrayList<boolean[][]>();
        mMassState.add(b.clone());
    }
}
