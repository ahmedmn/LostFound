package com.lostfound.rest.controllers;

import com.lostfound.rest.ApiUris;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.LostFound.dto.ItemDTO;
import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.facade.PostFacade;
import com.lostfound.rest.exceptions.InvalidParameterException;
import com.lostfound.rest.exceptions.ResourceAlreadyExistingException;
import com.lostfound.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Posts
 *
 * @author ahmed
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_POSTS)
public class PostsController {

    final static Logger logger = LoggerFactory.getLogger(PostsController.class);

    @Inject
    private PostFacade postFacade;

    /**
     * Get list of Posts curl -i -X GET
     * http://localhost:8080/pa165/rest/posts
     *
     * @return PostDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PostDTO> getPosts() {
        logger.debug("rest getPosts()");
        return postFacade.getAllPosts();
    }

    /**
     *
     * Get Post by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/posts/1
     *
     * @param id identifier for a post
     * @return PostDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PostDTO getPost(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getPost({})", id);
        PostDTO postDTO = postFacade.getPostById(id);
        if (postDTO != null) {
            return postDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one post by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/posts/1
     *
     * @param id identifier for post
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePost(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deletePost({})", id);
        try {
            postFacade.deletePost(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new post by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"test","description":"test","color":"UNDEFINED","price":"200",
     * "currency":"CZK", "categoryId":"1"}' 
     * http://localhost:8080/pa165/rest/posts/create
     * 
     * @param post PostCreateDTO with required fields for creation
     * @return the created Post PostDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PostDTO createPost(@RequestBody PostCreateDTO post) throws Exception {

        logger.debug("rest createPost()");

        try {
            Long id = postFacade.createPost(post);
            return postFacade.getPostById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Add a new item by POST Method
     *
     * @param id the identifier of the Post to have the Category added
     * @param item the item to be added
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}/items", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PostDTO addItem(@PathVariable("id") long id, @RequestBody ItemDTO item) throws Exception {

        logger.debug("rest addItem({})", id);

        try {
            postFacade.addItem(id, item.getId());
            return postFacade.getPostById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }
}
