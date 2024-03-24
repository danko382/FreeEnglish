package com.solution.freenglish;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class ResetPasswordViewModel extends ViewModel {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public MutableLiveData<String> getError(){
        return error;
    }
    public MutableLiveData<Boolean> isSuccess(){
        return success;
    }
    public void resetPassword(String email){
        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                error.setValue(e.getMessage());
            }
        });
    }
}
