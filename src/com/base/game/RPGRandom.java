package com.base.game;

import java.util.Random;

public class RPGRandom extends Random
{
	private long[] state;
	private int index;
	
	public RPGRandom(int seed)
	{
		state = new long[16];
		index = 0;
		
		seed = Math.abs(seed);
		
		for(int i = 0; i < 16; i++)
		{
			state[i] = (seed + 1) * ((seed + 1) << 2) * i;
		}
	}
	
	public RPGRandom()
	{
		this((int)System.currentTimeMillis());
	}
	
	@Override
	protected int next(int nbits)
	{
		long a, b, c, d;
		a = state[index];
		c = state[(index + 13) & 15];
		b = a ^ c ^ (a << 16) ^ (c << 15);
		c = state[(index + 9) & 15];
		c ^= (c >> 11);
		a = state[index] = b ^ c;
		d = a ^ ((a << 15) & 0xDA442D24L);
		index = (index + 15) & 15;
		a = state[index];
		state[index] = a ^ b ^ d ^ (a << 2) ^ (b << 18) ^ (c << 28);
		return (int)state[index];
	}
}
