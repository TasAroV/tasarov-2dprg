package com.base.game;

public class Delay
{
	private int length;
	boolean started;
	private long endTime;
	
	public Delay(int length)
	{
		this.length = length;
		started = false;
	}
	
	public boolean isOver()
	{
		if(!started) return false;
		
		return (Time.getTime() > endTime);
	}
	
	public boolean isActive()
	{
		return started;
	}
	
	public void restart()
	{
		started = true;
		endTime = length * 1000000 + Time.getTime();
	}
	
	public void stop()
	{
		started = false;
	}
	
	public void terminate()
	{
		started = true;
		endTime = 0;
	}
}
