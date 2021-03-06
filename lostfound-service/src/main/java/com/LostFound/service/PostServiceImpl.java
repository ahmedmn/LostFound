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
import com.LostFound.entity.Item;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import com.LostFound.exceptions.LostFoundServiceException;
import com.LostFound.service.facade.PostFacadeImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal
 */
@Service
public class PostServiceImpl implements PostService{

    final static Logger log = LoggerFactory.getLogger(PostFacadeImpl.class);
    
    @Autowired
    private PostDAO postDao;
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

    public List<Post> findByType(PostType type) {
        return postDao.findByType(type);
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
            throw new LostFoundServiceException(
                    "Post already contais this item. Post: "
                            + post.getId() + ", item: "
                            + item.getId());
        }
        post.addPostItem(item);
        postDao.update(post);
    }

    public List<Post> findPostByKeywords(List<String> keywords) {
        if (keywords == null) throw new IllegalArgumentException(
                    "Argument keywords is null");
                 
        List<Set<Post>> postsByKeywords = new ArrayList<Set<Post>>();
        List<Item> tmpItems;
        Set<Post> tmpPosts;

        for(String kw: keywords){
            tmpItems = itemDao.findByKeywords(kw);                      
            tmpPosts = new HashSet<Post>();
            
            for(Item item:tmpItems){        
                tmpPosts.add(postDao.findByItem(item));
            }
            postsByKeywords.add(tmpPosts);
        }
               
        Set<Post> intersectSet = postsByKeywords.get(0);
        for (int i = 1; i < postsByKeywords.size(); i++) {
            intersectSet.retainAll(postsByKeywords.get(i));
        }
        List<Post> result = new ArrayList<>();
        result.addAll(intersectSet);
        return result;
    }

}
