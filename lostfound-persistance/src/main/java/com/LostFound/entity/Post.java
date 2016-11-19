package com.LostFound.entity;

import com.LostFound.enums.PostState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Peter
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    private List<Item> postItems = new ArrayList<>();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @NotNull
    private String location;

    @Enumerated
    @NotNull
    private PostState state;

    public Post(Long postId) {
        this.setId(postId);
    }
    
    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getPostItems() {
        return Collections.unmodifiableList(postItems);
    }

    public void addPostItem(Item item) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

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
