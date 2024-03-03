package com.solution.freenglish;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ModuleActivity extends AppCompatActivity {

    TextView textViewWord;
    ImageView imageViewTranslateWord, imageViewBackFromModule;
    Button buttonChooseTranslateWord1, buttonChooseTranslateWord2, buttonNextWord;
    int size, count, numOfTrueButton, numOfFalseTranslate;
    String[] words, translateWords;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        random = new Random();

        initWords();

        imageViewBackFromModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonChooseTranslateWord1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textViewWord.setText("" + numOfTrueButton);
                if (numOfTrueButton == 1) {
                    buttonChooseTranslateWord1.setBackgroundColor(Color.GREEN);
                    buttonChooseTranslateWord2.setBackgroundColor(Color.RED);
                } else {
                    buttonChooseTranslateWord1.setBackgroundColor(Color.RED);
                    buttonChooseTranslateWord2.setBackgroundColor(Color.GREEN);
                }
            }
        });

        buttonChooseTranslateWord2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewWord.setText("" + numOfTrueButton);
                if (numOfTrueButton == 1) {
                    buttonChooseTranslateWord1.setBackgroundColor(Color.RED);
                    buttonChooseTranslateWord2.setBackgroundColor(Color.GREEN);
                } else {
                    buttonChooseTranslateWord1.setBackgroundColor(Color.GREEN);
                    buttonChooseTranslateWord2.setBackgroundColor(Color.RED);
                }
            }
        });

        buttonNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < size) {
                    textViewWord.setText(words[count]);

                    int numOfTrueButton = random.nextInt(2);
                    int numOfFalseTranslate = random.nextInt(size);
                    while (numOfFalseTranslate == count) {
                        numOfFalseTranslate = random.nextInt(size);
                    }

                    if (numOfTrueButton == 0) {
                        buttonChooseTranslateWord1.setText(translateWords[numOfFalseTranslate]);
                        buttonChooseTranslateWord1.setBackgroundColor(Color.BLUE);
                        buttonChooseTranslateWord2.setText(translateWords[count]);
                        buttonChooseTranslateWord2.setBackgroundColor(Color.BLUE);
                    } else if (numOfTrueButton == 1){
                        buttonChooseTranslateWord1.setText(translateWords[count]);
                        buttonChooseTranslateWord1.setBackgroundColor(Color.BLUE);
                        buttonChooseTranslateWord2.setText(translateWords[numOfFalseTranslate]);
                        buttonChooseTranslateWord2.setBackgroundColor(Color.BLUE);
                    }

                    count++;
                } else {
                    Intent intent = new Intent(ModuleActivity.this, ChooseSectionInModuleActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void chooseWordsModule() {

    }

    private void initWords() {
        words = getResources().getStringArray(R.array.wordsModule1);
        translateWords = getResources().getStringArray(R.array.translateWordsModule1);
        size = words.length; count = 0; numOfTrueButton = 1; numOfFalseTranslate = 1;

        textViewWord = findViewById(R.id.textViewWord);
        imageViewTranslateWord = findViewById(R.id.imageViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        buttonNextWord = findViewById(R.id.buttonNextWord);
        imageViewBackFromModule = findViewById(R.id.imageViewBackFromModule);

        textViewWord.setText(words[0]);
        buttonChooseTranslateWord1.setText(translateWords[0]);
        buttonChooseTranslateWord1.setBackgroundColor(Color.BLUE);
        buttonChooseTranslateWord2.setText(translateWords[1]);
        buttonChooseTranslateWord2.setBackgroundColor(Color.BLUE);
    }

}