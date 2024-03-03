package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseSectionInModuleActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewWors, textViewRule, textViewPractice;
    ImageView imageViewBackFromChoose;
    Intent moduleActivity, mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section_in_module);

        init();
    }

    private void init() {
        moduleActivity = new Intent(ChooseSectionInModuleActivity.this, ModuleActivity.class);
        mainActivity = new Intent(ChooseSectionInModuleActivity.this, MainActivity.class);

        textViewWors = findViewById(R.id.textViewWords);
        textViewRule = findViewById(R.id.textViewRoole);
        textViewPractice = findViewById(R.id.textViewPractice);
        imageViewBackFromChoose = findViewById(R.id.imageViewBackFromChoose);

        textViewWors.setOnClickListener(this);
        textViewRule.setOnClickListener(this);
        textViewPractice.setOnClickListener(this);
        imageViewBackFromChoose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == textViewWors) {
            startActivity(moduleActivity);
        } else if (v == textViewRule) {
            startActivity(moduleActivity);
        } else if (v == textViewPractice) {
            startActivity(moduleActivity);
        } else if (v == imageViewBackFromChoose) {
            startActivity(mainActivity);
        }
    }
}