package com.example.quanlynhansu.model;
// lớp được tạo ra để lưu thông tin phòng ban
public class Phongban {
    private  Integer maPb;
    private String tenPb;
    // sinh ra constructor ngầm định

    public Phongban() {
    }

    public Phongban(Integer maPb, String tenPb) {
        this.maPb = maPb;
        this.tenPb = tenPb;
    }

    // Constructor khởi tạo giá trị cho 2 trường
    public Integer getMaPb() {
        return maPb;
    }

    public void setMaPb(Integer maPb) {
        this.maPb = maPb;
    }

    public String getTenPb() {
        return tenPb;
    }

    public void setTenPb(String tenPb) {
        this.tenPb = tenPb;
    }
}
