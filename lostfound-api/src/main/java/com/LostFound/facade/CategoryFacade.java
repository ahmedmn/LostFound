package com.LostFound.facade;

import java.util.List;

import com.LostFound.dto.CategoryCreateDTO;
import com.LostFound.dto.CategoryDTO;

/**
 * @author Peter
 */
public interface CategoryFacade
{
    public Long createCategory(CategoryCreateDTO categoryCreateDTO);

    public List<CategoryDTO> getAllCategories();

    public CategoryDTO getCategoryById(Long id);

    public CategoryDTO getCategoryByName(String name);

    public void deleteCategory(Long productId);
}
