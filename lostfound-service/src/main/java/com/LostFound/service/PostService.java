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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michal
 */
public interface PostService {
        
    public Post findById(Long id);
    
    public List<Post> findByUser(User user);
    
    public List<Post> findByLocation(String location);
    
    public List<Post> findCreatedBetween(Date fromDate, Date toDate);
    
    public List<Post> findByState(PostState state);
	
    public List<Post> findAll();
    
    public void addItem(Post post, Item item);
      
    public Post createPost(Post post);
    
    public void deletePost(Post post);
	
}
