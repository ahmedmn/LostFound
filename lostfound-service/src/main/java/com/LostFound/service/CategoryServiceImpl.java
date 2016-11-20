package com.LostFound.service;

import com.LostFound.dao.CategoryDAO;
import com.LostFound.entity.Category;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Peter
 * Implementation of the {@link CategoryService}.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDAO categoryDao;

    public void createCategory(Category category) {
        categoryDao.create(category);
    }

    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
