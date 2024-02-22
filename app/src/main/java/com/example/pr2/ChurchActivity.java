package com.example.pr2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;


import java.util.Objects;

public class ChurchActivity extends AppCompatActivity {
    private int fav;
    private MapView mapView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church);


        MapKitFactory.initialize(this);
        mapView = findViewById(R.id.mapViewYa);

        double latitude = getIntent().getDoubleExtra("first", 0);
        double longitude = getIntent().getDoubleExtra("second", 0);
        Point point = new Point(latitude, longitude);
        ImageProvider imageProvider = ImageProvider.fromResource(this, R.drawable.marker);

        mapView.getMap().move(
                new CameraPosition(point, 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark(point, imageProvider);
        placemark.setUserData(getIntent().getStringExtra("fieldTitle"));
        placemark.addTapListener(mapObjectTapListener);


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

    private final MapObjectTapListener mapObjectTapListener = (mapObject, point) -> {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                Objects.requireNonNull(mapObject.getUserData()).toString(),
                Toast.LENGTH_SHORT);
        toast.show();
        return true;
    };

    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

}

