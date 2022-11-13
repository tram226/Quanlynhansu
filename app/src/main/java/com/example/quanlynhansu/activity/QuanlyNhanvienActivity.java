package com.example.quanlynhansu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.adapter.NhanvienAdapter;
import com.example.quanlynhansu.adapter.PhongbanAdapter;
import com.example.quanlynhansu.helper.NgayThoigianHelper;
import com.example.quanlynhansu.model.Nhanvien;
import com.example.quanlynhansu.model.Phongban;
import com.example.quanlynhansu.sqlite.NhanvienDao;
import com.example.quanlynhansu.sqlite.PhongbanDao;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
// lớp Đưa nội dung xử lí trong bảng Nhan viên vào CSDL sqlite

public class QuanlyNhanvienActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etMaNv, etTenNv, etNgaysinhNv, etQuequanNv;
    private Spinner spPb;
    private List<Phongban> pbList;
    private List<Nhanvien> nvList;
    private ListView lvNv;
    private NhanvienAdapter nhanvienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_nhanvien);
        etMaNv=findViewById(R.id.etMaNv);
        etTenNv=findViewById(R.id.etTenNv);
        etNgaysinhNv=findViewById(R.id.etNgaysinhNv);
        etQuequanNv=findViewById(R.id.etQuequanNv);
        spPb= findViewById(R.id.spPb);

        lvNv= findViewById(R.id.lvNv);

        fillPbToSpinner();

        findViewById(R.id.btnLuu).setOnClickListener(this);

    }
    private void fillPbToSpinner(){

        PhongbanDao dao= new PhongbanDao(this);
         pbList = dao.getAll();
        PhongbanAdapter pbAdapter = new PhongbanAdapter(this,pbList);
        spPb.setAdapter(pbAdapter);
        // gắn p thức xử lí sự kiện là khi người dùng lựa chọn 1 lớp học mới trong spinner thì
        // sẽ gọi fillNvToListView để cập nhật lại nội dung hiển thị trong lítview
        spPb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillNvToListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

    }
    //
    private void fillNvToListView(){

        try {
            NhanvienDao dao = new NhanvienDao(this);
            Phongban pbs = (Phongban) spPb.getSelectedItem();
            // đổ dữ liệu
           // nvList = dao.getAllByPb(pbs.getMaPb());
            nvList = dao.getAllByPb(pbs.getMaPb());
            //nvList = dao.getAll();
            nhanvienAdapter= new NhanvienAdapter(this,nvList);
            //hiển thị trên listview
            lvNv.setAdapter(nhanvienAdapter);

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Erorr: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        NhanvienDao dao = new NhanvienDao(this);
        switch (view.getId()){
            case R.id.btnLuu:
                // viết đầu mã xử lí lưu thông tin
                try {
                    Nhanvien nv= new Nhanvien();
                    // thiết lập các thông tin
                    nv.setMaNv(etMaNv.getText().toString());
                    nv.setTenNv(etTenNv.getText().toString());
                    nv.setNgaysinhNv(NgayThoigianHelper.toDate(etNgaysinhNv.getText().toString()));
                    nv.setQuequanNv(etQuequanNv.getText().toString());

                    // chuyển đổi sang phòng ban
                    // trả về giá trị được gọi trong spinner và trả thông tin pb
                   Phongban pbs = (Phongban) spPb.getSelectedItem();
                   nv.setMaPb(pbs.getMaPb());
                   String msg;
                   // chèn thông tin đã đc thiết lập và CSDL
                   dao.insert(nv);
                   msg=" Nhân viên đã được lưu";
                   //
                    Snackbar snackbar= Snackbar.make(view,msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    // gọi thực hiện các thành phần
                    etMaNv.setText("");
                    etTenNv.setText("");
                    etNgaysinhNv.setText("");
                    etQuequanNv.setText("");
                    // hiển thị dữ liệu lên trên view
                    fillNvToListView();






                }catch (Exception ex){
                    // hiển thị thông tin chi tiết của lỗi
                    ex.printStackTrace();
                    // hiển thị thông báo lỗi
                    Toast.makeText(this, "ERROR: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}