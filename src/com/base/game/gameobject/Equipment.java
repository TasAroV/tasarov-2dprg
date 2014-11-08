package com.base.game.gameobject;

import com.base.game.gameobject.item.EquipableItem;

public class Equipment
{
	private EquipableItem[] items;
	private Inventory inv;
	
	public Equipment(Inventory inv)
	{
		items = new EquipableItem[EquipableItem.NUM_SLOTS];
		this.inv = inv;
	}
	
	public boolean equip(EquipableItem item)
	{
		int index = item.getSlot();
		
		if(items[index] != null)
		{
			if(!deEquip(index))
			{
				return false;
			}
		}
		
		inv.remove(item);
		items[index] = item;
		return true;
	}
	
	public boolean deEquip(int slot)
	{
		if(inv.add(items[slot]))
		{
			items[slot] = null;
			return true;
		}
		
		return false;
	}
}
