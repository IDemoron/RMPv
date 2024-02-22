package com.example.pr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_churches);

        ImageButton buttonHome = findViewById(R.id.mainhome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                ChooseActivity.this.startActivity(intent);
            }
        });

        ImageButton buttonFav = findViewById(R.id.mainfav);
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, FavActivity.class);
                ChooseActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView churchesScreen = findViewById(R.id.churchesRecycler);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();

        List<Church> churches = databaseManager.getChurches();

        String religion = getIntent().getStringExtra("churchType");

        churches.removeIf(church -> !Objects.equals(church.getReligion(), religion));

        ChurchAdapter churchAdapter = new ChurchAdapter(this, churches);
        churchesScreen.setAdapter(churchAdapter);

        databaseManager.close();

    }

}

