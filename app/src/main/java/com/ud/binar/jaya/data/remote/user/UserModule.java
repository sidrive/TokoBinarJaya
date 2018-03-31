package com.ud.binar.jaya.data.remote.user;

import com.ud.binar.jaya.base.annotation.UserScope;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.data.remote.CategoryService;
import com.ud.binar.jaya.data.remote.model.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 28/12/17.
 */


@Module
public class UserModule {

    User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }

    @Provides
    @UserScope
    CategoryService provideCategoryService(){
        return new CategoryService();
    }

   @Provides
    @UserScope
   Barang provideMotor(){
        return new Barang();
    }

/*    @UserScope
    @Provides
    LocationService locationService(){
        return new LocationService();
    }*/

}
