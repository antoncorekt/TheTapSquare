package com.mygdx.game;

import java.util.Random;

/**
 * Created by Anton on 24.06.2016.
 */
public class RepeatGame extends AbstractModel {
    IModel mRepeat;

    public RepeatGame(int mLevel, int mPodLevel, int mWidth, int mHeight) {
        super(mLevel, mPodLevel, mWidth, mHeight);

        mRepeat =  new SimpleGame(mLevel, mPodLevel, 318, 318);
        if (mLevel<2)
        ((SimpleGame) mRepeat).getStartState(-1);
    }

    public RepeatGame(AbstractModel t) {
        super(t);
    }

    @Override
    public void initMass() {
        //getStartState(1);
        for (int i = 0; i < this.Size()*this.Size()+1; i++) {
            setState(new Random().nextInt(this.Size()),new Random().nextInt(this.Size()));
        }
    }

    @Override
    public boolean isWin() {
        return this.equals(mRepeat);
    }

    @Override
    public boolean isLose() {
        return false;
    }

    @Override
    public void Info() {

    }




    public IModel getRepeaterGame(){return mRepeat;}
}
