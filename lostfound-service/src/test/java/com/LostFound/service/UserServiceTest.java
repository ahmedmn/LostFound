/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import com.LostFound.dao.UserDAO;
import com.LostFound.entity.User;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 *
 * @author Michal
 * UserServiceTest class is used to test functionalities of User Service
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-service.xml")
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDAO userDao;
    
    @Autowired
    @InjectMocks
    private UserService userService;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private User expectedUser;
    
    private List<User> expectedUserList = new ArrayList<User>();
    
    @Test 
    public void create(){
        //call service method
        userService.create(expectedUser);

        //verify
        verify(userDao, times(1)).create(expectedUser);
    }
    
    @Test
    public void findById(){
        //setup
        Long idToFind = 10L;
        //mock
        when(userDao.findById(any(Long.class))).thenReturn(expectedUser);
        //call service method
        User actualUser = userService.findById(idToFind);
        //verify
        verify(userDao, times(1)).findById(idToFind);
        //check
        Assert.assertEquals(actualUser, expectedUser);
    }
    
    @Test
    public void findByName(){
        //setup
        String name = "Michal";
        //mock
        when(userDao.findByName(any(String.class))).thenReturn(expectedUser);
        //call service method
        User actualUser = userService.findByName(name);
        //verify
        verify(userDao, times(1)).findByName(name);
        //check
        Assert.assertEquals(actualUser, expectedUser);
    }
    
    @Test
    public void findByEmail(){
        //setup
        String email = "michal@mail.com";
        //mock
        when(userDao.findByEmail(any(String.class))).thenReturn(expectedUser);
        //call service method
        User actualUser = userService.findByEmail(email);
        //verify
        verify(userDao, times(1)).findByEmail(email);
        //check
        Assert.assertEquals(actualUser, expectedUser);
    }
    
    @Test
    public void findAll(){
        //mock
        when(userDao.findAll()).thenReturn(expectedUserList);
        //call service method
        List<User> actualUserList = userService.findAll();
        //verify
        verify(userDao, times(1)).findAll();
        //check
        Assert.assertEquals(actualUserList, expectedUserList);
    }
    
    @Test
    public void delete() {
        
    }
}
