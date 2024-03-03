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

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewWord;
    ImageView imageViewTranslateWord, imageViewBackFromModule;
    Button buttonChooseTranslateWord1, buttonChooseTranslateWord2, buttonNextWord;
    int size, count, numOfTrueButton, numOfFalseTranslate;
    String[] words, translateWords;
    Random random;
    Intent chooseSectionInModuleActivity, mainActivity;
    String numOfModule, typeOfModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        random = new Random();

        chooseSectionInModuleActivity = new Intent(ModuleActivity.this, ChooseSectionInModuleActivity.class);
        mainActivity = new Intent(ModuleActivity.this, MainActivity.class);

        initWords();
    }

    private void init() {
        textViewWord = findViewById(R.id.textViewWord);
        imageViewTranslateWord = findViewById(R.id.imageViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        buttonNextWord = findViewById(R.id.buttonNextWord);
        imageViewBackFromModule = findViewById(R.id.imageViewBackFromModule);

        buttonChooseTranslateWord1.setOnClickListener(this);
        buttonChooseTranslateWord2.setOnClickListener(this);
        buttonNextWord.setOnClickListener(this);
        imageViewBackFromModule.setOnClickListener(this);
    }

    private void initWords() {
        words = getResources().getStringArray(R.array.wordsModule1);
        translateWords = getResources().getStringArray(R.array.translateWordsModule1);
        size = words.length; count = 1; numOfTrueButton = 1; numOfFalseTranslate = 1;

        textViewWord.setText(words[0]);
        buttonChooseTranslateWord1.setText(translateWords[0]);
        buttonChooseTranslateWord2.setText(translateWords[1]);

        buttonChooseTranslateWord1.setBackgroundColor(Color.BLUE);
        buttonChooseTranslateWord2.setBackgroundColor(Color.BLUE);
    }

    private void initRule() {

    }

    private void initPractice() {

    }

    @Override
    public void onClick(View v) {
        if (v == buttonChooseTranslateWord1) {
            chooseWord1();
        } else if (v == buttonChooseTranslateWord2) {
            chooseWord2();
        } else if (v == buttonNextWord) {
            nextWord();
        } else if (v == imageViewBackFromModule) {
            startActivity(mainActivity);
        }
    }

    private void nextWord() {
        if (count < size) {
            textViewWord.setText(words[count]);

            numOfTrueButton = random.nextInt(2) + 1;
            numOfFalseTranslate = random.nextInt(size);

            while (numOfFalseTranslate == count) {
                numOfFalseTranslate = random.nextInt(size);
            }

            Log.d("numOfTrueButton", "" + numOfTrueButton);

            buttonChooseTranslateWord1.setBackgroundColor(Color.BLUE);
            buttonChooseTranslateWord2.setBackgroundColor(Color.BLUE);

            if (numOfTrueButton == 1) {
                buttonChooseTranslateWord1.setText(translateWords[count]);
                buttonChooseTranslateWord2.setText(translateWords[numOfFalseTranslate]);
            } else if (numOfTrueButton == 2){
                buttonChooseTranslateWord1.setText(translateWords[numOfFalseTranslate]);
                buttonChooseTranslateWord2.setText(translateWords[count]);
            }

            count++;
        } else {
            startActivity(chooseSectionInModuleActivity);
        }
    }

    private void chooseWord1() {
        Log.d("numOfTrueButton", "" + numOfTrueButton);
        if (numOfTrueButton == 1) {
            buttonChooseTranslateWord1.setBackgroundColor(Color.GREEN);
            buttonChooseTranslateWord2.setBackgroundColor(Color.RED);
        } else {
            buttonChooseTranslateWord1.setBackgroundColor(Color.RED);
            buttonChooseTranslateWord2.setBackgroundColor(Color.GREEN);
        }
    }

    private void chooseWord2() {
        Log.d("numOfTrueButton", "" + numOfTrueButton);
        if (numOfTrueButton == 2) {
            buttonChooseTranslateWord1.setBackgroundColor(Color.RED);
            buttonChooseTranslateWord2.setBackgroundColor(Color.GREEN);
        } else {
            buttonChooseTranslateWord1.setBackgroundColor(Color.GREEN);
            buttonChooseTranslateWord2.setBackgroundColor(Color.RED);
        }
    }
}