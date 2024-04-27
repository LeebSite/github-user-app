package com.example.githubuserapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubuserapp.R;
import com.example.githubuserapp.data.response.User;
import com.example.githubuserapp.data.retrofit.ApiConfig;
import com.example.githubuserapp.data.retrofit.ApiService;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvFollowers, tvFollowing;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<User> userCall = apiService.getUser(username);

            TextView tvName = findViewById(R.id.tvName);
            TextView tvUsername = findViewById(R.id.tvUsername);
            TextView tvBio = findViewById(R.id.tvBio);
            ImageView ivAvatar = findViewById(R.id.ivAvatar);

            showLoading(true);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        showLoading(false);
                        User user = response.body();
                        if (user != null) {
                            String name = "Name : " + user.getName();
                            String usernames = "Username : " + user.getUsername();
                            String bio = "Bio : " + user.getBio();
                            String avatar = user.getAvatarUrl();

                            tvName.setText(name);
                            tvUsername.setText(usernames);
                            tvBio.setText(bio);
                            Picasso.get().load(avatar).into(ivAvatar);
                        } else {
                            Toast.makeText(DetailActivity.this, "Gagal untuk mendapatkan 'users'", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}