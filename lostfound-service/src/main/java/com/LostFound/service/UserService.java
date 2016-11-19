/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import com.LostFound.entity.User;

/**
 *
 * @author Michal
 */
public interface UserService {

    public User findById(Long userId);
   //todo 
}
