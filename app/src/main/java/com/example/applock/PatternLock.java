package com.example.applock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.applock.model.Password;
import com.shuhart.stepview.StepView;

import java.util.List;

public class PatternLock extends AppCompatActivity {

    StepView stepView;
    LinearLayout normalLayout;
    TextView statusPassword;
    RelativeLayout relativeLayout;

    Password utilsPassword;
    String userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);


        stepView = findViewById(R.id.step_view);
        normalLayout = findViewById(R.id.normal_layout);
        relativeLayout = findViewById(R.id.root);
        statusPassword = findViewById(R.id.status_password);

        utilsPassword = new Password(this);          //constructor object
        statusPassword.setText(utilsPassword.status_first_step);



        if(utilsPassword.getPassword() == null){
//            normalLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0,false);

        }else{
            normalLayout.setVisibility(View.VISIBLE);
//            stepView.setVisibility(View.GONE);

        }

        initPatternListener();






    }
    private void initPatternListener(){

        final PatternLockView patternLockView = findViewById(R.id.pattern_view);

        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List progressPattern) {

            }

            @Override
            public void onComplete(List pattern) {
                String pwd = PatternLockUtils.patternToString(patternLockView, pattern);

                if(pwd.length() < 4){
                    statusPassword.setText(utilsPassword.failed);
                    patternLockView.clearPattern();
                    return;
                }
                if(utilsPassword.getPassword() == null){
                    if(utilsPassword.isFirstStep()){
                        userPassword = pwd;
                        utilsPassword.setFirstStep(false);
                        statusPassword.setText(utilsPassword.status_next_step);
                        stepView.go(1,false);
                    }else{

                        if(userPassword.equals(pwd)){
                            utilsPassword.setPassword(userPassword);
                            statusPassword.setText(utilsPassword.status_password_correct);
                            stepView.done(true);
                            startAct();


                        }else{
                            statusPassword.setText(utilsPassword.status_password_incorrect);
                        }

                    }
                }else{
                    if(utilsPassword.isCorrect(pwd)){
                        statusPassword.setText(utilsPassword.status_password_correct);
                        startAct();
                    }else{
                        statusPassword.setText(utilsPassword.status_password_incorrect);
                    }
                }
                patternLockView.clearPattern();


            }

            @Override
            public void onCleared() {

            }
        });



    }

    private void startAct() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed(){
        if(utilsPassword.getPassword() == null && !utilsPassword.isFirstStep()){
            stepView.go(0,false);
            utilsPassword.setFirstStep(true);
            statusPassword.setText(utilsPassword.status_first_step);

        }else{
            finish();
            super.onBackPressed();
        }

    }
}
