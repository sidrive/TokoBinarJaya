package com.motor.binar.jaya.ui.editmotor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.motor.binar.jaya.R;
import com.motor.binar.jaya.base.BaseActivity;
import com.motor.binar.jaya.base.BaseApplication;
import com.motor.binar.jaya.data.model.Motor;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class EditMotorActivity extends BaseActivity {

    @Inject
    EditMotorPresenter presenter;

    @Inject
    Motor motor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_motor);
        ButterKnife.bind(this);


    }



    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getMotorComponent()
                .plus(new EditMotorActivityModule(this))
                .inject(this);
//        BaseApplication.get(this).createEditMotorComponent(this);
    }


    public static void startWithMotor(Activity activity, final Motor motor) {
        Intent intent = new Intent(activity, EditMotorActivity.class);

        BaseApplication.get(activity).createMotorComponent(motor);
        activity.startActivity(intent);
    }
}
