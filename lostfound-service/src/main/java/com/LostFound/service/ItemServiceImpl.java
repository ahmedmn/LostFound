package com.LostFound.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.LostFound.dao.ItemDAO;
import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import com.LostFound.exceptions.LostFoundServiceException;

/**
 *
 * @author Ahmed
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Inject
	private ItemDAO ItemDao;

	public Item findById(Long id) {
		return ItemDao.findById(id);
	}

	public List<Item> findByName(String name) {
		return ItemDao.findByName(name);
	}

	public List<Item> findByDescription(String description) {
		return ItemDao.findByDescription(description);
	}

	public List<Item> findByKeywords(String keywords) {
		return ItemDao.findByKeywords(keywords);
	}

	public List<Item> findByCategory(Category categories) {
		return ItemDao.findByCategory(categories);
	}

	public List<Item> findAll() {
		return ItemDao.findAll();
	}

	public Item createItem(Item item) {
		ItemDao.create(item);
		return item;
	}

	public void addCategory(Item item, Category category) {
		if (item.getCategories().contains(category)) {
			throw new LostFoundServiceException(
					"Item already contais this category. Item: " + item.getId() + ", category: " + category.getId());
		}
		item.addCategory(category);
                ItemDao.update(item);

	}

	public void deleteCategory(Item item, Category category) {
		item.deleteCategory(category);

	}

	public void deleteItem(Item item) {
		ItemDao.delete(item);

	}
}
