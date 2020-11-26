package com.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button Autorization = findViewById(R.id.buttonAutorization);
        Autorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToNavig = new Intent(MainActivity.this,NavigationActivity.class);
                startActivity(GoToNavig);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/lab/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call<List<Patients>> call = mainInterface.getPosts("");
        call.enqueue(new Callback<List<Patients>>() {
            @Override
            public void onResponse(Call<List<Patients>> call, Response<List<Patients>> response) {
                List<Patients> posts = response.body();
                for (Patients post: posts)
                {
                    String content = "";
                    content += post.getId_patient();
                    content += post.getLastname();
                    content += post.getFirstname();
                }
                
            }

            @Override
            public void onFailure(Call<List<Patients>> call, Throwable t) {

            }
        });
    }
}