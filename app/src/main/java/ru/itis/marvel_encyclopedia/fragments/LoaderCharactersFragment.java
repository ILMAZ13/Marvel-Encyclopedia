package ru.itis.marvel_encyclopedia.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.itis.marvel_encyclopedia.POJO.Marvel;
import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.interfaces.MarvelApi;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class LoaderCharactersFragment extends Fragment {
    private TaskInterface mTaskInterface;
    private AsyncTaskGetCharacters myAsync;

    public boolean isRunning() {return myAsync!=null;}

    @Override
    public void onAttach(Context context) {
        setInterface(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        setInterface(activity);
        super.onAttach(activity);
    }

    private void setInterface(Context context){
        if(context instanceof TaskInterface)
            mTaskInterface = (TaskInterface) context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
//        myAsync = new AsyncTaskGetCharacters(mTaskInterface);
//        myAsync.execute();
    }

    public void startAsync(int pos, String startSymbols){
        if(myAsync==null){
            myAsync = new AsyncTaskGetCharacters(mTaskInterface);

            myAsync.execute(String.valueOf(pos), startSymbols);
        }
    }
    public void stopAsync(){
        if(myAsync!=null){
            myAsync.cancel(true);
            myAsync=null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myAsync=null;
    }

    private class AsyncTaskGetCharacters extends AsyncTask<String,Void,List<Result>> {
        private TaskInterface mTaskInterface;

        public AsyncTaskGetCharacters(TaskInterface taskInterface) {
            this.mTaskInterface = taskInterface;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(mTaskInterface != null) {
                mTaskInterface.OnTaskStart();
            }
        }

        @Override
        protected List<Result> doInBackground(String... strings) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gateway.marvel.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Call<Marvel> charactersCall = null;
            MarvelApi marvelApi = retrofit.create(MarvelApi.class);
            if(strings[1]==null) {
                charactersCall = marvelApi.getCharacters(MarvelApi.TS, MarvelApi.API_KEY, MarvelApi.HASH, MarvelApi.LIMIT, strings[0]);
            }
            else charactersCall = marvelApi.getCharactersWithStartSymbols(MarvelApi.TS, MarvelApi.API_KEY, MarvelApi.HASH, MarvelApi.LIMIT, strings[0],strings[1]);

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
            if(mTaskInterface != null) {
                mTaskInterface.OnTaskFinish(results);
            }
            myAsync = null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            myAsync = null;
        }
    }
}
