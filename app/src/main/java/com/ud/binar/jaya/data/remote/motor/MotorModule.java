package com.ud.binar.jaya.data.remote.motor;

import com.ud.binar.jaya.base.annotation.UserScope;
import com.ud.binar.jaya.data.model.Barang;

import dagger.Module;
import dagger.Provides;

@Module
public class MotorModule {
    Barang barang;

    public MotorModule(Barang barang) {
        this.barang = barang;
    }

    @Provides
    @UserScope
    Barang provideMotor() {
        return barang;
    }

//
//
//    @Provides
//    @UserScope
//    Barang provideMotor(){
//        return new Barang();
//    }
}
