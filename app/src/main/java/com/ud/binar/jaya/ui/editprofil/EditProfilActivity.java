package com.ud.binar.jaya.ui.editprofil;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ud.binar.jaya.R;
import com.ud.binar.jaya.base.BaseActivity;
import com.ud.binar.jaya.base.BaseApplication;
import com.ud.binar.jaya.data.remote.model.User;
import com.ud.binar.jaya.ui.dialog.DialogUploadOption;
import com.ud.binar.jaya.ui.dialog.DialogUploadOption.OnDialogUploadOptionClickListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;
import de.hdodenhof.circleimageview.CircleImageView;

import com.ud.binar.jaya.ui.main.MainAct;
import com.ud.binar.jaya.utils.DateFormater;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import com.bumptech.glide.Glide;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

/**
 * Created by ikun on 29/12/17.
 */

public class EditProfilActivity extends BaseActivity implements OnDateSetListener,
        OnDialogUploadOptionClickListener, PermissionCallbacks{

    private static final String TAG = "EditProfilActivity";
    public static final int REQUST_CODE_CAMERA = 1002;
    public static final int REQUST_CODE_GALLERY = 1001;
    private static final int RC_CAMERA_PERM = 205;
    public static Uri mCapturedImageURI;

    @BindString(R.string.error_field_required)
    String strErrRequired;
    @BindColor(R.color.colorBlack)
    int colorBlack;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.view_progress)
    LinearLayout viewProgress;

    @Bind(R.id.input_name)
    EditText inputName;

    @Bind(R.id.layout_input_birthday)
    TextInputLayout inputLayoutBirthday;

    @Bind(R.id.layout_input_gender)
    TextInputLayout inputLayoutGender;

    @Bind(R.id.layout_input_jabatan)
    TextInputLayout inputLayoutJabatan;

    @Bind(R.id.input_birthday)
    EditText inputBirthDay;

    @Bind(R.id.input_gender)
    EditText inputGender;

    @Bind(R.id.input_email)
    EditText inputEmail;

    @Bind(R.id.input_phone)
    EditText inputPhone;

    @Bind(R.id.input_jabatan)
    TextView inputJabatan;

    @Bind(R.id.img_avatar)
    CircleImageView imgAvatar;

    @Inject
    EditProfilPresenter presenter;

    @Inject
    User user;

    CharSequence[] charGenders;
    CharSequence[] charJabatans;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    int genderVal = 3;
    int jabatanVal = 5;

    long dateBirthDay = 0;

    byte[] imgSmall;
    Uri imgOriginal;

    boolean register = false;


   public static void startWithUser(BaseActivity activity, final User user, boolean register) {
        Intent intent = new Intent(activity, EditProfilActivity.class);
        intent.putExtra("register", register);
        BaseApplication.get(activity).createUserComponent(user);
        activity.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        if (mCapturedImageURI != null) {
            savedInstanceState.putString("mUriKey", mCapturedImageURI.toString());
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        mCapturedImageURI = Uri.parse(savedInstanceState.getString("mUriKey"));
        Log.d("Restore state", "mCapturedImageURI = " + mCapturedImageURI);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Ubah Data Profil");

        charGenders = getResources().getStringArray(R.array.list_gender);
        charJabatans = getResources().getStringArray(R.array.list_jabatan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            register = extras.getBoolean("register");
        }

        init();
    }

    @Override
    public void onBackPressed() {
        if (viewProgress.getVisibility() == View.GONE) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
      /*finish();*/
            MainAct.startWithUser(this, user);
        }

        if (id == R.id.menu_done) {
            showLoading(true);
            validate();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getUserComponent()
                .plus(new EditProfilActivityModule(this))
                .inject(this);

    }

    public void showLoading(boolean show) {
        if (show) {
            viewProgress.setVisibility(View.VISIBLE);
        } else {
            viewProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUST_CODE_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = mCapturedImageURI;
                handleCrop(uri);
            }
        }

        if (requestCode == REQUST_CODE_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                handleCrop(uri);

            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = result.getUri();
                imgOriginal = uri;

                try {
                    Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgAvatar.setImageBitmap(bitmap2);
                    encodeBitmapAndSaveToFirebase(bitmap2);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                error.printStackTrace();
            }
        }
    }



    private void init() {
        inputEmail.setText(mUser.getEmail());
        inputEmail.setEnabled(false);
        /*if (user.getUid() != null) {
            presenter.getPayment(user.getUid());
        }*/

        if (user.getFull_name() != null) {
            inputName.setText(user.getFull_name());
        }
        if (user.getBirthday() != 0) {
            initBirthDay(user.getBirthday());
        }
        if (user.getGender() != null) {
            initGender(user.getGender());
        }

        if (user.getPhone() != null) {
            inputPhone.setText(user.getPhone());
        }
        if (user.getJabatan() != null) {
            inputJabatan.setText(user.getJabatan());
        }
        if (user.getPhoto_url() != null) {
            if (!user.getPhoto_url().equals("NOT")) {
                Glide.with(this)
                        .load(user.getPhoto_url())
                        .placeholder(R.color.colorSoft)
                        .dontAnimate()
                        .into(imgAvatar);
            }
        }

        if (!register) {
            inputBirthDay.setEnabled(false);
            inputName.setEnabled(false);
            inputEmail.setEnabled(false);
            inputPhone.setEnabled(false);
        }
    }

    private void initGender(String i) {
        if (i.equalsIgnoreCase("M")) {
            genderVal = 0;
            inputGender.setText("Laki laki");
        }
        if (i.equalsIgnoreCase("F")) {
            genderVal = 1;
            inputGender.setText("Perempuan");
        }

    }

    private void initBirthDay(long timemilis) {
        dateBirthDay = timemilis;
        String date = DateFormater.getDate(dateBirthDay, "dd MMM yyyy");
        inputBirthDay.setText(date);
    }

    // Onclick list -------------//

    @OnClick(R.id.input_birthday)
    void showBirthDay() {
        showDialogDatePicker();
    }

    @OnClick(R.id.input_gender)
    void showGender() {
        showDialogGender();
    }

    @OnClick(R.id.input_jabatan)
    void showJabatan() {
        showDialogJabatan();
    }

    @OnClick(R.id.btn_upload_avatar)
    void showDialogUploadOption() {
        DialogUploadOption dialogUploadOption = new DialogUploadOption(this);
        dialogUploadOption.show();
    }

    // Onclick list -------------//

    private void showDialogDatePicker() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        dpd.setTitle("Tanggal Lahir");
        dpd.vibrate(true);
        dpd.dismissOnPause(false);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void showDialogGender() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Jenis Kelamin");
        alert.setSingleChoiceItems(charGenders, genderVal, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String whichIs = charGenders[which].toString();

                inputGender.setText(whichIs);
                genderVal = which;

                dialog.dismiss();

            }
        });
        alert.show();
    }

    private void showDialogJabatan() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Jabatan");
        alert.setSingleChoiceItems(charJabatans, jabatanVal, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String whichIs = charJabatans[which].toString();

                inputJabatan.setText(whichIs);
                jabatanVal = which;

                dialog.dismiss();

            }
        });
        alert.show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        initBirthDay(calendar.getTimeInMillis());
    }

    @Override
    public void onGalleryClicked(Dialog dialog) {
        galeryTask();
        dialog.dismiss();
    }

    @Override
    public void onCameraClicked(Dialog dialog) {
        cameraTask();
        dialog.dismiss();
    }


    private void handleCrop(Uri uri) {
        CropImage.activity(uri)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(500, 500)
                .start(this);
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        imgSmall = baos.toByteArray();

    }

    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    public void onLaunchCamera() {
        Intent intent;
        String fileName = nextSessionId();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        mCapturedImageURI = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(intent, REQUST_CODE_CAMERA);
        }
    }

    public void onLaunchGallery() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"));
        intent.setAction(Intent.ACTION_GET_CONTENT);

        intent.setType("image/*");
        startActivityForResult(intent, REQUST_CODE_GALLERY);
    }

    public void successUploadImage(String url) {
        if (url != null) {
            user.setPhoto_url(url);
        }
        presenter.updateProfile(user);
        Log.e(TAG, "successUploadImage: "+user );

    }

    public void successUpdateProfile(User user) {
        showLoading(false);
        if (register) {
            MainAct.startWithUser(this, user);

        } else {
            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
            BaseApplication.get(this).createUserComponent(user);
            finish();
        }
    }

    public void validate() {
        inputName.setError(null);
        inputEmail.setError(null);
        inputGender.setError(null);
        inputBirthDay.setError(null);
        inputPhone.setError(null);

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String gender = "";
        if (genderVal == 0) {
            gender = "M";
        }
        if (genderVal == 1) {
            gender = "F";
        }

        String jabatan = "";
        if (jabatanVal == 0) {
            jabatan = "Supervisor";
        }
        if (jabatanVal == 1) {
            jabatan = "Owner";
        }
        if (jabatanVal == 2) {
            jabatan = "Data Entry";
        }
        if (jabatanVal == 3) {
            jabatan = "Kasir";
        }
        if (jabatanVal == 4) {
            jabatan = "Pramuniaga";
        }
        String phone = inputPhone.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            inputName.setError(strErrRequired);
            focusView = inputName;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(strErrRequired);
            focusView = inputEmail;
            cancel = true;
        } else {
            if (!isValidEmail(email)) {
                inputEmail.setError("Email not valid");
                focusView = inputEmail;
                cancel = true;

            }
        }

       if (!TextUtils.isEmpty(phone)) {
            if (!isValidPhoneNumber(phone)) {
                inputPhone.setError("Phone number not valid");
                focusView = inputPhone;
                cancel = true;
            }
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            user.setFull_name(name);
            user.setEmail(email);

            if (!TextUtils.isEmpty(phone)) {
                user.setPhone(phone);
            }
            if (!TextUtils.isEmpty(jabatan)) {
                user.setJabatan(jabatan);
            }

            if (genderVal != 3) {
                user.setGender(gender);
            }
            if (jabatanVal != 5) {
                user.setJabatan(jabatan);
            }
            if (dateBirthDay != 0) {
                user.setBirthday(dateBirthDay);
            }
            if (register) {
                user.setCreatedAt(System.currentTimeMillis());
            }

            if (imgOriginal != null) {
                Log.e(TAG, "validate: "+user );
                presenter.uploadAvatar(user, imgSmall, imgOriginal);
            } else {
                presenter.updateProfile(user);
            }

        }

    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onLaunchCamera();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.ijin_camera),
                    RC_CAMERA_PERM, perms);
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void galeryTask() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onLaunchGallery();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.ijin_camera),
                    RC_CAMERA_PERM, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidPhoneNumber(CharSequence target) {
        Pattern mPattern = Pattern.compile("^08[0-9]{9,}$");
        if (target == null) {
            return false;
        } else {
            return mPattern.matcher(target).matches();
        }
    }


}
