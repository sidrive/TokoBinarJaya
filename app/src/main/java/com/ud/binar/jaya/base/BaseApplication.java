package com.ud.binar.jaya.base;

/**
 * Created by ikun on 28/12/17.
 */

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.ud.binar.jaya.base.config.DefaultConfig;
import com.ud.binar.jaya.data.editMotor.EditMotorComponent;
import com.ud.binar.jaya.data.editMotor.EditMotorModule;
import com.ud.binar.jaya.data.main.MainComponent;
import com.ud.binar.jaya.data.main.MainModule;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.data.remote.firebase.FirebaseModule;
import com.ud.binar.jaya.data.remote.model.User;
import com.ud.binar.jaya.data.remote.motor.MotorComponent;
import com.ud.binar.jaya.data.remote.motor.MotorModule;
import com.ud.binar.jaya.data.remote.network.NetworkModule;
import com.ud.binar.jaya.data.remote.user.UserComponent;
import com.ud.binar.jaya.data.remote.user.UserModule;
import com.ud.binar.jaya.ui.editmotor.EditMotorActivity;
import com.ud.binar.jaya.ui.main.MainAct;

public class BaseApplication extends MultiDexApplication {
    private AppComponent appComponent;
    private UserComponent userComponent;
    private MainComponent mainComponent;
    private MotorComponent motorComponent;
    private DefaultConfig defaultConfig;
    private EditMotorComponent editMotorComponent;
//    private LocationComponent locationComponent;
//    private OrderDetailComponent orderDetailComponent;
//    private SkillComponent skillComponent;
//    private WalletActivityComponent walletActivityComponent;
//    private MapActivityComponent mapActivityComponent;
    private Context context;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //FirebaseApp.initializeApp(base);
        defaultConfig = new DefaultConfig(base);
        context =base;
        MultiDex.install(getBaseContext());
        //FirebaseApp.initializeApp(getBaseContext());
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .firebaseModule(new FirebaseModule())
                .networkModule(new NetworkModule(defaultConfig.getApiUrl()))
                .build();
        FirebaseApp.initializeApp(getBaseContext());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
        return userComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public MotorComponent createMotorComponent(Barang barang) {
        motorComponent = appComponent.plus(new MotorModule(barang));
        return motorComponent;
    }

    public MotorComponent getMotorComponent(){
        return motorComponent;
    }

    /*public MapActivityComponent getMapActivityComponent(){
        return mapActivityComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }*/

    public MainComponent createMainComponent(MainAct activity) {
        mainComponent = userComponent.plus(new MainModule(activity));
        return mainComponent;
    }



    public EditMotorComponent createEditMotorComponent(EditMotorActivity activity){
        editMotorComponent = userComponent.plus(new EditMotorModule(activity));
        return editMotorComponent;
    }

/*    public InputmotorComponent getMainComponent() {
        return mainComponent;
    }

    public void releaseMainComponent() {
        mainComponent = null;
    }

    public LocationComponent createLocationComponent(Location location){
        locationComponent = userComponent.plus(new LocationModule(location));
        return locationComponent;
    }

    public LocationComponent getLocationComponent(){
        return locationComponent;
    }

    public void releaseLocationComponent(){
        locationComponent = null;
    }


    public OrderDetailComponent createOrderDetailComponent(Order order){
        orderDetailComponent = mainComponent.plus(new OrderDetailModule((order)));
        return orderDetailComponent;
    }


    public OrderDetailComponent getOrderDetailComponent(){
        return orderDetailComponent;
    }

    public void releaseOrderDetailComponent(){
        orderDetailComponent = null;
    }

    public SkillComponent createSkillComponent(Skill skill){
        skillComponent = userComponent.plus(new SkillModule(skill));
        return skillComponent;
    }

    public SkillComponent getSkillComponent(){
        return skillComponent;
    }

    public void releaseSkillComponent(){
        skillComponent = null;
    }*/

}
