package com.lostfound.sampledata;

import com.LostFound.entity.Category;
import com.LostFound.entity.Item;
import com.LostFound.entity.Post;
import com.LostFound.entity.User;
import com.LostFound.enums.PostState;
import com.LostFound.enums.PostType;
import com.LostFound.service.CategoryService;
import com.LostFound.service.ItemService;
import com.LostFound.service.PostService;
import com.LostFound.service.UserService;
//import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by pedro1 on 6.12.2016.
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @Override
    public void loadData() throws IOException {
        Category technologyCategory = category("Technology");
        Category accessoriesCategory = category("Accessories");
        Category petsCategory = category("Pets");
        Category toysCategory = category("Toys");

        User petr = user("petr123", "heslo", "pepa@novak.cz", "603123456", toDate(2015, 5, 12), false);
        User savco = user("savco", "heslo", "jiri@dvorak.cz", "603789123", toDate(2015, 3, 5), false);
        User bokofak = user("bokofak", "heslo", "eva@adamova.cz", "603457890", toDate(2015, 6, 5), false);
        User admin = user("pedro1", "admin", "admin@eshop.com", "9999999999", toDate(2014, 12, 31), true);

//      LOST items
        Item walletItem = postItem("wallet", "Wallet contains credit card, ISIC card and 20 crowns.", "wallet.jpg", "wallet", accessoriesCategory);
        Item isicCardItem = postItem("isic card", "UCO of ISIC is 422123.", null, "ISIC:MasarykUniversity", accessoriesCategory);
        Item dogItem = postItem("Rocky", "Reacts to name Rocky and has blue dog-collar.", "dog.jpg", "dog:germanShepard", petsCategory);
        Item keysItem = postItem("keys", "Keys containing USB stick.", "keys.jpg", "keys", accessoriesCategory);
        Item mobileItem = postItem("mobile", "Its brand is Samsung.", "mobile.jpg", "mobile", accessoriesCategory, technologyCategory);

//      FOUND items
        Item stuffedToyItem = postItem("bird stuffed toy", "My little daughter lost her favorite stuffed toy. Probably near her elementary school.", "bird.jpg", "bird:stuffedToy", toysCategory);
        Item watchItem = postItem("CASIO watch", "Silver Watch of brand CASIO", "watch.jpg", "watch:CASIO", technologyCategory);

//      LOST POSTS
        post(petr, "Brno", toDate(2016, 1, 13), PostState.OPENED, PostType.LOST, walletItem, isicCardItem);
        post(bokofak, "New York", toDate(2016, 5, 5), PostState.OPENED, PostType.LOST, mobileItem);
        post(savco, "Brno", toDate(2016, 6, 20), PostState.OPENED, PostType.LOST, dogItem);
        post(petr, "Dubai", toDate(2016, 7, 10), PostState.OPENED, PostType.LOST, keysItem);

//      FOUND POSTS
        post(petr, "Brno", toDate(2016, 8, 25), PostState.OPENED, PostType.FOUND, watchItem);
        post(savco, "Bratislava", toDate(2016, 5, 2), PostState.OPENED, PostType.FOUND, stuffedToyItem);
    }


    private static Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Post post(User user, String location, Date creationDate, PostState state, PostType type, Item... itemList) {
        Post post = new Post();
        post.setUser(user);
        post.setLocation(location);
        post.setCreationDate(creationDate);
        post.setState(state);
        post.setType(type);
        for (Item item : itemList) {
            post.addPostItem(item);
        }
        postService.createPost(post);
        return post;
    }

    private Item postItem(String name, String description, String imageName, String keyWords, Category... categoryList) throws IOException {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setKeywords(keyWords);
        //item.setImage(readImage(imageName));
        for (Category category : categoryList) {
            item.addCategory(category);
        }
        itemService.createItem(item);
        return item;
    }

    private User user(String userName, String password, String email, String phoneNumber, Date joinedDate, boolean admin) {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setJoinedDate(joinedDate);
        userService.registerUser(user, password);
        return user;
    }

    private Category category(String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.createCategory(category);
        return category;
    }

    private byte[] readImage(String file) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
