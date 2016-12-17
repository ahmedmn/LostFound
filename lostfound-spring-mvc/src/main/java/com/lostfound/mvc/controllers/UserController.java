package com.lostfound.mvc.controllers;


import com.LostFound.dto.UserDTO;
import com.LostFound.dto.UserLoginDTO;
import com.LostFound.dto.UserRegisterDTO;
import com.LostFound.facade.UserFacade;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
 * @author Michal, Peter
 *
 * Controller for user registration and authentization.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;


    /**
     *
     * @param model data to display
     * @return      diplayed JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }


    /**
     * Function for new registration form.
     *
     * @param model data to display
     * @return      JSP page for registration
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("userRegister", new UserRegisterDTO());
        return "user/register";
    }


    /**
     * Function for registration of user.
     *
     * @param userRegisterForm      DTO containing provided user information
     * @param bindingResult         form validation
     * @param model                 data to display
     * @param redirectAttributes    redirect attributes
     * @param uriBuilder            uri builder
     * @return                      diplayed JSP page name
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userRegister") UserRegisterDTO userRegisterForm,
                           BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                           UriComponentsBuilder uriBuilder) {

        log.debug("register(userRegisterForm={})", userRegisterForm);
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

        //register user in system
        if(userFacade.registerUser(userRegisterForm, userRegisterForm.getPassword()))
        {
            //report success
            redirectAttributes.addFlashAttribute("alert_success", "You have been successfuly registered. You can login now.");
            return "redirect:" + uriBuilder.path("/user/login").toUriString();
        } else {
            //report success
            redirectAttributes.addFlashAttribute("alert_danger", "User is already existing.");
            return "user/register";
        }


    }


    /**
     * Function for new login form.
     *
     * @param model data to display
     * @return      JSP page for login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {

        model.addAttribute("userLogin", new UserLoginDTO());
        return "user/login";
    }


    /**
     * Function for login user.
     *
     * @param userLoginForm         DTO containing provided name and password of user
     * @param bindingResult         form validation
     * @param r                     servlet request to create session
     * @param model                 data to display
     * @param redirectAttributes    redirect attributes
     * @param uriBuilder            uri builder
     * @return                      diplayed JSP page name
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("userLogin") UserLoginDTO userLoginForm, BindingResult bindingResult,
                        ServletRequest r, Model model, RedirectAttributes redirectAttributes,
                        UriComponentsBuilder uriBuilder) {

        log.debug("login(userLoginForm={})", userLoginForm);
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
        if (userFacade.login(userLoginForm)) {
            UserDTO loginUser = userFacade.findUserByName(userLoginForm.getUsername());
            HttpServletRequest request = (HttpServletRequest) r;
            request.getSession().setAttribute("authenticatedUser", loginUser);
            redirectAttributes.addFlashAttribute("alert_success", "You have been successfuly logged in.");
            log.info("User logged in");
            return "redirect:" + uriBuilder.path("/post/all/0").toUriString();
        } else {
            model.addAttribute("alert_warning", "Incorrect username or password ");
            log.warn("User failed login");
            return "user/login";
        }
    }


    /**
     * Function to logout user.
     *
     * @param r         servlet request to get logged user
     * @param model     data to display
     * @return          diplayed JSP page name
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ServletRequest r, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpSession session = request.getSession();
        if ((UserDTO) session.getAttribute("authenticatedUser") == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "You are not logged in.");
            return "redirect:" + uriBuilder.path("/user/login").toUriString();
        }
        log.info("User logged out");
        session.removeAttribute("authenticatedUser");
        redirectAttributes.addFlashAttribute("alert_success", "You have been succesfully logged out.");
        return "redirect:" + uriBuilder.path("/post/all/0").toUriString();
    }
}
