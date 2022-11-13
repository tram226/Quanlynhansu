package com.example.quanlynhansu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.model.Phongban;

import java.util.List;

public class PhongbanAdapter extends BaseAdapter {
    private Context context;
    private List<Phongban> list;
    public PhongbanAdapter(Context context, List<Phongban> list) {
        this.context = context;
        this.list = list;
    }
    // Trả về tổng số hiện có trong list
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }
    // trả về vị trí trong danh sách
    @Override
    public long getItemId(int i) {
        return i;
    }
    // tạo view và hiển thị
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // tạo view, inflate nghĩ là thổi lên(tạo mới)
        // nếu view chưa tồn tại
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_phongban_item,null);

        }
        // tìm kiếm thông tin của các thành phần
        TextView tvMaPb = view.findViewById(R.id.tvMaPb);
        TextView tvTenPb = view.findViewById(R.id.tvTenPb);
        // Đọc các thông tin từ list
        Phongban pbs= list.get(i);
        // lấy thông tin mã và tên phòng ban

        tvMaPb.setText(""+pbs.getMaPb());
        tvTenPb.setText(""+pbs.getTenPb());
        // trả về view đã được tạo ra
        return view;
    }
}
