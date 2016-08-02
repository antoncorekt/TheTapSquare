package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Anton on 13.06.2016.
 */
public class Model {

    private int mLevel;
    private int mPodLevel;
    private int mWidth;
    private int mHeight;
    private boolean[][] mMass;
    private boolean[][] mMassSAVE;
    private ArrayList<boolean[][]> mMassState;
    private int SIZE;
    private int mRealWidthTexture;
    private int DELTA = 3;

    public Model(int mLevel, int mPodLevel, int mWidth, int mHeight)
    {
        this.mLevel = mLevel;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mPodLevel = mPodLevel;


        switch (mLevel)
        {
            case 0:
                if (mPodLevel==0)
                    SIZE = 3;
                else
                    SIZE = 4;
                break;
            case 1: SIZE = 5; break;
            case 2:case 3: SIZE = 6; break;
            default: SIZE=3; break;
        }

        mRealWidthTexture = mHeight / SIZE;

        mMass = new boolean[SIZE][SIZE];
        mMassSAVE = new boolean[SIZE][SIZE];
        mMassState = new ArrayList<boolean[][]>();

        initMass();

        boolean[][] b = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                mMassSAVE[i][j] = mMass[i][j];
                b[i][j] = mMass[i][j];
            }

        mMassState = new ArrayList<boolean[][]>();
        mMassState.add(b.clone());
        System.out.println(mMassState.size());
    }

    public void initMass(Model t)
    {

    }

    public int getStartWidth(){return mWidth;}
    public int getStartHeight() {return mHeight;}

    public void initMass()
    {
        switch (mLevel) {
            case 0:
                for (int i = 0; i < 25; i++) {
                    setState(new Random().nextInt(SIZE),new Random().nextInt(SIZE));
                }
                break;
            case 2:
                for (int i = 0; i < 30; i++) {
                    setState(new Random().nextInt(SIZE),new Random().nextInt(SIZE));
                }
                break;
        }
    }

    public void setState(int x,int y)
    {
        boolean[][] b = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i==x || j==y)
                    mMass[i][j]=(!mMass[i][j]);
                    b[i][j] = mMass[i][j];
            }
        }
        mMassState.add( b.clone());
    }


    public void BackState(){
        if (mMassState.size() > 1) {
            System.out.println("changge");
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    mMass[i][j] = mMassState.get(mMassState.size()-2)[i][j];
                }
            }
        }

        if (mMassState.size()>1)
             mMassState.remove(mMassState.size()-1);
    }

    public boolean getState(int x, int y)
    {
        return mMass[x][y];
    }

    public int getRealX(int x, int y){
        return  mWidth/2 - (mRealWidthTexture*SIZE/2 + SIZE*DELTA + DELTA) + mRealWidthTexture*x + x*DELTA + DELTA;
    }

    public int getRealY(int x, int y){
        return getRealHeight() - (mRealWidthTexture*Math.abs(y-SIZE+1) + Math.abs(y-SIZE+1)*DELTA + DELTA) - (mRealWidthTexture) ;
    }

    Vector2 mForGetRealXY;
    public Vector2 getRealXY(int x, int y) {
        return (mForGetRealXY==null)?mForGetRealXY=
                new Vector2(mWidth/2 - (mRealWidthTexture*SIZE/2 + SIZE*DELTA + DELTA) + mRealWidthTexture*x + x*DELTA + DELTA,
                        getRealHeight() - (mRealWidthTexture*Math.abs(y-SIZE+1) + Math.abs(y-SIZE+1)*DELTA + DELTA) - (mRealWidthTexture)):mForGetRealXY;
    }

    public int getRealHeight(){return mRealWidthTexture*SIZE + SIZE*DELTA + DELTA;}
    public int getRealWidth(){return mWidth + SIZE*DELTA + DELTA;}

    public Vector2 getPointFromRealCoord(int x1, int y1)
    {
        int x=mWidth*x1/100,y=mHeight*y1/100;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((getRealXY(i,j).x-DELTA/2 <= x && getRealXY(i,j).x+mRealWidthTexture + DELTA/2 >x) && (getRealXY(i,j).y-DELTA/2 <=y && getRealXY(i,j).y+mRealWidthTexture + DELTA/2 >y))
                    return new Vector2(i,j);
            }
        }

        return null;
    }



    public String getTextureNameSize()
    {
        int t = (mLevel==1)?3:Math.abs(mLevel-2);
        switch (t)
        {
            case 0:
                if (mPodLevel==0)
                    return String.valueOf(720/3);
                else
                    return String.valueOf(720/4);
            default: return String.valueOf(720/(t+4));
        }
    }

    public int Size(){return SIZE;}

}
