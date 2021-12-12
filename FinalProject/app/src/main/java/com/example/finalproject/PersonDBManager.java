package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDBManager extends SQLiteOpenHelper {

    static final String PERSON_DB = "Persons.db";
    static final String PERSON_TABLE = "Persons";
    Context context = null;

    private static PersonDBManager dbManager = null;

    static final String CREATE_TABLE = " CREATE TABLE " + PERSON_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " id TEXT NOT NULL, password TEXT NOT NULL,name TEXT NOT NULL);";

    public static PersonDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new PersonDBManager(context, PERSON_DB, null, 1);
        }
        return dbManager;
    }

    public PersonDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(ContentValues contentValues) {
        return getWritableDatabase().insert(PERSON_TABLE, null, contentValues);
    }

    public Cursor query(String[] colums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(PERSON_TABLE, colums, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(PERSON_TABLE, whereClause, whereArgs);
    }
}
