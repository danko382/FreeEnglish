package com.solution.freenglish;

import android.content.*;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextAge;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private RegistartionViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        viewModel = new ViewModelProvider(this).get(RegistartionViewModel.class);
        observerViewModel();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = 0;
                String email = getTrimmedValue(editTextEmail);
                String trimmedValue = getTrimmedValue(editTextAge);
                String password = getTrimmedValue(editTextPassword);
                String confirmPassword = getTrimmedValue(editTextConfirmPassword);
                if (email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() && age == 0)
                {
                    Intent intent = LoginActivity.newIntent(RegistrationActivity.this);
                    startActivity(intent);
                    finish();
                }
                else{
                    if (password.equals(confirmPassword) && (age >= 18 && age <= 90) && !password.isEmpty()) {
                        viewModel.signUp(email, age, password, confirmPassword);
                    }
                    else
                    {
                        if (trimmedValue != null && !trimmedValue.equals("Age")) {
                            try {
                                age = Integer.parseInt(trimmedValue);
                            } catch (NumberFormatException e) {
                                editTextAge.setError("Uncorrected format");
                            }
                        }
                        Toast.makeText(RegistrationActivity.this, "Uncorrected", Toast.LENGTH_SHORT).show();
                        if (!(age >= 18 && age <= 90)){
                            editTextAge.setError("Your age should be from 18 to 90");
                        }
                        if (!password.equals(confirmPassword)){
                            editTextConfirmPassword.setError("Error: does not match the password");
                        }
                        if (password.isEmpty()){
                            editTextPassword.setError("Error: Error: this field is empty");
                            editTextConfirmPassword.setError("Error: Error: this field is empty");
                        }
                    }
                }
            }
        });
    }

    private void observerViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null){
                    Toast.makeText(RegistrationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    Intent intent = MainActivity.newIntent(RegistrationActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}
