package com.ud.binar.jaya.base;

import com.ud.binar.jaya.data.remote.firebase.FirebaseModule;
import com.ud.binar.jaya.data.remote.motor.MotorComponent;
import com.ud.binar.jaya.data.remote.motor.MotorModule;
import com.ud.binar.jaya.data.remote.network.NetworkModule;
import com.ud.binar.jaya.data.remote.user.UserComponent;
import com.ud.binar.jaya.data.remote.user.UserModule;
import com.ud.binar.jaya.ui.login.LoginActivityComponent;
import com.ud.binar.jaya.ui.login.LoginActivityModule;
import com.ud.binar.jaya.ui.splash.SplashActivityComponent;
import com.ud.binar.jaya.ui.splash.SplashActivityModule;

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
