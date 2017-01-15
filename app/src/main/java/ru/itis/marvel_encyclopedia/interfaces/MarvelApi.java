package ru.itis.marvel_encyclopedia.interfaces;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.itis.marvel_encyclopedia.POJO.Data;

/**
 * Created by Anatoly on 14.01.2017.
 */

public interface MarvelApi {
    String TS="1";
    String API_KEY ="dc882b9b1456762ea3aec434fd1278d2";
    String HASH ="e7f13fdc683a241f1acd00c9c8ce4be9";
    String LIMIT="20";

    @Headers({"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Accept-Encoding:UTF-8",
    "Accept-Language:ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4" ,
            "Cache-Control:max-age=0" ,
            "Connection:keep-alive",
    "Host:gateway.marvel.com" ,
            "If-None-Match:2f3278dd8b076342f2141911ae54a231be9d1838" ,
            "Upgrade-Insecure-Requests:1" ,
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"})
    @GET("v1/public/characters")
    Call<Data> getCharacters (@Query("ts") String ts, @Query("apikey") String key, @Query("hash") String hash,@Query("limit") String limit);
}
