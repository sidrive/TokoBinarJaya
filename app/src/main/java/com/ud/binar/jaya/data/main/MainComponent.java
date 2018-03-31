package com.ud.binar.jaya.data.main;

import com.ud.binar.jaya.base.annotation.MainScope;

import dagger.Subcomponent;

/**
 * Created by ikun on 05/01/18.
 */
@MainScope
@Subcomponent(
        modules = {
                MainModule.class
        }
)

public interface MainComponent {
}
