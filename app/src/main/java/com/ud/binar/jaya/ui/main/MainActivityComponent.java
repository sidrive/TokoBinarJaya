package com.ud.binar.jaya.ui.main;

import com.ud.binar.jaya.base.annotation.ActivityScope;


import dagger.Subcomponent;

/**
 * Created by ikun on 02/01/18.
 */

@ActivityScope
@Subcomponent(
        modules = {
                MainActivityModule.class
        }
)
public interface MainActivityComponent {
    MainAct inject(MainAct activity);
}
