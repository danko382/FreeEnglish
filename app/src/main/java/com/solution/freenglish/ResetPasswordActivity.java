package com.solution.freenglish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ResetPasswordActivity extends AppCompatActivity {
    private static final String EXTRA_EMAIL = "email";

    private EditText editTextEmail;
    private Button buttonRessetPassword;

    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initView();
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observerViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextEmail.setText(email);
        buttonRessetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                viewModel.resetPassword(email);
            }
        });
    }

    private void observerViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null){
                    Toast.makeText(ResetPasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.isSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if(success){
                    Toast.makeText(ResetPasswordActivity.this, "The reset link has been successfully sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonRessetPassword = findViewById(R.id.buttonResetPassword);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}