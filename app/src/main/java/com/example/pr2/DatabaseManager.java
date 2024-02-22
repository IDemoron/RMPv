package com.example.pr2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final DatabaseCreator creator;
    private SQLiteDatabase sqlite;

    public DatabaseManager(Context context){
        creator = new DatabaseCreator(context.getApplicationContext());
    }

    public DatabaseManager open(){
        sqlite = creator.getWritableDatabase();
        return this;
    }

    public void close(){
        creator.close();
    }

    public List<Church> getChurches() {
        List<Church> churches = new ArrayList<>();
        String[] columns = new String[] {DatabaseCreator.TABLE1_COLUMN1, DatabaseCreator.TABLE1_COLUMN2,
                DatabaseCreator.TABLE1_COLUMN3, DatabaseCreator.TABLE1_COLUMN4, DatabaseCreator.TABLE1_COLUMN5,
                DatabaseCreator.TABLE1_COLUMN6, DatabaseCreator.TABLE1_COLUMN7};
        Cursor cursor = sqlite.query(DatabaseCreator.TABLE1, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()){
            churches.add(new Church(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
        }
        cursor.close();
        return churches;
    }

    public long setFav(int id, int flag){

        String element = DatabaseCreator.TABLE1_COLUMN1 + "=" + id;
        ContentValues editedElement = new ContentValues();
        editedElement.put(DatabaseCreator.TABLE1_COLUMN7, flag);
        return sqlite.update(DatabaseCreator.TABLE1, editedElement, element, null);
    }

}
