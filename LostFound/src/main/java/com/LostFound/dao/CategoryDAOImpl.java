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
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of CategoryDao interface.
 * For more information see interface.
 * 
 * @author michal
 */

@Repository
public class CategoryDAOImpl implements CategoryDAO{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void create(Category category) {
            em.persist(category);
    } 

    @Override
    @Transactional
    public void update(Category category) {
            em.merge(category);
    }

    @Override
    @Transactional
    public void delete(Category category) throws IllegalArgumentException {
            em.remove(category);
    }

    @Override
    public Category findById(Long id) {
            return em.find(Category.class, id);
    }

    @Override
    public Category findByName(String name) {
            return em.createQuery("select c from Category c "
                        + "where c.name like :name", Category.class).setParameter("name", name)
                        .getSingleResult();
    }

    @Override
    public List<Category> findAll() {
            return em.createQuery("select c from Category c", Category.class)
				.getResultList();
    }
    
}
