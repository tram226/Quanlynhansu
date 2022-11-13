package com.example.quanlynhansu.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// lớp được sinh ra để chuyển dổi dữ liệu từ string sang date của java và ngược lại
// Khi định nghĩa khai báo static là để giúp truy cập trực tiếp thông qua lớp mà không cần tạo thêm đối tượng
public  class NgayThoigianHelper {
    private static final String Pattern = "dd/MM/yyyy";
    // từ String sang date
   public static Date toDate(String st) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
       return sdf.parse(st);

   }
   // Định nghĩa p thức chuyển giá trị date thành giá trị có kiểu dl string
    public static String toString (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
        return sdf.format(date);

    }


}
