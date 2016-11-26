package com.LostFound.service;

import com.LostFound.dao.ItemDAO;
import com.LostFound.dao.PostDAO;
import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.exceptions.LostFoundServiceException;
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

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * @author Peter
 * PostServiceTest class is used to test functionalities of Post Service
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-service.xml")
public class PostServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PostDAO postDao;

    @Mock
    private ItemDAO itemDao;

    @Autowired
    @InjectMocks
    private PostService postService;

    // TEST DATA
    private Calendar cal;
    private Post expectedPost;
    private Item itemGuitar;
    private List<Post> expectedPostCollection = new ArrayList<Post>();

    //test data for keyword find testing
    private Post post1;
    private Post post2;
    private Post post3;
    private Item itemKey1;
    private Item itemKey2;
    private Item itemWallet1;
    private Item itemWallet2;


    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }


    @BeforeMethod
    public void createTestData() {
        // reset invocation counters on mocks
        resetMocks();

        // initialize instance of calendar
        cal = Calendar.getInstance();
        cal.set(2016, Calendar.AUGUST, 15, 0, 0, 0);

        expectedPost = post();
        expectedPost.setId(4L);

        itemGuitar = postItem("Guitar");
        expectedPostCollection.add(expectedPost);

        // test data for testing business function "findPostsBykeywords"
        post1 = post();
        post2 = post();
        post3 = post();

        itemKey1 = postItem("key1");
        itemKey2 = postItem("key2");
        itemWallet1 = postItem("wallet1");
        itemWallet2 = postItem("wallet2");

        itemKey1.setPost(post1);
        itemKey2.setPost(post2);
        itemWallet1.setPost(post2);
        itemWallet2.setPost(post3);
    }


    @Test
    public void createPost() {
        //call service method
        postService.createPost(expectedPost);
        //verify
        verify(postDao, times(1)).create(expectedPost);
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



   @Test(expectedExceptions=LostFoundServiceException.class)
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
    public void findById() {
        //setup
        Long idToFind = expectedPost.getId();
        //mock
        when(postDao.findById(idToFind)).thenReturn(expectedPost);
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
        when(postDao.findByLocation(locationToFind)).thenReturn(expectedPostCollection);
        //call service method
        List<Post> actualPosts = postService.findByLocation(locationToFind);
        //verify
        verify(postDao, times(1)).findByLocation(locationToFind);
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
    }


    @Test
    public void findCreatedBetween() {
        //setup
        Date fakeEndDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Date fakeStartDate = cal.getTime();
        //mock
        when(postDao.findCreatedBetween(fakeStartDate, fakeEndDate, PostState.OPENED)).thenReturn(expectedPostCollection);
        //call service method
        List<Post> actualPosts = postService.findCreatedBetween(fakeStartDate, fakeEndDate);
        //check
        Assert.assertEquals(actualPosts, expectedPostCollection);
        //verify
        verify(postDao, times(1)).findCreatedBetween(fakeStartDate, fakeEndDate, PostState.OPENED);
    }


    @Test
    public void findByOpenedState() {
        //mock
        when(postDao.findByState(PostState.OPENED)).thenReturn(expectedPostCollection);
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
        when(postDao.findByUser(user)).thenReturn(expectedPostCollection);
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


    @Test
    public void findBySingleKeyword() {
        //setup
        List<String> singleKeywordList = new ArrayList<>();
        singleKeywordList.add("keys");
        List<Post> expectPosts = new ArrayList<>();
        expectPosts.add(post1);
        //mock
        when(itemDao.findByKeywords("keys")).thenReturn(Collections.singletonList(itemKey1));
        //call service method
        Set<Post> actualPosts = postService.findPostByKeywords(singleKeywordList);
        //verify
        verify(itemDao, times(1)).findByKeywords("keys");
        //check
        Assert.assertEquals(actualPosts, expectPosts);
    }


    @Test
    public void findByMultipleKeyword() {
        //setup
        List<String> multipleKeywordList = new ArrayList<>();
        multipleKeywordList.add("keys");
        multipleKeywordList.add("wallet");
        List<Post> expectPosts = new ArrayList<>();
        expectPosts.add(post2);
        //mock
        List<Item> keysItems = new ArrayList<>();
        keysItems.add(itemKey1);
        keysItems.add(itemKey2);
        List<Item> walletItems = new ArrayList<>();
        walletItems.add(itemWallet1);
        walletItems.add(itemWallet2);
        when(itemDao.findByKeywords("keys")).thenReturn(keysItems);
        when(itemDao.findByKeywords("wallet")).thenReturn(walletItems);
        //call service method
        Set<Post> actualPosts = postService.findPostByKeywords(multipleKeywordList);
        //verify
        verify(itemDao, times(1)).findByKeywords("keys");
        verify(itemDao, times(1)).findByKeywords("wallet");
        //check
        Assert.assertEquals(actualPosts, expectPosts);
    }


    @Test
    public void findByNonExistingKeyword() {
        //setup
        List<String> nonExistingKeyword = new ArrayList<>();
        nonExistingKeyword.add("non-existing");
        //mock
        when(itemDao.findByKeywords("non-existing")).thenReturn(Collections.emptyList());
        //call service method
        Set<Post> actualPosts = postService.findPostByKeywords(nonExistingKeyword);
        //verify
        verify(itemDao, times(1)).findByKeywords("non-existing");
        //check
        Assert.assertEquals(actualPosts, Collections.emptyList());
    }


    @Test(expectedExceptions=IllegalArgumentException.class)
    public void findnullKeyword() {
        postService.findPostByKeywords(null);
    }


    // private helper functions

    private Item postItem(String name) {
        Item item = new Item();
        item.setName(name);
        return item;
    }

    private Post post() {
        Post post = new Post();

        post.setState(PostState.OPENED);
        post.setLocation("Brno");
        post.setCreationDate(cal.getTime());
        return post;
    }

    private void resetMocks()
    {
        reset(itemDao);
        reset(postDao);
    }
}
