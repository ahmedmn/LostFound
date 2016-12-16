package com.lostfound.mvc.controllers;


import com.LostFound.dto.UserLoginDTO;
import com.LostFound.dto.UserRegisterDTO;
import com.LostFound.facade.UserFacade;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author Michal
 * Controller for user registration and authentization.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);
   
    @Autowired
    private UserFacade userFacade;
    
    	    @RequestMapping(value="/register", method = RequestMethod.GET)
	    public String registerForm(Model model) {

	           model.addAttribute("userCreate", new UserRegisterDTO());
                    return "user/register";
	    }
            
             @RequestMapping(value="/register", method = RequestMethod.POST)
	    public String register(@Valid @ModelAttribute("userCreate") UserRegisterDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

	           log.debug("register(formBean={})", formBean);
                    //in case of validation error forward back to the the form
                    if (bindingResult.hasErrors()) {
                        for (ObjectError ge : bindingResult.getGlobalErrors()) {
                            log.trace("ObjectError: {}", ge);
                        }
                        for (FieldError fe : bindingResult.getFieldErrors()) {
                            model.addAttribute(fe.getField() + "_error", true);
                            log.trace("FieldError: {}", fe);
                        }
                        return "user/register";
                    }
                    //create user
                    userFacade.registerUser(formBean, formBean.getPassword());
                    //report success
                    redirectAttributes.addFlashAttribute("You have been successfuly registered. You can login now.");
                    return "redirect:" + uriBuilder.path("/user/login").toUriString();
	    }
            
             @RequestMapping(value="/login", method = RequestMethod.GET)
	    public String loginForm(Model model) {

	           model.addAttribute("userLogin", new UserLoginDTO());
                    return "user/login";
	    }
            
             @RequestMapping(value="/login", method = RequestMethod.POST)
	    public String login(@Valid @ModelAttribute("userLogin") UserLoginDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

	           log.debug("login(formBean={})", formBean);
                    //in case of validation error forward back to the the form
                    if (bindingResult.hasErrors()) {
                        for (ObjectError ge : bindingResult.getGlobalErrors()) {
                            log.trace("ObjectError: {}", ge);
                        }
                        for (FieldError fe : bindingResult.getFieldErrors()) {
                            model.addAttribute(fe.getField() + "_error", true);
                            log.trace("FieldError: {}", fe);
                        }
                        return "user/login";
                    }
                    //create user
                    userFacade.login(formBean);
                    //report success
                    redirectAttributes.addFlashAttribute("You have been successfuly registered. You can login now.");
                    return "redirect:" + uriBuilder.path("/").toUriString();
	    }
}
