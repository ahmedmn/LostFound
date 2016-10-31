package com.LostFound.dao;

import com.LostFound.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * UserDaoTest class is used to test functionalities of each Post.
 *
 * @author Peter
 *
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDAO userDao;

    private User u1;
    private User u2;

    @BeforeMethod
    public void createUsers() {
        u1 = new User();
        u2 = new User();

        u1.setName("Peter");
        u1.setEmail("peter@gmail.com");

        u1.setName("Michal");
        u1.setEmail("michal@gmail.com");

        userDao.create(u1);
        userDao.create(u2);
    }

    @Test
    public void update() {
        Assert.assertEquals(userDao.findById(u1.getId()).getName(), "Peter");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "petookovac@gmail.com");

        User user = new User();
        user.setId(u1.getId());
        user.setName("Pedro1");
        user.setEmail("pedro1@gmail.com");
        userDao.update(user);
        Assert.assertEquals(userDao.findById(u1.getId()).getName(), "Pedro1");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "pedro1@gmail.com");
    }

    @Test
    public void delete() {
        int count = userDao.findAll().size();
        Assert.assertNotNull(userDao.findById(u1.getId()));

        userDao.delete(u1);
        //after deletion we should not find deleted user and number of users should be decreased by 1
        Assert.assertNull(userDao.findById(u1.getId()));
        Assert.assertEquals(userDao.findAll().size(), count-1);
    }

    @Test()
    public void findCreatedUsersById() {
        Assert.assertEquals(userDao.findAll().size(), 2);
        Assert.assertEquals(userDao.findById(u1.getId()).getName(), "Peter");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "peter@gmail.com");
        Assert.assertEquals(userDao.findById(u2.getId()).getName(), "Michal");
        Assert.assertEquals(userDao.findById(u2.getId()).getEmail(), "michal@gmail.com");
    }

    @Test
    public void findByNonExistentId() {
        Long nonExistentId = u2.getId() + 100L;
        Assert.assertNull(userDao.findById(nonExistentId));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void findByNullId() {
        userDao.findById(null);
    }

    @Test
    public void findByEmail() {
        Assert.assertNotNull(userDao.findByEmail("peter@gmail.com"));
    }

    @Test
    public void findByNonExistentEmail() {
        Assert.assertNull(userDao.findByEmail("lala@gmail.com"));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void findByNullEmail() {
        userDao.findByEmail(null);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nullUserEmailNotAllowed(){
        u1.setEmail(null);
        userDao.update(u1);
    }

    @Test(expectedExceptions=PersistenceException.class)
    public void emailIsUnique(){
        User u3 = new User();
        u3.setName("Ahmed");
        u3.setName("petookovac@gmail.com");
        userDao.create(u3);
    }

    @Test
    public void findByName() {
        Assert.assertNotNull(userDao.findByName("Peter"));
    }

    @Test
    public void findByNonExistentName() {
        Assert.assertNull(userDao.findByName("Lala"));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void findByNullName() {
        userDao.findByName(null);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nullUserNameNotAllowed(){
        u1.setName(null);
        userDao.update(u1);
    }

    @Test(expectedExceptions=PersistenceException.class)
    public void nameIsUnique(){
        User u3 = new User();
        u3.setName("Peter");
        u3.setName("test@gmail.com");
        userDao.create(u3);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nullUserPasswordNotAllowed(){
        u1.setPassword(null);
        userDao.update(u1);
    }

}
