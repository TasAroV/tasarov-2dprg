package com.base.game;

import java.util.Random;

public class RPGRandom
{
	private static Random rand;
	
	public static void initRand()
	{
		rand = new Random();
	}
	
	public static int nextInt()
	{
		return rand.nextInt();
	}
	
	public static double nextDouble()
	{
		return rand.nextDouble();
	}
	
	public static int nextInt(int cap)
	{
		return (int)((rand.nextInt() / Integer.MAX_VALUE) * (double)cap);
	}
	
	public static double nextDouble(double cap)
	{
		return rand.nextDouble() * cap;
	}
}
