package com.lostfound.rest;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the 
 * other URIs also for the sub-resources so that it can 
 * reused globally from all the controllers
 * 
 * @author ahmed
 */
public abstract class ApiUris {
    public static final String ROOT_URI_ITEMS   = "/items"; 
    public static final String ROOT_URI_USERS      = "/users";
    public static final String ROOT_URI_POSTS     = "/posts";
    public static final String ROOT_URI_CATEGORIES = "/categories";  
}
