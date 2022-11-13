package com.example.quanlynhansu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.model.Phongban;
import com.example.quanlynhansu.sqlite.PhongbanDao;


// kích vào nút tương ứng  thì hiển thị  Dialog là cho phép nhập thông tin của department - extends Dialog
public class TaoPhongbanDialog extends Dialog implements View.OnClickListener {
    // ấn phải chọn generate chọn constructor rồi chọn cái đầu tiên nhân ok
    // Khởi tạo Construcor(hàm tạo) cho các thành phần
    private Context context;
    // khái báo các Edittext
    private EditText etMaPb,etTenPb;
    // khởi tạo giá trị và lưu giũ thông tin của context và các phương thức sau này.
    public TaoPhongbanDialog(@NonNull Context context) {
        super(context);
        this.context=context;

    }
    // Định nghĩa lại phương thức cho phép tạo ra các thành phần giao diện của dialog
    //  ấn phải chọn generate chọn Override Method chọn Oncreat
    // tạo ra phần view của dialog


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tao_phongban);
        // gọi thực hiện
        etMaPb = findViewById(R.id.etMaPb);
        etTenPb = findViewById(R.id.etTenPb);
        // gắn các phương thức xử lí sự kiện cho 2 button
        findViewById(R.id.btnLuu).setOnClickListener(this);
        findViewById(R.id.btnDong).setOnClickListener(this);
    }
    // Xử lí sự kiện cho 2 button
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLuu:
                // xử lí lưu thông tin của phong ban do người dùng nhập mới vào trên form lưu vào csdl
                //
                // tạo 1 đối tượng

                Phongban pbs= new Phongban();
                // thiết lập
                pbs.setTenPb(etTenPb.getText().toString());
                PhongbanDao dao = new PhongbanDao(context);
                dao.insert(pbs);

                // hiển thị thông báo cho người dùng đã được lưu
                Toast.makeText(context, "Phòng ban đã được lưu", Toast.LENGTH_SHORT).show();
                // đóng hộp thoại sau khi được lưu xong
                dismiss();
                break;
            case R.id.btnDong:
                // Đóng hộp thoại
                dismiss();
                break;
        }

    }
}
