package com.solution.freenglish;

import android.content.Context;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_NUM_OF_MODULE = "numOfModule";
    private MainActivityViewModel viewModel;
    TextView textViewModule1, textViewModule2, textViewModule3, textViewModule4, textViewModule5;
    Intent intent;
    int numOfModule, firstModule, secondModule, thirdModule;
    String userName;

    private TextView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        observViewModel();
        setLogoutButton();
    }

    public static Intent newIntent(
            Context context
    ) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private void observViewModel(){
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser == null){
                    Intent intent = LoginActivity.newIntent(MainActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    public void setLogoutButton(){
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.logout();
            }
        });
    }

    private void init() {
        textViewModule1 = findViewById(R.id.textViewModule1);
        textViewModule2 = findViewById(R.id.textViewModule2);
        textViewModule3 = findViewById(R.id.textViewModule3);
        textViewModule4 = findViewById(R.id.textViewModule4);
        textViewModule5 = findViewById(R.id.textViewModule5);
        logoutButton = findViewById(R.id.logoutButton);
        textViewModule1.setOnClickListener(this);
        textViewModule2.setOnClickListener(this);
        textViewModule3.setOnClickListener(this);
        textViewModule4.setOnClickListener(this);
        textViewModule5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == textViewModule1) {
            numOfModule = 1;
        } else if (v == textViewModule2) {
            numOfModule = 2;
        } else if (v == textViewModule3) {
            numOfModule = 3;
        } else if (v == textViewModule4) {
            numOfModule = 4;
        } else if (v == textViewModule5) {
            numOfModule = 5;
        }

        intent = ChooseSectionInModuleActivity.newIntent(
                this,
                numOfModule
        );
        startActivity(intent);
    }
}