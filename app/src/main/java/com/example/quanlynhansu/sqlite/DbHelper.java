package com.example.quanlynhansu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// Mở CSDL và tạo bảng
public class DbHelper extends SQLiteOpenHelper {
    // để gọi Constructor của lớp cha gọi các hằng dữ liệu
    // tên dữ liệu
    private static final String DB_NAME = "QLNS";
    // phiên bản khởi tạo =1;
    private static final int  DB_VERSION = 1;
    public DbHelper(@Nullable Context context) {

        //gọi constructor của lớp cha
        super(context,DB_NAME,null,DB_VERSION);
    }
    // Tạo ra  bảng
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // tạo bảng Phòng ban
        String phongbanSQL = "CREATE TABLE phongban(maPb integer primary key autoincrement, " +
                " tenPb text not null)";
        String nhanvienSQL= "CREATE TABLE nhanvien(maNv text primary key, "+
                "tenNv text not null, maPb1 integer, ngaysinhNv text, quequanNv text, " +
                "FOREIGN KEY (maPb1) REFERENCES phongban(maPb))";
        // tạo bảng phong ban và nhân viên
        sqLiteDatabase.execSQL(phongbanSQL);
        sqLiteDatabase.execSQL(nhanvienSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // NẾU tồn TẠI bảng phòng ba xóa phòng ban;

        String phongbanSQL = "DROP TABLE IF EXISTS phongban";
        String nhanvienSQL = "DROP TABLE IF EXISTS nhanvien";
        // gọi thực hiện các câu lệnh vừa tạo bàng phương thức execSQL
        sqLiteDatabase.execSQL(nhanvienSQL);
        sqLiteDatabase.execSQL(phongbanSQL);
        // gọi lại oncerate để tạo lại cấu trúc bảng mới
        onCreate(sqLiteDatabase);
    }
}
