package com.example.hexa;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("598210903552388/conversations/?access_token=EAAFwjZBQdZB94BABZAPelauuEVHZA2fWozLLzKEdBNGykzA31yG7lRZBWhsZCrnMKZB0On59Xy2zii6rh8P5lhFPpeAWkboupHnEQkKKELBMcpkRZCXsZArHU72IzZCvdhX7AZBSrTuwXsPILr7P0gSEFZCbDcJc6iKrgmH3PCQyrYBou1RD2monicZBVX7ftgZBXPKnWAc3DbnmocZCgZDZD")
    Call<JsonObject> getMessageList();

    @GET("/go/{id}")
    Call<JsonObject> getMessages(@Path(value = "id", encoded = false) String id, @Query("fields") String fields, @Query("access_token") String access_token);

    /*
    @GET("cloud.ibm.com/v3/tone_chat")
    Call<JsonObject> getMessageList();
     */
}