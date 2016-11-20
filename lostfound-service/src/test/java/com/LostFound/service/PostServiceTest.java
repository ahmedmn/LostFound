package com.LostFound.service;

import com.LostFound.dao.ItemDAO;
import com.LostFound.dao.PostDAO;
import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Peter
 * PostServiceTest class is used to test functionalities of Post Service
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class PostServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PostDAO postDao;

    @Mock
    private ItemDAO itemDao;

    @Autowired
    @InjectMocks
    private PostService postService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }


    private Post expectedPost;
    private Item itemGuitar;
    private Calendar cal;

    private List<Post> expectedPostCollection = new ArrayList<Post>();

    @BeforeMethod
    public void createPosts() {

        cal = Calendar.getInstance();
        cal.set(2016, Calendar.AUGUST, 15, 0, 0, 0);

        expectedPost = new Post();
        expectedPost.setId(4L);

        itemGuitar = postItem("Guitar");
        expectedPostCollection.add(expectedPost);
    }


    @Test
    public void findCreatedBetween() {
        //setup
        Date fakeEndDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Date fakeStartDate = cal.getTime();
        //mock
        when(postDao.findCreatedBetween(any(Date.class), any(Date.class), eq(PostState.OPENED))).thenReturn(expectedPostCollection);

        //call service method
        List<Post> actualPosts = postService.findCreatedBetween(fakeStartDate, fakeEndDate);

        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
        //verify
        verify(postDao, times(1)).findCreatedBetween(fakeStartDate, fakeEndDate, PostState.OPENED);
    }


    @Test
    public void addItemToPostSuccessfuly() {
        //call service method
        postService.addItem(expectedPost, itemGuitar);

        //verify
        verify(postDao, times(1)).update(expectedPost);
        //check
        Assert.assertEquals(expectedPost.getPostItems(), Collections.singletonList(itemGuitar));
    }


    @Test(expectedExceptions=IllegalArgumentException.class)
    public void addItemToPostFailed() {
        //setup
        expectedPost.addPostItem(itemGuitar);

        //call service method and expect throwing exception
        postService.addItem(expectedPost, itemGuitar);
    }


    @Test
    public void deletePost() {

        //setup
        List<Item> itemsToDelete = new ArrayList<Item>();
        itemsToDelete.add(itemGuitar);
        itemsToDelete.add(postItem("Piano"));
        itemsToDelete.add(postItem("Trumpet"));
        for(Item item : itemsToDelete)
        {
            expectedPost.addPostItem(item);
        }

        //call service method
        postService.deletePost(expectedPost);

        //verify
        for(Item item : itemsToDelete)
        {
            verify(itemDao, times(1)).delete(item);
        }
        verify(postDao, times(1)).delete(expectedPost);
    }


    @Test
    public void createPost() {
        //call service method
        postService.createPost(expectedPost);

        //verify
        verify(postDao, times(1)).create(expectedPost);
    }


    @Test
    public void findById() {
        //setup
        Long idToFind = 4L;
        //mock
        when(postDao.findById(any(Long.class))).thenReturn(expectedPost);

        //call service method
        Post actualPost = postService.findById(idToFind);

        //verify
        verify(postDao, times(1)).findById(idToFind);
        //check
        Assert.assertEquals(actualPost, expectedPost);
    }


    @Test
    public void findByName() {
        //setup
        String locationToFind = "Bratislava";
        //mock
        when(postDao.findByLocation(any(String.class))).thenReturn(expectedPostCollection);

        //call service method
        List<Post> actualPosts = postService.findByLocation(locationToFind);

        //verify
        verify(postDao, times(1)).findByLocation(locationToFind);
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
    }


    @Test
    public void findByOpenedState() {
        //mock
        when(postDao.findByState(any(PostState.class))).thenReturn(expectedPostCollection);

        //call service method
        List<Post> actualPosts = postService.findByState(PostState.OPENED);

        //verify
        verify(postDao, times(1)).findByState(PostState.OPENED);
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
    }


    @Test
    public void findByUser() {
        //setup
        User user = new User();
        //mock
        when(postDao.findByUser(any(User.class))).thenReturn(expectedPostCollection);

        //call service method
        List<Post> actualPosts = postService.findByUser(user);

        //verify
        verify(postDao, times(1)).findByUser(user);
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
    }


    @Test
    public void findAll() {
        //mock
        when(postDao.findAll()).thenReturn(expectedPostCollection);

        //call service method
        List<Post> actualPosts = postService.findAll();

        //verify
        verify(postDao, times(1)).findAll();
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
    }


    //FACADE Tests


    private static Item postItem(String name) {
        Item item = new Item();
        item.setName(name);
        return item;
    }

}
