package com.example.pr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myText.setText(bitmap.toString());

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
}