package com.example.pr2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private TextView weatherState;
    private TextView weatherDeg;
    private ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myText.setText(bitmap.toString());

        String key = "eb614831c6297e71be660505f3add79a";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Krasnoyarsk&appid="
                + key + "&units=metric&lang=ru";

        weatherState = findViewById(R.id.weatherState);
        weatherDeg = findViewById(R.id.weatherDeg);
        weatherIcon = findViewById(R.id.weatherImg);

        new GetURLData().execute(url);

        // Переход на другой активити
        ImageButton buttonOrthodox = findViewById(R.id.orthodox);
        buttonOrthodox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                intent.putExtra("churchType", "orthodox");
                MainActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonCatholic = findViewById(R.id.catholic);
        buttonCatholic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                intent.putExtra("churchType", "catholic");
                MainActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonIslam = findViewById(R.id.islam);
        buttonIslam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                intent.putExtra("churchType", "islam");
                MainActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonJews = findViewById(R.id.jews);
        buttonJews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                intent.putExtra("churchType", "jews");
                MainActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonFav = findViewById(R.id.mainfav);
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
    private class GetURLData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                return  buffer.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @SuppressLint("SetTextI18n")
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                int temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp"));
                String description = jsonObject.getJSONArray("weather").getJSONObject(0)
                        .getString("description");
                String icon = jsonObject.getJSONArray("weather").getJSONObject(0)
                        .getString("icon");
                weatherDeg.setText(temp + "°");
                weatherState.setText(description.substring(0, 1).toUpperCase() + description.substring(1));
                Picasso.with(MainActivity.this).load("https://openweathermap.org/img/wn/"
                        + icon + ".png").into(weatherIcon);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}