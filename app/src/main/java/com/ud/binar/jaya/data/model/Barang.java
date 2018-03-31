package com.ud.binar.jaya.data.model;

import android.support.annotation.NonNull;

/**
 * Created by ikun on 17/01/18.
 */

public class Barang {

    @NonNull
    private String idbarang;

    @NonNull
    private String userid;

    @NonNull
    private String nama_barang;

    @NonNull
    private String barcode;

    @NonNull
    private int jumlah_barang;

    @NonNull
    private String kategori;



    public Barang(){}



    public Barang(@NonNull String idbarang, @NonNull String userid, @NonNull String nama_barang, @NonNull String barcode, @NonNull int jumlah_barang, @NonNull String kategori) {
        this.idbarang = idbarang;
        this.userid = userid;
        this.nama_barang = nama_barang;
        this.barcode = barcode;
        this.jumlah_barang = jumlah_barang;
        this.kategori = kategori;
    }

    @NonNull
    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(@NonNull String idbarang) {
        this.idbarang = idbarang;
    }

    @NonNull
    public String getUserid() {
        return userid;
    }

    public void setUserid(@NonNull String userid) {
        this.userid = userid;
    }

    @NonNull
    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(@NonNull String nama_barang) {
        this.nama_barang = nama_barang;
    }

    @NonNull
    public int getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(@NonNull int jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    @NonNull
    public String getKategori() {
        return kategori;
    }

    public void setKategori(@NonNull String kategori) {
        this.kategori = kategori;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "idbarang='" + idbarang + '\'' +
                ", userid='" + userid + '\'' +
                ", nama_barang='" + nama_barang + '\'' +
                ", barcode='" + barcode + '\'' +
                ", jumlah_barang=" + jumlah_barang +
                ", kategori='" + kategori + '\'' +
                '}';
    }
}
