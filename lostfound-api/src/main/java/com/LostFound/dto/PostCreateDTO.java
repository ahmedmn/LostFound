/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LostFound.dto;


import com.LostFound.enums.PostType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michal, Peter
 */
public class PostCreateDTO {


    @NotNull
    @Size(min = 3, max = 100)
    private String location;

    @NotNull
    private PostType type;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 500)
    private String description;

    private byte[] image;

    private String keywords;

    private Long categoryId;

    private UserDTO userDTO;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCreateDTO that = (PostCreateDTO) o;

        if (!location.equals(that.location)) return false;
        return (type != that.type);

    }

    @Override
    public int hashCode() {
        int result = location.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
