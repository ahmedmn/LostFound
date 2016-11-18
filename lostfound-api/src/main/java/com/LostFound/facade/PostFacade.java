/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.facade;

import com.LostFound.dto.PostDTO;
import com.LostFound.enums.PostState;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michal
 */
public interface PostFacade {
    //public Long createPost(ProductCreateDTO p);
    
    public void addItem(Long productId, Long categoryId);
    
    public void removeItem(Long productId, Long categoryId);
    
    public void deletePost(Long productId);
    
    public List<PostDTO> getAllPosts();
    
    public List<PostDTO> getPostsByUser(Long userId);
    
    public List<PostDTO> getPostsByState(PostState state);
    
    public List<PostDTO> getPostsByLocation(String location);
    
    public List<PostDTO> getPostsCreatedBetween(Date fromDate, Date toDate);
    
}
