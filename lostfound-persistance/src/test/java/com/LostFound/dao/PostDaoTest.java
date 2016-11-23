/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dao;

import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import java.util.Calendar;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * PostDaoTest class is used to test functionalities of each Post.
 *
 * @author Michal
 *
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PostDaoTest extends AbstractTestNGSpringContextTests   {
    
    
    @Autowired
    private PostDAO postDao; 
    
    @Autowired
    private ItemDAO itemDao;
       
    private Post post1;
    private Post post2;
    private Post post3;
        
    @BeforeMethod
    public void createPosts(){      
        Calendar cal = Calendar.getInstance();
        cal.set(2016,11,20);
        
        post1 = new Post();
        post2 = new Post();
        post3 = new Post();
        
        post1.setState(PostState.OPENED);
        post1.setLocation("Brno");
        post1.setCreationDate(cal.getTime());
        
        post2.setState(PostState.OPENED);
        post2.setLocation("Bratislava");       
        post2.setCreationDate(cal.getTime());
        
        post3.setState(PostState.OPENED);
        post3.setLocation("Praha");
        post3.setCreationDate(cal.getTime());
        
        postDao.create(post1);
        postDao.create(post2);
    }
    
    
    @Test
    public void findByExistingIdPostTest() {                     
        Assert.assertNotNull(postDao.findById(post1.getId()));
        Assert.assertNotNull(postDao.findById(post2.getId()));
    }
    
    @Test
    public void findByNonExistingIdPostTest() {                     
        Assert.assertNull(postDao.findById(post1.getId()+2l));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId() {
        postDao.findById(null);
    }
    
    @Test
    public void findByLocationTest() {
        Assert.assertNotNull(postDao.findByLocation("Brno"));       
        Assert.assertEquals(postDao.findByLocation("Brno").size(), 1);
    }
    
    @Test
    public void findByNonExistentLocation() {
        Assert.assertTrue(postDao.findByLocation("Kosice").isEmpty());
    }
    
    @Test
    public void findByNullLocationTest() {
        Assert.assertTrue(postDao.findByLocation(null).isEmpty());
    }
    
    @Test
    public void findByStateTest() {
        Assert.assertNotNull(postDao.findByState(PostState.OPENED));
        Assert.assertTrue(postDao.findByState(PostState.DONE).isEmpty());
    }
    
    @Test
    public void findByNullStateTest() {
        Assert.assertTrue(postDao.findByState(null).isEmpty());
    }
       
    @Test
    public void findCreatedBetweenTest() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2016, 11, 19);
        cal2.set(2016, 11, 21);
        
        //all posts are created 20.11.2016
        Assert.assertEquals(postDao.findCreatedBetween(cal1.getTime(), cal2.getTime(), PostState.OPENED).size()
                , postDao.findByState(PostState.OPENED).size());
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullStatePostTest() {  
        post3.setState(null);                     
        postDao.create(post3);         
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullLocationPostTest() {       
        post3.setLocation(null);
        postDao.create(post3);         
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullCreationDatePostTest(){        
        post3.setCreationDate(null);
        postDao.create(post3);         
    }
      
    @Test
    public void addItemToPost(){  
        Assert.assertEquals(postDao.findById(post1.getId()).getPostItems().size(), 0);
        //creation of item to be added
        Item item = new Item();
        item.setName("ABC");
        itemDao.create(item);
        
        post1.addPostItem(item);       
        postDao.update(post1);
                
        Assert.assertEquals(postDao.findById(post1.getId()).getPostItems().size(), 1);       
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void addNullItemToPost(){        
        post1.addPostItem(null);       
        postDao.update(post1);      
    }
    
    
    @Test
    public void updatePostStateTest() {
        post1.setState(PostState.DONE);
        postDao.update(post1);
        
        Assert.assertEquals(postDao.findById(post1.getId()).getState(), PostState.DONE);
    }
       
    @Test
    public void updatePostLocationTest() {
        post1.setLocation("Bratislava");
        postDao.update(post1);
        
        Assert.assertEquals(postDao.findById(post1.getId()).getLocation(), "Bratislava");
    }
                  
    @Test
    public void updatePostCreationDateTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 11, 11);
        
        post1.setCreationDate(cal.getTime());
        postDao.update(post1);
        
        Assert.assertEquals(postDao.findById(post1.getId()).getCreationDate(), cal.getTime());
    }
                           
    @Test
    public void deletePostTest(){
        Assert.assertNotNull(postDao.findById(post1.getId()));
        postDao.delete(post1);    
        Assert.assertNull(postDao.findById(post1.getId()));
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void deletePostNullTest(){
        postDao.delete(null);            
    }   
}

