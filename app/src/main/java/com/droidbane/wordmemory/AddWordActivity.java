package com.droidbane.wordmemory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.droidbane.wordmemory.database.DatabaseHelper;
import com.droidbane.wordmemory.database.Words;
import com.droidbane.wordmemory.database.WordsDAO;

import java.util.ArrayList;

public class AddWordActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter1 adapter1;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        databaseHelper = new DatabaseHelper(this);
        ArrayList<Words> wordsArrayList = new WordsDAO().allWords(databaseHelper);

        recyclerView = findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter1 = new Adapter1(this, new WordsDAO().dataCount(databaseHelper), wordsArrayList);

        recyclerView.setAdapter(adapter1);
    }

    public void addWordButton(View view) {
        Intent intent = new Intent(this, AddWord2Activity.class);
        startActivity(intent);
    }
}