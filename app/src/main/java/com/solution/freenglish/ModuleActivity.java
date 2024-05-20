package com.solution.freenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener{

    //////////////////////////////////////////////////////////////////////////////////
    // общие переменные
    //////////////////////////////////////////////////////////////////////////////////
    Random random;
    TextView textViewBackToChooseModule;
    Intent chooseSectionInModuleActivity, mainActivity, superIntent;
    String numOfModule, typeOfModule;
    //////////////////////////////////////////////////////////////////////////////////
    // перменные для модуля слов
    //////////////////////////////////////////////////////////////////////////////////
    TextView textViewTranslateWord, textViewWord, textViewNextWord;
    Button buttonChooseTranslateWord1, buttonChooseTranslateWord2;
    int size, count, numOfTrueButton, numOfFalseTranslate;
    String[] words, translateWords;
    //////////////////////////////////////////////////////////////////////////////////
    // перменные для модуля правил
    //////////////////////////////////////////////////////////////////////////////////
    ConstraintLayout answerLayout, questionLayout;
    Button buttonYellow, buttonBlue, buttonGreen;
    ArrayList<Button> buttons;
    TextView textRule, textViewNextRule;
    int hashAnswer, numOfRuleTask;
    int numOfRule;
    String[] yellowWords, blueWords, greenWords, whiteWords;
    //////////////////////////////////////////////////////////////////////////////////
    // инициализация activity
    //////////////////////////////////////////////////////////////////////////////////
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

    //////////////////////////////////////////////////////////////////////////////////
    // блок изначальной инициализации, все view, кроме общих, имеют видимость gone
    //////////////////////////////////////////////////////////////////////////////////
    private void init() {
        //////////////////////////////////////////////////////////////////////////////////
        // инициализация общих переменных
        //////////////////////////////////////////////////////////////////////////////////
        random = new Random();
        numOfModule = superIntent.getStringExtra("numOfModule");
        typeOfModule = superIntent.getStringExtra("typeOfModule");
        textViewBackToChooseModule = findViewById(R.id.textViewBackToChooseModule);
        //////////////////////////////////////////////////////////////////////////////////
        // инициализация переменных модуля слов
        //////////////////////////////////////////////////////////////////////////////////
        textViewWord = findViewById(R.id.textViewWord);
        textViewTranslateWord = findViewById(R.id.textViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        textViewNextWord = findViewById(R.id.textViewNextWord);

        textViewWord.setVisibility(View.GONE);
        textViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);

        buttonChooseTranslateWord1.setOnClickListener(this);
        buttonChooseTranslateWord2.setOnClickListener(this);
        textViewNextWord.setOnClickListener(this);
        textViewBackToChooseModule.setOnClickListener(this);
        //////////////////////////////////////////////////////////////////////////////////
        // инициализация переменных модуля слов
        //////////////////////////////////////////////////////////////////////////////////
        answerLayout = findViewById(R.id.answerLayout);
        questionLayout = findViewById(R.id.questionLayout);
        textRule = findViewById(R.id.textRule);
        textViewNextRule = findViewById(R.id.textViewNextRule);

        answerLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        textViewNextRule.setVisibility(View.GONE);

        textViewNextRule.setOnClickListener(this);
    }
    //////////////////////////////////////////////////////////////////////////////////
    // блок инициализации модуля слов, все view, кроме принадлежащих модулю слов,
    // имеют видимость gone
    //////////////////////////////////////////////////////////////////////////////////
    private void initWords() {
        answerLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        textViewNextRule.setVisibility(View.GONE);

        textViewWord.setVisibility(View.VISIBLE);
        textViewTranslateWord.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord1.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord2.setVisibility(View.VISIBLE);
        textViewNextWord.setVisibility(View.VISIBLE);

        words = getResources().getStringArray(R.array.words_module1);
        translateWords = getResources().getStringArray(R.array.translate_words_module1);
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
        textViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);

        textRule.setVisibility(View.VISIBLE);
        answerLayout.setVisibility(View.VISIBLE);
        questionLayout.setVisibility(View.VISIBLE);
        textViewNextRule.setVisibility(View.VISIBLE);

        initYellowWords(1);
        initBlueWords(1);
        initGreenWords(1);
        initWhiteWords(1, 1);

        hashAnswer = 0;
        numOfRuleTask = 0;
        numOfRule = 1;
    }

    private void initYellowWords(final int numOfModule) {
        switch(numOfModule) {
            case 1:
                yellowWords = getResources().getStringArray(R.array.yellow_rule_module1);
        }
    }
    private void initBlueWords(final int numOfModule) {
        switch(numOfModule) {
            case 1:
                blueWords = getResources().getStringArray(R.array.blue_rule_module1);
        }
    }
    private void initGreenWords(final int numOfModule) {
        switch(numOfModule) {
            case 1:
                greenWords = getResources().getStringArray(R.array.green_rule_module1);
        }
    }
    private void initWhiteWords(final int numOfModule, final int numOfRule) {
        if (numOfModule == 1) {
            switch(numOfRule) {
                case 1:
                    whiteWords = getResources().getStringArray(R.array.whire_rule_module1_1);
                    break;
                case 2:
                    whiteWords = getResources().getStringArray(R.array.whire_rule_module1_2);
                    break;
                case 3:
                    whiteWords = getResources().getStringArray(R.array.whire_rule_module1_1);
                    break;
            }
        }
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
        } else if (v == textViewBackToChooseModule) {
            startActivity(mainActivity);
        } else if (v == textViewNextRule) {
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

    private void constraintRuleWords(ArrayList<Button> buttons, ConstraintLayout constraintLayout) {
        
    }
    private void nextRuleTask() {
        buttons = new ArrayList<>();

        numOfRule += 1;

        buttonYellow = new Button(this);
        buttonBlue = new Button(this);
        buttonGreen = new Button(this);

        buttonYellow.setText(yellowWords[numOfRule]);
        buttonBlue.setText(blueWords[numOfRule]);
        buttonGreen.setText(greenWords[numOfRule]);

        buttons.add(buttonYellow);
        buttons.add(buttonBlue);
        buttons.add(buttonGreen);

        initWhiteWords(1, numOfRule);

        for (int i = 0; i < whiteWords.length; ++i) {
            Button whiteButton = new Button(this);
            whiteButton.setText(whiteWords[i]);

            buttons.add(whiteButton);
        }

        for (int i = 0; i < buttons.size(); ++i) {
            buttons.get(i).setTextSize(20);
            buttons.get(i).setId(View.generateViewId());
            buttons.get(i).setVisibility(View.VISIBLE);
            buttons.get(i).setLayoutParams(new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT ));
        }

        for (int i = 0; i < buttons.size(); ++i) {
            questionLayout.addView(buttons.get(i));

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(questionLayout);

            constraintSet.connect(buttons.get(i).getId(), ConstraintSet.START, questionLayout.getId(),ConstraintSet.START,0);
            constraintSet.connect(buttons.get(i).getId(), ConstraintSet.END, questionLayout.getId(), ConstraintSet.END, 0);
            if(buttons.size() - i == )
            if (i < 3) {
                constraintSet.connect(buttons.get(i).getId(), ConstraintSet.TOP , buttonYellow.getId(), ConstraintSet.TOP,0);
            } else {
                constraintSet.connect(buttons.get(i).getId(), ConstraintSet.TOP , buttons.get(i - 3).getId(), ConstraintSet.TOP,0);
            }

            constraintSet.applyTo(questionLayout);
        }
    }
}