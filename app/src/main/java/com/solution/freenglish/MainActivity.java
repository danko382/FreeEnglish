package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewModule1, textViewModule2, textViewModule3, textViewModule4, textViewModule5;
    Intent ChooseSelectionModuleActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ChooseSelectionModuleActivity = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);

        textViewModule1 = findViewById(R.id.textViewModule1);
        textViewModule2 = findViewById(R.id.textViewModule2);
        textViewModule3 = findViewById(R.id.textViewModule3);
        textViewModule4 = findViewById(R.id.textViewModule4);
        textViewModule5 = findViewById(R.id.textViewModule5);

        textViewModule1.setOnClickListener(this);
        textViewModule2.setOnClickListener(this);
        textViewModule3.setOnClickListener(this);
        textViewModule4.setOnClickListener(this);
        textViewModule5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == textViewModule1) {
            startActivity(ChooseSelectionModuleActivity);
        } else if (v == textViewModule2) {
            startActivity(ChooseSelectionModuleActivity);
        } else if (v == textViewModule3) {
            startActivity(ChooseSelectionModuleActivity);
        } else if (v == textViewModule4) {
            startActivity(ChooseSelectionModuleActivity);
        } else if (v == textViewModule5) {
            startActivity(ChooseSelectionModuleActivity);
        }
    }
}