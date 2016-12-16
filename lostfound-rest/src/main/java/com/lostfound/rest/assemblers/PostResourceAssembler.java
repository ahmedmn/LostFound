package com.lostfound.rest.assemblers;

import com.LostFound.dto.PostDTO;
import com.lostfound.rest.controllers.PostsControllerHateoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 *
 * @author ahmed
 */
@Component
public class PostResourceAssembler implements ResourceAssembler<PostDTO, Resource<PostDTO>> {

    @Override
    public Resource<PostDTO> toResource(PostDTO postDTO) {
        Resource<PostDTO> postResource = new Resource<PostDTO>(postDTO);

        try {
        	postResource.add(linkTo(PostsControllerHateoas.class).slash(postDTO.getId()).withSelfRel());
        	postResource.add(linkTo(PostsControllerHateoas.class).slash(postDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(PostResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from PostsControllerHateoas", ex);
        }

        return postResource;
    }
}
