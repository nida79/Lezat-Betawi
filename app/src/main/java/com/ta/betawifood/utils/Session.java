package com.ta.betawifood.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";

    public static final String SP_NAMA_KARYAWAN = "spNamaKaryawan";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_NIK ="spNik";
    public static final String SP_UID ="spUid";
    public static final String SP_LEVEL ="spLevel";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public static final String SP_TEMA ="spTema";
    public static final String SP_PEMATER ="spPemateri";
    public static final String SP_STATUS ="spStatus";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Session(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPUid(){
        return sp.getString(SP_UID, "");
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpNamaKaryawan(){
        return sp.getString(SP_NAMA_KARYAWAN, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpNik(){
        return sp.getString(SP_NIK,"");}

    public String getSpTema(){
        return sp.getString(SP_TEMA,"");}

    public String getSpPemater(){
        return sp.getString(SP_PEMATER,"");}
    public String getSpStatus(){
        return sp.getString(SP_STATUS,"");}



    public String getSpLevel(){
        return sp.getString(SP_LEVEL,"");}


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
