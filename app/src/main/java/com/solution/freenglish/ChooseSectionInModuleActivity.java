package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChooseSectionInModuleActivity extends AppCompatActivity {

    TextView textViewWors;
    TextView textViewRoole;
    TextView textViewPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section_in_module);

        textViewWors = findViewById(R.id.textViewWords);
        textViewRoole = findViewById(R.id.textViewRoole);
        textViewPractice = findViewById(R.id.textViewPractice);
    }
}