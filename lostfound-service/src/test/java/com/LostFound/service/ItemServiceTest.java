package com.LostFound.service;

import com.LostFound.dao.ItemDAO;
import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author bokos
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-service.xml")
public class ItemServiceTest extends AbstractTestNGSpringContextTests {

    private Item testItem;

    @Mock
    private Category category;

    @Mock
    private ItemDAO itemDao;

    @Autowired
    @InjectMocks
    private ItemService itemService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createItem() {
        testItem = new Item();
        testItem.setId(1L);
    }

//    @Test
//    public void findItemById()
//    {
//        when(itemDao.findById(any(Long.class))).thenReturn(testItem);
//
//
//    }
}
