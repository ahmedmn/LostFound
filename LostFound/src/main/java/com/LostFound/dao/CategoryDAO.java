/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dao;

import com.LostFound.entity.Category;
import java.util.List;

/**
 *
 * @author michal
 */
public interface CategoryDAO {
    
    /**
    * create method is used for insert information such as 
    * category id and name into table Category.
    * 
    * @param category is new category
    */
    public void create(Category category);
    
    /**
    * update method is used for update information such as name
    * according to category id in table Category.
    * 
    * @param category is category to be updated
    */
    public void update(Category category);
    
    /**
     * delete method is used for delete category according to category id in table category
     * 
     * @param category is category to be deleted
     * @throws IllegalArgumentException
     *             when there is null.
     */
    public void delete(Category category) throws IllegalArgumentException;
    
    /**
    * Returns information about a category corresponding to given category id. 
    * 
    * @param id is the category id.
    */
    public Category findById(Long id);
    
    /**
    * Returns information about a category corresponding to given category name. 
    * 
    * @param name is the category name.
    */
    public Category findByName(String name);
    
    /**
    * Returns information about all categories
    */
    public List<Category> findAll();
    
}
