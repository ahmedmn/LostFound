/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dao;

import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.enums.PostState;
import java.util.Calendar;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * PostDaoTest class is used to test functionalities of each Post.
 *
 * @author Michal
 *
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class PostDaoTest extends AbstractTestNGSpringContextTests   {
    
    
    @Autowired
    private PostDAO postDao; 
    
    @Autowired
    private ItemDAO itemDao; 
                  
    @Test
    public void createPostTest() {
        
        Post post1 = new Post();
        Post post2 = new Post();
    
        Calendar cal = Calendar.getInstance();
        
        post1.setState(PostState.OPENED);
        post1.setLocation("Brno");
        post1.setCreationDate(cal.getTime());
        
        post2.setState(PostState.OPENED);
        post2.setLocation("Bratislava");       
        post2.setCreationDate(cal.getTime());
        
        postDao.create(post1);
        postDao.create(post2);
          
        Assert.assertEquals(postDao.findById(post1.getId()).getLocation(), "Brno");       
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullStatePostTest() {  
        
        Post post1 = new Post();         
        Calendar cal = Calendar.getInstance();
        
        post1.setState(null);
        post1.setLocation("Brno");
        post1.setCreationDate(cal.getTime());
                     
        postDao.create(post1);         
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullLocationPostTest() {
        
        Post post1 = new Post();           
        Calendar cal = Calendar.getInstance();
        
        post1.setState(PostState.DONE);
        post1.setLocation(null);
        post1.setCreationDate(cal.getTime());
              
        postDao.create(post1);         
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createWithNullCreationDatePostTest(){        
        
        Post post1 = new Post(); 
              
        post1.setState(PostState.DONE);
        post1.setLocation("Brno");
        post1.setCreationDate(null);

        postDao.create(post1);         
    }
    
    
    @Test
    public void addItemToPost(){
        
        Post post1 = new Post();           
        Calendar cal = Calendar.getInstance();
        
        post1.setState(PostState.DONE);
        post1.setLocation("Brno");
        post1.setCreationDate(cal.getTime());
        
        //creation of item to be added
        Item item = new Item();
        item.setName("ABC");
        itemDao.create(item);
        
        post1.addPostItem(item);       
        postDao.create(post1);
                
        Assert.assertEquals(postDao.findById(post1.getId()).getPostItems().size(), 1);
        Assert.assertEquals(postDao.findById(post1.getId()).getPostItems().get(0).getName(), "ABC");  
    }
    
    
    @Test
    public void updatePostTest() {
                 
        Post post = new Post();
        Calendar cal = Calendar.getInstance();
        
        post.setState(PostState.OPENED);
        post.setLocation("Brno");
        post.setCreationDate(cal.getTime());
        
        postDao.create(post);
        
        Assert.assertEquals(postDao.findById(post.getId()).getState(), PostState.OPENED);
        Assert.assertEquals(postDao.findById(post.getId()).getLocation(), "Brno");
        Assert.assertEquals(postDao.findById(post.getId()).getCreationDate(), cal.getTime());
        
        post.setState(PostState.DONE);
        post.setLocation("Bratislava");
        cal.add(Calendar.DAY_OF_YEAR, 1);
        post.setCreationDate(cal.getTime());
        postDao.update(post);
        
        Assert.assertEquals(postDao.findById(post.getId()).getState(), PostState.DONE);      
        Assert.assertEquals(postDao.findById(post.getId()).getLocation(), "Bratislava");
        Assert.assertEquals(postDao.findById(post.getId()).getCreationDate(), cal.getTime());                  
    }
    
    /*
    @Test(expectedExceptions=ConstraintViolationException.class) 
    public void updateToNullStatePostTest() {

        Post post = postDao.findAll().get(0);
        
        post.setState(null);
        postDao.update(post);
    }
       
    @Test(expectedExceptions=ConstraintViolationException.class) 
    public void updateToNullCreationDatePostTest() {
        Post post = postDao.findAll().get(0);
        post.setCreationDate(null);
        postDao.update(post);
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class) 
    public void updateToNullLocationPostTest() {
        Post post = postDao.findAll().get(0);
        
        post.setLocation(null);
        postDao.update(post);
    }
    */
       
    @Test
    public void deletePostTest(){
        Post postd = new Post();
        Calendar cal = Calendar.getInstance();
        
        postd.setState(PostState.OPENED);
        postd.setLocation("Brno");
        postd.setCreationDate(cal.getTime());

        postDao.create(postd);
        
        int count = postDao.findAll().size();
        Assert.assertNotNull(postDao.findById(postd.getId()));
        postDao.delete(postd);
        
        //after deletion we should not find deleted post and number of post should be decreased by 1
        Assert.assertNull(postDao.findById(postd.getId()));
        Assert.assertEquals(postDao.findAll().size(), count-1);
    }
    
    @Test
    public void findByLocationTest() {
        Assert.assertEquals(postDao.findByLocation("Brno").size(), 1);
        Assert.assertEquals(postDao.findByLocation("Bratislava").size(), 1);
    }
    
    @Test
    public void findByStateTest() {
        Assert.assertEquals(postDao.findByState(PostState.OPENED).size(), 2);
        Assert.assertEquals(postDao.findByState(PostState.DONE).size(), 1);
    }
    
    @Test
    public void findCreatedBetweenTest() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_YEAR, -1);
        cal2.add(Calendar.DAY_OF_YEAR, 1);
        
        //all posts are created today
        Assert.assertEquals(postDao.findCreatedBetween(cal1.getTime(), cal2.getTime()).size()
                , postDao.findAll().size());
    }
}

