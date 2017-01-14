package com.LostFound.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Michal
 */
public class UserRegisterDTO {

    @NotNull
    @Size(min = 4, max = 30, message ="Password size should be between 4 and 30 characters!")
    private String password;

    @Pattern(regexp=".+@.+\\....?", message = "Wrong email format. Email address should look like user@gmail.com!")
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 30, message ="Username size should be between 4 and 30 characters!")
    private String username;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be exactly 9 digits long!")
    private String phoneNumber;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRegisterDTO other = (UserRegisterDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" + "password=" + password + ", email=" + email + ", username=" + username + ", phoneNumber=" + phoneNumber + '}';
    }


}
