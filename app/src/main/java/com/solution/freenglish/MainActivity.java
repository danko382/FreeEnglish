package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewModule1;
    TextView textViewModule2;
    TextView textViewModule3;
    TextView textViewModule4;
    TextView textViewModule5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewModule1 = findViewById(R.id.textViewModule1);
        textViewModule2 = findViewById(R.id.textViewModule2);
        textViewModule3 = findViewById(R.id.textViewModule3);
        textViewModule4 = findViewById(R.id.textViewModule4);
        textViewModule5 = findViewById(R.id.textViewModule5);

        textViewModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewModule4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewModule5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSectionInModuleActivity.class);
                startActivity(intent);
            }
        });
    }

}