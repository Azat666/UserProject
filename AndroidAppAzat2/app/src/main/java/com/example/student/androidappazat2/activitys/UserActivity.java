package com.example.student.androidappazat2.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.example.student.androidappazat2.Datas;
import com.example.student.androidappazat2.GetInet;
import com.example.student.androidappazat2.R;
import com.example.student.androidappazat2.adapters.UserAdapter;
import com.example.student.androidappazat2.models.ModelRecycler;
import com.example.student.androidappazat2.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getUserDatas();
        userAdapter = new UserAdapter(UserActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(userAdapter);
    }

    private void getUserDatas() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final GetInet service = retrofit.create(GetInet.class);
        service.randomUsers(6).enqueue(new Callback<ModelRecycler>() {
            @Override
            public void onResponse(Call<ModelRecycler> call, Response<ModelRecycler> response) {
                if (response != null && response.isSuccess()) {
                    ModelRecycler modelForRecycler = response.body();
                    List<Result> result = modelForRecycler.getResults();
                    Datas.list.addAll(response.body().getResults());
                    userAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(UserActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelRecycler> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
