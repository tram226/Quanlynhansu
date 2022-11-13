package com.example.quanlynhansu.model;

import java.util.Date;

// lớp được tạo ra để lưu thông tin nhân viên
public class Nhanvien   {
    private String maNv;
    private String tenNv;
    private Date ngaysinhNv;
    private  String quequanNv;
    private int maPb1;

    /*private boolean gioitinhNv;*/

    public Nhanvien(String maNv, String tenNv, Date ngaysinhNv, String quequanNv) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.ngaysinhNv = ngaysinhNv;
        this.quequanNv = quequanNv;
    }

    public Nhanvien() {
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public Date getNgaysinhNv() {
        return ngaysinhNv;
    }

    public void setNgaysinhNv(Date ngaysinhNv) {
        this.ngaysinhNv = ngaysinhNv;
    }

    public String getQuequanNv() {
        return quequanNv;
    }

    public void setQuequanNv(String quequanNv) {
        this.quequanNv = quequanNv;
    }

    public Integer getMaPb() {
        return maPb1;
    }

    public void setMaPb(int maPb1) {
        this.maPb1 =maPb1;

    }

    /* public boolean isGioitinhNv() {
        return gioitinhNv;
    }

    public void setGioitinhNv(boolean gioitinhNv) {
        this.gioitinhNv = gioitinhNv;
    }*/

}
