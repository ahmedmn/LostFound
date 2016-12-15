package com.lostfound.rest.assemblers;

import com.LostFound.dto.ItemDTO;
import com.lostfound.rest.controllers.ItemsControllerHateoas;
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
public class ItemResourceAssembler implements ResourceAssembler<ItemDTO, Resource<ItemDTO>> {

    @Override
    public Resource<ItemDTO> toResource(ItemDTO itemDTO) {
        long id = itemDTO.getId();
        Resource<ItemDTO> itemResource = new Resource<ItemDTO>(itemDTO);

        try {
        	itemResource.add(linkTo(ItemsControllerHateoas.class).slash(itemDTO.getId()).withSelfRel());
        	itemResource.add(linkTo(ItemsControllerHateoas.class).slash(itemDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(ItemResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from ItemsControllerHateoas", ex);
        }

        return itemResource;
    }
}
