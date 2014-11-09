package com.base.game;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.game.gameobject.CookieMonster;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.item.Cube;
import com.base.game.gameobject.item.Wall;

public class Game
{
	public static Game game;
	
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> remove;
	private Player player;
	
	public void GenerateTestLevel()
	{
		objects.add(new Wall(200, 200, 1, 300));
		objects.add(new Wall(500, 200, 1, 100));
		objects.add(new Wall(500, 400, 1, 100));
		objects.add(new Wall(200, 200, 300, 1));
		objects.add(new Wall(200, 500, 100, 1));
		objects.add(new Wall(400, 500, 100, 1));

		objects.add(new Wall(300, 500, 1, 200));
		objects.add(new Wall(400, 500, 1, 200));

		objects.add(new Wall(200, 700, 100, 1));
		objects.add(new Wall(400, 700, 100, 1));
		objects.add(new Wall(200, 700, 1, 300));
		objects.add(new Wall(500, 700, 1, 300));
		objects.add(new Wall(200, 1000, 300, 1));

		objects.add(new Wall(500, 300, 100, 1));
		objects.add(new Wall(500, 400, 100, 1));
		objects.add(new Wall(600, 200, 1, 100));
		objects.add(new Wall(600, 400, 1, 100));
		objects.add(new Wall(600, 200, 300, 1));
		objects.add(new Wall(600, 500, 300, 1));
		objects.add(new Wall(900, 200, 1, 300));
	}
	
	public Game()
	{
		this.objects = new ArrayList<GameObject>();
		this.remove = new ArrayList<GameObject>();
		
		this.player = new Player(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2);
		
		this.objects.add(player);
		GenerateTestLevel();
//		objects.add(new Cube(32, 32));
//		objects.add(new CookieMonster(300, 500, 1));
//		objects.add(new Wall(200, 200, 1, 300));
		
	}
	
	public void getInput()
	{
		player.getInput();
	}
	
	public void update()
	{
		for(GameObject go : this.objects)
		{
			if(!go.getRemove())
				go.update();
			else
			{
				remove.add(go);
			}
		}
		
		for(GameObject go : remove)
		{
			objects.remove(go);
		}
	}
	
	public void render()
	{
		for(GameObject go : this.objects)
		{
			go.render();
		}
	}
	
	public static ArrayList<GameObject> sphereCollide(float x, float y, float radius)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		for(GameObject go : game.objects)
		{
			if(Util.dist(go.getX(), go.getY(), x, y) < radius)
			{
				res.add(go);
			}
		}
		
		return res;
	}

	public static ArrayList<GameObject> rectangleCollide(float x1, float y1, float x2, float y2)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		float sx = x2 - x1;
		float sy = y2 - y1;
		
		Rectangle collider = new Rectangle((int)x1, (int)y1, (int)sx, (int)sy);
		
		for(GameObject go : game.objects)
		{
			if(Physics.checkCollision(collider, go) != null)
			{
				res.add(go);
			}
		}
		
		return res;
	}
}
