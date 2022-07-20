package com.droidbane.wordmemory.database;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class WordsDAO {
    public String regExForSQL(String inputText) {
        return inputText.replaceAll(" ", "%20&");
    }

    public String regExParserForSQL(String inputText) {
        return inputText.replaceAll("%20&", " ");
    }

    public void addWord(DatabaseHelper db, String word1, String word2) {
        SQLiteDatabase dbx = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("word1", word1);
        contentValues.put("word2", word2);

        dbx.insertOrThrow("words", null, contentValues);
        dbx.close();
    }

    public ArrayList<Words> allWords(DatabaseHelper db) {
        ArrayList<Words> wordsArrayList = new ArrayList<>();
        SQLiteDatabase dbx = db.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM words", null);
        while (c.moveToNext()) {
            @SuppressLint("Range") Words words = new Words(c.getInt(c.getColumnIndex("id"))
                    , c.getString(c.getColumnIndex("word1"))
                    , c.getString(c.getColumnIndex("word2")));
            wordsArrayList.add(words);
        }
        return wordsArrayList;
    }

    @SuppressLint("Range")
    public int dataCount(DatabaseHelper databaseHelper) {
        int data = 0;

        SQLiteDatabase raw = databaseHelper.getWritableDatabase();

        Cursor cursor = raw.rawQuery("SELECT count(*) as data FROM words", null);
        while (cursor.moveToNext()) {
            data = cursor.getInt(cursor.getColumnIndex("data"));
        }
        return data;
    }

    public Words getWord(DatabaseHelper databaseHelper, int id) {
        Words words = new Words();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM words WHERE id=" + id, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Words word = new Words(cursor.getInt(cursor.getColumnIndex("id"))
                    , cursor.getString(cursor.getColumnIndex("word1"))
                    , cursor.getString(cursor.getColumnIndex("word2")));

            words = word;
        }
        return words;
    }

    public Words getNameWord(DatabaseHelper databaseHelper, String word1) {
        Words words = new Words();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM words WHERE word1 like '%" + word1 + "%'", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Words word = new Words(cursor.getInt(cursor.getColumnIndex("id"))
                    , cursor.getString(cursor.getColumnIndex("word1"))
                    , cursor.getString(cursor.getColumnIndex("word2")));

            words = word;
        }
        return words;
    }

    public void deleteWord(DatabaseHelper databaseHelper, int id) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        database.delete("words", "id=?", new String[]{String.valueOf(id)});
        database.close();
    }

    public void updataWord(DatabaseHelper databaseHelper, int id, String word1, String word2) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("word1", word1);
        contentValues.put("word2", word2);

        database.update("words", contentValues, "id=?", new String[]{String.valueOf(id)});
        database.close();
    }
}
