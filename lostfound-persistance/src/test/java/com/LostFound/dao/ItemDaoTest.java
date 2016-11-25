package com.LostFound.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;

import com.LostFound.entity.Category;
import com.LostFound.entity.Item;

/**
 * ItemDaoTest class is used to test functionalities of each Item.
 *
 * @author Ahmed
 *
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-persistance.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ItemDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ItemDAO itemdao;

	@Test()
	public void create() {
		Item item = new Item();

		item.setName("Labtop");
		item.setDescription("Apple laptop Macbook pro");
		item.setKeywords("Apple, Laptop, Macbook");
		itemdao.create(item);

		Assert.assertEquals(itemdao.findById(item.getId()).getName(), "Labtop");
	}

	@Test
	public void update() {
		Item item = new Item();
		item.setName("Labtop1");
		item.setDescription("Apple laptop Macbook pro1111");
		item.setKeywords("Apple, Laptop, Macbook,pro, 20160311");
		itemdao.create(item);

		item.setName("Labtop");
		item.setDescription("Apple laptop Macbook pro");
		item.setKeywords("Apple, Laptop, Macbook,pro, 2016");

		itemdao.update(item);
		Assert.assertEquals(itemdao.findById(item.getId()).getKeywords(), "Apple, Laptop, Macbook,pro, 2016");
	}

	@Test
	public void delete() {
		Item t1 = new Item();
		t1.setName("Wallet money");
		t1.setDescription("Wallet");
		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
		itemdao.create(t1);

		Assert.assertNotNull(itemdao.findById(t1.getId()));
		itemdao.delete(t1);
		Assert.assertNull(itemdao.findById(t1.getId()));
	}

	@Test
	public void findById() {
		Item t1 = new Item();
		t1.setName("Wallet money");
		t1.setDescription("Wallet");
		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
		itemdao.create(t1);

		Assert.assertNotNull(itemdao.findById(t1.getId()));
	}

	@Test
	public void findAll() {
		Item item = new Item();
		item.setName("Labtop1");
		item.setDescription("Apple laptop Macbook pro1111");
		item.setKeywords("Apple, Laptop, Macbook,pro, 20160311");
		itemdao.create(item);
		
		List<Item> items = itemdao.findAll();
		Assert.assertEquals(items.size(), 1);
	}

	@Test
	public void findByName() {
		Item t1 = new Item();
		t1.setName("Labtop");
		t1.setDescription("Labtop");
		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
		itemdao.create(t1);
		
		List<Item> items = itemdao.findByName("Labtop");
		
		Assert.assertEquals(items.size(), 1);
	}

	@Test
	public void findByKeywords() {
		Item t1 = new Item();
		t1.setName("Labtop");
		t1.setDescription("Labtop");
		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
		itemdao.create(t1);
		
		List<Item> items = itemdao.findByKeywords("Wallet");
		
		Assert.assertEquals(items.size(), 1);
	}
	@Test
	public void findByDescription() {
		Item t1 = new Item();
		t1.setName("Labtop");
		t1.setDescription("Labtop");
		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
		itemdao.create(t1);
		
		List<Item> items = itemdao.findByDescription("Labtop");
		
		Assert.assertEquals(items.size(), 1);
	}
	
//	@Test
//	public void findByCategory() {
//		Item t1 = new Item();
//		Category cat = new Category();
//		t1.setName("Labtop");
//		t1.setDescription("Labtop");
//		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
//		t1.addCategory(cat);
//		itemdao.create(t1);
//		
//		List<Item> items = itemdao.findByCategory(cat);
//		
//		Assert.assertEquals(items.size(), 1);
//	}
}
