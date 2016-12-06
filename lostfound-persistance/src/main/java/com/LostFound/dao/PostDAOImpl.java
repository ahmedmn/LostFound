package com.LostFound.dao;

import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * PostDAOImpl is implementation of PostDAO interface.
 * @author Peter
 */
@Repository
public class PostDAOImpl implements PostDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Post post) {
        em.persist(post);
    }

    @Override
    @Transactional
    public void update(Post post) {
        em.merge(post);
    }

    @Override
    @Transactional
    public void delete(Post post) {
        em.remove(findById(post.getId()));
    }

    @Override
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Post> findByUser(User user) {
        TypedQuery<Post> query = em.createQuery(
                "SELECT p FROM Post p WHERE p.user = :user",
                Post.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public List<Post> findByLocation(String location) {
        TypedQuery<Post> query = em.createQuery(
                "SELECT p FROM Post p WHERE p.state = :state AND " +
                "p.location = :location",
                Post.class);
        query.setParameter("state", PostState.OPENED);
        query.setParameter("location", location);
        return query.getResultList();
    }

    @Override
    public List<Post> findCreatedBetween(Date start, Date end, PostState state) {
        TypedQuery<Post> query = em
                .createQuery(
                        "SELECT p FROM Post p WHERE p.state = :state AND " +
                        "p.creationDate BETWEEN :startDate AND :endDate",
                        Post.class);
        query.setParameter("state", state);
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        return query.getResultList();
    }

    @Override
    public List<Post> findAll() {
        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p",
                Post.class);
        return query.getResultList();
    }

    @Override
    public List<Post> findByState(PostState state) {
        TypedQuery<Post> query = em.createQuery(
                "SELECT p FROM Post p WHERE p.state = :state", Post.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    @Override
    public List<Post> findByType(PostType type)
    {
        TypedQuery<Post> query = em.createQuery(
                "SELECT p FROM Post p WHERE p.type = :type", Post.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

}
