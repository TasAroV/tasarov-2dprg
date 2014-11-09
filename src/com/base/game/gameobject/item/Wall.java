package com.base.game.gameobject.item;

import com.base.engine.GameObject;

public class Wall extends GameObject
{
	public Wall(float x, float y, float sizeX, float sizeY)
	{
		init(x, y, 1.0f, 0.5f, 0, sizeX, sizeY, DEFAULTL_ID);
		setSolid(true);
	}
}
