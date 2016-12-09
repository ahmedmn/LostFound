
package com.LostFound.facade;

//
//import com.LostFound.dto.*;
//import com.LostFound.enums.PostState;
//import com.LostFound.enums.PostType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//
//
///**
// * @author Peter
// * PostFacadeTest class is used to test functionalities of Post Facade
// */
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration(locations = "file:src/main/resources/spring-service.xml")
//public class PostFacadeTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private PostFacade postFacade;
//
//    @Autowired
//    private ItemFacade itemFacade;
//
//    @Autowired
//    private UserFacade userFacade;
//
//    // TEST DATA
//    private Calendar cal;
//    private PostCreateDTO postCreateDTO;
//    private UserDTO userDTO;
//    private PostDTO expectedPostDTO;
//    private Long newPostId;
//
//    @BeforeMethod
//    public void createTestData() {
//        //initialize calendar instance
//        cal = Calendar.getInstance();
//        cal.set(2016, 11, 10);
//
//        // register user in system
//        userDTO = new UserDTO();
//        userDTO.setJoinedDate(cal.getTime());
//        userDTO.setUsername("user1");
//        userDTO.setEmail("user1@gmail.com");
//        userFacade.registerUser(userDTO, "password");
//
//        // according to postCreateDTO values new post in DB is created
//        postCreateDTO = new PostCreateDTO();
//        postCreateDTO.setLocation("Bratislava");
//        postCreateDTO.setCreationDate(cal.getTime());
//        postCreateDTO.setUser(userDTO);
//        postCreateDTO.setState(PostState.OPENED);
//        postCreateDTO.setType(PostType.LOST);
//
//        //expected expectedPostDTO object which is retrieved from DB
//        expectedPostDTO = new PostDTO();
//        expectedPostDTO.setLocation("Bratislava");
//        expectedPostDTO.setCreationDate(cal.getTime());
//        expectedPostDTO.setUser(userDTO);
//
//        newPostId = postFacade.createPost(postCreateDTO);
//    }
//
//
//    @Test
//    void createPost()    {
//        PostDTO actualPostDTO = postFacade.getPostById(newPostId);
//        Assert.assertEquals(actualPostDTO, expectedPostDTO);
//    }
//
//
//    @Test
//    public void deletePost()
//    {
//        Assert.assertEquals(postFacade.getAllPosts().size(), 1);
//        Assert.assertNotNull(postFacade.getPostById(newPostId));
//        postFacade.deletePost(newPostId);
//        Assert.assertEquals(postFacade.getAllPosts().size(), 0);
//        Assert.assertNull(postFacade.getPostById(newPostId));
//    }
//
//
//    @Test
//    public void getPostById()
//    {
//        Assert.assertEquals(postFacade.getPostById(newPostId), expectedPostDTO);
//    }
//
//
//    @Test
//    public void getAllPosts()
//    {
//        Assert.assertEquals(postFacade.getAllPosts(), Collections.singletonList(expectedPostDTO));
//    }
//
//    @Test
//    public void getPostsByUser()
//    {
//        Assert.assertEquals(postFacade.getPostsByUser(userDTO.getId()), Collections.singletonList(expectedPostDTO));
//    }
//
//    @Test
//    public void getPostsByState()
//    {
//        Assert.assertEquals(postFacade.getPostsByState(PostState.OPENED), Collections.singletonList(expectedPostDTO));
//    }
//
//    @Test
//    public void getPostsByType()
//    {
//        Assert.assertEquals(postFacade.getPostsByType(PostType.LOST), Collections.singletonList(expectedPostDTO));
//    }
//
//    @Test
//    public void getPostsByLocation()
//    {
//        Assert.assertEquals(postFacade.getPostsByLocation("Bratislava"), Collections.singletonList(expectedPostDTO));
//    }
//
//    @Test
//    public void getPostsCreatedBetween()
//    {
//        cal.add(Calendar.DAY_OF_WEEK, -1);
//        Date fromDate = cal.getTime();
//        cal.add(Calendar.DAY_OF_WEEK, 1);
//        Date toDate = cal.getTime();
//        Assert.assertEquals(postFacade.getPostsCreatedBetween(fromDate, toDate), Collections.singletonList(expectedPostDTO));
//    }
//
//}
