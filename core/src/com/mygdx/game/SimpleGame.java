package com.mygdx.game;

import java.util.Random;

/**
 * Created by Anton on 22.06.2016.
 */
public class SimpleGame extends AbstractModel {
    public SimpleGame(int mLevel, int mPodLevel, int mWidth, int mHeight) {
        super(mLevel, mPodLevel, mWidth, mHeight);
    }

    @Override
    public void initMass() {
        //mMass[0][0] = !mMass[0][0];
        for (int i = 0; i < this.Size()*this.Size()+1; i++) {
            setState(new Random().nextInt(this.Size()),new Random().nextInt(this.Size()));
        }
    }

    @Override
    public boolean isWin() {
        boolean etalon = mMass[0][0];
        for (int i = 0; i <  this.Size(); i++) {
            for (int j = 0; j <  this.Size(); j++) {
               if (mMass[i][j]!=etalon)
                   return false;
            }
        }
        return true;
    }

    @Override
    public boolean isLose() {
        return false;
    }

    @Override
    public void Info() {

    }

    public void getStartState(int tt)
    {
        int t = (tt==-1)?new Random().nextInt(5):tt;
        for (int i = 0; i < Size(); i++)
            for (int j = 0; j < Size(); j++)
                mMass[i][j] = false;
        boolean etalon = !mMass[0][0];

        switch (t)
        {
            case 0:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if ((i==j)||(Math.abs(i-j)==Math.abs(1)))
                            mMass[i][j] = etalon;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if (j % 2 ==0 && i % 2 == 0)
                            mMass[i][j] = etalon;
                        if (j % 2 ==1 && i % 2 == 1)
                            mMass[i][j] = etalon;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if ((i<=j))
                            mMass[i][j] = etalon;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if ((i>=j))
                            mMass[i][j] = etalon;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if ((i>=Size()/2 && j % 2!=0))
                            mMass[i][j] = etalon;
                        if ((i<Size()/2 && j % 2==0))
                            mMass[i][j] = etalon;
                    }
                }
                break;
            default:
                for (int i = 0; i < Size(); i++) {
                    for (int j = 0; j < Size(); j++) {
                        if (i % 2 == 0 && j >= Size()/2)
                            mMass[i][j] = etalon;
                        if ((i<=j) && j <= Size()/2)
                            mMass[i][j] = etalon;
                    }
                }
                break;


        }
    }
}
