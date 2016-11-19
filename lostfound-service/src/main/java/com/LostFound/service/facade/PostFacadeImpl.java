/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service.facade;

import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.facade.PostFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.ItemService;
import com.LostFound.service.PostService;
import com.LostFound.service.UserService;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal
 */
@Transactional
@Service
public class PostFacadeImpl implements PostFacade{

    @Inject
    private PostService postService;
    
    @Inject
    private UserService userService;
    
    @Inject 
    private ItemService itemService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    public Long createPost(PostCreateDTO p) {
        Post mappedPost = beanMappingService.mapTo(p, Post.class);
        Post newPost = postService.createPost(mappedPost);
        return newPost.getId();
    }

    public void addItem(Long postId, Long itemId) {
        postService.addItem(postService.findById(postId), itemService.findById(itemId));
    }
    
    public void deletePost(Long postId) {
        postService.deletePost(new Post(postId));
    }
    
    public PostDTO getPostById(Long id) {
        Post post = postService.findById(id);
        if (post == null) return null;
        else return beanMappingService.mapTo(post, PostDTO.class);
    }

    public List<PostDTO> getAllPosts() {
        return beanMappingService.mapTo(postService.findAll(), PostDTO.class);
    }

    public List<PostDTO> getPostsByUser(Long userId) {
        User user = userService.findById(userId);
        return beanMappingService.mapTo(postService.findByUser(user), PostDTO.class);
    }

    public List<PostDTO> getPostsByState(PostState state) {
        return beanMappingService.mapTo(postService.findByState(state), PostDTO.class);
    }

    public List<PostDTO> getPostsByLocation(String location) {
        return beanMappingService.mapTo(postService.findByLocation(location), PostDTO.class);
    }

    public List<PostDTO> getPostsCreatedBetween(Date fromDate, Date toDate) {
        return beanMappingService.mapTo(postService.findCreatedBetween(fromDate, toDate), PostDTO.class);
    }    
}
