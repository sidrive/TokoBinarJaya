package com.motor.binar.jaya.data.remote.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.io.Serializable;

/**
 * Created by ikun on 22/12/17.
 */

public class User implements Serializable {

    @NonNull
    public String uid;
    @Nullable
    public String phone;
    @Nullable
    public String email;
    @Nullable
    public String jabatan;
    @Nullable
    public String provider;
    @Nullable
    public String photo_url;
    @Nullable
    public String full_name;
    @Nullable
    public String gender;
    @Nullable
    public long birthday;
    @Nullable
    public boolean verified;

    @Nullable
    public String fullAddress;


    @Nullable
    public long createdAt;

    @Nullable
    public String userType;

    @Nullable
    public String token;

    public static User newInstance(FirebaseUser firebaseUser, UserInfo provider) {
        User user = new User(firebaseUser.getUid());
        user.setProvider(provider.getProviderId());
        // TODO : refactoring
        if (provider.getProviderId().equals("password")) {
            user.setEmail(firebaseUser.getEmail());

        } else {

        }

        return user;
    }

    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public User(@NonNull String uid, String phone, String email, String jabatan, String provider, String photo_url, String full_name, String gender, long birthday, boolean verified, String fullAddress, long createdAt, String userType, String token) {
        this.uid = uid;
        this.phone = phone;
        this.email = email;
        this.jabatan = jabatan;
        this.provider = provider;
        this.photo_url = photo_url;
        this.full_name = full_name;
        this.gender = gender;
        this.birthday = birthday;
        this.verified = verified;
        this.fullAddress = fullAddress;
        this.createdAt = createdAt;
        this.userType = userType;
        this.token = token;
    }


    @Nullable
    public String getToken() {
        return token;
    }

    public void setToken(@Nullable String token) {
        this.token = token;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(@Nullable String photo_url) {
        this.photo_url = photo_url;
    }

    @Nullable
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(@Nullable String full_name) {
        this.full_name = full_name;
    }

    @Nullable
    public String getProvider() {
        return provider;
    }

    public void setProvider(@Nullable String provider) {
        this.provider = provider;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    @Nullable
    public String getGender() {
        return gender;
    }

    public void setGender(@Nullable String gender) {
        this.gender = gender;
    }

    @Nullable
    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(@Nullable long birthday) {
        this.birthday = birthday;
    }

    @Nullable
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(@Nullable boolean verified) {
        this.verified = verified;
    }

    @Nullable
    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(@Nullable String fullAddress) {
        this.fullAddress = fullAddress;
    }

   @Nullable
    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Nullable long createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public String getUserType() {
        return userType;
    }

    public void setUserType(@Nullable String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", jabatan='" + jabatan + '\'' +
                ", provider='" + provider + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", verified=" + verified +
                ", fullAddress='" + fullAddress + '\'' +
                ", createdAt=" + createdAt +
                ", userType='" + userType + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Nullable
    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(@Nullable String jabatan) {
        this.jabatan = jabatan;
    }

}
