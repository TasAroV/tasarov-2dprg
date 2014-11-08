package com.base.game.gameobject;


public class CookieMonster extends Enemy
{
	public static final int SIZE = 32;
	
	public CookieMonster(float x, float y, int level)
	{
		super(level);
		this.init(x, y, 0.2f, 0.2f, 1f, SIZE, SIZE, ENEMY_ID);
		setAttackDelay(500);
		setAttackDamage(1);
	}
}
