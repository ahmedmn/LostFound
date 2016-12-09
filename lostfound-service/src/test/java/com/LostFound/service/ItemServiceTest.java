package com.LostFound.service;

import com.LostFound.dao.CategoryDAO;
import com.LostFound.dao.ItemDAO;
import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import com.LostFound.exceptions.LostFoundServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.LostFound.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import org.testng.Assert;

/**
 * @author bokos
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ItemServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ItemDAO itemDao;
    
    @Mock
    private CategoryDAO categoryDao;

    @Autowired
    @InjectMocks
    private ItemService itemService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    Item expectedItem;
    
    Category cat;
    
    private List<Item> expectedItemList = new ArrayList<Item>();
    
    @BeforeMethod
    public void createTestData() {
        // reset invocation counters on mocks
        reset(itemDao);
        reset(categoryDao);

        expectedItem = item();
        expectedItem.setId(4L);

        cat = itemCategory();
        expectedItemList.add(expectedItem);
    }
    
    @Test
    public void createItem() {
        //call service method
        itemService.createItem(expectedItem);
        //verify
        verify(itemDao, times(1)).create(expectedItem);
    }
    
    @Test
    public void deleteItem() {
        //call service method
        itemService.deleteItem(expectedItem);
        //verify
        verify(itemDao, times(1)).delete(expectedItem);
    }
    
    @Test
    public void addCategoryToItemSuccessfully() {
        //call service method
        itemService.addCategory(expectedItem, cat);
        //verify
        verify(itemDao, times(1)).update(expectedItem);
        //check
        Assert.assertEquals(expectedItem.getCategories(), Collections.singletonList(cat));
    }
    
   @Test(expectedExceptions=LostFoundServiceException.class)
   public void addCategoryToItemFailed() {
        //setup
       expectedItem.addCategory(cat);
       //call service method and expect throwing exception
       itemService.addCategory(expectedItem, cat);
   }
   
    @Test
    public void deleteItemCategory() {
        //call service method
        itemService.deleteCategory(expectedItem, cat);
        //verify
        Assert.assertTrue(expectedItem.getCategories().isEmpty());
    }
    
    @Test
    public void findById(){
        //setup
        Long idToFind = 10L;
        //mock
        when(itemDao.findById(any(Long.class))).thenReturn(expectedItem);
        //call service method
        Item actualItem = itemService.findById(idToFind);
        //verify
        verify(itemDao, times(1)).findById(idToFind);
        //check
        Assert.assertEquals(actualItem, expectedItem);
    }
    
    @Test
    public void findByCategory(){
        //setup
        Category cat = new Category();
        //mock
        when(itemDao.findByCategory(any(Category.class))).thenReturn(expectedItemList);
        //call service method
        List<Item> actualItemList = itemService.findByCategory(cat);
        //verify
        verify(itemDao, times(1)).findByCategory(cat);
        //check
        Assert.assertEquals(actualItemList, expectedItemList);
    }
    
    @Test
    public void findByDescription(){
        //setup
        String description = "Lost";
        //mock
        when(itemDao.findByDescription(any(String.class))).thenReturn(expectedItemList);
        //call service method
        List<Item> actualItemList = itemService.findByDescription(description);
        //verify
        verify(itemDao, times(1)).findByDescription(description);
        //check
        Assert.assertEquals(actualItemList, expectedItemList);
    }
    
    @Test
    public void findByName(){
        //setup
        String name = "mywallet";
        //mock
        when(itemDao.findByName(any(String.class))).thenReturn(expectedItemList);
        //call service method
        List<Item> actualItemList = itemService.findByName(name);
        //verify
        verify(itemDao, times(1)).findByName(name);
        //check
        Assert.assertEquals(actualItemList, expectedItemList);
    }
    
       @Test
    public void findByKeywords(){
        //setup
        String keyword = "Kitchen";
        //mock
        when(itemDao.findByKeywords(any(String.class))).thenReturn(expectedItemList);
        //call service method
        List<Item> actualItemList = itemService.findByKeywords(keyword);
        //verify
        verify(itemDao, times(1)).findByKeywords(keyword);
        //check
        Assert.assertEquals(actualItemList, expectedItemList);
    }
        
    @Test
    public void findAll(){
        //mock
        when(itemDao.findAll()).thenReturn(expectedItemList);
        //call service method
        List<Item> actualItemList = itemService.findAll();
        //verify
        verify(itemDao, times(1)).findAll();
        //check
        Assert.assertEquals(actualItemList, expectedItemList);
    }
    
    
    private Item item() {
        Item item = new Item();
        item.setDescription("Lost");
        item.setKeywords("wallet");
        item.setName("mywallet");
        item.setId(10l);
        return item;
    }
    
    private Category itemCategory(){
        Category cat = new Category();
        cat.setName("Kichen");
        cat.setId(10l);
        return cat;
    }
    
}
