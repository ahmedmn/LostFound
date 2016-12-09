package com.LostFound.dao;

import com.LostFound.PersistanceApplicationContext;
import com.LostFound.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * CategoryDaoTest class is used to test functionalities of each Category.
 *
 * @author Bokos
 *
 */

@ContextConfiguration(classes = PersistanceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CategoryDaoTest  extends AbstractTestNGSpringContextTests{

    @Autowired
    private CategoryDAO categoryDao;

    @Test
    public void findAll(){
        Category cat1 = new Category();
        Category cat2 = new Category();
        cat1.setName("category1");
        cat2.setName("category2");

        categoryDao.create(cat1);
        categoryDao.create(cat2);

        List<Category> categories  = categoryDao.findAll();

        Assert.assertEquals(categories.size(), 2);

        Category cat1assert = new Category();
        Category cat2assert = new Category();
        cat1assert.setName("category1");
        cat2assert.setName("category2");

        Assert.assertTrue(categories.contains(cat1assert));
        Assert.assertTrue(categories.contains(cat2assert));
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nullCategoryNameNotAllowed(){
        Category cat = new Category();
        cat.setName(null);
        categoryDao.create(cat);
    }

    @Test(expectedExceptions = JpaSystemException.class)
    public void nameIsUnique(){
        Category cat = new Category();
        cat.setName("Cars");
        categoryDao.create(cat);

        Category cat1 = new Category();
        cat1.setName("Cars");
        categoryDao.create(cat1);
    }

    @Test()
    public void delete(){
        Category cat = new Category();
        cat.setName("Cars");
        categoryDao.create(cat);
        Assert.assertNotNull(categoryDao.findById(cat.getId()));
        categoryDao.delete(cat);
        Assert.assertNull(categoryDao.findById(cat.getId()));
    }

    @Test()
    public void update(){
        Category cat = new Category();
        cat.setName("Cars");
        categoryDao.create(cat);
        cat.setName("Toys");
        categoryDao.update(cat);
        Assert.assertEquals(categoryDao.findById(cat.getId()).getName(), "Toys");
    }


    @Test()
    public void savesName(){
        Category cat = new Category();
        cat.setName("category3");
        categoryDao.create(cat);
        Assert.assertEquals(categoryDao.findById(cat.getId()).getName(),"category3");
    }

    @Test
    public void findByName() {
        Category cat = new Category();
        cat.setName("category3");
        categoryDao.create(cat);
        Assert.assertNotNull(categoryDao.findByName("category3"));
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNonExistentName() {
        categoryDao.findByName("Lala");
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNullName() {
        categoryDao.findByName(null);
    }

}
