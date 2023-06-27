package com.evelyn.chichande.apirest.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evelyn.chichande.apirest.IO.Actor;
import com.evelyn.chichande.apirest.IO.ActorService;
import com.evelyn.chichande.apirest.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter arrayAdapter;
    ArrayList<String> title = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.lstViewAlbums);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,title);
        list.setAdapter(arrayAdapter);
        getActor();
    }

    private void getActor(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.catalogopolis.xyz")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActorService actorService= retrofit.create(ActorService.class);
        Call<List<Actor>> call= actorService.getList();
        call.enqueue(new Callback<List<Actor>>()
        {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                for (Actor actor: response.body()){
                    title.add(actor.getName());
                }
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {

            }
        });
    }

}