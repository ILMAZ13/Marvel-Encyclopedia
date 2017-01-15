package ru.itis.marvel_encyclopedia.asyncTasks;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.itis.marvel_encyclopedia.POJO.Data;
import ru.itis.marvel_encyclopedia.POJO.Marvel;
import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.interfaces.MarvelApi;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class AsyncTaskGetCharacters extends AsyncTask<Void,Void,List<Result>>{
    private TaskInterface mTaskInterface;

    public AsyncTaskGetCharacters(TaskInterface taskInterface) {
        this.mTaskInterface = taskInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTaskInterface.OnTaskStart();

    }

    @Override
    protected List<Result> doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MarvelApi marvelApi = retrofit.create(MarvelApi.class);

        Call<Marvel> charactersCall = marvelApi.getCharacters(MarvelApi.TS, MarvelApi.API_KEY, MarvelApi.HASH, MarvelApi.LIMIT);
        List<Result> characters=null;
        try {

            Response<Marvel> response = charactersCall.execute();

            Marvel marvel = response.body();
            characters = marvel.getData().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    protected void onPostExecute(List<Result> results) {
        super.onPostExecute(results);
        mTaskInterface.OnTaskFinish(results);
    }
}
