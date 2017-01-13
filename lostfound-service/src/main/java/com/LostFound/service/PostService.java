/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Michal
 * An interface that defines a service access to the Post entity in persistance layer.
 */
public interface PostService {

    /**
     * FindById method is used to find Posts by their Id
     *
     * @param id is Post id
     * @return Post with specified id
     */
    public Post findById(Long id);

    /**
     * FindByUser method is used to find Posts by specific user
     *
     * @param user is specific user
     * @return list of all Posts with specified user
     */
    public List<Post> findByUser(User user);

    /**
     * FindByLocation method is used to find Posts by specific location
     *
     * @param location is Post location
     * @return list of all Posts with specified location
     */
    public List<Post> findByLocation(String location);

    /**
     * FindCreatedBetween method is used to find Posts in specific time
     *
     * @param fromDate is date since Posts are searched
     * @param toDate is date until Posts are searched
     * @return list of all Posts in specified time
     */
    public List<Post> findCreatedBetween(Date fromDate, Date toDate);

    /**
     * FindbyState method is used to find Posts by specific state
     *
     * @param state is Post state
     * @return list of all Posts with specified state
     */
    public List<Post> findByState(PostState state);

    /**
     * FindByType method is used to find Posts by specific type
     *
     * @param type is Post type
     * @return list of all Posts with specified state
     */
    public List<Post> findByType(PostType type);
    
    /**
     * findPostByKeywords method is used to find Posts by item´s keywords
     *
     * @param keywords is List of keywords
     * @return list of all Posts which contain items with all specified keywords
     */
    public List<Post> findPostByKeywords(List<String> keywords);
    /**
     * FindAll method is used to find all Posts
     *
     * @return list of all Posts
     */
    public List<Post> findAll();

    /**
     * AddItem method is used to add new item to Post
     *
     * @param post is Post, in which new item will be added 
     * @param item is Item to be added
     */
    public void addItem(Post post, Item item);

    /**
     * Create method is used to create new Post in system
     *
     * @param post is new Post
     * @return created Post
     */
    public Post createPost(Post post);

    /**
     * Delete method is used to delete Post from system
     *
     * @param post is deleted Post
     */
    public void deletePost(Post post);

}
