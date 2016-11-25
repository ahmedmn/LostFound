package com.LostFound.service.facade;

import com.LostFound.dto.ItemCreateDTO;
import com.LostFound.dto.ItemDTO;
import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import com.LostFound.facade.ItemFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.CategoryService;
import com.LostFound.service.ItemService;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

/**
 * @author bokos
 */
@Service
@Transactional
public class ItemFacadeImpl implements ItemFacade {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BeanMappingService beanMappingService;

    public Long createItem(ItemCreateDTO p) {
        Item mappedItem = beanMappingService.mapTo(p, Item.class);
        Item newItem = itemService.createItem(mappedItem);
        return newItem.getId();
    }

    public void addCategory(Long ItemId, Long categoryId) {
        itemService.addCategory(itemService.findById(ItemId), categoryService.findById(categoryId));
    }

    public void deleteCategory(Long productId, Long categoryId) {
        itemService.deleteCategory(itemService.findById(productId), categoryService.findById(categoryId));
    }

    public void deleteProduct(Long productId) {
        itemService.deleteItem(itemService.findById(productId));
    }

    public List<ItemDTO> getAllItems() {
        return beanMappingService.mapTo(itemService.findAll(), ItemDTO.class);
    }

    public List<ItemDTO> getItemsByCategory(String categoryName) {
        Category category = categoryService.findByName(categoryName);
        return beanMappingService.mapTo(itemService.findByCategory(category), ItemDTO.class);
    }

    public List<ItemDTO> getItemsByKeywords(String Keywords) {
        return beanMappingService.mapTo(itemService.findByKeywords(Keywords), ItemDTO.class);
    }

    public List<ItemDTO> getItemsByDescription(String Description) {
        return beanMappingService.mapTo(itemService.findByDescription(Description), ItemDTO.class);
    }

    public List<ItemDTO> getItemsByName(String ItemName) {
        return beanMappingService.mapTo(itemService.findByName(ItemName), ItemDTO.class);
    }

    public ItemDTO getItemWithId(Long id) {
        return beanMappingService.mapTo(itemService.findById(id), ItemDTO.class);
    }
}
