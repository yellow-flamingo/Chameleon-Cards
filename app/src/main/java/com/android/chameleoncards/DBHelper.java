package com.android.chameleoncards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Auth.db";

    public DBHelper(Context context) {
        super(context, "Auth.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase AuthDB) {
        AuthDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        AuthDB.execSQL("create Table cards(Id INTEGER primary key autoincrement, username TEXT, image BLOB)");
    }

    public Boolean queryData(String sql) {
        SQLiteDatabase AuthDB = this.getWritableDatabase();
        try {
            Cursor cursor = AuthDB.rawQuery(sql, null);
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase AuthDB, int i, int i1) {
        AuthDB.execSQL("drop Table if exists users");
        AuthDB.execSQL("drop Table is exists cards");
    }


    public Boolean checkUsername(String username) {
        SQLiteDatabase AuthDB = this.getWritableDatabase();
        Cursor cursor = AuthDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase AuthDB = this.getWritableDatabase();
        Cursor cursor = AuthDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void insertCard(String username, byte[] image) {
        SQLiteDatabase AuthDB = this.getWritableDatabase();
        String sql = "INSERT INTO cards VALUES (NULL, ?, ?)";

        SQLiteStatement statement = AuthDB.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, username);
        statement.bindBlob(2, image);

        statement.executeInsert();

    }

    public Cursor getImageData(String sql, String[] selectionArgs) {
        SQLiteDatabase AuthDB = getReadableDatabase();
        return AuthDB.rawQuery(sql, selectionArgs);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase AuthDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = AuthDB.insert("users", null, contentValues);
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }
}
