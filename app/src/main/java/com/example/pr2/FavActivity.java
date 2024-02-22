package com.example.pr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        ImageButton buttonHome = findViewById(R.id.mainhome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavActivity.this, MainActivity.class);
                FavActivity.this.startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView churchesScreen = findViewById(R.id.churchesRecycler2);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();

        List<Church> churches = databaseManager.getChurches();

        churches.removeIf(church -> !Objects.equals(church.getFav(), 1));

        ChurchAdapter churchAdapter = new ChurchAdapter(this, churches);
        churchesScreen.setAdapter(churchAdapter);

        databaseManager.close();

    }
}

