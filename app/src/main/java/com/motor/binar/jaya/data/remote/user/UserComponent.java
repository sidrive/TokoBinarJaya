package com.motor.binar.jaya.data.remote.user;

import com.motor.binar.jaya.base.annotation.UserScope;
import com.motor.binar.jaya.data.editMotor.EditMotorComponent;
import com.motor.binar.jaya.data.editMotor.EditMotorModule;
import com.motor.binar.jaya.data.inputMotor.InputmotorComponent;
import com.motor.binar.jaya.data.inputMotor.InputmotorModule;
import com.motor.binar.jaya.data.main.MainComponent;
import com.motor.binar.jaya.data.main.MainModule;
import com.motor.binar.jaya.ui.editmotor.EditMotorActivityComponent;
import com.motor.binar.jaya.ui.editmotor.EditMotorActivityModule;
import com.motor.binar.jaya.ui.inputMotor.InputMotorComponent;
import com.motor.binar.jaya.ui.inputMotor.InputMotorModule;
import com.motor.binar.jaya.ui.editprofil.EditProfilActivityComponent;
import com.motor.binar.jaya.ui.editprofil.EditProfilActivityModule;
import com.motor.binar.jaya.ui.main.MainActivityComponent;
import com.motor.binar.jaya.ui.main.MainActivityModule;

import dagger.Subcomponent;

/**
 * Created by ikun on 28/12/17.
 */

@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {

//    IntroActivityComponent plus(IntroActivityModule activityModule);
//
    MainActivityComponent plus(MainActivityModule activityModule);
    InputMotorComponent plus(InputMotorModule activityModule);
    EditMotorActivityComponent plus(EditMotorActivityModule activityModule);
//
//    InputmotorComponent plus(InputmotorModule mainModule);
//
    EditProfilActivityComponent plus(EditProfilActivityModule activityModule);

    MainComponent plus(MainModule mainModule);

    InputmotorComponent plus(InputmotorModule inputMotorModule);

    EditMotorComponent plus(EditMotorModule editMotorModule);
//
//    SettingActivityComponent plus(SettingActivityModule activityModule);
//
//    BriefActivityComponent plus(BriefActivityModule activityModule);
//
//    SkillActivityComponent plus(SkillActivityModule activityModule);
//
//    PrestasiActivityComponent plus(PrestasiActivityModule activityModule);
//
//    PengalamanActivityComponent plus(PengalamanActivityModule activityModule);
//
//    SkillComponent plus(SkillModule module);
//
//    LocationComponent plus(LocationModule locationModule);
//
//    AddLocationActivityComponent plus(AddLocationActivityModule activityModule);
//
//    PaymentDetailActivityComponent plus(PaymentDetailActivityModule activityModule);
//
//    VerificationActivityComponent plus(VerificationActivityModule activityModule);
//
//    ReviewsActivityComponent plus(ReviewsActivityModule activityModule);
//
//    MapActivityComponent plus(MapActivityModule mapActivityModule);
//    WalletActivityComponent plus (WalletActivityModule walletActivityModule);
}
