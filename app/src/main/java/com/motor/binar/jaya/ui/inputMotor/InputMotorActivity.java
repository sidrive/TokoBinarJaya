package com.motor.binar.jaya.ui.inputMotor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.motor.binar.jaya.R;
import com.motor.binar.jaya.base.BaseActivity;
import com.motor.binar.jaya.base.BaseApplication;
import com.motor.binar.jaya.data.model.Category;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.model.User;
import com.motor.binar.jaya.ui.main.MainAct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ikun on 11/01/18.
 */

public class InputMotorActivity extends BaseActivity {

    @Bind(R.id.txtMerk)
    TextView txtmerk;

    @Bind(R.id.txtType)
    TextView txttype;

    @Bind(R.id.txtSeri)
    TextView txtseri;

    @Bind(R.id.txtnama_user)
    TextView txtnama;

    @Bind(R.id.imglogo)
    ImageView imglogo;

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;

    @Bind(R.id.txt_plat)
    TextView txtplat;



    @Bind(R.id.txt_norangka)
    TextView norangka;

    @Bind(R.id.btn_pajak)
    Button btnpajak;

    @Bind(R.id.btn_tahunmtr)
    Button btnthnmtr;

    @Inject
    Motor motor;

    @Inject
    InputMotorPresenter presenter;

    @Inject
    User user;

    CharSequence[] merk;
    CharSequence[] type;
    CharSequence[] seri;

    List<Category> listMerk;
    List<Category> listType;
    List<Category> listSeri;

    int merkVal;
    int typeVal;
    int seriVal;

