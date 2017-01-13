package com.lostfound.mvc.controllers;

import com.LostFound.dto.PostCreateDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.dto.UserDTO;
import com.LostFound.dto.UserRegisterDTO;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import com.LostFound.facade.PostFacade;
import com.LostFound.facade.UserFacade;
import static com.lostfound.mvc.controllers.UserController.log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.validation.Valid;
import static org.apache.taglibs.standard.resources.Resources.getMessage;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Michal
 * 
 * Controller for post.
 */
@Controller
@RequestMapping("/post")
public class PostController {

    final static Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostFacade postFacade;
    
    @Autowired
    private UserFacade userFacade;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {       
        model.addAttribute("posts", postFacade.getAllPosts());
        return "post/list";
    }
    
    /**
    * Shows a list of posts, filtered by specified filterType
    *
    * @param filterType selects filterType
    * @param value select specific value for filterType (for example filterType can be user and value can be userId)
    * @param model data to display
    * @return JSP page name
    */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String list(@RequestParam String filterType, @RequestParam String location, @RequestParam String userName, 
            @RequestParam String postType, @RequestParam String keywordList, Model model) {
        List<PostDTO> posts = null;
               
        switch (filterType){
            case "5":            
                    posts = postFacade.getAllPosts();
                break;
            case "4":
                List<String> keywords = new ArrayList<>();
                String[] tmpKeywords = keywordList.split("\\r?\\n");
                for (int i=0; i < tmpKeywords.length; i++)
                {
                    keywords.add(tmpKeywords[i]);
                } 
                //model.addAttribute("alert_warning", keywords.get(1));
                posts = postFacade.getPostsByKeywords(keywords);
                
                if (posts.isEmpty()) {
                    model.addAttribute("alert_warning", "There is not post with specified keywords");
                    posts = postFacade.getAllPosts();
                }
                break;
            case "2":
                UserDTO user = userFacade.findUserByName(userName);
                if (user == null) {
                    model.addAttribute("alert_warning", "There is not user with specified name");
                    posts = postFacade.getAllPosts();
                }
                else posts = postFacade.getPostsByUser(user.getId());
                break;
            case "1":
                posts = postFacade.getPostsByLocation(location);
                if (posts.isEmpty()) {                  
                    model.addAttribute("alert_warning", "There is not post with specified location");
                    posts = postFacade.getAllPosts();
                }
                break;
            case "0":
                if (postType.equals("0")) posts = postFacade.getPostsByType(PostType.LOST);
                if (postType.equals("1")) posts = postFacade.getPostsByType(PostType.FOUND);
                break;
        }
       
        model.addAttribute("posts", posts);
        return "post/list";
    }
    
       
    /**
    * Shows specific post details
    *
    * @param id id of selected post
    * @param model data to display
    * @return JSP page post detail
    */
    @RequestMapping(value = "/postDetail/{id}", method = RequestMethod.POST)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("post", postFacade.getPostById(id));
        return "post/postDetail";
    }
    
      /**
     * Function for new post form.
     *
     * @param model data to fill
     * @return      JSP page for creating new post
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        log.debug("new()");
        model.addAttribute("postCreate", new PostCreateDTO());
        return "post/new";
    }

    /**
     * Function for creating post.
     *
     * @param formBean              DTO containing filled post information
     * @param bindingResult         form validation
     * @param model                 data to display
     * @param redirectAttributes    redirect attributes
     * @return                      diplayed JSP page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("postCreate") PostCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes){
        
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


    @ModelAttribute("postTypes")
    public PostType[] postTypes() {
        log.debug("postType()");
        return PostType.values();
    }

}