/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import com.LostFound.entity.Item;

/**
 *
 * @author Michal
 */
public interface ItemService {

    public Item findById(Long itemId);
    //todo
}
