package com.example.hexa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView facebookcard;
    private CardView immediate;
    private CardView moderate;
    private CardView low;
    private CardView spam;
    private RecyclerView message_recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<String> mess = new ArrayList<>();
    private ApiInterface api;
    private ArrayList<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = ApiClient.getClient().create(ApiInterface.class);
        facebookcard = findViewById(R.id.facebookcard);
        immediate = findViewById(R.id.immediate);
        moderate = findViewById(R.id.moderate);
        low = findViewById(R.id.low);
        spam = findViewById(R.id.spam);
        message_recycler = findViewById(R.id.messages);

        mess.add("Please fix this issues ASAP! My cards are not working!");
        adapter = new Adapter(MainActivity.this, mess);
        message_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        message_recycler.setAdapter(adapter);

        facebookcard.setOnClickListener(this);
        immediate.setOnClickListener(this);
        moderate.setOnClickListener(this);
        low.setOnClickListener(this);
        spam.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == facebookcard) {
            api.getMessageList().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    for (int i=0; i<response.body().getAsJsonArray("data").size(); i++) {
                        ids.add(response.body().getAsJsonArray("data").get(i).getAsJsonObject().get("id").toString());
                    }
                    api.getMessages(ids.get(0), "messages{message}", "EAAFwjZBQdZB94BABZAPelauuEVHZA2fWozLLzKEdBNGykzA31yG7lRZBWhsZCrnMKZB0On59Xy2zii6rh8P5lhFPpeAWkboupHnEQkKKELBMcpkRZCXsZArHU72IzZCvdhX7AZBSrTuwXsPILr7P0gSEFZCbDcJc6iKrgmH3PCQyrYBou1RD2monicZBVX7ftgZBXPKnWAc3DbnmocZCgZDZD").enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Log.wtf("hahaha", response.toString());
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Nahi CHAL GAYA", Toast.LENGTH_LONG).show();
                }
            });
            mess.add("I need immediate attention to this issue. I have tried contacting you everywhere.");
            adapter.notifyDataSetChanged();
        }
        else if (view == immediate) {

        }
        else if (view == moderate) {
            mess.clear();
            mess.add("I wanted to look into that refund that I request a week ago. Please get back");
            mess.add("Hey. When are the holiday coupons going to become active again?");
            mess.add("Could I please get a copy of the privacy policy?");
            adapter.notifyDataSetChanged();
        }
        else if (view == low) {
            mess.clear();
            mess.add("Gr8 service. Would luv to try again.");
            mess.add("Awsm guys. Would love a shout out.");
            mess.add("Hey? please ripli.");
            adapter.notifyDataSetChanged();
        }
        else if (view == spam) {
            mess.clear();
            mess.add("Abbe hatt");
            mess.add("Ghatiya service. Kucch kam mat karo tum log");
            mess.add("Please check our product out at producthunt.com/yolo-234112423");
            mess.add("Need professional web services at cheap cost? Reach out to us immediately.");
            mess.add("SALE! SALE! SALE! Get 85+10% off at latest merchandise.");
            adapter.notifyDataSetChanged();
        }
    }
}