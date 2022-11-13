package com.example.quanlynhansu.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlynhansu.R;// cần import R vào trong lớp ListDepartmentActivity
import com.example.quanlynhansu.adapter.PhongbanAdapter;
import com.example.quanlynhansu.model.Phongban;
import com.example.quanlynhansu.sqlite.PhongbanDao;

import java.util.List;


public class DanhsachPhongbanActivity extends AppCompatActivity {
    private ListView lvPb;
    private List<Phongban>list;
    private PhongbanAdapter pbsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_phongban);
        lvPb = findViewById(R.id.lvPb);
        fillPbListview();
        // xây dựng hàm xóa
        lvPb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                PhongbanDao dao = new PhongbanDao(DanhsachPhongbanActivity.this);
                Phongban pbs= list.get(i);
                AlertDialog.Builder b= new AlertDialog.Builder(DanhsachPhongbanActivity.this);
                b.setTitle("Thông báo ");
                b.setMessage("Bạn có chắc chắn muốn xóa không? ");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.delete(""+pbs.getMaPb());
                        // tạo lại phần hiển thị thông tin ở trong lít view và return
                        fillPbListview();

                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                b.create().show();
                // tạo lại phần hiển thị thông tin ở trong lít view và return
                fillPbListview();
                return false;

            }
        });

    }

    private void fillPbListview() {
        PhongbanDao dao = new PhongbanDao(this);
        list= dao.getAll();

        pbsAdapter = new PhongbanAdapter(this, list);
        lvPb.setAdapter(pbsAdapter);

    }
}