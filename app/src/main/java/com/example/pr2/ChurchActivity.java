package com.example.pr2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChurchActivity extends AppCompatActivity {
    private int fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church);
        this.fav = getIntent().getIntExtra("fav", 0);
        System.out.println(this.fav);
        ImageView img = findViewById(R.id.churchAImg);
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img.setImageBitmap(bitmap);
        TextView name = findViewById(R.id.churchAName);
        name.setText(getIntent().getStringExtra("name"));
        TextView address = findViewById(R.id.churchAAd);
        address.setText(getIntent().getStringExtra("address"));
        TextView hours = findViewById(R.id.churchAHours);
        hours.setText(getIntent().getStringExtra("hours"));

        ImageButton buttonHome = findViewById(R.id.mainhome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChurchActivity.this, MainActivity.class);
                ChurchActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonFav = findViewById(R.id.mainfav);
        buttonFav.setOnClickListener(v -> {
            Intent intent = new Intent(ChurchActivity.this, FavActivity.class);
            ChurchActivity.this.startActivity(intent);
        });

        ImageView favButton = findViewById(R.id.favButton);
        favButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){

                if (fav == 0) {
                    CharSequence text = "Добавлено в избранное!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
                    databaseManager.open();
                    databaseManager.setFav(getIntent().getIntExtra("id", 0), 1);
                    fav = 1;
                    databaseManager.close();
                } else if (fav == 1) {
                    CharSequence text = "Удалено из избранного!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
                    databaseManager.open();
                    databaseManager.setFav(getIntent().getIntExtra("id", 0), 0);
                    fav = 0;
                    databaseManager.close();
                }
            }

        });
    }
}

