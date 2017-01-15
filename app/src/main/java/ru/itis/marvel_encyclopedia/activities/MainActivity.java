package ru.itis.marvel_encyclopedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;
import ru.itis.marvel_encyclopedia.adapters.RecyclerCharactersAdapter;
import ru.itis.marvel_encyclopedia.fragments.LoaderCharactersFragment;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

/**
 * Created by UseR7 on 14.01.2017.
 */

public class MainActivity extends AppCompatActivity implements TaskInterface{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        if (getAsyncFragment().isRunning()) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // else
        getAsyncFragment().startAsync();
    }

    private LoaderCharactersFragment getAsyncFragment(){
        LoaderCharactersFragment fragment = (LoaderCharactersFragment) getSupportFragmentManager().findFragmentByTag(LoaderCharactersFragment.class.getName());
        if(fragment==null){
            fragment = new LoaderCharactersFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, LoaderCharactersFragment.class.getName()).commit();
        }
        return fragment;
    }

    @Override
    public void OnTaskFinish(List<Result> characters) {
        Toast.makeText(this, "Finish!!", Toast.LENGTH_LONG).show();
        getAsyncFragment().stopAsync();
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("characters", (Serializable) characters);
        startActivity(intent);
    }

    @Override
    public void OnTaskStart() {
        Toast.makeText(this, "Start!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnTaskProgress() {

    }
}
