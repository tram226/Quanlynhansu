package com.example.quanlynhansu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.quanlynhansu.activity.DanhsachPhongbanActivity;
import com.example.quanlynhansu.activity.QuanlyNhanvienActivity;
import com.example.quanlynhansu.dialog.TaoPhongbanDialog;
import com.example.quanlynhansu.sqlite.DbHelper;
// cài đặt phương thức xử lí điều kiện cho các button - implements View.OnClickListener

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // gắn các phương thức xử lí này cho các button trong giao diện
        //gọi thực hiện
        findViewById(R.id.btnPbMoi).setOnClickListener(this);
        findViewById(R.id.btnDsPb).setOnClickListener(this);
        findViewById(R.id.btnQlNv).setOnClickListener(this);
        findViewById(R.id.btnDangxuat).setOnClickListener(this);
        // Đọc sqllite
        DbHelper dbHelper = new DbHelper(this);
        // mở cSDL để tạo hệ thống
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
       // đóng CSDL lại
        readableDatabase.close();
    }

    // Định nghĩa phương thức onclick
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPbMoi:
                //hiển thị hộp thoại khi người dùng kích vào new department
                // tạo lớp new dialog truyền vào thông tin context
                TaoPhongbanDialog dialog = new TaoPhongbanDialog(this);
                // Sử dụng dílog và gọi show dialog cho người dùng
                dialog.show();

                break;
            case R.id.btnDsPb:
                //Hiển thị ra activity
                Intent intent = new Intent(this, DanhsachPhongbanActivity.class);

                // Gọi hàm startActivity(intent); để hiện thị activity
                startActivity(intent);
                break;
            case R.id.btnQlNv:
                // Tạo ra đối tượng intent dể hiển thị ra QuanlyNhanvien

                Intent qlintent = new Intent(this, QuanlyNhanvienActivity.class);
                startActivity(qlintent);
                break;
            case R.id.btnDangxuat:
                break;
        }
    }
}