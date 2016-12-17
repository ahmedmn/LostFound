    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author michal
 */
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private String name;
    
    @ManyToMany(mappedBy="categories", cascade = CascadeType.REMOVE)
    private Set<Item> items = new HashSet<Item>();

    public Category() {
    }

    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Set<Item> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    
    // hashCode and equals
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
            if (! (obj instanceof Category))
                    return false;
            Category other = (Category) obj;
            if (name == null) {
                    if (other.getName() != null)
                            return false;
            } else if (!name.equals(other.getName()))
                    return false;
            return true;
    }
    
    
}
