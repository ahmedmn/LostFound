package com.LostFound.service.facade;

import com.LostFound.dto.CategoryCreateDTO;
import com.LostFound.dto.CategoryDTO;
import com.LostFound.entity.Category;
import com.LostFound.facade.CategoryFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Peter
 */
@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade
{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BeanMappingService beanMappingService;


    public List<CategoryDTO> getAllCategories()
    {
        return beanMappingService.mapTo(categoryService.findAll(),CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id)
    {
        Category category = categoryService.findById(id);
        return (category == null) ? null : beanMappingService.mapTo(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryByName(String name)
    {
        Category category = categoryService.findByName(name);
        return (category == null) ? null : beanMappingService.mapTo(category,CategoryDTO.class);
    }

    @Override
    public Long createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        categoryService.createCategory(category);
        return  category.getId();
    }

    @Override
    public void deleteCategory(Long productId)
    {
        categoryService.deleteCategory(categoryService.findById(productId));
    }
}
