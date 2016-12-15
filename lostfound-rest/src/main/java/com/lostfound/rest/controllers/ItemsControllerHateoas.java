package com.lostfound.rest.controllers;

import com.LostFound.dto.ItemDTO;
import  com.LostFound.dto.UserDTO;
import  com.LostFound.facade.ItemFacade;
import com.lostfound.rest.assemblers.ItemResourceAssembler;
import com.lostfound.rest.exceptions.ResourceNotFoundException;
import com.lostfound.rest.exceptions.ResourceNotModifiedException;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
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
 * REST HATEOAS Controller for Items
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link cz.fi.muni.pa165.rest.assemblers.itemResourceAssembler} to create 
 * resources to be returned as ResponseEntities
 *
 */
@RestController
@RequestMapping("/items_hateoas")
public class ItemsControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(ItemsControllerHateoas.class);

    @Inject
    private ItemFacade itemFacade;

    @Inject
    private ItemResourceAssembler itemResourceAssembler;

    /**
     *
     * Get list of items
     * 
     * @return HttpEntity<Resources<Resource<ItemDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<ItemDTO>>> getItems() {
        
        logger.debug("rest getItems({}) hateoas");

        Collection<ItemDTO> itemsDTO = itemFacade.getAllItems();
        Collection<Resource<ItemDTO>> itemResourceCollection = new ArrayList();

        for (ItemDTO p : itemsDTO) {
        	itemResourceCollection.add(itemResourceAssembler.toResource(p));
        }

        Resources<Resource<ItemDTO>> itemsResources = new Resources<Resource<ItemDTO>>(itemResourceCollection);
        itemsResources.add(linkTo(ItemsControllerHateoas.class).withSelfRel());

        return new ResponseEntity<Resources<Resource<ItemDTO>>>(itemsResources, HttpStatus.OK);

    }
    
    /**
     *
     * Get list of items - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * 
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * 
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/eshop-rest/items_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     * 
     * @return HttpEntity<Resources<Resource<ItemDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<ItemDTO>>> getItemsCached(WebRequest webRequest) {
        
        logger.debug("rest getItems({}) hateoas cached version");
       
        final Collection<ItemDTO> itemsDTO = itemFacade.getAllItems();
        final Collection<Resource<ItemDTO>> itemResourceCollection = new ArrayList();

        for (ItemDTO p : itemsDTO) {
        	itemResourceCollection.add(itemResourceAssembler.toResource(p));
        }

        Resources<Resource<ItemDTO>> itemsResources = new Resources(itemResourceCollection);
        itemsResources.add(linkTo(ItemsControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(itemsResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(itemsResources);
    }

    /**
     *
     * Get one item according to id
     * 
     * @param id identifier for item
     * @return HttpEntity<Resource<ItemDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<ItemDTO>> getItem(@PathVariable("id") long id) throws Exception {
        
        logger.debug("rest getItem({}) hateoas", id);

        try {
        	ItemDTO itemDTO = itemFacade.getItemWithId(id);
            Resource<ItemDTO> resource = itemResourceAssembler.toResource(itemDTO);
            return new ResponseEntity<Resource<ItemDTO>>(resource, HttpStatus.OK);    
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
        /**
     * Delete one item by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/items/1
     *
     * @param id identifier for item
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteItem(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteItem({}) hateoas", id);
        try {
            itemFacade.deleteItem(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
