package com.example.quanlynhansu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.helper.NgayThoigianHelper;
import com.example.quanlynhansu.model.Nhanvien;

import java.util.List;

// tùy chỉnh các thông tin hiển thị ở trong listview nhanvieen
public class NhanvienAdapter extends BaseAdapter {
    private Context context;
    private List<Nhanvien> list;
    // Khởi tạo giá trị cho context và líst
    public NhanvienAdapter(Context context, List<Nhanvien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // trả về tổng số phần tử hiện có trong danh sách 
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        // trả về đối tượng tại vị trí thứ i
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            // Tạo view
            view = LayoutInflater.from(context).inflate(R.layout.layout_nhanvien_item,null);

        }
        TextView tvMaNv = view.findViewById(R.id.tvMaNv);
        TextView tvTenNv = view.findViewById(R.id.tvTenNv);
        TextView tvNgaysinhNv = view.findViewById(R.id.tvNgaysinhNv);
        TextView tvQuequanNv = view.findViewById(R.id.tvQuequanNv);
        // lấy thông tin của các đối tượng đc chọn để hiển thị
        Nhanvien nvs = list.get(i);
        // thay đổi nội dung hiển thị cho các text view
        tvMaNv.setText(nvs.getMaNv());
        tvTenNv.setText(nvs.getTenNv());
        // Dùng lớp Ngaysinhhelper chuyển từ dạng date sang string
        tvNgaysinhNv.setText(NgayThoigianHelper.toString(nvs.getNgaysinhNv()));
        tvQuequanNv.setText(nvs.getQuequanNv());
        // trả về view vừa tạo ra;
        return view;
    }
}
