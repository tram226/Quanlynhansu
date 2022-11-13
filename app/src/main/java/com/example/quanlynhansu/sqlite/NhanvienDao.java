package com.example.quanlynhansu.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynhansu.helper.NgayThoigianHelper;
import com.example.quanlynhansu.model.Nhanvien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// lớp được tạo để tạo thêm các thao tác thêm xóa sửa cập nhật
public class NhanvienDao {
    // khia báo trường để lưu thông tin của CSDL sqlite mà chúng ta mở ra
    private SQLiteDatabase db;
    // khởi tạo constructor để khởi tạo CSDL

    // truyền content vào
    public NhanvienDao(Context context) {
        // sử dụng dữ liệu content taohj thành phần
        DbHelper helper= new DbHelper(context);
        // tạo đối tượng để ghi
        this.db =helper.getWritableDatabase();
    }
    // phương thức chèn thông tin của 1 phòng ban được truyền vào thông qua tham số sử dụng put
    // sử dụng value vào cái tham số thứ 3 của p thức insert của database
    public long insert(Nhanvien emp){

        ContentValues values= new ContentValues();
        //
        values.put("maNv", emp.getMaNv());
        values.put("tenNv", emp.getTenNv());
        values.put("ngaysinhNv", NgayThoigianHelper.toString((emp.getNgaysinhNv())));
        values.put("quequanNv", emp.getQuequanNv());
        values.put("maPb1", emp.getMaPb());

        //chèn vào trong bảng phòng ban với các giá trị được đưa vào
        return db.insert("nhanvien",null,values);

    }
    // định nghĩa getAll() ở Phongbandao
    //get(String sql, String ... selectArgs) thực hiện các câu truy vấn được truyền vào
    @SuppressLint("Range")
    public List<Nhanvien> get(String sql, String ... selectArgs) throws ParseException {
        // tạo danh sách
        List<Nhanvien> list = new ArrayList<>();
        // rawQuery truyền vào các câu lệnh và các đối số đưa vào
        Cursor cursor= db.rawQuery(sql, selectArgs);
        // đọc và thiết ập cho đối nhân viên
        while (cursor.moveToNext()){
            Nhanvien nvs= new Nhanvien();
            // thiết lập các trường đối tượng
            nvs.setMaNv(cursor.getString(cursor.getColumnIndex("maNv")));
            nvs.setMaNv(cursor.getString(cursor.getColumnIndex("tenNv")));
            nvs.setNgaysinhNv(NgayThoigianHelper.toDate(cursor.getString(cursor.getColumnIndex("ngaysinhNv"))));
            nvs.setQuequanNv(cursor.getString(cursor.getColumnIndex("quequanNv")));
            nvs.setMaPb(cursor.getInt(cursor.getColumnIndex("maPb1")));
            list.add(nvs);
        }
        return list;
    }
    // trả về các lớp hiện có trong CSDL gọi p thức get đọc thông tin chi tiết
    public  List<Nhanvien> getAll() throws ParseException {
        String sql = "SELECT * FROM nhanvien";
        // lấy dữ liệu
        return get(sql);
    }
    // P thức lấy về thông tin của các nhân viên  có lớp học là mã được truyền vào cho phần tham số của p thức getallbyPb
    public  List<Nhanvien> getAllByPb(Integer maPb1) throws ParseException {
        String sql = "SELECT * FROM nhanvien WHERE maPb1 = ?";
        // lấy dữ liệu
        return get(sql,""+ maPb1);
    }
}
