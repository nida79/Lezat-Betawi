package com.ta.betawifood.models;

public class ResepModel {

    private String judul;
    private String gambar;
    private String deskripsi;
    private String videoID;

    public ResepModel(){}

    public ResepModel(String judul, String gambar, String deskripsi, String videoID) {
        this.judul = judul;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.videoID = videoID;
    }

    public String getJudul() {
        return judul;
    }

    public String getGambar() {
        return gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getVideoID() {
        return videoID;
    }
}
