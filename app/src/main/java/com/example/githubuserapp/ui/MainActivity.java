package com.example.githubuserapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;
import com.example.githubuserapp.R;
import com.example.githubuserapp.data.response.GithubSearchResponse;
import com.example.githubuserapp.data.response.User;
import com.example.githubuserapp.data.retrofit.ApiConfig;
import com.example.githubuserapp.data.retrofit.ApiService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvReview);

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubSearchResponse> call = apiService.searchUsers("leeb");

        call.enqueue(new Callback<GithubSearchResponse>() {
            @Override
            public void onResponse(Call<GithubSearchResponse> call, Response<GithubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getUsers();
                    adapter = new UserAdapter(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                Toast.makeText(MainActivity.this, "Gagal dalam mendapatkan 'user'", Toast.LENGTH_SHORT).show();
            }
            }
            @Override
            public void onFailure(Call<GithubSearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
