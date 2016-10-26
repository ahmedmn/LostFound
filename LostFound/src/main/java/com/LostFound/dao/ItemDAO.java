package com.LostFound.dao;

import java.util.List;
import com.LostFound.entity.Item;

/**
 * ItemDao performs some basic operations such as insert, update, delete and
 * find data in Item table .
 * 
 * @author Ahmed
 **/

public interface ItemDAO {

	/**
	 * create method is used for insert information such as Item id, name, Description
	 * and Keywords into table Item.
	 * 
	 * @param item
	 *            is the Item class.
	 */
	public void create(Item p);

	/**
	 * update method is used for update information such as name, Description
	 * and Keywords according to item id in table Item.
	 * 
	 * @param item
	 *            is the Item class.
	 */
	public Item update(Item p);

	/**
	 * delete method is used for delete items according to item id in table item
	 * 
	 * @param item
	 *            is the Item class.
	 * @throws IllegalArgumentException
	 *             when there is null.
	 */
	public void delete(Item p) throws IllegalArgumentException;

	/**
	 * Returns information about a item corresponding to given item id. Result
	 * is always a record about each item such as id, name, Description and Keywords.
	 * 
	 * @param id
	 *            is the item id.
	 */
	public Item findById(int id);

	/**
	 * Returns the list of all information about each item. Result is a list
	 * about each item such as id, name, Description and Keywords.
	 */
	public List<Item> findByAll();

	/**
	 * Returns the list of all information about each item. Result is a list
	 * about each item such as id, name, Description and Keywords corresponding to input
	 * item name condition.
	 */
	public List<Item> findByName(String name);

}
