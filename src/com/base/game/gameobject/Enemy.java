package com.base.game.gameobject;

import java.util.ArrayList;

import com.base.engine.GameObject;
import com.base.engine.Sprite;
import com.base.game.Delay;
import com.base.game.Game;
import com.base.game.Time;
import com.base.game.Util;

public class Enemy extends StatObject
{
	public static final float DAMPING = 0.5f;
	
	private StatObject target;
	private int attackRange;
	private int attackDamage;
	private Delay attackDelay;
	private float sightRange;
	
	public Enemy(int level)
	{
		stats = new Stats(level, false);
		target = null;
		attackDelay = new Delay(500);
		attackRange = 48;
		attackDelay.terminate();
		sightRange = 128f;
	}
	
	@Override
	public void update()
	{
		if(target == null)
		{
			Look();
		}
		else
		{
			if(Util.LineOfSight(this, target) && Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= attackRange)
			{
				if(attackDelay.isOver())
				{
					Attack();
				}
			}
			else
			{
				Chase();
			}
		}
		
		if(stats.getCurrentHealth() <= 0)
		{
			Death();
		}
	}
	
	protected void Attack()
	{
		getTarget().damage(getAttackDamage());
		System.out.println("We're hit! : " + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
		attackDelay.restart();
	}
	
	protected void Death()
	{
		remove();
	}
	
	protected void Look()
	{
		ArrayList<GameObject> objects = Game.sphereCollide(x, y, sightRange);
		
		for(GameObject go : objects)
		{
			if(go.getType() == PLAYER_ID)
			{
				setTarget((StatObject)go);
			}
		}
	}
	
	protected void Chase()
	{
		float nextX = (getTarget().getX() - x);
		float nextY = (getTarget().getY() - y);
		
		float maxSpeed = getStats().getSpeed() * DAMPING;
		
		if(nextX > maxSpeed)
		{
			nextX = maxSpeed;
		}
		if(nextX < -maxSpeed)
		{
			nextX = -maxSpeed;
		}
		
		if(nextY > maxSpeed)
		{
			nextY = maxSpeed;
		}
		if(nextY < -maxSpeed)
		{
			nextY = -maxSpeed;
		}
		
		x += nextX * Time.getDelta();
		y += nextY * Time.getDelta();
	}
	
	public void setTarget(StatObject go)
	{
		target = go;
	}
	
	public StatObject getTarget()
	{
		return target;
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public void setAttackRange(int range)
	{
		attackRange = range;
	}
	
	public void setAttackDelay(int time)
	{
		attackDelay = new Delay(time);
		attackDelay.terminate();
	}
	
	public int getAttackDamage()
	{
		return attackDamage;
	}
	
	public void setAttackDamage(int amt)
	{
		this.attackDamage = amt;
	}
	
	public void setSightRange(float dist)
	{
		sightRange = dist;
	}
	
	@Override
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
	{
		this.x = x;
		this.y = y;
		this.type = ENEMY_ID;
		this.spr = new Sprite(r, g, b, sx, sy);
	}
}
