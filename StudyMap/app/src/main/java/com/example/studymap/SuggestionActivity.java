package com.example.studymap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.add_suggestion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddSuggestionDialog(SuggestionActivity.this);
            }
        });

        ArrayList<String> listItems=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,
                R.layout.suggestion_item,
                listItems);

        final ListView lv = findViewById(R.id.suList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String item = (String) (lv.getItemAtPosition(position));
                //Toast.makeText(DisciplineList2Activity.this, item, Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(SuggestionActivity.this, SuggestionItemActivity.class);
                myIntent.putExtra("item", item);
                SuggestionActivity.this.startActivity(myIntent);
            }
        });
    }

    private void showAddSuggestionDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Adicionar Sugestão")
                .setMessage("Texto:")
                .setView(taskEditText)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        adapter.add(task);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.show();
    }
}