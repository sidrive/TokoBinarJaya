package com.ud.binar.jaya.data.remote;

import android.app.Application;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by ikun on 29/12/17.
 */

public class FirebaseImageService {

    private Application application;

    private FirebaseStorage firebaseStorage;

    private StorageReference storageReference;

    public FirebaseImageService(Application application){
        this.application = application;
        this.firebaseStorage = FirebaseStorage.getInstance();
        this.storageReference = firebaseStorage.getReference();
    }

    public StorageReference getUserImageRefOriginal(String uid){
        StorageReference avatarRef = storageReference.child("users/"+uid+"/profile.jpg");
        return avatarRef;
    }

    public StorageReference getImageRefThumb(String uid){
        StorageReference avatarRefThumb = storageReference.child("users/"+uid+"/thumb_profile.jpg");
        return avatarRefThumb;
    }

    public StorageReference getUserProofKtp(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/ktp.jpg");
        return imgRef;
    }

    public StorageReference getUserProofKtpThumb(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/thumb_ktp.jpg");
        return imgRef;
    }

    public StorageReference getUserProofSertifikat(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/sertifikat.jpg");
        return imgRef;
    }

    public StorageReference getUserProofSertifikatThumb(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/thumb_sertifikat.jpg");
        return imgRef;
    }


    public StorageReference getUserProofIjazah(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/ijazah.jpg");
        return imgRef;
    }

    public StorageReference getUserProofIjazahThumb(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/thumb_ijazah.jpg");
        return imgRef;
    }

    public StorageReference getUserProofKTM(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/ktm.jpg");
        return imgRef;
    }

    public StorageReference getUserProofKTMThumb(String uid){
        StorageReference imgRef = storageReference.child("users/"+uid+"/thumb_ktm.jpg");
        return imgRef;
    }


}
