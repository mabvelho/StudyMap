package com.example.studymap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.studymap.data.UserRole;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class ScrollingActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private UserRole role;

    private int topId;
    private int constraintType;

    private boolean asd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("name"));
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getIntent().getStringExtra("name"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addButton();
                showAddButtonDialog(ScrollingActivity.this);
            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ScrollingActivity.this, SuggestionActivity.class);
                //myIntent.putExtra("name", item);
                ScrollingActivity.this.startActivity(myIntent);
            }
        });

        String login = getIntent().getStringExtra("login");
        role = UserRole.parseUserLogin(login);



        switch (role) {
            case STUDENT:
                fab2.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
                break;
            case PROFESSOR:
                fab2.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);
                break;
            case VISITOR:
                fab2.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.INVISIBLE);
                break;
        }

        layout = findViewById(R.id.mainLayout);
        topId = layout.getId();
        constraintType = ConstraintSet.TOP;

        if (3 > 1) {
            addButton("Olá Mundo!");
            addButton("Variáveis");
            addButton("Array");
            addButton("Ponteiros");
            addButton("If-Else");
        }
    }

    private void addButton(final String name) {

        Button myButton = new Button(this);
        myButton.setBackground(ContextCompat.getDrawable(this, R.drawable.circular_button));
        myButton.setText(name);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ScrollingActivity.this, SubjectActivity.class);
                myIntent.putExtra("name", name);
                ScrollingActivity.this.startActivity(myIntent);
            }
        });

        ConstraintSet set = new ConstraintSet();
        myButton.setId(View.generateViewId());
        layout.addView(myButton, 0);

        myButton.getLayoutParams().width = (int) (96 * getResources().getDisplayMetrics().density);
        myButton.getLayoutParams().height = (int) (96 * getResources().getDisplayMetrics().density);

        set.clone(layout);
        set.connect(myButton.getId(), ConstraintSet.TOP, topId, constraintType, 60);
        set.connect(myButton.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, 60);
        set.connect(myButton.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END, 60);
        set.applyTo(layout);




        View line = new View(this);
        line.setBackground(ContextCompat.getDrawable(this, R.drawable.material_drawer_ico_account_layer));

        ConstraintSet lineSet = new ConstraintSet();
        line.setId(View.generateViewId());
        layout.addView(line, 0);

        line.getLayoutParams().width = (int) (2 * getResources().getDisplayMetrics().density);
        line.getLayoutParams().height = (int) (38 * getResources().getDisplayMetrics().density);

        lineSet.clone(layout);
        lineSet.connect(line.getId(), ConstraintSet.BOTTOM, myButton.getId(), ConstraintSet.TOP, 0);
        lineSet.connect(line.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, 60);
        lineSet.connect(line.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END, 60);
        lineSet.applyTo(layout);


        topId = myButton.getId();
        constraintType = ConstraintSet.BOTTOM;
    }

    private void showAddButtonDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Adicionar Assunto")
                .setMessage("Nome:")
                .setView(taskEditText)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        addButton(task);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        if (role == UserRole.PROFESSOR) {
            item.setVisible(true);
        }
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(ScrollingActivity.this, SuggestionActivity.class);
            //myIntent.putExtra("name", item);
            ScrollingActivity.this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFlashcard(View view) {
        /*AnkiFragment yourDialogFragment;
        switch (view.getId()){
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