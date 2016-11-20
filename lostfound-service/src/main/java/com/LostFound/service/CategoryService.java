package com.LostFound.service;

import com.LostFound.entity.Category;
import com.LostFound.entity.Item;

import java.util.List;

/**
 * @author Peter
 * An interface that defines a service access to the {@link Category} entity.
 */
public interface CategoryService {

    /**
     * Create method is used to create new category in system
     *
     * @param category is new category
     */
    void createCategory(Category category);


    /**
     * Delete method is used to delete existing category from system
     *
     * @param category is category to be deleted
     */
    void deleteCategory(Category category);


    /**
     * FindById method is used to return existing category with specified id
     *
     * @param id is the category id.
     * @return category with specified id
     */
    Category findById(Long id);


    /**
     * FindByName method is used to return existing category with specified name
     *
     * @param name is the category name.
     * @return category with specified name
     */
    Category findByName(String name);


    /**
     * FindAll method is used to return all existing categories in system
     *
     * @return list of all categories
     */
    List<Category> findAll();
}
