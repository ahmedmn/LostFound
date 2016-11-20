package com.LostFound.service;

import com.LostFound.entity.Item;
import org.springframework.stereotype.Service;

/**
 * Only fake implementation of ItemService because
 * project couldn't be compiled without no implementation of ItemService
 */
@Service
public class ItemServiceImpl implements ItemService {
    public Item findById(Long itemId) {
        return null;
    }
}
