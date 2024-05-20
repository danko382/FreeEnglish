package com.solution.freenglish;

import static java.util.Collections.shuffle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener{
    //////////////////////////////////////////////////////////////////////////////////
    // общие переменные
    //////////////////////////////////////////////////////////////////////////////////
    public static final String EXTRA_NUM_OF_MODULE = "numOfModule";
    public static final String EXTRA_TYPE_OF_SECTION = "typeOfSection";
    Random random;
    TextView textViewBackToChooseModule;
    Intent superIntent;
    int numOfModule;
    String typeOfSection;
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
    ArrayList<Button> buttonsQuestion, buttonsAnswer;
    TextView textRule, textViewNextRule;
    int numOfRule, numberOfRules, clickOnNextRule;
    String[] yellowWords, blueWords, greenWords, whiteWords;
    //////////////////////////////////////////////////////////////////////////////////
    // инициализация activity
    //////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        superIntent = getIntent();

        init();

        switch(typeOfSection) {
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

    public static Intent newIntent(
            Context context,
            int numOfModule,
            String typeOfSection
    ) {
        Intent intent = new Intent(context, ModuleActivity.class);
        intent.putExtra(EXTRA_NUM_OF_MODULE, numOfModule);
        intent.putExtra(EXTRA_TYPE_OF_SECTION, typeOfSection);
        return intent;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // блок изначальной инициализации, все view, кроме общих, имеют видимость gone
    //////////////////////////////////////////////////////////////////////////////////
    private void init() {
        random = new Random();
        numOfModule = superIntent.getIntExtra(EXTRA_NUM_OF_MODULE, 1);
        typeOfSection = superIntent.getStringExtra(EXTRA_TYPE_OF_SECTION);

        initAllViews();

        goneAllViews();
    }
    //////////////////////////////////////////////////////////////////////////////////
    // блок инициализации модуля слов, все view, кроме принадлежащих модулю слов,
    // имеют видимость gone
    //////////////////////////////////////////////////////////////////////////////////
    private void initWords() {
        setVisabilityForWords();

        getResourcesForWords();

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
        clickOnNextRule = 1;

        numOfRule = 1;

        setVisabilityForRules();

        textViewNextRule.setText("Check");

        answerLayout.setMinHeight(100);
        questionLayout.setMinHeight(100);

        getResourcesForRules();

        startInitButtonsForRules();

        shuffle(buttonsQuestion);
        constraintRuleWords(buttonsQuestion, questionLayout);
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
            Intent intent = ChooseSectionInModuleActivity.newIntent(
                    this,
                    numOfModule
            );
            startActivity(intent);
        } else if (v == textViewNextRule) {
            if (clickOnNextRule % 2 == 0) {
                textViewNextRule.setText("Check");
                nextRuleTask();
            } else {
                textViewNextRule.setText("Next");
                checkRuleTask();
            }
            clickOnNextRule += 1;
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

            Intent intent = ChooseSectionInModuleActivity.newIntent(
                    this,
                    numOfModule
            );
            startActivity(intent);
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
        answerLayout.removeAllViews();
        questionLayout.removeAllViews();

        answerLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));

        numOfRule += 1;

        if (numOfRule <= numberOfRules) {
            startInitButtonsForRules();

            shuffle(buttonsQuestion);
            constraintRuleWords(buttonsQuestion, questionLayout);
        } else {
            Intent intent = ChooseSectionInModuleActivity.newIntent(
                    this,
                    numOfModule
            );
            startActivity(intent);
        }
    }

    private void checkRuleTask() {
        boolean isTrue;
        if (buttonsQuestion.size() == 0) {
            isTrue =
                buttonsAnswer.get(0).getText().equals(yellowWords[numOfRule - 1])
                && buttonsAnswer.get(1).getText().equals(blueWords[numOfRule - 1])
                && buttonsAnswer.get(2).getText().equals(greenWords[numOfRule - 1]);

            for (int i = 0; i < whiteWords.length; ++i) {
                if (!buttonsAnswer.get(i + 3).getText().equals(whiteWords[i])) {
                    isTrue = false;
                    break;
                }
            }
        } else {
            isTrue = false;
        }

        if (isTrue) {
            answerLayout.setBackgroundColor(getResources().getColor(R.color.textForTrueGreen));
        } else {
            answerLayout.setBackgroundColor(getResources().getColor(R.color.wrong_orange));
        }
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
                    whiteWords = getResources().getStringArray(R.array.white_rule_module1_1);
                    break;
                case 2:
                    whiteWords = getResources().getStringArray(R.array.white_rule_module1_2);
                    break;
                case 3:
                    whiteWords = getResources().getStringArray(R.array.white_rule_module1_3);
                    break;
            }
        }
    }

    private void constraintRuleWords(ArrayList<Button> buttons, ConstraintLayout constraintLayout) {
        for (int i = 0; i < buttons.size(); ++i) {
            constraintLayout.addView(buttons.get(i));

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            constraintSet.connect(buttons.get(i).getId(), ConstraintSet.START, constraintLayout.getId(),ConstraintSet.START,0);
            constraintSet.connect(buttons.get(i).getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
            if (i < 3) {
                constraintSet.connect(buttons.get(i).getId(), ConstraintSet.TOP , constraintLayout.getId(), ConstraintSet.TOP,0);
            } else {
                constraintSet.connect(buttons.get(i).getId(), ConstraintSet.TOP , buttons.get(i - 3).getId(), ConstraintSet.BOTTOM,0);
            }
            if (i % 3 == 0) {
                constraintSet.setHorizontalBias(buttons.get(i).getId(), 0.0f);
            } else if (i % 3 == 1) {
                constraintSet.setHorizontalBias(buttons.get(i).getId(), 0.5f);
            } else {
                constraintSet.setHorizontalBias(buttons.get(i).getId(), 1.0f);
            }

            constraintSet.applyTo(constraintLayout);
        }
    }

    private void goneAllViews() {
        //////////////////////////////////////////////////////////////////////////////////
        // words
        //////////////////////////////////////////////////////////////////////////////////
        textViewWord.setVisibility(View.GONE);
        textViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);
        //////////////////////////////////////////////////////////////////////////////////
        // rules
        //////////////////////////////////////////////////////////////////////////////////
        answerLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        textViewNextRule.setVisibility(View.GONE);
    }

    private void initAllViews() {
        //////////////////////////////////////////////////////////////////////////////////
        // common
        //////////////////////////////////////////////////////////////////////////////////
        textViewBackToChooseModule = findViewById(R.id.textViewBackToChooseModule);
        //////////////////////////////////////////////////////////////////////////////////
        // words
        //////////////////////////////////////////////////////////////////////////////////
        textViewWord = findViewById(R.id.textViewWord);
        textViewTranslateWord = findViewById(R.id.textViewTranslateWord);
        buttonChooseTranslateWord1 = findViewById(R.id.buttonChooseTranslateWord1);
        buttonChooseTranslateWord2 = findViewById(R.id.buttonChooseTranslateWord2);
        textViewNextWord = findViewById(R.id.textViewNextWord);

        buttonChooseTranslateWord1.setOnClickListener(this);
        buttonChooseTranslateWord2.setOnClickListener(this);
        textViewNextWord.setOnClickListener(this);
        textViewBackToChooseModule.setOnClickListener(this);
        //////////////////////////////////////////////////////////////////////////////////
        // rules
        //////////////////////////////////////////////////////////////////////////////////
        answerLayout = findViewById(R.id.answerLayout);
        questionLayout = findViewById(R.id.questionLayout);
        textRule = findViewById(R.id.textRule);
        textViewNextRule = findViewById(R.id.textViewNextRule);

        textViewNextRule.setOnClickListener(this);
    }

    private void setVisabilityForWords() {
        answerLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.GONE);
        textRule.setVisibility(View.GONE);
        textViewNextRule.setVisibility(View.GONE);

        textViewWord.setVisibility(View.VISIBLE);
        textViewTranslateWord.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord1.setVisibility(View.VISIBLE);
        buttonChooseTranslateWord2.setVisibility(View.VISIBLE);
        textViewNextWord.setVisibility(View.VISIBLE);
    }

    private void getResourcesForWords() {
        switch(numOfModule) {
            case 1:
                words = getResources().getStringArray(R.array.words_module1);
                translateWords = getResources().getStringArray(R.array.translate_words_module1);
                break;
        }
    }

    private void setVisabilityForRules() {
        textViewWord.setVisibility(View.GONE);
        textViewTranslateWord.setVisibility(View.GONE);
        buttonChooseTranslateWord1.setVisibility(View.GONE);
        buttonChooseTranslateWord2.setVisibility(View.GONE);
        textViewNextWord.setVisibility(View.GONE);

        textRule.setVisibility(View.VISIBLE);
        answerLayout.setVisibility(View.VISIBLE);
        questionLayout.setVisibility(View.VISIBLE);
        textViewNextRule.setVisibility(View.VISIBLE);
    }

    private void getResourcesForRules() {
        initYellowWords(numOfModule);
        initBlueWords(numOfModule);
        initGreenWords(numOfModule);
        initWhiteWords(numOfModule, numOfRule);

        numberOfRules = yellowWords.length;
    }

    private void startInitButtonsForRules() {
        buttonsQuestion = new ArrayList<>();
        buttonsAnswer = new ArrayList<>();

        buttonYellow = new Button(this);
        buttonBlue = new Button(this);
        buttonGreen = new Button(this);

        buttonYellow.setText(yellowWords[numOfRule - 1]);
        buttonBlue.setText(blueWords[numOfRule - 1]);
        buttonGreen.setText(greenWords[numOfRule - 1]);

        buttonsQuestion.add(buttonYellow);
        buttonsQuestion.add(buttonBlue);
        buttonsQuestion.add(buttonGreen);

        initWhiteWords(numOfModule, numOfRule);

        for (int i = 0; i < whiteWords.length; ++i) {
            Button whiteButton = new Button(this);
            whiteButton.setText(whiteWords[i]);

            buttonsQuestion.add(whiteButton);
        }

        for (int i = 0; i < buttonsQuestion.size(); ++i) {
            buttonsQuestion.get(i).setTextSize(14);
            buttonsQuestion.get(i).setId(View.generateViewId());
            buttonsQuestion.get(i).setVisibility(View.VISIBLE);
            buttonsQuestion.get(i).setLayoutParams(new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT ));
            buttonsQuestion.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConstraintLayout parent = (ConstraintLayout) v.getParent();
                    Button b = (Button)v;

                    if (parent == questionLayout) {
                        buttonsQuestion.remove(b);
                        buttonsAnswer.add(b);
                    } else {
                        buttonsAnswer.remove(b);
                        buttonsQuestion.add(b);
                    }

                    answerLayout.removeAllViews();
                    questionLayout.removeAllViews();

                    constraintRuleWords(buttonsAnswer, answerLayout);
                    constraintRuleWords(buttonsQuestion, questionLayout);
                }
            });
        }
    }
}