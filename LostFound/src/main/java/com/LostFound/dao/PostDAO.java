package com.LostFound.dao;

import com.LostFound.entity.Category;
import com.LostFound.entity.Post;
//import com.LostFound.entity.User;
import com.LostFound.enums.PostState;

import java.util.Date;
import java.util.List;

/**
 * PostDAO declares some basic operations such as insert,
 * update, delete and find data in Post table.
 *
 * @author Peter
 */
public interface PostDAO {
    /**
     * Create method is used for insert information such as
     * Post id, Creation date, Location, Items included,
     * Owner user id into table Post.
     *
     * @param post object to be created
     */
    public void create(Post post);

    /**
     * Update method is used for update information such as
     * Creation date, Location, Items included,
     * Owner user id according to post id in table Post.
     *
     * @param post object to be updated
     */
    public void update(Post post);

    /**
     * delete method is used for delete items according to post id in table Post.
     *
     * @param post object to be deleted.
     */
    public void delete(Post post);

    /**
     * Returns post corresponding to given post id.
     *
     * @param id id of post to be found
     * @return post with given id
     */
    public Post findById(Long id);


    /**
     * Returns posts corresponding to given user.
     *
     * @param user user who owns posts
     * @return posts of user with given id
     */
//    TODO - !!! Uncomment when user class will be implemented !!!
//    public List<Post> findByUser(User user);

    /**
     * Returns posts corresponding to given location.
     *
     * @param location location of posts to be found
     * @return list of posts with given location
     */
    public List<Post> findByLocation(String location);

    /**
     * Returns posts created in date range (fromDate, toDate).
     *
     * @param fromDate starting date
     * @param toDate ending date
     * @param state state
     * @return list of posts in given date range
     */
    public List<Post> findCreatedBetween(Date fromDate, Date toDate, PostState state);

    /**
     * Returns information about all posts.
     *
     * @return list of all posts
     */
    public List<Post> findAll();

    /**
     * Returns list of all posts corresponding to given state.
     *
     * @return list of posts with given state
     */
    public List<Post> findByState(PostState state);

}
