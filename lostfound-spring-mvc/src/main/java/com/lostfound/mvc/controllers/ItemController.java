package com.lostfound.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.LostFound.facade.CategoryFacade;
import com.LostFound.facade.ItemFacade;

import com.LostFound.dto.CategoryDTO;
import com.LostFound.dto.ItemCreateDTO;
import com.LostFound.dto.ItemDTO;

/**
 * @author ahmed
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	final static Logger log = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemFacade itemFacade;

	@Autowired
	private CategoryFacade categoryFacade;

	/**
	 * Shows a list of products with the ability to add, delete or edit.
	 *
	 * @param model
	 *            data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("items", itemFacade.getAllItems());
		return "item/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
			RedirectAttributes redirectAttributes) {
		ItemDTO item = itemFacade.getItemWithId(id);
		itemFacade.deleteItem(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success", "Item \"" + item.getName() + "\" was deleted.");
		return "redirect:" + uriBuilder.path("/item/list").toUriString();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		log.debug("view({})", id);
		model.addAttribute("item", itemFacade.getItemWithId(id));
		return "item/view";
	}

	/**
	 * Prepares an empty form.
	 *
	 * @param model
	 *            data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newItem(Model model) {
		log.debug("new()");
		model.addAttribute("ItemCreate", new ItemCreateDTO());
		return "item/new";
	}

	@ModelAttribute("categories")
	public List<CategoryDTO> categories() {
		log.debug("categories()");
		return categoryFacade.getAllCategories();
	}

	/**
	 * Spring Validator added to JSR-303 Validator for this @Controller only. It
	 * is useful for custom validations that are not defined on the form bean by
	 * annotations.
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
	 */
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("itemCreate") ItemCreateDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("create(itemCreate={})", formBean);
		// in case of validation error forward back to the the form
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "item/new";
		}
		// create product
		Long id = itemFacade.createItem(formBean);
		// report success
		redirectAttributes.addFlashAttribute("alert_success", "Item " + id + " was created");
		return "redirect:" + uriBuilder.path("/item/view/{id}").buildAndExpand(id).encode().toUriString();
	}

}
