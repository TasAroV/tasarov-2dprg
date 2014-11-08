package com.base.game.gameobject;

import com.base.game.RPGRandom;

public class StatScale
{
	public static final int NUM_STATS = 8;
	public static final double MIN_STATSCALE = 0.125;
	public static final double MAX_STAT_BOOST = 0.25;
	
	public static final int VITALITY = 0;
	public static final int SPEED = 1;
	// public static final int UNKNOWN1 = 2;
	// public static final int UNKNOWN2 = 3;
	public static final int STRENGTH = 4;
	public static final int PHYSICAL_DEFENCE = 5;
	public static final int MAGIC = 6;
	public static final int MAGIC_DEFENSE = 7;
	
	private double[] scales;
	private double[] scaleBonus;
	
	public StatScale()
	{
		scales = new double[NUM_STATS];
		scaleBonus = new double[NUM_STATS];
	}
	
	public void generateStatScale()
	{
		double sum = 0;
		
		for(int i = 0; i < NUM_STATS; i++)
		{
			double val = RPGRandom.nextDouble(1);
			scales[i] = val;
			sum += val * val;
		}
		
		sum = Math.sqrt(sum);
		
		for(int i = 0; i < NUM_STATS; i++)
		{
			scales[i] /= sum;
			if(scales[i] < MIN_STATSCALE)
			{
				generateStatScale();
				return;
			}
		}
	}
	
	public double getStatScale(int stat)
	{
		return scales[stat] + (scaleBonus[stat] * MAX_STAT_BOOST);
	}
	
	public void addScaleBonus(int stat, double bonus)
	{
		if(bonus > 1)
			bonus = 1;
		if(bonus < 0)
			bonus = 0;
		
		scaleBonus[stat] = bonus;
	}
	
	public String toString()
	{
		return "VIT " + scales[0] + "\nSPE " + scales[1] + "\nUN1 " + scales[2] + "\nUN2 " + scales[3] + "\nSTR " + scales[4] + "\nPDE " + scales[5] + "\nMAG "
				+ scales[6] + "\nMDE " + scales[7];
	}
}
