package com.LostFound.service.facade;

import com.LostFound.dto.UserDTO;
import com.LostFound.dto.UserLoginDTO;
import com.LostFound.dto.UserRegisterDTO;
import com.LostFound.entity.User;
import com.LostFound.exceptions.LostFoundServiceException;
import com.LostFound.facade.UserFacade;
import com.LostFound.service.BeanMappingService;
import com.LostFound.service.UserService;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bokos
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private static final Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    public boolean registerUser(UserRegisterDTO userForm, String unencryptedPassword) {

        if (userService.findUserByName(userForm.getUsername()) != null) return false;
        if (userService.findUserByEmail(userForm.getEmail()) != null) return false;

        User userEntity = beanMappingService.mapTo(userForm, User.class);
        userEntity.setJoinedDate(new Date());
        try {
            userService.registerUser(userEntity, unencryptedPassword);
        } catch (LostFoundServiceException ex)
        {
            log.error("User cannot be registered.", ex);
            return false;
        }
        return true;
    }

    public List<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    public boolean login(UserLoginDTO user) {
        try {
            return userService.login(
                    userService.findUserByName(user.getUsername()), user.getPassword());
        } catch (LostFoundServiceException e)
        {
            log.error("User " + user + " does not exist.", e);
            return false;
        }
    }

    public boolean isAdmin(UserDTO user) {
        return userService.isAdmin(beanMappingService.mapTo(user, User.class));
    }

    public UserDTO findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    public UserDTO findUserByName(String name) {
        User user = userService.findUserByName(name);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

}
