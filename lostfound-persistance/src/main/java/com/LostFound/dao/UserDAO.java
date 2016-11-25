package com.LostFound.dao;

import com.LostFound.entity.User;
import java.util.List;

/**
 * UserDAO declares CRUD operations and some others as
 * find data in Users table.
 *
 * @author Bokos
 */
public interface UserDAO {

    /**
     * Create method is used for insert information in table Users
     *
     * @param user object to be created
     */
    public void create(User user);

    /**
     * Update method is used for update information of user
     *
     * @param user object to be updated
     */
    public void update(User user);

    /**
     * Delete method is used for delete user according to user id in table Users.
     *
     * @param user object to be deleted.
     */
    public void delete(User user);

    /**
     * Returns user corresponding to given user id.
     *
     * @param id id of user to be found
     * @return user with given id
     */
    public User findById(Long id);

    /**
     * Returns user corresponding to given user name.
     *
     * @param name name of user to be found
     * @return user with given name
     */
    public User findByName(String name);

    /**
     * Returns user corresponding to given user email.
     *
     * @param email email of user to be found
     * @return user with given email
     */
    public User findByEmail(String email);

    /**
     * Returns information about all users.
     *
     * @return list of all users
     */
    public List<User> findAll();  
}
