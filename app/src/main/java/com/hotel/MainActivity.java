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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/lab/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Button Authorization = findViewById(R.id.buttonAutorization);
        Authorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToNavigation = new Intent(MainActivity.this,NavigationActivity.class);
                startActivity(GoToNavigation);
            }
        });
        Call<List<Patients>> call = mainInterface.getPosts("semenov_alex@mail.ru");
        call.enqueue(new Callback<List<Patients>>() {
            @Override
            public void onResponse(Call<List<Patients>> call, Response<List<Patients>> response) {
                List<Patients> posts = response.body();
                String content = "";
                for (Patients post: posts)
                {
                    content += post.getId_patient();
                }
                if (!content.equals(""))
                {

                }

            }

            @Override
            public void onFailure(Call<List<Patients>> call, Throwable t) {

            }
        });
    }
}