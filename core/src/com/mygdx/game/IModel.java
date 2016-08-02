package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anton on 22.06.2016.
 */
public interface IModel {
    boolean isWin();
    boolean isLose();
    void Info();
    void BackState();
    void Menu();
    void Voluml();
    Vector2 getPointFromRealCoord(int x1, int y1);
    int getRealX(int x, int y);
    int getRealY(int x, int y);
    void setState(int x, int y);
    boolean getState(int x, int y);
    int getRealHeight();
    int getRealWidth();
    int Size();
    String getTextureNameSize();
    boolean equals(Object obj);
}
