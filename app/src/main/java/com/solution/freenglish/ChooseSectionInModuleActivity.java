package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseSectionInModuleActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_NUM_OF_MODULE = "numOfModule";
    public static final String EXTRA_TYPE_OF_SECTION = "typeOfSection";

    TextView textViewWors, textViewRule, textViewPractice;
    ImageView imageViewBackFromChoose;
    Intent superActivity;
    int numOfModule;
    String typeOfSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section_in_module);

        superActivity = getIntent();

        init();
    }

    public static Intent newIntent(
            Context context,
            int numOfModule
    ) {
        Intent intent = new Intent(context, ChooseSectionInModuleActivity.class);
        intent.putExtra(EXTRA_NUM_OF_MODULE, numOfModule);
        return intent;
    }

    private void init() {
        numOfModule = superActivity.getIntExtra(EXTRA_NUM_OF_MODULE, 1);

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
            typeOfSection = "words";
        } else if (v == textViewRule) {
            typeOfSection = "rule";
        } else if (v == textViewPractice) {
            typeOfSection = "practice";
        } else if (v == imageViewBackFromChoose) {
            Intent intent = MainActivity.newIntent(this);
            startActivity(intent);
        }
        Intent intent = ModuleActivity.newIntent(
                this,
                numOfModule,
                typeOfSection
        );
        startActivity(intent);
    }
}