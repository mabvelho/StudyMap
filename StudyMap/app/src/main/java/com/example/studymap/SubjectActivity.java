package com.example.studymap;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("name"));
        setSupportActionBar(toolbar);

        FloatingActionButton fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgotDialog(SubjectActivity.this);
            }
        });

        final ListView lv = findViewById(R.id.listD);

        ArrayList<String> listItems=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String item = (String) (lv.getItemAtPosition(position));
                //Toast.makeText(DisciplineList2Activity.this, item, Toast.LENGTH_SHORT).show();


                //showFlashcard();

               /* Intent myIntent = new Intent(DisciplineList2Activity.this, ScrollingActivity.class);
                myIntent.putExtra("name", item);
                myIntent.putExtra("login", login);
                DisciplineList2Activity.this.startActivity(myIntent);*/
            }
        });

    }

    private void showForgotDialog(Context c) {
        /*final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Criar Flashcard")
                //.setMessage("Nome:")
                //.setView(taskEditText)

                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String task = String.valueOf(taskEditText.getText());
                        //adapter.add(task);
                        /*SharedPreferences sharedPref = DisciplineList2Activity.this.getPreferences(Context.MODE_PRIVATE);
                        String list = sharedPref.getString("disciplines", "");
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("disciplines", list + "#" + task);
                        editor.commit();//
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.setContentView(R.layout.flashcard_create);
        dialog.show();*/
        final Dialog dialog = new Dialog(SubjectActivity.this);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.flashcard_create);

        //TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        //text.setText("msg");

        Button dialogButton = (Button) dialog.findViewById(R.id.buttonBBB);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button dialogButton2 = (Button) dialog.findViewById(R.id.buttonAAA);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText frente = (EditText) dialog.findViewById(R.id.editTextTextMultiLine);
                EditText verso = (EditText) dialog.findViewById(R.id.editTextTextMultiLine3);
                Log.i("ASD", String.valueOf(frente.getText()));
                adapter.add(String.valueOf(frente.getText()) + " " + String.valueOf(verso.getText()));

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showFlashcard(View view) {
        AnkiFragment yourDialogFragment;
        /*switch (view.getId()){
            case R.id.button2:
                yourDialogFragment = AnkiFragment.newInstance("\nComo que é o código para imprimir uma mensagem em Java?\n", "\nclass HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\"); \n    }\n}\n");
                yourDialogFragment.show(getSupportFragmentManager().beginTransaction(), "DialogFragment");
                break;
            case R.id.button4:
                yourDialogFragment = AnkiFragment.newInstance("\nComo é que se declara uma variável em em Java?\n", "\nstring texto = \"minha string\";\nint numero = 3;\nboolean verdadeiro = true;\n");
                yourDialogFragment.show(getSupportFragmentManager().beginTransaction(), "DialogFragment");
                break;
        }*/
    }
}