package com.LostFound.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



/**
 * @author Ahmed
 * Item class is the base class storing and retrieving information about items that will be lost or found 
 */

@Entity
public class Item {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private byte[] image;
    
    @Column(name="keywords")
    private String keywords;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<Category>(); 

    public int getId() {
            return id;
    }

    public void setId(int id) {
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

	@Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
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
            return true;
    }	
}
