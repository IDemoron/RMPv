package com.example.pr2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseCreator extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "churches.db";
    private static final int SCHEMA = 1;
    public static final String TABLE1 = "church";
    public static final String TABLE1_COLUMN1 = "_id";
    public static final String TABLE1_COLUMN2 = "religion";
    public static final String TABLE1_COLUMN3 = "name";
    public static final String TABLE1_COLUMN4 = "address";
    public static final String TABLE1_COLUMN5 = "hours";
    public static final String TABLE1_COLUMN6 = "img";
    public static final String TABLE1_COLUMN7 = "fav";

    public DatabaseCreator(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE1 + " (" + TABLE1_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE1_COLUMN2 + " TEXT, " + TABLE1_COLUMN3 + " TEXT, " + TABLE1_COLUMN4 + " TEXT, "
                + TABLE1_COLUMN5 + " TEXT, " + TABLE1_COLUMN6 + " TEXT, " + TABLE1_COLUMN7 + " INTEGER);");
        initData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        onCreate(db);
    }

    private void initData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('orthodox', 'Церковь Иоанна Предтечи', 'ул. Горького 27', '7:00 - 19:00'," +
                " 'ioannpredtech.png', 0);");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('orthodox', 'Храм Рождества Христова', 'ул. Щорса 44А', '7:00 - 19:00'," +
                " 'rozhd.png', 0);");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('catholic', 'Церковь Преображения Господня', 'ул. Декабристов 20', '11:00 - 22:00'," +
                " 'preobraz.png', 0);");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('catholic', 'Церковь прихода святого Семейства', 'ул. Солнечный бул., 5/4', '10:00 - 21:00'," +
                " 'semya.png', 0);");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('islam', 'Соборная мечеть', 'ул. Металлургов 65 ', '6:00 - 22:00'," +
                " 'mechet.png', 0);");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2 + ", " + TABLE1_COLUMN3 + ", "
                + TABLE1_COLUMN4 + ", " + TABLE1_COLUMN5 + ", " + TABLE1_COLUMN6 + ", "
                + TABLE1_COLUMN7 + ") " +
                "VALUES ('jews', 'Синагога', 'ул. Сурикова 67', '9:00 - 17:00'," +
                " 'sinagoga.png', 0);");
    }
}
