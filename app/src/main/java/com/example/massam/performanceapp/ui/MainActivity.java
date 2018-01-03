package com.example.massam.performanceapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.massam.performanceapp.R;
import com.example.massam.performanceapp.adapter.DataAdapter;
import com.example.massam.performanceapp.api.ApiClient;
import com.example.massam.performanceapp.api.TypiCodeServices;
import com.example.massam.performanceapp.model.Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> listData;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        loadJSON();
    }

    public void initView (){
        recyclerView = (RecyclerView)findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter cardViewPresidentAdapter = new DataAdapter(this);
        cardViewPresidentAdapter.setListData(listData);
        recyclerView.setAdapter(cardViewPresidentAdapter);
    }

    public void loadJSON() {

        TypiCodeServices typiCodeServices = ApiClient.getClient().create(TypiCodeServices.class);

        Call<ArrayList<Model>> call = typiCodeServices.data();

        call.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(Call<ArrayList<Model>> call, Response<ArrayList<Model>> response) {
                List<Model> result = response.body();
                listData = new ArrayList<>();

                listData.addAll(result);

                progressBar.setVisibility(View.GONE);

                initView();

            }

            @Override
            public void onFailure(Call<ArrayList<Model>> call, Throwable t) {
                Log.d("title", "onFailure: " + t.getMessage());
            }
        });

    }
}
