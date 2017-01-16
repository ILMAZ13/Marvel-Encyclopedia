package ru.itis.marvel_encyclopedia.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;

/**
 * Created by Anatoly on 16.01.2017.
 */

public class CharacterProvider {
    public static final String CHARACTER_PREFERENCES = "CharactersList";
    public static final String PREFERENCES_NAME = "CharacterName";

    private static CharacterProvider sInstance;
    private Context context;

    public CharacterProvider(Context context) {
        this.context=context;
    }

    public static CharacterProvider getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new CharacterProvider(context.getApplicationContext());
        }
        return sInstance;
    }
    public List <Result> getCharacters(){
        SharedPreferences preferences = context.getSharedPreferences(CHARACTER_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(PREFERENCES_NAME)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(PREFERENCES_NAME, "");
            Type listType = new TypeToken<List<Result>>() {
            }.getType();
            List<Result> characters = gson.fromJson(jsonText, listType);
            return characters;
        } else {
            List<Result> characters = new ArrayList<>();
            saveCharacters(characters);
            return characters;
        }
    }
    public void saveCharacters(List<Result> cities) {
        SharedPreferences preferences = context.getSharedPreferences(CHARACTER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Result>>() {
        }.getType();
        String jsonText = gson.toJson(cities, listType);
        editor.putString(PREFERENCES_NAME, jsonText);
        editor.commit();
    }


    public void addCharacter(Result character) {
        List<Result> characters = getCharacters();
        characters.add(character);
        saveCharacters(characters);
    }

}
