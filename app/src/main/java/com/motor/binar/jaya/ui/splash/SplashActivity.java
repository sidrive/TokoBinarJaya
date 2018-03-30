package com.motor.binar.jaya.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.motor.binar.jaya.R;
import com.motor.binar.jaya.base.BaseActivity;
import com.motor.binar.jaya.base.BaseApplication;
import com.motor.binar.jaya.data.remote.model.User;
import com.motor.binar.jaya.ui.editprofil.EditProfilActivity;
import com.motor.binar.jaya.ui.login.LoginActivity;
import com.motor.binar.jaya.ui.main.MainAct;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by ikun on 28/12/17.
 */

public class SplashActivity extends BaseActivity {
    @Inject
    SplashPresenter presenter;

    @Bind(R.id.imageView3)
    ImageView imageView;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*overridePendingTransition(R.anim.fadein,R.anim.fadeout);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    public void showLoginActivity() {
        Intent a=new Intent(this,LoginActivity.class);
        startActivity(a);
        finish();

    }

    public void showMainActivity(User user){
            MainAct.startWithUser(this, user);
            finish();
    }

    public void showRegisterActivity(User user){
        EditProfilActivity.startWithUser(this, user, true);
        finish();
    }

    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);
    }


}
