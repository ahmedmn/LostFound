package com.LostFound.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.LostFound.entity.Category;
import com.LostFound.entity.Item;

/**
 * ItemDAOImpl implements ItemDAO to give some basic operations such as insert,
 * update, delete and find data in Item table .
 * 
 * @author Ahmed
 **/

@Repository("ItemDAOImpl")
public class ItemDAOImpl implements ItemDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void create(Item item) {
		entityManager.persist(item);
	}

	@Transactional
	public Item update(Item t) {
		return entityManager.merge(t);
	}

	@Transactional
	public void delete(Item t) {// throws IllegalArgumentException {

		entityManager.remove(findById(t.getId()));
	}

	public Item findById(Long id) {
		return entityManager.find(Item.class, id);
	}

	public List<Item> findAll() {
		return entityManager.createQuery("select t from Item t", Item.class)
				.getResultList();
	}

	public List<Item> findByName(String name) {
		return entityManager
				.createQuery("SELECT t FROM Item t WHERE t.name like :name ",
						Item.class).setParameter("name", "%" + name + "%")
				.getResultList();
	}

	@Override
	public List<Item> findByKeywords(String keywords) {
		return entityManager
				.createQuery("SELECT t FROM Item t WHERE t.keywords like :keywords ",
						Item.class).setParameter("keywords", "%" + keywords + "%")
				.getResultList();
	}

	@Override
	public List<Item> findByDescription(String description) {
		return entityManager
				.createQuery("SELECT t FROM Item t WHERE t.description like :description ",
						Item.class).setParameter("description", "%" + description + "%")
				.getResultList();
	}

	@Override
	public List<Item> findByCategory(Category categories) {
		return entityManager
				.createQuery("SELECT t FROM Item t WHERE t.categories = :categories ",
						Item.class).setParameter("categories", "%" + categories.getId() + "%")
				.getResultList();
	}

}
