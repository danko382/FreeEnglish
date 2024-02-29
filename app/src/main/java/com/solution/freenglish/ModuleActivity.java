package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ModuleActivity extends AppCompatActivity {

    TextView textViewWord;
    ImageView imageViewTranslateWord;
    Button buttonChooseTranslateWord1;
    Button buttonChooseTranslateWord2;
    Button buttonNextWord;
    ImageView imageViewBackFromModule;
    int size;
    int count = 0;
    String[] words;
    String[] translateWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        textViewWord = findViewById(R.id.textViewWord);
        imageViewTranslateWord = findViewById(R.id.imageViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        buttonNextWord = findViewById(R.id.buttonNextWord);
        imageViewBackFromModule = findViewById(R.id.imageViewBackFromModule);

        chooseWordsModule();
        textViewWord.setText(words[0]);
        buttonChooseTranslateWord1.setText(translateWords[0]);
        buttonChooseTranslateWord2.setText(translateWords[(int) Math.random() * 9]);
        count++;

        imageViewBackFromModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < size) {
                    textViewWord.setText(words[count]);
                    buttonChooseTranslateWord1.setText(translateWords[count]);
                    buttonChooseTranslateWord2.setText(translateWords[(int) Math.random() * 9]);
                    count++;
                } else {
                    Intent intent = new Intent(ModuleActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void chooseWordsModule() {
        words = getResources().getStringArray(R.array.wordsModule1);
        translateWords = getResources().getStringArray(R.array.translateWordsModule1);
        size = words.length;
        count = 0;
    }


}