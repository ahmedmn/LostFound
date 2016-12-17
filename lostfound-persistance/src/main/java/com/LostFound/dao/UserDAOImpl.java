package com.LostFound.dao;

import com.LostFound.entity.User;
import com.LostFound.exceptions.LostFoundServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * UserDAOImpl is implementation of UserDAO interface.
 *
 * @author Bokos
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
            em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(findById(user.getId()));
    }
    
    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
                User.class);
        return query.getResultList();
    }
}
