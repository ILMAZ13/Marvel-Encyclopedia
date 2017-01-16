package ru.itis.marvel_encyclopedia.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;
import ru.itis.marvel_encyclopedia.adapters.RecyclerCharactersAdapter;
import ru.itis.marvel_encyclopedia.providers.CharacterProvider;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        rv = (RecyclerView) findViewById(R.id.fav_list);

        List<Result> characters = CharacterProvider.getInstance(this).getCharacters();
        RecyclerCharactersAdapter adapter = new RecyclerCharactersAdapter(this, characters ,FavoriteActivity.this);
        rv.setAdapter(adapter);
    }
}
