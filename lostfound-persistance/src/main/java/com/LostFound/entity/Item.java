package com.LostFound.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Ahmed Item class is the base class storing and retrieving information
 *         about items that will be lost or found
 */

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	@Lob
	private byte[] image;

	@Column(name = "keywords")
	private String keywords;
        
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	@ManyToMany
	private Set<Category> categories = new HashSet<Category>();

	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}
	
	public void addCategory(Category c) {
		categories.add(c);
		c.addItem(this);
	}

	public void deleteCategory(Category category)
	{
		this.categories.remove(category);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.getCategories()))
			return false;

		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.getKeywords()))
			return false;

		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.getImage()))
			return false;

		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.getDescription()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + 
				  ", name=" + name + 
				  ", categories=" + categories +
				  ", description=" + description + 
				  ", keywords=" + keywords + 
				  "]";
	}

}
