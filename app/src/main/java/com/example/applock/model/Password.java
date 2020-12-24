package com.example.applock.model;

import android.content.Context;

import io.paperdb.Paper;

public class Password {

    private String password_key = "Password_key";
    public String status_first_step = "Draw an unlock pattern";
    public String status_next_step = "Draw pattern again to confirm";
    public String status_password_incorrect = "Try Again";
    public String status_password_correct = "Pattern set";
    public String failed = "Connect at least 4 dots";

    private boolean isFirstStep = true;

    public Password(Context ctx){                    //constructor
        Paper.init(ctx);                              //initalising paper database


    }

    public void setPassword(String pwd){
        Paper.book().write(password_key , pwd);
    }
    public String getPassword(){
       return Paper.book().read(password_key);
    }
    public boolean isFirstStep(){
        return  isFirstStep;
    }
    public void setFirstStep(boolean firstStep){
        isFirstStep = firstStep;
    }
    public boolean isCorrect(String pwd){
        return pwd.equals(getPassword());
    }




}
