package com.solution.freenglish;

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

    TextView imageViewTranslateWord, textViewWord, textViewNextWord;
    ImageView imageViewBackFromModule;
    Button buttonChooseTranslateWord1, buttonChooseTranslateWord2;
    int size, count, numOfTrueButton, numOfFalseTranslate;
    String[] words, translateWords;
    Random random;
    Intent chooseSectionInModuleActivity, mainActivity, superIntent;
    String numOfModule, typeOfModule;

    View ruleLayout, answerLayout1, answerLayout2, questionLayout1, questionLayout2;
    TextView textRule;
    Button buttonAnswerGreen, buttonAnswerPurple, buttonAnswerBlue, buttonAnswerWhite;
    Button buttonQuestionGreen, buttonQuestionPurple, buttonQuestionBlue, buttonQuestionWhite;
    Button buttonNextAndCheck;
    int hashAnswer, numOfRuleTask;
    String[] greenWords, purpleWords, blueWords, whiteWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        chooseSectionInModuleActivity = new Intent(ModuleActivity.this, ChooseSectionInModuleActivity.class);
        mainActivity = new Intent(ModuleActivity.this, MainActivity.class);
        superIntent = getIntent();

        init();

        switch(typeOfModule) {
            case "words":
                initWords();
                break;
            case "rule":
                initRule();
                break;
            case "practice":
                initPractice();
                break;
        }
    }

    private void init() {
        random = new Random();

        numOfModule = superIntent.getStringExtra("numOfModule");
        typeOfModule = superIntent.getStringExtra("typeOfModule");

        textViewWord = findViewById(R.id.textViewWord);
        imageViewTranslateWord = findViewById(R.id.textViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        textViewNextWord = findViewById(R.id.textViewNextWord);
        imageViewBackFromModule = findViewById(R.id.imageViewBackFromModule);

        textViewWord.setVisibility(View.GONE);
        imageViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);

        buttonChooseTranslateWord1.setOnClickListener(this);
        buttonChooseTranslateWord2.setOnClickListener(this);
        textViewNextWord.setOnClickListener(this);
        imageViewBackFromModule.setOnClickListener(this);


        ruleLayout = findViewById(R.id.ruleLayout);
        answerLayout1 = findViewById(R.id.answerLayout1);
        answerLayout2 = findViewById(R.id.answerLayout2);
        questionLayout1 = findViewById(R.id.questionLayout1);
        questionLayout2 = findViewById(R.id.questionLayout2);
        textRule = findViewById(R.id.textRule);
        buttonAnswerGreen = findViewById(R.id.buttonAnswerGreen);
        buttonAnswerPurple = findViewById(R.id.buttonAnswerPurple);
        buttonAnswerBlue = findViewById(R.id.buttonAnswerBlue);
        buttonAnswerWhite = findViewById(R.id.buttonAnswerWhite);
        buttonQuestionGreen = findViewById(R.id.buttonQuestionGreen);
        buttonQuestionPurple = findViewById(R.id.buttonQuestionPurple);
        buttonQuestionBlue = findViewById(R.id.buttonQuestionBlue);
        buttonQuestionWhite = findViewById(R.id.buttonQuestionWhite);
        buttonNextAndCheck = findViewById(R.id.buttonNextAndCheck);

        ruleLayout.setVisibility(View.GONE);
        answerLayout1.setVisibility(View.GONE);
        answerLayout2.setVisibility(View.GONE);
        questionLayout1.setVisibility(View.GONE);
        questionLayout2.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        buttonAnswerGreen.setVisibility(View.GONE);
        buttonAnswerPurple.setVisibility(View.GONE);
        buttonAnswerBlue.setVisibility(View.GONE);
        buttonAnswerWhite.setVisibility(View.GONE);
        buttonQuestionGreen.setVisibility(View.GONE);
        buttonQuestionPurple.setVisibility(View.GONE);
        buttonQuestionBlue.setVisibility(View.GONE);
        buttonQuestionWhite.setVisibility(View.GONE);
        buttonNextAndCheck.setVisibility(View.GONE);

        buttonAnswerGreen.setOnClickListener(this);
        buttonAnswerPurple.setOnClickListener(this);
        buttonAnswerBlue.setOnClickListener(this);
        buttonAnswerWhite.setOnClickListener(this);
        buttonQuestionGreen.setOnClickListener(this);
        buttonQuestionPurple.setOnClickListener(this);
        buttonQuestionBlue.setOnClickListener(this);
        buttonQuestionWhite.setOnClickListener(this);
        buttonNextAndCheck.setOnClickListener(this);

        buttonAnswerGreen.setBackgroundColor(Color.GREEN);
        buttonAnswerPurple.setBackgroundColor(Color.YELLOW);
        buttonAnswerBlue.setBackgroundColor(Color.BLUE);
        buttonAnswerWhite.setBackgroundColor(Color.GRAY);
        buttonQuestionGreen.setBackgroundColor(Color.GREEN);
        buttonQuestionPurple.setBackgroundColor(Color.YELLOW);
        buttonQuestionBlue.setBackgroundColor(Color.BLUE);
        buttonQuestionWhite.setBackgroundColor(Color.GRAY);
        buttonNextAndCheck.setBackgroundColor(Color.BLUE);
    }

    private void initWords() {
        ruleLayout.setVisibility(View.GONE);
        answerLayout1.setVisibility(View.GONE);
        answerLayout2.setVisibility(View.GONE);
        questionLayout1.setVisibility(View.GONE);
        questionLayout2.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        buttonQuestionGreen.setVisibility(View.GONE);
        buttonQuestionPurple.setVisibility(View.GONE);
        buttonQuestionBlue.setVisibility(View.GONE);
        buttonQuestionWhite.setVisibility(View.GONE);
        buttonNextAndCheck.setVisibility(View.GONE);

        textViewWord.setVisibility(View.VISIBLE);
        imageViewTranslateWord.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord1.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord2.setVisibility(View.VISIBLE);
        textViewNextWord.setVisibility(View.VISIBLE);

        words = getResources().getStringArray(R.array.wordsModule1);
        translateWords = getResources().getStringArray(R.array.translateWordsModule1);
        size = words.length; count = 1; numOfTrueButton = 1; numOfFalseTranslate = 1;

        textViewWord.setText(words[0]);
        buttonChooseTranslateWord1.setText(translateWords[0]);
        buttonChooseTranslateWord2.setText(translateWords[1]);

        textViewNextWord.setEnabled(false);

        buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));
        buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));

        buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
        buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForGentlyLilac));

        textViewNextWord.setTextColor(getResources().getColor(R.color.gray));
    }

    private void initRule() {
        textViewWord.setVisibility(View.GONE);
        imageViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);

        ruleLayout.setVisibility(View.VISIBLE);
        answerLayout1.setVisibility(View.VISIBLE);
        answerLayout2.setVisibility(View.VISIBLE);
        questionLayout1.setVisibility(View.VISIBLE);
        questionLayout2.setVisibility(View.VISIBLE);
        textRule.setVisibility(View.VISIBLE);
        buttonQuestionGreen.setVisibility(View.VISIBLE);
        buttonQuestionPurple.setVisibility(View.VISIBLE);
        buttonQuestionBlue.setVisibility(View.VISIBLE);
        buttonQuestionWhite.setVisibility(View.VISIBLE);
        buttonNextAndCheck.setVisibility(View.VISIBLE);

        greenWords = getResources().getStringArray(R.array.greenWords);
        purpleWords = getResources().getStringArray(R.array.purpleWords);
        blueWords = getResources().getStringArray(R.array.blueWords);
        whiteWords = getResources().getStringArray(R.array.whiteWords);

        buttonAnswerGreen.setText(greenWords[0]);
        buttonAnswerPurple.setText(purpleWords[0]);
        buttonAnswerBlue.setText(blueWords[0]);
        buttonAnswerWhite.setText(whiteWords[0]);
        buttonQuestionGreen.setText(greenWords[0]);
        buttonQuestionPurple.setText(purpleWords[0]);
        buttonQuestionBlue.setText(blueWords[0]);
        buttonQuestionWhite.setText(whiteWords[0]);

        hashAnswer = 0;
        numOfRuleTask = 0;
    }

    private void initPractice() {

    }

    @Override
    public void onClick(View v) {
        if (v == buttonChooseTranslateWord1) {
            chooseWord1();
        } else if (v == buttonChooseTranslateWord2) {
            chooseWord2();
        } else if (v == textViewNextWord) {
            nextWord();
        } else if (v == imageViewBackFromModule) {
            startActivity(mainActivity);
        } else if (v == buttonAnswerGreen) {
            buttonAnswerGreen.setVisibility(View.GONE);
            buttonQuestionGreen.setVisibility(View.VISIBLE);

            if (hashAnswer == 0) {
                hashAnswer -= 1;
            }
        } else if (v == buttonAnswerPurple) {
            buttonAnswerPurple.setVisibility(View.GONE);
            buttonQuestionPurple.setVisibility(View.VISIBLE);

            if (hashAnswer == 1) {
                hashAnswer -= 1;
            }
        } else if (v == buttonAnswerBlue) {
            buttonAnswerBlue.setVisibility(View.GONE);
            buttonQuestionBlue.setVisibility(View.VISIBLE);

            if (hashAnswer == 2) {
                hashAnswer -= 1;
            }
        } else if (v == buttonAnswerWhite) {
            buttonAnswerWhite.setVisibility(View.GONE);
            buttonQuestionWhite.setVisibility(View.VISIBLE);

            if (hashAnswer == 3) {
                hashAnswer -= 1;
            }
        } else if (v == buttonQuestionGreen) {
            buttonAnswerGreen.setVisibility(View.VISIBLE);
            buttonQuestionGreen.setVisibility(View.GONE);

            if (hashAnswer == 0) {
                hashAnswer += 1;
            }
        } else if (v == buttonQuestionPurple) {
            buttonAnswerPurple.setVisibility(View.VISIBLE);
            buttonQuestionPurple.setVisibility(View.GONE);

            if (hashAnswer == 1) {
                hashAnswer += 1;
            }
        } else if (v == buttonQuestionBlue) {
            buttonAnswerBlue.setVisibility(View.VISIBLE);
            buttonQuestionBlue.setVisibility(View.GONE);

            if (hashAnswer == 2) {
                hashAnswer += 1;
            }
        } else if (v == buttonQuestionWhite) {
            buttonAnswerWhite.setVisibility(View.VISIBLE);
            buttonQuestionWhite.setVisibility(View.GONE);

            if (hashAnswer == 3) {
                hashAnswer += 1;
            }
        } else if (v == buttonNextAndCheck) {
            nextRuleTask();
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

            buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));
            buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));

            buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
            buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForGentlyLilac));

            if (numOfTrueButton == 1) {
                buttonChooseTranslateWord1.setText(translateWords[count]);
                buttonChooseTranslateWord2.setText(translateWords[numOfFalseTranslate]);
            } else if (numOfTrueButton == 2){
                buttonChooseTranslateWord1.setText(translateWords[numOfFalseTranslate]);
                buttonChooseTranslateWord2.setText(translateWords[count]);
            }

            buttonChooseTranslateWord1.setEnabled(true);
            buttonChooseTranslateWord2.setEnabled(true);
            textViewNextWord.setEnabled(false);

            count++;
        } else {
            buttonChooseTranslateWord1.setEnabled(true);
            buttonChooseTranslateWord2.setEnabled(true);
            textViewNextWord.setEnabled(false);

            startActivity(chooseSectionInModuleActivity);
        }
    }

    private void chooseWord1() {
        if (numOfTrueButton == 1) {
            buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.trueGreen));
            buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));

            buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForTrueGreen));
            buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
        } else {
            buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.wrongRed));
            buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));

            buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForWrongRed));
            buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
        }

        buttonChooseTranslateWord1.setEnabled(false);
        buttonChooseTranslateWord2.setEnabled(false);
        textViewNextWord.setEnabled(true);
    }

    private void chooseWord2() {
        if (numOfTrueButton == 2) {
            buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));
            buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.trueGreen));

            buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
            buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForTrueGreen));
        } else {
            buttonChooseTranslateWord1.setBackgroundColor(getResources().getColor(R.color.gentlyLilac));
            buttonChooseTranslateWord2.setBackgroundColor(getResources().getColor(R.color.wrongRed));

            buttonChooseTranslateWord1.setTextColor(getResources().getColor(R.color.textForGentlyLilac));
            buttonChooseTranslateWord2.setTextColor(getResources().getColor(R.color.textForWrongRed));
        }

        buttonChooseTranslateWord1.setEnabled(false);
        buttonChooseTranslateWord2.setEnabled(false);
        textViewNextWord.setEnabled(true);
    }

    private void nextRuleTask() {
        if (hashAnswer == 4) {
            if (numOfRuleTask == 2) {
                startActivity(chooseSectionInModuleActivity);
            }
            
            numOfRuleTask += 1;

            buttonAnswerGreen.setText(greenWords[numOfRuleTask]);
            buttonAnswerPurple.setText(purpleWords[numOfRuleTask]);
            buttonAnswerBlue.setText(blueWords[numOfRuleTask]);
            buttonAnswerWhite.setText(whiteWords[numOfRuleTask]);
            buttonQuestionGreen.setText(greenWords[numOfRuleTask]);
            buttonQuestionPurple.setText(purpleWords[numOfRuleTask]);
            buttonQuestionBlue.setText(blueWords[numOfRuleTask]);
            buttonQuestionWhite.setText(whiteWords[numOfRuleTask]);

            buttonAnswerGreen.setVisibility(View.GONE);
            buttonAnswerPurple.setVisibility(View.GONE);
            buttonAnswerBlue.setVisibility(View.GONE);
            buttonAnswerWhite.setVisibility(View.GONE);
            buttonQuestionGreen.setVisibility(View.VISIBLE);
            buttonQuestionPurple.setVisibility(View.VISIBLE);
            buttonQuestionBlue.setVisibility(View.VISIBLE);
            buttonQuestionWhite.setVisibility(View.VISIBLE);

            hashAnswer = 0;
        }
    }
}