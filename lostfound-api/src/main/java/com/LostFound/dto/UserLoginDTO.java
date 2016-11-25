package com.LostFound.dto;

/**
 * Created by bokos on 23/11/2016.
 */
public class UserLoginDTO
{
    private Long userId;
    private String password;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
