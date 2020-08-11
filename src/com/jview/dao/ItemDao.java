package com.jview.dao;

import java.util.List;

import com.jview.model.Item;

public interface ItemDao {

	public List<Item> searchItem(String search);
	
	public int getLastItemId();
	
	public void updateItem(Item itm);
	
	public void createItem(Item itm);
	
	public void deleteItem(int id);
	
	public Item findByItmName(String itm_name);

	public List<Item> showItem(int proj_id);
}
