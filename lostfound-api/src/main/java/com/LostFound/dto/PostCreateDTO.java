/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dto;

import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michal
 */
public class PostCreateDTO {
    
    @NotNull
    private UserDTO user;

    @NotNull
    private List<ItemDTO> postItems = new ArrayList<ItemDTO>();
    
    @NotNull
    private Date creationDate;

    @NotNull
    @Size(min = 3, max = 100)
    private String location;

    private PostState state;

    private PostType type;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ItemDTO> getPostItems() {
        return Collections.unmodifiableList(postItems);
    }

    public void addPostItem(ItemDTO item) {
        postItems.add(item);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDTO)) return false;

        PostDTO post = (PostDTO) o;

        if (getUser() != null ? !getUser().equals(post.getUser()) : post.getUser() != null) return false;
        if (!getPostItems().equals(post.getPostItems())) return false;
        if (!getCreationDate().equals(post.getCreationDate())) return false;
        return getLocation().equals(post.getLocation());

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + getPostItems().hashCode();
        result = 31 * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = 31 * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        return result;
    }
}
