package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public abstract class AbstractModel implements IModel {
    private int mLevel;
    private int mPodLevel;
    private int mWidth;
    private int mHeight;
    protected boolean[][] mMass;
    protected ArrayList<boolean[][]> mMassState;
    private int SIZE;
    private int mRealWidthTexture;
    private int DELTA = 3;

    public AbstractModel(int mLevel, int mPodLevel, int mWidth, int mHeight)
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
            case 2: SIZE = 6; break;
            case 3: SIZE = 7; break;
            default: SIZE=3; break;
        }

        mRealWidthTexture = Integer.parseInt(getTextureNameSize());

        mMass = new boolean[SIZE][SIZE];
        mMassState = new ArrayList<boolean[][]>();

        initMass();

        boolean[][] b = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                b[i][j] = mMass[i][j];
            }

        mMassState = new ArrayList<boolean[][]>();
        mMassState.add(b.clone());
        System.out.println(mMassState.size());
    }

    public AbstractModel(AbstractModel t)
    {
        this.mLevel = t.mLevel;
        this.mWidth = t.mWidth;
        this.mHeight = t.mHeight;
        this.mPodLevel = t.mPodLevel;
        mRealWidthTexture = t.mRealWidthTexture;
        mMass=t.mMass;
        mMassState=t.mMassState;
        initMass();
    }

    abstract public void initMass();

    @Override
    abstract public boolean isWin();

    @Override
    abstract public boolean isLose();

    @Override
    abstract public void Info();

    @Override
    public void BackState() {
        if (mMassState.size() > 1) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    mMass[i][j] = mMassState.get(mMassState.size()-2)[i][j];
                }
            }
        }

        if (mMassState.size()>1)
            mMassState.remove(mMassState.size()-1);
    }

    @Override
    public void Menu() {

    }

    @Override
    public void Voluml() {

    }

    @Override
    public Vector2 getPointFromRealCoord(int x1, int y1) {
        int x=mWidth*x1/100,y=mHeight*y1/100;

        //System.out.println("x1 - " + x + "  y1 - " + y);

        System.out.println(" "+getRealX(0,0) + "   " + x);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {



                if ((getRealX(i,j)-DELTA/2 <= x && getRealX(i,j)+mRealWidthTexture + DELTA/2 >x) &&
                    (getRealY(i,j)-DELTA/2 <= y && getRealY(i,j)+mRealWidthTexture + DELTA/2 >y))
                    return new Vector2(i,j);
            }
        }

        return null;
    }

    @Override
    public void setState(int x, int y) {
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

    @Override
    public int getRealX(int x, int y) {
        return  mWidth/2 - (mRealWidthTexture*SIZE/2 + SIZE*DELTA + DELTA) + mRealWidthTexture*x + x*DELTA + DELTA;
    }

    @Override
    public int getRealY(int x, int y) {
        return getRealHeight() - (mRealWidthTexture*Math.abs(y-SIZE+1) + Math.abs(y-SIZE+1)*DELTA + DELTA) - (mRealWidthTexture) ;
    }


    @Override
    public boolean getState(int x, int y) {
        return mMass[x][y];
    }

    @Override
    public int getRealHeight() {
        return mRealWidthTexture*SIZE + SIZE*DELTA + DELTA;
    }

    @Override
    public int getRealWidth() {
        return mWidth + SIZE*DELTA + DELTA;
    }

    @Override
    public int Size() {
        return SIZE;
    }

    @Override
    public String getTextureNameSize() {
        //int t = (mLevel==1)?3:Math.abs(mLevel-2);
        int t = mLevel;
        switch (t)
        {
            case 0:
                if (mPodLevel==0)
                    return String.valueOf(mHeight/3);
                else
                    return String.valueOf(mHeight/4);
            default: return String.valueOf(mHeight/(t+4));
        }
    }

    @Override
    public boolean equals(Object obj) {
        IModel t = (IModel)obj;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mMass[i][j] != t.getState(i, j))
                    return false;
            }
        }
        return true;
    }
}
