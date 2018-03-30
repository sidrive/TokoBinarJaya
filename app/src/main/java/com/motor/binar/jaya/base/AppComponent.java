package com.motor.binar.jaya.base;

import com.motor.binar.jaya.data.remote.firebase.FirebaseModule;
import com.motor.binar.jaya.data.remote.motor.MotorComponent;
import com.motor.binar.jaya.data.remote.motor.MotorModule;
import com.motor.binar.jaya.data.remote.network.NetworkModule;
import com.motor.binar.jaya.data.remote.user.UserComponent;
import com.motor.binar.jaya.data.remote.user.UserModule;
import com.motor.binar.jaya.ui.login.LoginActivityComponent;
import com.motor.binar.jaya.ui.login.LoginActivityModule;
import com.motor.binar.jaya.ui.splash.SplashActivityComponent;
import com.motor.binar.jaya.ui.splash.SplashActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by ikun on 28/12/17.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                FirebaseModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {

        SplashActivityComponent plus(SplashActivityModule activityModule);

        LoginActivityComponent plus(LoginActivityModule activityModule);

//        MainActivityComponent plus(MainActivityModule activityModule);

        UserComponent plus(UserModule userModule);
        MotorComponent plus(MotorModule motorModule);

        Retrofit retrofit();
}
