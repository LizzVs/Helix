package com.hotel.ui.catalog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.hotel.MainInterface;
import com.hotel.Patients;
import com.hotel.R;
import com.hotel.Research;
import com.hotel.ResearchAdapter;
import com.hotel.StatusInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatalogFragment extends Fragment {

    private CatalogViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(CatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_catalog, container, false);
        ListView listView = root.findViewById(R.id.list_researches);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.183.1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

      //  List<Research> status = new ArrayList<>();
        ArrayList<String> status = new ArrayList<String>();
        StatusInterface statusInterface = retrofit.create(StatusInterface.class);
        Call<List<Research>> call = statusInterface.getPosts(1);
        call.enqueue(new Callback<List<Research>>() {
            @Override
            public void onResponse(Call<List<Research>> call, Response<List<Research>> response) {
                List<Research> posts = response.body();
                int i=0;
                for (Research post: posts)
                {
                   // status.add(new Research(post.getId_research(),post.getStatus()));
                    status.add(post.getStatus());
                    i++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(),
                        android.R.layout.simple_list_item_1, status);
                listView.setAdapter(adapter);

             //   ResearchAdapter researchAdapter = new ResearchAdapter(root.getContext(), R.layout.list_item, status);
              //  ArrayAdapter<Research> researchAdapter = new ArrayAdapter<>(root.getContext(), R.layout.list_item, posts);
              //  listView.setAdapter(researchAdapter);
            }

            @Override
            public void onFailure(Call<List<Research>> call, Throwable t) {

            }
        });
        return root;
    }
}