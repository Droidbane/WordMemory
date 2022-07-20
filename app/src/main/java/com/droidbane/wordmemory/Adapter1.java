package com.droidbane.wordmemory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.droidbane.wordmemory.database.DatabaseHelper;
import com.droidbane.wordmemory.database.Words;
import com.droidbane.wordmemory.database.WordsDAO;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.CardViewNesneTutucu> {
    private Context context;
    int itemCount;
    private final List<Words> wordsList;

    DatabaseHelper databasehelper;

    public Adapter1(Context context, int itemCount, List<Words> wordsArrayList) {
        this.context = context;
        this.itemCount = itemCount;
        this.wordsList = wordsArrayList;
    }

    //        kart tasarimi
    public class CardViewNesneTutucu extends RecyclerView.ViewHolder {
        public EditText editTextAnaDil, editTextIkinciDil;
        public CardView card;
        public AppCompatButton button1, button2;


        public CardViewNesneTutucu(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            editTextAnaDil = itemView.findViewById(R.id.editTextAnaDil);
            editTextIkinciDil = itemView.findViewById(R.id.editTextIkinciDil);
            button1 = itemView.findViewById(R.id.button1);
            button2 = itemView.findViewById(R.id.button2);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        databasehelper = new DatabaseHelper(parent.getContext());
        return new CardViewNesneTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucu holder, int position) {
        Words words = wordsList.get(position);

        holder.editTextAnaDil.setText(String.valueOf(words.getWord1()));
        holder.editTextIkinciDil.setText(String.valueOf(words.getWord2()));

//        remove button
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("as", String.valueOf(words.getId()));
                new WordsDAO().deleteWord(databasehelper, words.getId());
                holder.card.setVisibility(View.GONE);
                Toast.makeText(context, "Removed: " + words.getWord1(), Toast.LENGTH_SHORT).show();
            }
        });

//        edit button
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!words.getWord1().equals(holder.editTextAnaDil.getText().toString())) {
                    new WordsDAO().updataWord(databasehelper, words.getId(), holder.editTextAnaDil.getText().toString(), holder.editTextIkinciDil.getText().toString());
                    Toast.makeText(context, "Updated: " + words.getWord1(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return wordsList.size();
    }


}
