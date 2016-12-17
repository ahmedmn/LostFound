package com.LostFound.dao;

import com.LostFound.PersistanceApplicationContext;
import com.LostFound.entity.User;
import com.LostFound.exceptions.LostFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.util.Calendar;

/**
 * UserDaoTest class is used to test functionalities of each Post.
 *
 * @author Peter
 *
 */
@ContextConfiguration(classes = PersistanceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDAO userDao;

    private User u1;
    private User u2;

    @BeforeMethod
    public void createUsers() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015,10,29);

        u1 = new User();
        u2 = new User();

        u1.setUsername("Peter");
        u1.setEmail("peter@gmail.com");
        u1.setJoinedDate(cal.getTime());

        u2.setUsername("Michal");
        u2.setEmail("michal@gmail.com");
        u2.setJoinedDate(cal.getTime());

        userDao.create(u1);
        userDao.create(u2);
    }

    @Test
    public void update() {
        Assert.assertEquals(userDao.findById(u1.getId()).getUsername(), "Peter");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "peter@gmail.com");

        User user = new User();
        user.setId(u1.getId());
        user.setUsername("Pedro1");
        user.setEmail("pedro1@gmail.com");
        userDao.update(user);
        Assert.assertEquals(userDao.findById(u1.getId()).getUsername(), "Pedro1");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "pedro1@gmail.com");
    }

    @Test()
    public void findCreatedUsersById() {
        Assert.assertEquals(userDao.findAll().size(), 2);
        Assert.assertEquals(userDao.findById(u1.getId()).getUsername(), "Peter");
        Assert.assertEquals(userDao.findById(u1.getId()).getEmail(), "peter@gmail.com");
        Assert.assertEquals(userDao.findById(u2.getId()).getUsername(), "Michal");
        Assert.assertEquals(userDao.findById(u2.getId()).getEmail(), "michal@gmail.com");
    }

    @Test
    public void findByNonExistentId() {
        Long nonExistentId = u2.getId() + 100L;
        Assert.assertNull(userDao.findById(nonExistentId));
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findByNullId() {
        userDao.findById(null);
    }

    @Test
    public void findByEmail() {
        Assert.assertNotNull(userDao.findByEmail("peter@gmail.com"));
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNonExistentEmail() {
        userDao.findByEmail("lala@gmail.com");
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNullEmail() {
        userDao.findByEmail(null);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void emailIsUnique(){
        User u3 = new User();
        u3.setUsername("Ahmed");
        u3.setEmail("peter@gmail.com");
        userDao.create(u3);
    }

    @Test
    public void findByName() {
        Assert.assertNotNull(userDao.findByName("Peter"));
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNonExistentName() {
        userDao.findByName("Lala");
    }

    @Test(expectedExceptions = EmptyResultDataAccessException.class)
    public void findByNullName() {
        userDao.findByName(null);
    }


    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nameIsUnique(){
        User u3 = new User();
        u3.setUsername("Peter");
        u3.setUsername("test@gmail.com");
        userDao.create(u3);
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

}
