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
