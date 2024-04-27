package com.example.githubuserapp.data.response;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private String bio;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }
    public String getFollowersUrl() {
        return followersUrl;
    }
    public String getFollowingUrl() {
        return followingUrl;
    }

}
