package com.LostFound.dao;

import com.LostFound.entity.Post;
import com.LostFound.enums.PostState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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

//    TODO - !!! Uncomment when user class will be implemented !!!
//    @Override
//    public List<Post> findByUser(User user) {
//        TypedQuery<Post> query = em.createQuery(
//                "SELECT p FROM Post p WHERE p.user = :userid",
//                Post.class);
//        query.setParameter("userid", user);
//        return query.getResultList();
//    }

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
    public List<Post> findCreatedBetween(Date start, Date end) {
        TypedQuery<Post> query = em
                .createQuery(
                        "SELECT p FROM Post p WHERE p.state = :state AND " +
                        "p.created BETWEEN :startDate AND :endDate",
                        Post.class);
        query.setParameter("state", PostState.OPENED);
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

}
