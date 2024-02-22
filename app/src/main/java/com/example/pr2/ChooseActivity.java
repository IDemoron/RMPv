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

        List<Church> churches = new ArrayList<>();
        String churchType = getIntent().getStringExtra("churchType");
        if (Objects.equals(churchType, "orthodox")) {
            Church church = new Church(1, "Церковь Иоанна Предтечи", "ул. Горького 27", "7:00 - 19:00", "ioannpredtech.png");
            churches.add(church);
            church = new Church(2, "Храм Рождества Христова", "ул. Щорса 44А", "7:00 - 19:00", "rozhd.png");
            churches.add(church);
        } else if (Objects.equals(churchType, "catholic")) {
            Church church = new Church(1, "Церковь Преображения Господня", "ул. Декабристов 20", "11:00 - 22:00", "preobraz.png");
            churches.add(church);
            church = new Church(2, "Церковь прихода святого Семейства", "ул. Солнечный бул., 5/4", "10:00 - 21:00", "semya.png");
            churches.add(church);
        } else if (Objects.equals(churchType, "islam")) {
            Church church = new Church(1, "Соборная мечеть", "ул. Металлургов 65", "6:00 - 22:00", "mechet.png");
            churches.add(church);
        } else if (Objects.equals(churchType, "jews")) {
            Church church = new Church(1, "Синагога", "ул. Сурикова 67", "9:00 - 17:00", "sinagoga.png");
            churches.add(church);

        }


        ChurchAdapter churchAdapter = new ChurchAdapter(this, churches);
        churchesScreen.setAdapter(churchAdapter);

    }

}