    String merkID;
    String typeID;
    String seriID;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_motor);
        ButterKnife.bind(this);
        txtnama.setText(String.valueOf(user.getFull_name()));
        initProfilePhoto();

        myCalendar = Calendar.getInstance();
        btnpajak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InputMotorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd MMMM";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        btnpajak.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }




    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getUserComponent()
                .plus(new InputMotorModule(this))
                .inject(this);
        BaseApplication.get(this).createInputMotorComponent(this);
    }

    public static void startWithUser(Activity activity, final User user) {
        Intent intent = new Intent(activity, InputMotorActivity.class);

        BaseApplication.get(activity).createUserComponent(user);
        activity.startActivity(intent);
    }

    @OnClick(R.id.ln_merk)
    void showCategoryMerk(){
        showMerk();
    }

    @OnClick(R.id.ln_type)
    void showCategoryType(){
        showType();
    }

    @OnClick(R.id.ln_seri)
    void showCategoryVarian(){
        showSeri();
    }

    private void showMerk(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);
        alert.setTitle("Pilih Merk Motor");
        alert.setSingleChoiceItems(merk, merkVal, (dialog, which) -> {
            handleSelectCategoryMerk(which);
            changeLogo();
            dialog.dismiss();
        });
        alert.setIcon(R.drawable.ic_2000px_motorbike_svg);
        alert.show();

    }

    private void changeLogo(){
        if((txtmerk.getText().toString()).equalsIgnoreCase("HONDA")){
            imglogo.setImageResource(R.drawable.ic_logo_honda);
        }if((txtmerk.getText().toString()).equalsIgnoreCase("YAMAHA")){
            imglogo.setImageResource(R.drawable.ic_logo_yamaha);
        }if((txtmerk.getText().toString()).equalsIgnoreCase("SUZUKI")){
            imglogo.setImageResource(R.drawable.ic_logo_suzuki);
        }if((txtmerk.getText().toString()).equalsIgnoreCase("KAWASAKI")){
            imglogo.setImageResource(R.drawable.ic_logo_kawasaki);
        }
    }

    private void handleSelectCategoryMerk(int pos){
        merkVal = pos;
        String merks = merk[pos].toString();
        txtmerk.setText(merks);
        presenter.getSubCategories(listMerk.get(pos).getId());
    }

    private void showType() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);
        alert.setTitle("Pilih Type Motor");
        alert.setSingleChoiceItems(type, typeVal, (dialog, which) -> {
            handleSelectSubCategoryType(which);
            dialog.dismiss();

        });
        alert.setIcon(R.drawable.ic_2000px_motorbike_svg);
        alert.show();
    }

    private void handleSelectSubCategoryType(int pos){
        typeVal = pos;
        String types = type[pos].toString();
        txttype.setText(types);

        merkID = listType.get(pos).getId();
        presenter.getLevels(listType.get(pos).getSeri());
    }

    private void showSeri() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);
        alert.setTitle("Pilih Seri");
        alert.setSingleChoiceItems(seri, seriVal, (dialog, which) -> {
            handleSelectLevelSeri(which);
            dialog.dismiss();

        });
        alert.setIcon(R.drawable.ic_2000px_motorbike_svg);
        alert.show();
    }

    private void handleSelectLevelSeri(int pos){
        seriVal = pos;
        String level = seri[pos].toString();
        txtseri.setText(level);
        seriID = listSeri.get(pos).getId();
    }

    private void init(String id){
        for (int i=0;i<listMerk.size();i++){
            String catId = listMerk.get(i).getId();
            if (id.equals(catId)){
                handleSelectCategoryMerk(i);
            }
        }
    }

    public void initMerk(List<Category> listMerk){
        this.listMerk = listMerk;
        merk = new CharSequence[listMerk.size()];
        for (int i=0;i<listMerk.size();i++){
            merk[i] = listMerk.get(i).getName();
        }

        if (seriID != null){
            init(seriID);
        }

    }


    public void initType(List<Category> listType){
        this.listType = listType;
        type = new CharSequence[listType.size()];
        for (int i=0;i<listType.size();i++){
            type[i] = listType.get(i).getName();
        }

    }

    public void initSeri(List<Category> listSeri){
        this.listSeri = listSeri;
        seri = new CharSequence[listSeri.size()];
        for (int i=0;i<listSeri.size();i++){
            seri[i] = listSeri.get(i).getName();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    public void initProfilePhoto(){
        if (user.getPhoto_url() != null) {
            if (!user.getPhoto_url().equalsIgnoreCase("NOT")){
                Glide.with(this)
                        .load(user.getPhoto_url()).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.e("IMAGE_EXCEPTION", "Exception " + e.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d("smtime img's not loaded",  "n dis tex's not di");
                        return false;
                    }
                });
                        /*.placeholder(R.color.colorSoft)
                        .dontAnimate()
                        .into(imgAvatar);*/

                Glide.with(this)
                        .load(user.getPhoto_url())
                        .placeholder(R.color.colorSoft)
                        .dontAnimate()
                        .into(imgAvatar);
            }
        }
    }

    @OnClick(R.id.btn_simpan)
    void saveMotor(){
        validate();
    }


    public void validate(){

        motor.setUserid(user.getUid());
        motor.setIdmotor(String.valueOf(txtmerk.getText())+String.valueOf(txtplat.getText()).toUpperCase());
        motor.setMerk(String.valueOf(txtmerk.getText()));
        motor.setType(String.valueOf(txttype.getText()));
        motor.setSeri(String.valueOf(txtseri.getText()));
        motor.setPlat(String.valueOf(txtplat.getText()).toUpperCase());
        motor.setTahun_buat(String.valueOf(btnthnmtr.getText()));
        motor.setNo_rangka(String.valueOf(norangka.getText()));
        motor.setTahun_pajak(String.valueOf(btnpajak.getText()));
        presenter.savemotor(motor);

    }

    public void succesSaveMotor() {
        showLoading(false);
        String title = "Motor disimpan";
        String desc = "Kami sedang melakukan update data motor";
        int icon = R.drawable.ic_alarm_on;
        showAlertDialog(title, desc, icon);
    }

    void showLoading(boolean b) {
    }

    private void showAlertDialog(String title, String desc, int icon) {
        final Intent intent = new Intent(this, MainAct.class);
        intent.putExtra("motor", "motor");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(desc)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    // continue with delete
                    dialog.dismiss();
                    startActivity(intent);

                })
                .setIcon(icon)
                .show();
    }


    @OnClick(R.id.btn_tahunmtr)
    void showYear(){
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.year_picker_dialog, null);
        d.setTitle("Pilih Tahun Pembuatan Motor Sesuai BPKB");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        myCalendar = Calendar.getInstance();
        int yearNow = myCalendar.get(Calendar.YEAR);
        Log.e("InputMotorActivity", "showYear: " + myCalendar.get(Calendar.YEAR));
        numberPicker.setMaxValue(yearNow);
        numberPicker.setMinValue(2004);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                Log.d(TAG, "onValueChange: ");
            }
        });
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("activity", "onClick: " + numberPicker.getValue());
                btnthnmtr.setText(String.valueOf(numberPicker.getValue()));
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();
    }
}
