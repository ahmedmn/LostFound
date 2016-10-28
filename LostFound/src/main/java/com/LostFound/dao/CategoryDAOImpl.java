/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dao;

import com.LostFound.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Implementation of CategoryDao interface.
 * For more information see interface.
 * 
 * @author michal
 */

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    
    EntityManager em;

    @Override
    public void create(Category category) {
            em.persist(category);
    } 

    @Override
    public void update(Category category) {
            em.merge(category);
    }

    @Override
    public void delete(Category category) throws IllegalArgumentException {
            em.remove(category);
    }

    @Override
    public Category findById(Long id) {
            return em.find(Category.class, id);
    }

    @Override
    public Category findByName(String name) {        
        try {
            return em.createQuery("select c from Category c "
                        + "where c.name like :name", Category.class).setParameter(":name", name)
                        .getSingleResult();
        } catch (NoResultException nrf) {
                return null;
        }
    }

    @Override
    public List<Category> findAll() {
            return em.createQuery("select c from Category c", Category.class)
				.getResultList();
    }
    
}
