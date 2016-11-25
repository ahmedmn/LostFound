package com.LostFound.service;

import com.LostFound.dao.CategoryDAO;
import com.LostFound.entity.Category;

import java.util.ArrayList;

import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Ahmed
 * CategoryServiceTest class is used to test functionalities of Category Service
 */

@ContextConfiguration(locations = "file:src/main/resources/spring-service.xml")
public class CategoryServiceTest extends AbstractTestNGSpringContextTests {

	@Mock
	private CategoryDAO categoryDao;

	@Autowired
	@InjectMocks
	private CategoryService categorycervice;

	@Mock
	private Category expectedCategory;

	@Mock
	private List<Category> expectedCategoryList = new ArrayList<Category>();

	@BeforeClass
	public void setup() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void createCategories() {
		Long id = 10L;

		expectedCategory = new Category();
		expectedCategory.setId(id);
		expectedCategory.setName("Cloth");
	}

	@Test
	public void createCategory() {
		// call service method
		categorycervice.createCategory(expectedCategory);

		// verify
		verify(categoryDao, times(1)).create(expectedCategory);
	}

	@Test
	public void findById() {
		// setup
		Long id = 10L;
		// mock
		when(categoryDao.findById(any(Long.class))).thenReturn(expectedCategory);
		// call service method
		Category actualcategory = categorycervice.findById(id);
		// verify
		verify(categoryDao, times(1)).findById(id);
		// check
		Assert.assertEquals(actualcategory, expectedCategory);
	}

	@Test
	public void findByName() {
		// setup
		String name = "Technologies";
		// mock
		when(categoryDao.findByName(any(String.class))).thenReturn(expectedCategory);
		// call service method
		Category actualUser = categorycervice.findByName(name);
		// verify
		verify(categoryDao, times(1)).findByName(name);
		// check
		Assert.assertEquals(actualUser, expectedCategory);
	}

	@Test
	public void findAll() {
		// mock
		when(categoryDao.findAll()).thenReturn(expectedCategoryList);
		// call service method
		List<Category> actualUserList = categorycervice.findAll();
		// verify
		verify(categoryDao, times(1)).findAll();
		// check
		Assert.assertEquals(actualUserList, expectedCategoryList);
	}

	@Test
	public void deleteCategory() {

		// call service method
		categorycervice.deleteCategory(expectedCategory);

		// verify
		verify(categoryDao, times(1)).delete(expectedCategory);

	}

}
