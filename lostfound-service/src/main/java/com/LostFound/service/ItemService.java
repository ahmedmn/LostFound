/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import java.util.List;

import com.LostFound.entity.Item;
import com.LostFound.entity.Category;

/**
 *
 * @author Ahmed
 */
public interface ItemService {
    public Item findById(Long id);
    public List<Item> findByName(String name);
    public List<Item> findByDescription(String description);
    public List<Item> findByKeywords(String keywords);
	public List<Item> findByCategory(Category categories);
	public List<Item> findAll();
	public Item createItem(Item item);
	public void addCategory(Item item, Category category);
	public void deleteCategory(Item item, Category category);
	public void deleteItem(Item item);
}
