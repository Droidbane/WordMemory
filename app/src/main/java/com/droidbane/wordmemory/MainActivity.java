package com.droidbane.wordmemory;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.droidbane.wordmemory.database.DatabaseHelper;
import com.droidbane.wordmemory.database.Words;
import com.droidbane.wordmemory.database.WordsDAO;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

//        new WordsDAO().addWord(databaseHelper, "birinci", "ikinci");
//        new WordsDAO().deleteWord(databaseHelper, 71);

        ArrayList<Words> wordsArrayList = new WordsDAO().allWords(databaseHelper);

        for (Words k : wordsArrayList) {
            Log.i(String.valueOf(k.getId()), "onCreate: " + k.getWord1() + " - " + k.getWord2());
        }

    }

    Words random;
    int var = 0;

    public void changeWordButton(View view) {
        TextView view1;

        view1 = findViewById(R.id.main_text);

        ArrayList<Words> wordsArrayList = new WordsDAO().allWords(databaseHelper);

//        final int max = new WordsDAO().dataCount(databaseHelper);
//        final int random = new Random().nextInt(max) + 1;

        if (var == 0) {
//            ilk defa basilinca
            random = wordsArrayList.get(new Random().nextInt(wordsArrayList.size()));

            var = 1;
            view1.setText(random.getWord1());
            view1.setTextColor(Color.BLACK);
            Log.e("1", "changeWordButton: " + random.getWord1());
        } else {
            Log.e("2", "changeWordButton: " + random.getWord2());
            view1.setText(random.getWord2());
            view1.setTextColor(Color.RED);
            var = 0;
        }
    }

    public void addWordButton(View view) {
        Intent intent = new Intent(this, AddWord2Activity.class);
        startActivity(intent);
    }

    public void updateWordButton(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
    }

}