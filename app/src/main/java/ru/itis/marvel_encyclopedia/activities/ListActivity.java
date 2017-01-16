package ru.itis.marvel_encyclopedia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;
import ru.itis.marvel_encyclopedia.adapters.RecyclerCharactersAdapter;
import ru.itis.marvel_encyclopedia.fragments.LoaderCharactersFragment;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

public class ListActivity extends AppCompatActivity implements TaskInterface{
    private RecyclerView rv;
    private ProgressBar progressBar;
    private List<Result> characters;
    private RecyclerCharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        characters = (List<Result>) intent.getSerializableExtra("characters");
        rv = (RecyclerView) findViewById(R.id.recycler_view_characters);
        adapter = new RecyclerCharactersAdapter(ListActivity.this, characters, this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

//        if(getAsyncFragment().isRunning()){
//            progressBar.setVisibility(View.VISIBLE);
//        }
//        else progressBar.setVisibility(View.GONE);
    }



    @Override
    public void OnTaskFinish(List<Result> characters) {
        adapter.updateInformation(characters);
    }

    @Override
    public void OnTaskStart() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void OnTaskProgress() {

    }
}
