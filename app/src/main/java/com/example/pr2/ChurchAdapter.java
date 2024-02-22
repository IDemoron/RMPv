package com.example.pr2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChurchAdapter extends RecyclerView.Adapter<ChurchAdapter.ChurchViewHolder> {

    private final Context context;
    private final List<Church> churches;

    public ChurchAdapter(Context context, List<Church> churches) {
        this.churches = churches;
        this.context = context;
    }

    @NonNull
    @Override
    public ChurchAdapter.ChurchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.church, parent, false);
        return new ChurchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChurchViewHolder holder, int position) {
        Church church = churches.get(position);
        Bitmap bitmap;

        AssetManager assetManager = context.getAssets();
        InputStream stream = null;
        try {
            stream = assetManager.open(church.getImg());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bitmap = BitmapFactory.decodeStream(stream);
        holder.churchImg.setImageBitmap(bitmap);

        holder.churchName.setText(church.getName());
        holder.churchAd.setText(church.getAddress());
        holder.constraintLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChurchActivity.class);

            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
            byte[] byteArray = stream1.toByteArray();

            intent.putExtra("img", byteArray);
            intent.putExtra("name", church.getName());
            intent.putExtra("address", church.getAddress());
            intent.putExtra("hours", church.getHours());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return churches.size();
    }

    public static final class ChurchViewHolder extends RecyclerView.ViewHolder {

        ImageView churchImg;
        TextView churchName;
        TextView churchAd;
        ConstraintLayout constraintLayout;

        public ChurchViewHolder(@NonNull View itemView) {
            super(itemView);

            churchImg = itemView.findViewById(R.id.churchImg);
            churchName = itemView.findViewById(R.id.churchName);
            churchAd = itemView.findViewById(R.id.churchAd);
            constraintLayout = itemView.findViewById(R.id.church);
        }
    }
}
