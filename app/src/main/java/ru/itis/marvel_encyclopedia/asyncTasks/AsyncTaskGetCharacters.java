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


