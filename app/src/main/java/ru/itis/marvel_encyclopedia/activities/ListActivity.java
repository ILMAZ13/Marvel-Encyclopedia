package ru.itis.marvel_encyclopedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;
import ru.itis.marvel_encyclopedia.adapters.RecyclerCharactersAdapter;
import ru.itis.marvel_encyclopedia.fragments.LoaderCharactersFragment;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

public class ListActivity extends AppCompatActivity implements TaskInterface {
    private RecyclerView rv;
    private ProgressBar progressBar;
    private List<Result> characters;
    private RecyclerCharactersAdapter adapter;
    private EditText etSearch;
    private boolean f = false;

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

        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    getAsyncFragment().stopAsync();
                    f = true;
                    getAsyncFragment().startAsync(0, charSequence.toString());
                } else {
                    getAsyncFragment().stopAsync();
                    f = false;
                    adapter.setData(characters);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    @Override
    public void OnTask2Finish(List<Result> characters) {
        if (f) {
            adapter.setData(characters);
        }
    }

    private LoaderCharactersFragment getAsyncFragment() {
        LoaderCharactersFragment fragment = (LoaderCharactersFragment) getSupportFragmentManager().findFragmentByTag(LoaderCharactersFragment.class.getName());
        if (fragment == null) {
            fragment = new LoaderCharactersFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, LoaderCharactersFragment.class.getName()).commit();
        }
        return fragment;
    }
}
