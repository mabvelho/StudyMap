package com.example.studymap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.studymap.data.UserRole;
import com.example.studymap.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.security.AccessController.getContext;

public class DisciplineList2Activity extends AppCompatActivity {

    //private UserRole userRole = UserRole.PROFESSOR;
    private String login;
    ArrayAdapter<String> adapter;
    private String m_Text = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            Intent myIntent = new Intent(DisciplineList2Activity.this, LoginActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            DisciplineList2Activity.this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_list2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = getIntent().getStringExtra("login");

       /* SharedPreferences sharedPref = DisciplineList2Activity.this.getPreferences(Context.MODE_PRIVATE);

        if (!sharedPref.contains("disciplines")) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("disciplines", "Algoritmos#Redes");
            editor.commit();
        }*/

        final ListView lv = findViewById(R.id.dList);

        ArrayList<String> listItems=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);

        /*String[] disciplines = sharedPref.getString("disciplines", "Algoritmos#Redes").split(Pattern.quote("#"));
        for (String d : disciplines) {
            adapter.add(d);
        }*/
        adapter.add("Algoritmos");
        adapter.add("Redes");

        lv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgotDialog(DisciplineList2Activity.this);
            }
        });

        UserRole userRole = UserRole.parseUserLogin(login);
        if (userRole != UserRole.PROFESSOR) {
            fab.setVisibility(View.INVISIBLE);
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String item = (String) (lv.getItemAtPosition(position));
                //Toast.makeText(DisciplineList2Activity.this, item, Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(DisciplineList2Activity.this, ScrollingActivity.class);
                myIntent.putExtra("name", item);
                myIntent.putExtra("login", login);
                DisciplineList2Activity.this.startActivity(myIntent);
            }
        });
    }


    private void showForgotDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Criar Disciplina")
                .setMessage("Nome:")
                .setView(taskEditText)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        adapter.add(task);
                        /*SharedPreferences sharedPref = DisciplineList2Activity.this.getPreferences(Context.MODE_PRIVATE);
                        String list = sharedPref.getString("disciplines", "");
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("disciplines", list + "#" + task);
                        editor.commit();*/
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.show();
    }
}