package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseSectionInModuleActivity extends AppCompatActivity {

    TextView textViewWors;
    TextView textViewRoole;
    TextView textViewPractice;
    ImageView imageViewBackFromChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section_in_module);

        textViewWors = findViewById(R.id.textViewWords);
        textViewRoole = findViewById(R.id.textViewRoole);
        textViewPractice = findViewById(R.id.textViewPractice);
        imageViewBackFromChoose = findViewById(R.id.imageViewBackFromChoose);

        textViewWors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSectionInModuleActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewRoole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSectionInModuleActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        textViewPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSectionInModuleActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        imageViewBackFromChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSectionInModuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}