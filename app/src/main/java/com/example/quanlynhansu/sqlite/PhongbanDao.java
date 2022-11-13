package com.example.quanlynhansu.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.quanlynhansu.model.Phongban;

import java.util.ArrayList;
import java.util.List;

// lớp được tạo để tạo thêm các thao tác thêm xóa sửa cập nhật
public class PhongbanDao {
    // khia báo trường để lưu thông tin của CSDL sqlite mà chúng ta mở ra
    private SQLiteDatabase db;
    // khởi tạo constructor để khởi tạo CSDL

// truyền content vào
    public PhongbanDao(Context context) {
        // sử dụng dữ liệu content taohj thành phần
        DbHelper helper = new DbHelper(context);

        // tạo đối tượng
        this.db = helper.getWritableDatabase();
    }
    // phương thức chèn thông tin của 1 phòng ban được truyền vào thông qua tham số sử dụng put
    // sử dụng value vào cái tham số thứ 3 của p thức insert của database
    public long insert(Phongban emp){

        ContentValues values= new ContentValues();

        values.put("maPb", emp.getMaPb());
        values.put("tenPb", emp.getTenPb());
        //chèn vào trong bảng phòng ban với các giá trị được đưa vào
        return db.insert("phongban",null,values);

    }
    // định nghĩa getAll() ở Phongbandao
    //get(String sql, String ... selectArgs) thực hiện các câu truy vấn được truyền vào
    @SuppressLint("Range")
    public List<Phongban> get(String sql, String ... selectArgs){
        // tạo danh sách
        List<Phongban> list = new ArrayList<>();
        // rawQuery truyền vào các câu lệnh và các đối số đưa vào
        Cursor cursor= db.rawQuery(sql, selectArgs);
        // đọc và thiết ập cho đối phòng ban
        while (cursor.moveToNext()){
            Phongban pbs= new Phongban();
            pbs.setMaPb(cursor.getInt(cursor.getColumnIndex("maPb")));
            pbs.setTenPb(cursor.getString(cursor.getColumnIndex("tenPb")));
            list.add(pbs);
        }
        return list;
    }
    // trả về các lớp hiện có trong CSDL
    public  List<Phongban> getAll(){
        String sql = "SELECT * FROM phongban";
        // lấy dữ liệu
        return get(sql);
    }
    // phương thức ấn lâu xóa thông tin bảng phòng ban với maPb truyền vào là String[maPb] và điều kiện là String[]{maPb} để lấy mã
    public int delete(String maPb){
        int n= db.delete("phongban", "maPb=?", new String[]{maPb});


        return n;
    }

}
