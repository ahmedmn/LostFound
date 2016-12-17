package com.LostFound.facade;//package com.LostFound.facade;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.LostFound.dto.CategoryCreateDTO;
//import com.LostFound.dto.CategoryDTO;
//
//
//@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
//public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {
//	@Autowired
//    private CategoryFacade categoryFacade;
//
//    private CategoryCreateDTO categoryCreateDTO;
//    private CategoryDTO expectedCategoryDTO;
//    private Long newCategoryId;
//
//
//    public void createTestData() {
//        //initialize calendar instance
//        // according to postCreateDTO values new post in DB is created
//    	categoryCreateDTO = new CategoryCreateDTO();
//    	categoryCreateDTO.setUsername("Technology");
//    }
//
//
//    @Test
//    void createCategory()    {
//    	CategoryDTO actualCategoryDTO = categoryFacade.getCategoryById(newCategoryId);
//        Assert.assertEquals(actualCategoryDTO, expectedCategoryDTO);
//    }
//
//    @Test
//    public void deleteCategory()
//    {
//        Assert.assertEquals(categoryFacade.getAllCategories().size(), 1);
//        Assert.assertNotNull(categoryFacade.getCategoryById(newCategoryId));
//        categoryFacade.deleteCategory(newCategoryId);
//        Assert.assertEquals(categoryFacade.getAllCategories().size(), 0);
//        Assert.assertNull(categoryFacade.getCategoryById(newCategoryId));
//    }
//
//    @Test
//    public void getPostById()
//    {
//        Assert.assertEquals(categoryFacade.getCategoryById(newCategoryId), expectedCategoryDTO);
//    }
//
//}
