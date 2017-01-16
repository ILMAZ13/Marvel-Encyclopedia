package ru.itis.marvel_encyclopedia.interfaces;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.itis.marvel_encyclopedia.POJO.Data;
import ru.itis.marvel_encyclopedia.POJO.Marvel;

/**
 * Created by Anatoly on 14.01.2017.
 */

public interface MarvelApi {
    String TS="100";
    String API_KEY ="dc882b9b1456762ea3aec434fd1278d2";
    String HASH ="ec6b198f90393d8f65cdaf8f4aa61043";
    String LIMIT="30";

    @GET("v1/public/characters")
    Call<Marvel> getCharacters (@Query("ts") String ts, @Query("apikey") String key, @Query("hash") String hash,
                                @Query("limit") String limit, @Query("offset")String offset);

    @GET("v1/public/characters")
    Call<Marvel> getCharactersWithStartSymbols (@Query("ts") String ts, @Query("apikey") String key, @Query("hash") String hash,
                                @Query("limit") String limit, @Query("offset")String offset, @Query("nameStartsWith") String startSymbols);
}
