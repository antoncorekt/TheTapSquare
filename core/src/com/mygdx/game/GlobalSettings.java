package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import sun.rmi.runtime.Log;


/**
 * Created by Anton on 19.06.2016.
 */
public class GlobalSettings {

    ArrayList<String> mLevels;
    Preferences mSave;

    public GlobalSettings()
    {
        mSave = Gdx.app.getPreferences("save.txt");

        if (!mSave.getBoolean(String.valueOf(0)))
        {
            System.out.println("нет первого уровень");
            //mSave.putBoolean(String.valueOf(0),true);
            clearSave();
            mSave.putInteger("BLOCK",0);
            mSave.putBoolean("VOLUMN",true);
            mSave.putBoolean("LAN",true);
            mSave.flush();
        }
        else
            System.out.println("уже есть первый уровень");



    }

    public boolean isEnglish() {return mSave.getBoolean("LAN");}
    public void setLanguage(){ mSave.putBoolean("LAN",!isEnglish());
        mSave.flush();}

    public String getLanguage(){return (isEnglish())?"en":"ru";}

    public String getSqStyle()
    {
        switch (mSave.getInteger("BLOCK"))
        {
            case 1: return "BORDER";
            case 2: return "BUT";
            default: return "NONE";
        }
    }

    public void setSqStyle(int t)
    {
        mSave.putInteger("BLOCK",t);
        mSave.flush();
    }

    public boolean isVolumnON()
    {
        return mSave.getBoolean("VOLUMN");
    }

    public void switchVolumn(){
        mSave.putBoolean("VOLUMN",!isVolumnON());
        mSave.flush();
    }

    public void clearSave()
    {

        mSave.clear();
        mSave.flush();
    }

    public void saveLevelOnPreference(int level, int pod_level) {
        boolean temp = mSave.getBoolean(String.valueOf(pod_level + level * 10));
        mSave.putBoolean(String.valueOf(pod_level + level * 10),true);
        mSave.flush();
    }

    public int getComplitedPodlevel(int level)
    {

        for (int i = 3; i >=0 ; i--) {
            if (mSave.getBoolean(String.valueOf(i + level*10)))
                return i;
        }
        return -1;
    }

    public boolean isLevelAvaible(int level, int pod_level)
    {
        if (level==0 && pod_level==0) return true;
        if (pod_level-1 < 0)
        {
            if (level-1<0)
                return mSave.getBoolean(String.valueOf(3));
            else
                return mSave.getBoolean(String.valueOf(3 + (level-1) * 10)) || mSave.getBoolean(String.valueOf(2 + (level-1) * 10));
        }
        else
            return mSave.getBoolean(String.valueOf(pod_level-1 + level * 10));
    }
}
