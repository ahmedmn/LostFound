/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.facade;

import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Michal
 */
public interface PostFacade {
    public Long createPost(PostCreateDTO p);
    
    public void addItem(Long productId, Long categoryId);
        
    public void deletePost(Long productId);
    
    public PostDTO getPostById(Long id);
    
    public List<PostDTO> getAllPosts();
    
    public List<PostDTO> getPostsByUser(Long userId);
    
    public List<PostDTO> getPostsByState(PostState state);
    
    public List<PostDTO> getPostsByKeywords(List<String> keywords);

    public List<PostDTO> getPostsByType(PostType type);
    
    public List<PostDTO> getPostsByLocation(String location);
    
    public List<PostDTO> getPostsCreatedBetween(Date fromDate, Date toDate);
    
}
