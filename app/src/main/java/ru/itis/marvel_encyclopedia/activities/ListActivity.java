package ru.itis.marvel_encyclopedia.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rv = (RecyclerView) findViewById(R.id.recycler_view_characters);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        if(getAsyncFragment().isRunning()){
            progressBar.setVisibility(View.VISIBLE);
        }
        else progressBar.setVisibility(View.GONE);
    }

    private LoaderCharactersFragment getAsyncFragment(){
        LoaderCharactersFragment fragment = (LoaderCharactersFragment) getSupportFragmentManager().findFragmentByTag(LoaderCharactersFragment.class.getName());
        if(fragment==null){
            fragment = new LoaderCharactersFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, LoaderCharactersFragment.class.getName()).commit();
            fragment.startAsync();
        }
        return fragment;
    }

    @Override
    public void OnTaskFinish(List<Result> characters) {
        getAsyncFragment().stopAsync();
        progressBar.setVisibility(View.GONE);
        RecyclerCharactersAdapter adapter = new RecyclerCharactersAdapter(ListActivity.this, characters);
        rv.setAdapter(adapter);

    }

    @Override
    public void OnTaskStart() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void OnTaskProgress() {

    }
}
