package com.lostfound.rest.controllers;

import com.LostFound.dto.PostDTO;

import  com.LostFound.facade.PostFacade;
import com.lostfound.rest.assemblers.PostResourceAssembler;
import com.lostfound.rest.exceptions.ResourceNotFoundException;
import com.lostfound.rest.exceptions.ResourceNotModifiedException;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

/**
 * REST HATEOAS Controller for Posts
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link cz.fi.muni.pa165.rest.assemblers.postResourceAssembler} to create 
 * resources to be returned as ResponseEntities
 *
 */
@RestController
@RequestMapping("/posts_hateoas")
public class PostsControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(PostsControllerHateoas.class);

    @Inject
    private PostFacade postFacade;

    @Inject
    private PostResourceAssembler postResourceAssembler;

    /**
     *
     * Get list of posts
     * 
     * @return HttpEntity<Resources<Resource<PostDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<PostDTO>>> getPosts() {
        
        logger.debug("rest getPosts({}) hateoas");

        Collection<PostDTO> postsDTO = postFacade.getAllPosts();
        Collection<Resource<PostDTO>> postResourceCollection = new ArrayList();

        for (PostDTO p : postsDTO) {
        	postResourceCollection.add(postResourceAssembler.toResource(p));
        }

        Resources<Resource<PostDTO>> postsResources = new Resources<Resource<PostDTO>>(postResourceCollection);
        postsResources.add(linkTo(PostsControllerHateoas.class).withSelfRel());

        return new ResponseEntity<Resources<Resource<PostDTO>>>(postsResources, HttpStatus.OK);

    }
    
    /**
     *
     * Get list of posts - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * 
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * 
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/eshop-rest/posts_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     * 
     * @return HttpEntity<Resources<Resource<PostDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<PostDTO>>> getPostsCached(WebRequest webRequest) {
        
        logger.debug("rest getPosts({}) hateoas cached version");
       
        final Collection<PostDTO> postsDTO = postFacade.getAllPosts();
        final Collection<Resource<PostDTO>> postResourceCollection = new ArrayList();

        for (PostDTO p : postsDTO) {
        	postResourceCollection.add(postResourceAssembler.toResource(p));
        }

        Resources<Resource<PostDTO>> postsResources = new Resources(postResourceCollection);
        postsResources.add(linkTo(PostsControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(postsResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(postsResources);
    }

    /**
     *
     * Get one post according to id
     * 
     * @param id identifier for post
     * @return HttpEntity<Resource<PostDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<PostDTO>> getPost(@PathVariable("id") long id) throws Exception {
        
        logger.debug("rest getPost({}) hateoas", id);

        try {
        	PostDTO postDTO = postFacade.getPostById(id);
            Resource<PostDTO> resource = postResourceAssembler.toResource(postDTO);
            return new ResponseEntity<Resource<PostDTO>>(resource, HttpStatus.OK);    
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
        /**
     * Delete one post by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/posts/1
     *
     * @param id identifier for post
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePost(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deletePost({}) hateoas", id);
        try {
            postFacade.deletePost(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
