/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import com.LostFound.dao.ItemDAO;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.dao.PostDAO;
import com.LostFound.dao.UserDAO;
import com.LostFound.entity.Item;
import com.LostFound.enums.PostState;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal
 */
@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostDAO postDao;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private ItemDAO itemDao;

    public Post findById(Long id) {
        return postDao.findById(id);
    }

    public List<Post> findByUser(User user) {
        return postDao.findByUser(user);
    }

    public List<Post> findByLocation(String location) {
        return postDao.findByLocation(location);    
    }

    public List<Post> findCreatedBetween(Date fromDate, Date toDate) {
        return postDao.findCreatedBetween(fromDate, toDate, PostState.OPENED);
    }

    public List<Post> findByState(PostState state) {
        return postDao.findByState(state);    
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }

    public Post createPost(Post post) {
        postDao.create(post);
        return post;
    }

    public void deletePost(Post post) {
        for (Item item : post.getPostItems())
        {
            itemDao.delete(item);
        }
        postDao.delete(post);
    }

    public void addItem(Post post, Item item) {
        if (post.getPostItems().contains(item)) {
			throw new IllegalArgumentException(
					"Post already contais this item. Post: "
							+ post.getId() + ", item: "
							+ item.getId());
		}
        post.addPostItem(item);
        postDao.update(post);
    }
    
}
