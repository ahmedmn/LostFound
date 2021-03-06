/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service.facade;

import com.LostFound.dto.ItemDTO;
import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import com.LostFound.facade.PostFacade;
import com.LostFound.service.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal
 */
@Transactional
@Service
public class PostFacadeImpl implements PostFacade{
    
    final static Logger log = LoggerFactory.getLogger(PostFacadeImpl.class);

    @Inject
    private PostService postService;
    
    @Inject
    private UserService userService;
    
    @Inject 
    private ItemService itemService;

    @Inject
    private CategoryService categoryService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    public Long createPost(PostCreateDTO p) {
        Item mappedItem = beanMappingService.mapTo(p, Item.class);
        mappedItem.addCategory(categoryService.findById(p.getCategoryId()));
        Item newItem = itemService.createItem(mappedItem);
        Post mappedPost = beanMappingService.mapTo(p, Post.class);
        mappedPost.addPostItem(newItem);
        mappedPost.setCreationDate(new Date());
        mappedPost.setState(PostState.OPENED);
        mappedPost.setUser(beanMappingService.mapTo(p.getUserDTO(), User.class));
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
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(postService.findByUser(user), PostDTO.class);
    }

    public List<PostDTO> getPostsByState(PostState state) {
        return beanMappingService.mapTo(postService.findByState(state), PostDTO.class);
    }

    public List<PostDTO> getPostsByType(PostType type)  {
        return beanMappingService.mapTo(postService.findByType(type), PostDTO.class);
    }

    public List<PostDTO> getPostsByLocation(String location) {
        return beanMappingService.mapTo(postService.findByLocation(location), PostDTO.class);
    }

    public List<PostDTO> getPostsCreatedBetween(Date fromDate, Date toDate) {
        return beanMappingService.mapTo(postService.findCreatedBetween(fromDate, toDate), PostDTO.class);
    }    

    @Override
    public List<PostDTO> getPostsByKeywords(List<String> keywords) {
        log.error(postService.findPostByKeywords(keywords).toString());
        return beanMappingService.mapTo(postService.findPostByKeywords(keywords), PostDTO.class);
    }
}
