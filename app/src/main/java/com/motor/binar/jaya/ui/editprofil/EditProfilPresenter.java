package com.motor.binar.jaya.ui.editprofil;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.motor.binar.jaya.base.BasePresenter;
import com.motor.binar.jaya.data.model.Category;
import com.motor.binar.jaya.data.remote.CategoryService;
import com.motor.binar.jaya.data.remote.FirebaseImageService;
import com.motor.binar.jaya.data.remote.FirebaseUserService;
import com.motor.binar.jaya.data.remote.UserService;
import com.motor.binar.jaya.data.remote.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ikun on 29/12/17.
 */

public class EditProfilPresenter implements BasePresenter {

    EditProfilActivity activity;
    UserService userService;
    FirebaseImageService firebaseImageService;
    FirebaseUserService firebaseUserService;
    CategoryService categoryService;

    public EditProfilPresenter(EditProfilActivity activity, UserService userService, FirebaseUserService firebaseUserService, FirebaseImageService firebaseImageService, CategoryService categoryService){
        this.activity = activity;
        this.userService = userService;
        this.firebaseImageService = firebaseImageService;
        this.firebaseUserService = firebaseUserService;
        this.categoryService = categoryService;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void updateProfile(final User user){
        userService.updateUser(user).addOnCompleteListener(
                task -> activity.successUpdateProfile(user));
        Log.e(TAG, "updateProfile: "+user );
    }

//    public void getPayment(String uid){
//        userService.getUserPayment(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                PartnerPayment partnerPayment = dataSnapshot.getValue(PartnerPayment.class);
//                if (partnerPayment != null) activity.initPayment(partnerPayment);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
//
//    public void updatePayment(PartnerPayment partnerPayment){
//        userService.updateUserPayment(partnerPayment);
//    }

    public void uploadAvatar(final User user, byte[] data, final Uri uri){
        StorageReference avatarPartnerRef = firebaseImageService.getUserImageRefOriginal(user.getUid());
        Log.e(TAG, "uploadAvatar: "+user );
        UploadTask uploadTask = avatarPartnerRef.putFile(uri);
// Register observers to listen for when the download is done or if it fails


        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
            System.out.print(exception);
        }).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            Uri downloadUrl = taskSnapshot.getDownloadUrl();
            activity.successUploadImage(downloadUrl.toString());

        });
    }




}
