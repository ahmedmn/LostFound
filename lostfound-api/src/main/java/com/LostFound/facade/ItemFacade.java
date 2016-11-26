package com.LostFound.facade;

import java.util.List;

import com.LostFound.dto.ItemCreateDTO;
import com.LostFound.dto.ItemDTO;


public interface ItemFacade {
	public Long createItem(ItemCreateDTO p);
	public void addCategory(Long itemId, Long categoryId);
	public void deleteCategory(Long itemId, Long categoryId);
	public void deleteItem(Long itemId);
	public List<ItemDTO> getAllItems();
	public List<ItemDTO> getItemsByCategory(String categoryName);
	public List<ItemDTO> getItemsByKeywords(String Keywords);
	public List<ItemDTO> getItemsByDescription(String Description);
	public List<ItemDTO> getItemsByName(String ItemName);
	public ItemDTO getItemWithId(Long id);
}
