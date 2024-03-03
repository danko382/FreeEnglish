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
    Intent moduleActivity, mainActivity, superActivity;
    String numOfModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section_in_module);

        moduleActivity = new Intent(ChooseSectionInModuleActivity.this, ModuleActivity.class);
        mainActivity = new Intent(ChooseSectionInModuleActivity.this, MainActivity.class);
        superActivity = getIntent();

        init();
    }

    private void init() {
        numOfModule = superActivity.getStringExtra("numOfModule");

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
            moduleActivity.putExtra("numOfModule", numOfModule);
            moduleActivity.putExtra("typeOfModule", "words");
            startActivity(moduleActivity);
        } else if (v == textViewRule) {
            moduleActivity.putExtra("numOfModule", numOfModule);
            moduleActivity.putExtra("typeOfModule", "rule");
            startActivity(moduleActivity);
        } else if (v == textViewPractice) {
            moduleActivity.putExtra("numOfModule", numOfModule);
            moduleActivity.putExtra("typeOfModule", "practice");
            startActivity(moduleActivity);
        } else if (v == imageViewBackFromChoose) {
            startActivity(mainActivity);
        }
    }
}