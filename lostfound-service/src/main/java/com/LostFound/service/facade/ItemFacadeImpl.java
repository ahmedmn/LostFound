package com.LostFound.service.facade;

import com.LostFound.dto.ItemCreateDTO;
import com.LostFound.dto.ItemDTO;
import com.LostFound.facade.ItemFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BeanMappingService beanMappingService;

    public Long createItem(ItemCreateDTO p) {
        return null;
    }

    public void addCategory(Long ItemId, Long categoryId) {

    }

    public void deleteCategory(Long productId, Long categoryId) {

    }

    public void deleteProduct(Long productId) {

    }

    public List<ItemDTO> getAllItems() {
        return null;
    }

    public List<ItemDTO> getItemsByCategory(String categoryName) {
        return null;
    }

    public List<ItemDTO> getItemsByKeywords(String Keywords) {
        return null;
    }

    public List<ItemDTO> getItemsByDescription(String Description) {
        return null;
    }

    public List<ItemDTO> getItemsByName(String ItemName) {
        return null;
    }

    public ItemDTO getItemWithId(Long id) {
        return null;
    }
}

