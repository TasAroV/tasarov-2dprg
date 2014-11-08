package com.base.game.gameobject.item;

public class SwordOfDebugging extends EquipableItem
{
	public static final float SIZE = 32;
	
	private int damage;
	
	public SwordOfDebugging(float x, float y)
	{
		init(x, y, 1.0f, 0.5f, 0, SIZE, SIZE, "The Legendary Sword Of Debugging", WEAPON_SLOT);
		damage = 3;
		
	}
}
