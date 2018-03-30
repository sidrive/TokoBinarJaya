package com.motor.binar.jaya.ui.editprofil;

import com.motor.binar.jaya.base.annotation.ActivityScope;
import com.motor.binar.jaya.data.remote.FirebaseImageService;
import com.motor.binar.jaya.data.remote.FirebaseUserService;
import com.motor.binar.jaya.data.remote.UserService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 29/12/17.
 */

@Module
public class EditProfilActivityModule {
    EditProfilActivity activity;

    public EditProfilActivityModule(EditProfilActivity activity){
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    EditProfilActivity provideEditProfilActivity(){return activity;}

    @ActivityScope
    @Provides
    EditProfilPresenter provideEditProfilPresenter(UserService userService, FirebaseUserService firebaseUserService, FirebaseImageService firebaseImageService){
        return new EditProfilPresenter(activity, userService, firebaseUserService, firebaseImageService);
    }
}
