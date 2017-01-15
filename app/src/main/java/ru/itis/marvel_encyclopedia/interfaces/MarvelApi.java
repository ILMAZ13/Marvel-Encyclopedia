package ru.itis.marvel_encyclopedia.interfaces;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.itis.marvel_encyclopedia.POJO.Data;

/**
 * Created by Anatoly on 14.01.2017.
 */

public interface MarvelApi {
    String TS="1";
    String API_KEY ="dc882b9b1456762ea3aec434fd1278d2";
    String HASH ="e7f13fdc683a241f1acd00c9c8ce4be9";

    @GET("/v1/public/characters")
    Call<Data> getCharacters (@Query("ts") String ts, @Query("apikey") String key, @Query("hash") String hash);
}
