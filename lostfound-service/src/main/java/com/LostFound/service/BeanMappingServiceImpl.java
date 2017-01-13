/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal
 */

@Service
public class BeanMappingServiceImpl implements BeanMappingService {
	
	@Autowired
    private Mapper dozer;

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {        
        List<T> mappedCollection = new ArrayList<T>();
       
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        if (u == null || mapToClass == null) return null;
        return dozer.map(u,mapToClass);
    }
          
    public Mapper getMapper(){
    	return dozer;
    }

}
