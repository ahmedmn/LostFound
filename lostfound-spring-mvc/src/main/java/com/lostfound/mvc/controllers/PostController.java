package com.lostfound.mvc.controllers;

import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.dto.UserRegisterDTO;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import com.LostFound.facade.PostFacade;
import com.LostFound.facade.UserFacade;
import static com.lostfound.mvc.controllers.UserController.log;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Michal
 */
@Controller
@RequestMapping("/post")
public class PostController {

    final static Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostFacade postFacade;
    
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private MessageSource messageSource;
   
    /**
    * Shows a list of posts, filtered by specified filterType
    *
    * @param filterType selects filterType
    * @param value select specific value for filterType (for example filterType can be user and value can be userId)
    * @param model data to display
    * @return JSP page name
    */
    @RequestMapping(value = {"/{filterType}/{value}",""}, method = RequestMethod.GET)
    public String list(@PathVariable String filterType,@PathVariable String value , Model model) {
        List<PostDTO> posts = null;
        switch (filterType){
            case "all":
                posts = postFacade.getAllPosts();
                break;
            case "postType": 
                if (value.equals("lost")) posts = postFacade.getPostsByType(PostType.LOST);
                if (value.equals("found")) posts = postFacade.getPostsByType(PostType.FOUND);
                break;
            case "postState":
                if (value.equals("opened")) posts = postFacade.getPostsByState(PostState.OPENED);
                if (value.equals("done")) posts = postFacade.getPostsByState(PostState.DONE);
                break;
            case "user":
                posts = postFacade.getPostsByUser(Long.valueOf(value));
                break;
            case "location":
                posts = postFacade.getPostsByLocation(value);
                break;
        }
       
        model.addAttribute("posts", posts);
        //model.addAttribute("users", userFacade.getAllUsers());
        return "post/list";
    }
    
    /**
    * Shows specific post details
    *
    * @param id id of selected post
    * @param model data to display
    * @return JSP page name
    */
    @RequestMapping(value = "/postDetail/{id}", method = RequestMethod.POST)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("post", postFacade.getPostById(id));
        return "post/postDetail";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        log.debug("new()");
        model.addAttribute("postCreate", new PostCreateDTO());
        return "post/new";
    }

    
    /**
    * Shows specific post details
    *
    * @param post form of post to be created 
    * @param id id of selected post
    * @return JSP page name
    */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("postCreate") PostCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilde){
        
        log.debug("postCreate(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "post/new";
        }
        
        Long id = postFacade.createPost(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Post was successfully created");
        return "post/list";
    }

}