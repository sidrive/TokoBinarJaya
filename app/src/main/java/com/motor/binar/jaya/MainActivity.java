package com.motor.binar.jaya;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.motor.binar.jaya.base.BaseApplication;
import com.motor.binar.jaya.data.remote.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void startWithUser(Activity activity, final User user) {
        Intent intent = new Intent(activity, MainActivity.class);

        BaseApplication.get(activity).createUserComponent(user);
        activity.startActivity(intent);
    }


}
