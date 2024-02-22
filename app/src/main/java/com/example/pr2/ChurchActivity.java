package com.example.pr2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChurchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church);

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
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChurchActivity.this, FavActivity.class);
                ChurchActivity.this.startActivity(intent);
            }
        });
    }
}

