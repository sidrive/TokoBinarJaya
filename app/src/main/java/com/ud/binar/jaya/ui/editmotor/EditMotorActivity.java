package com.ud.binar.jaya.ui.editmotor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ud.binar.jaya.R;
import com.ud.binar.jaya.base.BaseActivity;
import com.ud.binar.jaya.base.BaseApplication;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.ui.main.MainAct;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditMotorActivity extends BaseActivity {

    @Bind(R.id.txt_namabrg)
    EditText namaBrg;

    @Bind(R.id.txt_barcode)
    EditText barcode;

    @Bind(R.id.txt_jumlah)
    EditText jmlBrg;

    @Bind(R.id.input_kategori)
    TextView kategoriBrg;

    @Inject
    EditMotorPresenter presenter;

    @Inject
    Barang barang;

    CharSequence[] charKategoris;

    int kategoriVal = 4;

    @BindString(R.string.error_field_required)
    String strErrRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_motor);
        ButterKnife.bind(this);

        charKategoris = getResources().getStringArray(R.array.list_kategori);
        Log.e("editmotor", "onCreate: "+ barang);
    }



    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getMotorComponent()
                .plus(new EditMotorActivityModule(this))
                .inject(this);
//        BaseApplication.get(this).createEditMotorComponent(this);
    }


    public static void startWithMotor(Activity activity, final Barang barang) {
        Intent intent = new Intent(activity, EditMotorActivity.class);

        BaseApplication.get(activity).createMotorComponent(barang);
        activity.startActivity(intent);
    }

    @OnClick(R.id.input_kategori)
    void showKategori() {
        showDialogKategori();
    }

    @OnClick(R.id.btn_simpan)
    void validateBrg() {
        Log.e("btnSimpan", "validateBrg: " );
        validate();
    }

    private void showDialogKategori() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Jabatan");
        alert.setSingleChoiceItems(charKategoris, kategoriVal, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String whichIs = charKategoris[which].toString();

                kategoriBrg.setText(whichIs);
                kategoriVal = which;

                dialog.dismiss();

            }
        });
        alert.show();
    }

    private void validate() {
        namaBrg.setError(null);
        barcode.setError(null);
        jmlBrg.setError(null);
        kategoriBrg.setError(null);

        String nama = namaBrg.getText().toString();
        String barcodeid = barcode.getText().toString();
        String kategoriBarang = "";
        if (kategoriVal == 0){
            kategoriBarang = "Food";
        }
        if (kategoriVal == 1){
            kategoriBarang = "Non Food";
        }
        if (kategoriVal == 2){
            kategoriBarang = "Fashion";
        }
        if (kategoriVal == 3){
            kategoriBarang = "Alat Ruamh Tangga";
        }

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(nama)) {
            namaBrg.setError(strErrRequired);
            focusView = namaBrg;
            cancel = true;
        }
        if (TextUtils.isEmpty(barcodeid)) {
            barcode.setError(strErrRequired);
            focusView = namaBrg;
            cancel = true;
        }
        if (jmlBrg.getText() == null){
            jmlBrg.setError(strErrRequired);
            focusView = jmlBrg;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else{

            barang.setNama_barang(nama);
            barang.setBarcode(barcodeid);
            barang.setJumlah_barang(Integer.valueOf(jmlBrg.getText().toString()));
            if (kategoriVal != 5) {
                barang.setKategori(kategoriBarang);
            }
            else {
                presenter.saveBarangs(barang);
            }
        }
    }

    public void succesSaveMotor() {
        showLoading(false);
        String title = "Barang disimpan";
        String desc = "Kami sedang melakukan update data ud";
        int icon = R.drawable.ic_alarm_on;
        showAlertDialog(title, desc, icon);
    }

    void showLoading(boolean b) {
    }

    private void showAlertDialog(String title, String desc, int icon) {
        final Intent intent = new Intent(this, MainAct.class);
        intent.putExtra("ud", "ud");
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
}
