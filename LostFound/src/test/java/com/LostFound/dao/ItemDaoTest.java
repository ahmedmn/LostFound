package com.LostFound.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.LostFound.entity.Item;
import java.util.List;

/**
 * ItemDaoTest class is used to test functionalities of each Item.
 *
 * @author Ahmed
 *
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class ItemDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemDAO itemdao;

    private Item t1;
    private Item t2;

//	@BeforeMethod
//	public void createTests() {
//		t1 = new Item();
//		t2 = new Item();
//
//		t1.setId(1);
//		t1.setName("Wallet money");
//		t1.setDescription("Wallet");
//		t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
//
//		t2.setId(2);
//		t2.setName("keys");
//		t2.setDescription("Car's Keys");
//		t2.setKeywords("keys, car, keychain, new");
//
//		itemdao.create(t1);
//		itemdao.create(t2);
//	}
    @Test()
    public void create() {
        Item item = new Item();

        item.setId(3);
        item.setName("Labtop");
        item.setDescription("Apple laptop Macbook pro");
        item.setKeywords("Apple, Laptop, Macbook");
        itemdao.create(item);

        Assert.assertEquals(itemdao.findById(item.getId()).getName(), "Labtop");
    }

    @Test
    public void update() {
        Item item = new Item();
        item.setId(1);
        item.setName("Labtop");
        item.setDescription("Apple laptop Macbook pro");
        item.setKeywords("Apple, Laptop, Macbook,pro, 2016");

        itemdao.update(item);
        Assert.assertEquals(itemdao.findById(item.getId()).getKeywords(),
                "Apple, Laptop, Macbook,pro, 2016");
    }

    @Test
    public void delete() {
        t1 = new Item();
        t1.setId(1);
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
        
           t1 = new Item();
        t1.setId(1);
        t1.setName("Wallet money");
        t1.setDescription("Wallet");
        t1.setKeywords("Wallet, money, currency, salary, ID card, coins");
        itemdao.create(t1);
        
        Assert.assertNotNull(itemdao.findById(1));
    }

//    @Test
//    public void findAll() {
//        List<Item> item = itemdao.findByAll();
//        Assert.assertEquals(item.size(), 2);
//    }

//    @Test
//    public void findByName() {
//        Assert.assertEquals(itemdao.findByName("Labtop").size(), 2);
//        Assert.assertEquals(itemdao.findByName("keys").size(), 1);
//    }

}
