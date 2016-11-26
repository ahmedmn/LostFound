package com.LostFound.service.config;

import com.LostFound.dto.CategoryDTO;
import com.LostFound.dto.ItemDTO;
import com.LostFound.dto.PostDTO;
import com.LostFound.dto.UserDTO;
import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Peter
 */
@Configuration
public class ServiceConfiguration {
	

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
	
	/**
	 * Custom config for Dozer
	 *
	 */
	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(Category.class, CategoryDTO.class);
	        mapping(User.class, UserDTO.class);
	        mapping(Item.class, ItemDTO.class);
	        mapping(Post.class, PostDTO.class);
	    }
	}
	
}

