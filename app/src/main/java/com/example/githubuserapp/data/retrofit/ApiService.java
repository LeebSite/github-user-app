package com.example.githubuserapp.data.retrofit;

import com.example.githubuserapp.data.response.GithubSearchResponse;
import com.example.githubuserapp.data.response.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token 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"})
    @GET("search/users")
    Call<GithubSearchResponse> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token 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"})
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
    @Headers({"Authorization: token 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"})
    @GET("users/{username}/followers")
    Call<User> getFollowers(@Path("username") String username);

    @Headers({"Authorization: token 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"})
    @GET("users/{username}/following")
    Call<User> getFollowing(@Path("username") String username);


}
