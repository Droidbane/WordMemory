package com.droidbane.wordmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.droidbane.wordmemory.database.DatabaseHelper;
import com.droidbane.wordmemory.database.Words;
import com.droidbane.wordmemory.database.WordsDAO;
import com.google.android.material.snackbar.Snackbar;

public class AddWord2Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextAnaDil, editTextIkinciDil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word2);

        databaseHelper = new DatabaseHelper(this);

        editTextAnaDil = findViewById(R.id.editTextAnaDil);
        editTextIkinciDil = findViewById(R.id.editTextIkinciDil);
    }

    public void addWordButton(View view) {
        new WordsDAO().addWord(databaseHelper, editTextAnaDil.getText().toString(), editTextIkinciDil.getText().toString());

        Snackbar.make(view, editTextAnaDil.getText().toString() + " adding.", Snackbar.LENGTH_LONG).setAction("UNDO", view1 -> {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // yourMethod();
                    Words find = new WordsDAO().getNameWord(databaseHelper, editTextAnaDil.getText().toString());
                    new WordsDAO().deleteWord(databaseHelper, find.getId());
                }
            }, 5000);   //5 seconds
        }).show();
    }
}