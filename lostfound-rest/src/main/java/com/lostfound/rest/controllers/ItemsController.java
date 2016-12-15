package com.lostfound.rest.controllers;

import com.lostfound.rest.ApiUris;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LostFound.dto.CategoryDTO;

import com.LostFound.dto.ItemCreateDTO;
import com.LostFound.dto.ItemDTO;
import com.LostFound.exceptions.LostFoundServiceException;
import com.LostFound.facade.ItemFacade;
import com.lostfound.rest.exceptions.InvalidParameterException;
import com.lostfound.rest.exceptions.ResourceAlreadyExistingException;
import com.lostfound.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Items
 *
 * @author ahmed
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_ITEMS)
public class ItemsController {

    final static Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @Inject
    private ItemFacade itemFacade;

    /**
     * Get list of Items curl -i -X GET
     * http://localhost:8080/pa165/rest/items
     *
     * @return ItemDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ItemDTO> getItems() {
        logger.debug("rest getItems()");
        return itemFacade.getAllItems();
    }

    /**
     *
     * Get Item by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/items/1
     *
     * @param id identifier for a item
     * @return ItemDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ItemDTO getItem(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getItem({})", id);
        ItemDTO itemDTO = itemFacade.getItemWithId(id);
        if (itemDTO != null) {
            return itemDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one item by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/items/1
     *
     * @param id identifier for item
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteItem(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteItem({})", id);
        try {
            itemFacade.deleteItem(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new item by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"test","description":"test","color":"UNDEFINED","price":"200",
     * "currency":"CZK", "categoryId":"1"}' 
     * http://localhost:8080/pa165/rest/items/create
     * 
     * @param item ItemCreateDTO with required fields for creation
     * @return the created item ItemDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ItemDTO createItem(@RequestBody ItemCreateDTO item) throws Exception {

        logger.debug("rest createItem()");

        try {
            Long id = itemFacade.createItem(item);
            return itemFacade.getItemWithId(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Add a new category by POST Method
     *
     * @param id the identifier of the Item to have the Category added
     * @param category the category to be added
     * @return the updated item as defined by ItemDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}/categories", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ItemDTO addCategory(@PathVariable("id") long id, @RequestBody CategoryDTO category) throws Exception {

        logger.debug("rest addCategory({})", id);

        try {
            itemFacade.addCategory(id, category.getId());
            return itemFacade.getItemWithId(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }
}
